package ec.edu.ups.dao;

import ec.edu.ups.mysql.jdbc.JDBCCategoryDAO;
import ec.edu.ups.mysql.jdbc.JDBCProductDAO;
import ec.edu.ups.mysql.jdbc.JDBCShoppingBasketDAO;
import ec.edu.ups.mysql.jdbc.JDBCUserDAO;
import ec.edu.ups.mysql.jdbc.JDBCUserDetailDAO;

public class JDBCDAOFactory extends DAOFactory {

	@Override
	public void createTables() {
		this.getCategoryDAO().createTable();
		this.getUserDAO().createTable();
		this.getUserDetailDAO().createTable();
		this.getProductDAO().createTable();
		this.getShoppingBasketDAO().createTable();
	}

	@Override
	public CategoryDAO getCategoryDAO() {
		return new JDBCCategoryDAO();
	}

	@Override
	public UserDAO getUserDAO() {
		return new JDBCUserDAO();
	}

	@Override
	public UserDetailDAO getUserDetailDAO() {
		return new JDBCUserDetailDAO();
	}

	@Override
	public ProductDAO getProductDAO() {
		return new JDBCProductDAO();
	}

	@Override
	public ShoppingBasketDAO getShoppingBasketDAO() {
		return new JDBCShoppingBasketDAO();
	}

}
