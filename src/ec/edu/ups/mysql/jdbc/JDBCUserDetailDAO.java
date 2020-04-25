package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.dao.UserDetailDAO;
import ec.edu.ups.modelo.User;
import ec.edu.ups.modelo.UserDetail;

public class JDBCUserDetailDAO extends JDBCGenericDAO<UserDetail, Integer> implements UserDetailDAO {

	@Override
	public void createTable() {

		jdbc.update("DROP TABLE IF EXISTS User_Detail");
		jdbc.update("DROP TABLE IF EXISTS User");
		DAOFactory.getFactory().getUserDAO().createTable();
		jdbc.update("CREATE TABLE User_Detail (" + "ID INT NOT NULL, DETAIL STRING, "
				+ "USER_ID INT, PRIMARY KEY (ID), FOREIGN KEY(USER_ID) REFERENCES User(ID))");
	}

	@Override
	public void create(UserDetail userDetail) {
		if (userDetail.getUser() != null) {
			User user = DAOFactory.getFactory().getUserDAO().read(userDetail.getUser().getId());
			if (user == null) {
				DAOFactory.getFactory().getUserDAO().create(userDetail.getUser());
			}
			jdbc.update("INSERT User_Detail VALUES (" + userDetail.getId() + ", '" + userDetail.getDetail() + "', "
					+ userDetail.getUser().getId() + ")");
		}

	}

	@Override
	public UserDetail read(Integer id) {

		UserDetail detail = null;
		ResultSet rs = jdbc.query("SELECT * FROM User_Detail WHERE id=" + id);
		try {
			if (rs != null && rs.next()) {
				detail = new UserDetail(rs.getInt("id"), rs.getString("detail"));
				User user = DAOFactory.getFactory().getUserDAO().read(rs.getInt("user_id"));
				// user.setDetail(detail);
				detail.setUser(user);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDetailDAO:read): " + e.getMessage());
		}
		if (detail == null) {
			return null;
		}
		return detail;

	}

	@Override
	public void update(UserDetail userDetail) {
		UserDAO userDAO = DAOFactory.getFactory().getUserDAO();
		User user = userDAO.read(userDetail.getUser().getId());

		jdbc.update(
				"UPDATE User_Detail SET detail = '" + userDetail.getDetail() + "' WHERE id = " + userDetail.getId());

		if (userDetail.getUser() == null && user != null) {
			// this.delete(userDetail);
			userDAO.delete(user);
		} else if (userDetail.getUser() != null && user == null) {
			userDAO.create(userDetail.getUser());
			// this.create(userDetail);
		} else if (userDetail.getUser() != null && user != null) {
			userDAO.update(userDetail.getUser());
		}
	}

	@Override
	public void delete(UserDetail userDetail) {

		jdbc.update("DELETE FROM User_Detail WHERE id = " + userDetail.getId());
		/*
		 * if (userDetail.getUser() != null) {
		 * DAOFactory.getFactory().getUserDAO().delete(userDetail.getUser()); }
		 */

	}

	@Override
	public List<UserDetail> find() {
		List<UserDetail> list = new ArrayList<UserDetail>();
		ResultSet rs = jdbc.query("SELECT * FROM User_Detail");
		try {
			while (rs.next()) {
				UserDetail detail = new UserDetail(rs.getInt("id"), rs.getString("detail"));
				User user = DAOFactory.getFactory().getUserDAO().read(rs.getInt("user_id"));
				//user.setDetail(detail);
				detail.setUser(user);
				list.add(detail);
			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDetailDAO:find): " + e.getMessage());
		}
		return list;
	}

	@Override
	public UserDetail findByUserId(Integer userId) {
		UserDetail detail = null;
		ResultSet rs = jdbc.query("SELECT * FROM User_Detail WHERE user_id=" + userId);
		try {
			if (rs != null && rs.next()) {
				detail = new UserDetail(rs.getInt("id"), rs.getString("detail"));
				User user = DAOFactory.getFactory().getUserDAO().read(userId);
				//user.setDetail(detail);
				detail.setUser(user);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDetailDAO:findByUserId): " + e.getMessage());
		}
		if (detail == null) {
			return null;
		}
		return detail;
	}

}
