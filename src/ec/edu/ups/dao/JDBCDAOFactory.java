package ec.edu.ups.dao;

import ec.edu.ups.mysql.jdbc.JDBCCategoryDAO;
import ec.edu.ups.mysql.jdbc.JDBCProductDAO;
import ec.edu.ups.mysql.jdbc.JDBCShoppingBasketDAO;
import ec.edu.ups.mysql.jdbc.JDBCUserDAO;
import ec.edu.ups.mysql.jdbc.JDBCUserDetailDAO;

/**
 * Clase JDBCGenericDAO.
 * 
 * 
 * Esta clase implementa los métodos abstractos de la clase DAOFatcory
 * gestionando de esta manera a través de esta clase el acceso a los DAOs que
 * persistiran la información en la base de datos.
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 *
 * @see DAOFactory
 * @version 1.0
 * 
 */
public class JDBCDAOFactory extends DAOFactory {

	@Override
	public void createTables() {
		this.getCategoryDAO().createTable();
		this.getUserDAO().createTable();
		this.getUserDetailDAO().createTable();
		this.getShoppingBasketDAO().createTable();
		this.getProductDAO().createTable();

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
