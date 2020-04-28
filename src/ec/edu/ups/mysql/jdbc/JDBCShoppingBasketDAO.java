package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

/**
 * Clase JDBCShoppingBasketDAO.
 * 
 * La clase JDBCShoppingBasketDAO hereda los métodos y atributos de la clase abstracta
 * padre JDBCGenericDAO, así como también, implementa los métodos de la
 * interface ShoppingBasketDAO.
 * 
 * Teniendo de esta manera una clase específica que gestionara la persistencia a
 * la base de datos del modelo ShoppingBasket
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 *
 * @see JDBCShoppingBasketDAO
 * @see ShoppingBasketDAO
 * @see ShoppingBasket
 * 
 * @version 1.0
 */
public class JDBCShoppingBasketDAO extends JDBCGenericDAO<ShoppingBasket, Integer> implements ShoppingBasketDAO {

	@Override
	public void createTable() {

		conexionUno.update("DROP TABLE IF EXISTS Product");
		conexionUno.update("DROP TABLE IF EXISTS Shopping_Basket");
		conexionUno.update("CREATE TABLE Shopping_Basket ( ID INT NOT NULL, DATE DATE, PRIMARY KEY (ID))");
		DAOFactory.getFactory().getProductDAO().createTable();

	}

	@Override
	public void create(ShoppingBasket shoppingBasket) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("INSERT Shopping_Basket VALUES (" + shoppingBasket.getId() + ", '"
				+ formato.format(shoppingBasket.getDate().getTime()) + "')");

		conexionUno.update("INSERT Shopping_Basket VALUES (" + shoppingBasket.getId() + ", '"
				+ formato.format(shoppingBasket.getDate().getTime()) + "')");
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
		ResultSet rs = conexionUno.query("SELECT * FROM Shopping_Basket WHERE id=" + id);
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
		conexionUno.update("UPDATE User Shopping_Basket date = '" + shoppingBasket.getDate() + " WHERE id = "
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
		conexionUno.update("DELETE FROM Shopping_Basket WHERE id = " + shoppingBasket.getId());

	}

	@Override
	public List<ShoppingBasket> find() {
		List<ShoppingBasket> list = new ArrayList<ShoppingBasket>();
		ResultSet rs = conexionUno.query("SELECT * FROM Shopping_Basket");
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
