package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.dao.UserDetailDAO;
import ec.edu.ups.modelo.User;
import ec.edu.ups.modelo.UserDetail;

/**
 * Clase JDBCUserDetailDAO.
 * 
 * La clase JDBCUserDetailDAO hereda los métodos y atributos de la clase abstracta
 * padre JDBCGenericDAO, así como también, implementa los métodos de la
 * interface UserDetailDAO.
 * 
 * Teniendo de esta manera una clase específica que gestionara la persistencia a
 * la base de datos del modelo UserDetail
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 *
 * @see JDBCUserDetailDAO
 * @see UserDetailDAO
 * @see UserDetail
 * 
 * @version 1.0
 */
public class JDBCUserDetailDAO extends JDBCGenericDAO<UserDetail, Integer> implements UserDetailDAO {

	@Override
	public void createTable() {

		conexionDos.update("DROP TABLE IF EXISTS User_Detail");
		conexionDos.update("CREATE TABLE User_Detail (" + "ID INT NOT NULL, DETAIL VARCHAR(255), "
				+ "USER_ID INT, PRIMARY KEY (ID), FOREIGN KEY(USER_ID) REFERENCES User(ID))");
	}

	@Override
	public void create(UserDetail userDetail) {

		conexionDos.update("INSERT User_Detail VALUES (" + userDetail.getId() + ", '" + userDetail.getDetail() + "', "
				+ userDetail.getUser().getId() + ")");
	}

	@Override
	public UserDetail read(Integer id) {

		UserDetail detail = null;
		ResultSet rsDetail = conexionUno.query("SELECT * FROM User_Detail WHERE id=" + id);
		try {
			if (rsDetail != null && rsDetail.next()) {
				detail = new UserDetail(rsDetail.getInt("id"), rsDetail.getString("detail"));

				ResultSet rsUser = conexionDos.query("SELECT * FROM User WHERE id=" + rsDetail.getInt("user_id"));
				if (rsUser != null && rsUser.next()) {
					User user = new User(rsUser.getInt("id"), rsUser.getInt("level"), rsUser.getString("name"),
							rsUser.getString("password"));
					detail.setUser(user);
				}

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

		conexionDos.update(
				"UPDATE User_Detail SET detail = '" + userDetail.getDetail() + "' WHERE id = " + userDetail.getId());
	}

	@Override
	public void delete(UserDetail userDetail) {

		conexionDos.update("DELETE FROM User_Detail WHERE id = " + userDetail.getId());

	}

	@Override
	public List<UserDetail> find() {
		List<UserDetail> list = new ArrayList<UserDetail>();
		ResultSet rsDetail = conexionUno.query("SELECT * FROM User_Detail");
		try {
			while (rsDetail.next()) {
				UserDetail detail = new UserDetail(rsDetail.getInt("id"), rsDetail.getString("detail"));

				int userId = rsDetail.getInt("user_id");
				ResultSet rsUser = conexionDos.query("SELECT * FROM User WHERE id=" + userId);
				if (rsUser != null && rsUser.next()) {
					User user = new User(rsUser.getInt("id"), rsUser.getInt("level"), rsUser.getString("name"),
							rsUser.getString("password"));
					detail.setUser(user);
				}
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
		ResultSet rsDetail = conexionUno.query("SELECT * FROM User_Detail WHERE user_id=" + userId);
		try {
			if (rsDetail != null && rsDetail.next()) {
				detail = new UserDetail(rsDetail.getInt("id"), rsDetail.getString("detail"));

				ResultSet rsUser = conexionDos.query("SELECT * FROM User WHERE id=" + rsDetail.getInt("user_id"));
				if (rsUser != null && rsUser.next()) {
					User user = new User(rsUser.getInt("id"), rsUser.getInt("level"), rsUser.getString("name"),
							rsUser.getString("password"));
					detail.setUser(user);
				}

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
