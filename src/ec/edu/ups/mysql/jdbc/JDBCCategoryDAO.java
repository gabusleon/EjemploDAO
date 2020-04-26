package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.dao.CategoryDAO;
import ec.edu.ups.modelo.Category;

public class JDBCCategoryDAO extends JDBCGenericDAO<Category, Integer> implements CategoryDAO {

	@Override
	public void createTable() {
		conexionUno.update("DROP TABLE IF EXISTS Category");
		conexionUno.update("CREATE TABLE Category (" + "ID INT NOT NULL, " + "DESCRIPTION VARCHAR(255), "
				+ "NAME VARCHAR(255), " + "PRIMARY KEY (ID))");
	}

	@Override
	public void create(Category category) {
		conexionUno.update("INSERT Category VALUES (" + category.getId() + ", '" + category.getName() + "', '"
				+ category.getDescription() + "')");
	}

	@Override
	public Category read(Integer id) {
		Category category = null;
		ResultSet rs = conexionUno.query("SELECT * FROM Category WHERE id=" + id);
		try {
			if (rs != null && rs.next()) {
				category = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCCategoryDAO:read): " + e.getMessage());
		}

		return category;
	}

	@Override
	public void update(Category category) {
		conexionUno.update("UPDATE Category SET name = '" + category.getName() + "', description = '"
				+ category.getDescription() + "' WHERE id = " + category.getId());

	}

	@Override
	public void delete(Category category) {
		conexionUno.update("DELETE FROM Category WHERE id = " + category.getId());

	}

	@Override
	public List<Category> find() {
		List<Category> list = new ArrayList<Category>();
		ResultSet rs = conexionUno.query("SELECT * FROM Category");
		try {
			while (rs.next()) {
				list.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCCategoryDAO:find): " + e.getMessage());
		}
		return list;
	}

}
