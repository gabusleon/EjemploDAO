package ec.edu.ups.mysql.jdbc;

import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.modelo.Product;

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

	}

	@Override
	public Product read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findByShoppingBasketId(int idShoppingBasket) {
		// TODO Auto-generated method stub
		return null;
	}

}
