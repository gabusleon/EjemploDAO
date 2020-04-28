package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.modelo.Product;
import ec.edu.ups.modelo.ShoppingBasket;

/**
 * Clase JDBCProductDAO.
 * 
 * La clase JDBCProductDAO hereda los métodos y atributos de la clase abstracta
 * padre JDBCGenericDAO, así como también, implementa los métodos de la
 * interface ProductDAO.
 * 
 * Teniendo de esta manera una clase específica que gestionara la persistencia a
 * la base de datos del modelo Product
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 *
 * @see JDBCGenericDAO
 * @see ProductDAO
 * @see Product
 * 
 * @version 1.0
 */
public class JDBCProductDAO extends JDBCGenericDAO<Product, Integer> implements ProductDAO {

	@Override
	public void createTable() {

		conexionDos.update("DROP TABLE IF EXISTS Product");
		conexionDos.update("CREATE TABLE Product ( ID INT NOT NULL, AMOUNT INT, "
				+ "DESCRIPTION VARCHAR(255), SHOPPING_BASKET_ID INT, PRIMARY KEY (ID), "
				+ "FOREIGN KEY(SHOPPING_BASKET_ID) REFERENCES Shopping_Basket(ID))");
	}

	@Override
	public void create(Product product) {

		conexionDos.update("INSERT Product VALUES (" + product.getId() + ", " + product.getAmount() + ", '"
				+ product.getDescription() + "', " + product.getShoppingBasket().getId() + ")");

	}

	@Override
	public Product read(Integer id) {

		Product product = null;
		ResultSet rsProduct = conexionUno.query("SELECT * FROM Product WHERE id=" + id);
		try {
			if (rsProduct != null && rsProduct.next()) {
				product = new Product(rsProduct.getInt("id"), rsProduct.getInt("amount"),
						rsProduct.getString("description"));
				ResultSet rsShoppingBasket = conexionDos
						.query("SELECT * FROM Shopping_Basket WHERE id=" + rsProduct.getInt("shopping_basket_id"));

				if (rsShoppingBasket != null && rsShoppingBasket.next()) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(rsShoppingBasket.getDate("date"));
					ShoppingBasket shoppingBasket = new ShoppingBasket(rsShoppingBasket.getInt("id"), calendar);
					product.setShoppingBasket(shoppingBasket);
				}

			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCProductDAO:read): " + e.getMessage());
		}
		if (product == null) {
			return null;
		}
		return product;
	}

	@Override
	public void update(Product product) {

		conexionUno.update("UPDATE Product SET amount = " + product.getAmount() + ", description = '"
				+ product.getDescription() + "' WHERE id = " + product.getId());

	}

	@Override
	public void delete(Product product) {

		conexionUno.update("DELETE FROM Product WHERE id = " + product.getId());

	}

	@Override
	public List<Product> find() {
		List<Product> list = new ArrayList<Product>();
		ResultSet rsProduct = conexionUno.query("SELECT * FROM Product");
		try {
			while (rsProduct.next()) {
				Product product = new Product(rsProduct.getInt("id"), rsProduct.getInt("amount"),
						rsProduct.getString("description"));
				ResultSet rsShoppingBasket = conexionDos
						.query("SELECT * FROM Shopping_Basket WHERE id=" + rsProduct.getInt("shopping_basket_id"));

				if (rsShoppingBasket != null && rsShoppingBasket.next()) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(rsShoppingBasket.getDate("date"));
					ShoppingBasket shoppingBasket = new ShoppingBasket(rsShoppingBasket.getInt("id"), calendar);
					product.setShoppingBasket(shoppingBasket);
				}
				list.add(product);
			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCProductDAO:find): " + e.getMessage());
		}

		return list;
	}

	@Override
	public Set<Product> findByShoppingBasketId(int shoppingBasketId) {

		Set<Product> list = new HashSet<Product>();
		ResultSet rsProduct = conexionDos.query("SELECT * FROM Product WHERE shopping_basket_id=" + shoppingBasketId);
		try {
			while (rsProduct.next()) {
				Product product = new Product(rsProduct.getInt("id"), rsProduct.getInt("amount"),
						rsProduct.getString("description"));
				list.add(product);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCProductDAO:findByShoppingBasketId): " + e.getMessage());
		}

		return list;
	}

}
