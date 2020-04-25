package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.dao.ShoppingBasketDAO;
import ec.edu.ups.modelo.Product;
import ec.edu.ups.modelo.ShoppingBasket;

public class JDBCProductDAO extends JDBCGenericDAO<Product, Integer> implements ProductDAO {

	@Override
	public void createTable() {

		jdbc.update("DROP TABLE IF EXISTS Product");
		jdbc.update("DROP TABLE IF EXISTS Shopping_Basket");
		DAOFactory.getFactory().getShoppingBasketDAO().createTable();
		jdbc.update("CREATE TABLE Product ( ID INT NOT NULL, AMOUNT INT, "
				+ "DESCRIPTION VARCHAR(255), SHOPPING_BASKET_ID INT, PRIMARY KEY (ID), "
				+ "FOREIGN KEY(SHOPPING_BASKET_ID) REFERENCES Shopping_Basket(ID)))");
	}

	@Override
	public void create(Product product) {

		if (product.getShoppingBasket() != null) {
			ShoppingBasket shoppingBasket = DAOFactory.getFactory().getShoppingBasketDAO()
					.read(product.getShoppingBasket().getId());

			if (shoppingBasket == null) {
				DAOFactory.getFactory().getShoppingBasketDAO().create(shoppingBasket);
			}
			jdbc.update("INSERT Product VALUES (" + product.getId() + ", " + product.getAmount() + ", '"
					+ product.getDescription() + "', " + product.getShoppingBasket().getId() + ")");
		}

	}

	@Override
	public Product read(Integer id) {

		Product product = null;
		ResultSet rs = jdbc.query("SELECT * FROM Product WHERE id=" + id);
		try {
			if (rs != null && rs.next()) {
				product = new Product(rs.getInt("id"), rs.getInt("amount"), rs.getString("description"));
				ShoppingBasket shoppingBasket = DAOFactory.getFactory().getShoppingBasketDAO()
						.read(rs.getInt("shopping_basket_id"));
				product.setShoppingBasket(shoppingBasket);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDetailDAO:read): " + e.getMessage());
		}
		if (product == null) {
			return null;
		}
		return product;
	}

	@Override
	public void update(Product product) {

		ShoppingBasketDAO shoppingBasketDAO = DAOFactory.getFactory().getShoppingBasketDAO();
		ShoppingBasket shoppingBasket = shoppingBasketDAO.read(product.getShoppingBasket().getId());
		jdbc.update("UPDATE Shopping_Basket SET amount = " + product.getAmount() + ", description = '"
				+ product.getDescription() + "' WHERE id = " + product.getId());

		if (product.getShoppingBasket() == null && shoppingBasket != null) {
			// this.delete(product);
			shoppingBasketDAO.delete(shoppingBasket);
		} else if (product.getShoppingBasket() != null && shoppingBasket == null) {
			shoppingBasketDAO.create(product.getShoppingBasket());
			// this.create(userDetail);
		} else if (product.getShoppingBasket() != null && shoppingBasket != null) {
			shoppingBasketDAO.update(product.getShoppingBasket());
		}

	}

	@Override
	public void delete(Product product) {
		jdbc.update("DELETE FROM Product WHERE id = " + product.getId());

	}

	@Override
	public List<Product> find() {
		List<Product> list = new ArrayList<Product>();
		ResultSet rs = jdbc.query("SELECT * FROM Products");
		try {
			while (rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getInt("amount"), rs.getString("description"));
				ShoppingBasket shoppingBasket = DAOFactory.getFactory().getShoppingBasketDAO()
						.read(rs.getInt("shopping_basket_id"));
				product.setShoppingBasket(shoppingBasket);
				list.add(product);
			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCProductDAO:find): " + e.getMessage());
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	@Override
	public List<Product> findByShoppingBasketId(int shoppingBasketId) {

		List<Product> list = new ArrayList<Product>();
		ResultSet rs = jdbc.query("SELECT * FROM Products WHERE shopping_basket_id=" + shoppingBasketId);
		try {
			if (rs != null && rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getInt("amount"), rs.getString("description"));
				ShoppingBasket shoppingBasket = DAOFactory.getFactory().getShoppingBasketDAO().read(shoppingBasketId);
				product.setShoppingBasket(shoppingBasket);
				list.add(product);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCProductDAO:findByShoppingBasketId): " + e.getMessage());
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

}
