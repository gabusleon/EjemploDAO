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

public class JDBCUserDAO extends JDBCGenericDAO<User, Integer> implements UserDAO {

	@Override
	public void createTable() {

		jdbc.update("DROP TABLE IF EXISTS UserDetail");
		jdbc.update("DROP TABLE IF EXISTS User");
		jdbc.update("CREATE TABLE User (" + "ID INT NOT NULL, LEVEL INT, "
				+ "NAME VARCHAR(255), PASSWORD VARCHAR(255), PRIMARY KEY (ID))");
		DAOFactory.getFactory().getUserDetailDAO().createTable();

	}

	@Override
	public void create(User user) {

		jdbc.update("INSERT User VALUES (" + user.getId() + ", " + user.getLevel() + ", '" + user.getName() + ", '"
				+ user.getPassword() + ")");
		UserDetail userDetail = user.getDetail();
		if (userDetail != null) {
			DAOFactory.getFactory().getUserDetailDAO().create(userDetail);
		}

	}

	@Override
	public User read(Integer id) {

		User user = null;
		ResultSet rs = jdbc.query("SELECT * FROM User WHERE id=" + id);
		try {
			if (rs != null && rs.next()) {
				user = new User(rs.getInt("id"), rs.getInt("level"), rs.getString("name"), rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDAO:read): " + e.getMessage());
		}
		if (user == null) {
			return null;
		}
		UserDetail detail = DAOFactory.getFactory().getUserDetailDAO().findByUserId(user.getId());
		if (detail != null) {
			detail.setUser(user);
		}
		user.setDetail(detail);

		return user;
	}

	@Override
	public void update(User user) {

		UserDetailDAO userDetailDAO = DAOFactory.getFactory().getUserDetailDAO();
		UserDetail detail = userDetailDAO.findByUserId(user.getId());

		jdbc.update("UPDATE User SET name = '" + user.getName() + "', password = '" + user.getPassword() + "', level= "
				+ user.getLevel() + " WHERE id = " + user.getId());

		if (user.getDetail() == null && detail != null) {
			userDetailDAO.delete(detail);
		} else if (user.getDetail() != null && detail == null) {
			userDetailDAO.create(user.getDetail());
		} else if (user.getDetail() != null && detail != null) {
			userDetailDAO.update(user.getDetail());
		}

	}

	@Override
	public void delete(User user) {

		if (user.getDetail() != null) {
			DAOFactory.getFactory().getUserDetailDAO().delete(user.getDetail());
		}
		jdbc.update("DELETE FROM User WHERE id = " + user.getId());

	}

	@Override
	public List<User> find() {
		List<User> list = new ArrayList<User>();
		ResultSet rs = jdbc.query("SELECT * FROM User");
		try {
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getInt("level"), rs.getString("name"),
						rs.getString("password"));
				UserDetail detail = DAOFactory.getFactory().getUserDetailDAO().findByUserId(user.getId());
				if (detail != null) {
					detail.setUser(user);
					user.setDetail(detail);
				}
				list.add(user);

			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDAO:find): " + e.getMessage());
		}
		return list;
	}

}
