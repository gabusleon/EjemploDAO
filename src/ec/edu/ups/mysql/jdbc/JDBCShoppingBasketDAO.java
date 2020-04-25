package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.dao.ShoppingBasketDAO;
import ec.edu.ups.modelo.Product;
import ec.edu.ups.modelo.ShoppingBasket;

public class JDBCShoppingBasketDAO extends JDBCGenericDAO<ShoppingBasket, Integer> implements ShoppingBasketDAO {

	@Override
	public void createTable() {

		jdbc.update("DROP TABLE IF EXISTS Product");
		jdbc.update("DROP TABLE IF EXISTS Shopping_Basket");
		jdbc.update("CREATE TABLE Shopping_Basket ( ID INT NOT NULL, DATE DATE, PRIMARY KEY (ID))");
		DAOFactory.getFactory().getProductDAO().createTable();

	}

	@Override
	public void create(ShoppingBasket shoppingBasket) {
		jdbc.update(
				"INSERT ShoppingBasket VALUES (" + shoppingBasket.getId() + ", '" + shoppingBasket.getDate() + "')");
		Set<Product> products = shoppingBasket.getProducts();
		if (products != null) {
			for (Product product : products) {
				DAOFactory.getFactory().getProductDAO().create(product);
			}
		}

	}

	@Override
	public ShoppingBasket read(Integer id) {
		ShoppingBasket shoppingBasket = null;
		ResultSet rs = jdbc.query("SELECT * FROM Shopping_Basket WHERE id=" + id);
		try {
			if (rs != null && rs.next()) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(rs.getDate("date"));
				shoppingBasket = new ShoppingBasket(rs.getInt("id"), calendar);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCShoppingBasketDAO:read): " + e.getMessage());
		}
		if (shoppingBasket == null) {
			return null;
		}
		Set<Product> products = DAOFactory.getFactory().getProductDAO().findByShoppingBasketId(shoppingBasket.getId());
		if (products != null) {
			Set<Product> productsFinal = new HashSet<Product>();
			for (Product product : products) {
				product.setShoppingBasket(shoppingBasket);
				productsFinal.add(product);
			}
			shoppingBasket.setProducts(productsFinal);
		}

		return shoppingBasket;
	}

	@Override
	public void update(ShoppingBasket shoppingBasket) {

		ProductDAO productDAO = DAOFactory.getFactory().getProductDAO();
		Set<Product> products = productDAO.findByShoppingBasketId(shoppingBasket.getId());
		jdbc.update("UPDATE User Shopping_Basket date = '" + shoppingBasket.getDate() + " WHERE id = "
				+ shoppingBasket.getId());

		if (shoppingBasket.getProducts() == null && products != null) {
			for (Product product : products) {
				productDAO.delete(product);
			}
		} else if (shoppingBasket.getProducts() != null && products == null) {
			for (Product product : shoppingBasket.getProducts()) {
				productDAO.create(product);
			}
		} else if (shoppingBasket.getProducts() != null && products != null) {
			for (Product product : shoppingBasket.getProducts()) {
				productDAO.update(product);
			}
		}

	}

	@Override
	public void delete(ShoppingBasket shoppingBasket) {
		if (shoppingBasket.getProducts() != null) {
			for (Product products : shoppingBasket.getProducts()) {
				DAOFactory.getFactory().getProductDAO().delete(products);
			}
		}
		jdbc.update("DELETE FROM Shopping_Basket WHERE id = " + shoppingBasket.getId());

	}

	@Override
	public List<ShoppingBasket> find() {
		List<ShoppingBasket> list = new ArrayList<ShoppingBasket>();
		ResultSet rs = jdbc.query("SELECT * FROM Shopping_Basket");
		try {
			while (rs.next()) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(rs.getDate("date"));
				ShoppingBasket shoppingBasket = new ShoppingBasket(rs.getInt("id"), calendar);
				Set<Product> products = DAOFactory.getFactory().getProductDAO()
						.findByShoppingBasketId(shoppingBasket.getId());

				if (products != null) {
					Set<Product> productsFinal = new HashSet<Product>();
					for (Product product : products) {
						product.setShoppingBasket(shoppingBasket);
						productsFinal.add(product);
					}
					shoppingBasket.setProducts(productsFinal);
				}
				list.add(shoppingBasket);
			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCShoppingBasketDAO:find): " + e.getMessage());
		}
		return list;
	}

}
