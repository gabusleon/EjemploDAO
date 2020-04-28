package ec.edu.ups.dao;

import ec.edu.ups.mysql.jdbc.JDBCCategoryDAO;

/**
 * Clase DAOFactory.
 * 
 * La clase abstracta DAOFcatory implementa el patrón de diseño Factory el cuál
 * busca tener una jerarquía de clases para su control e implementación de
 * manera organizada. En este ejemplo la clase DAOFactory declara los métodos
 * que serán implementados en la clase que podra gestionar el acceso y creación
 * de todos los DAOs específicos.
 * 
 * @author Gabriel A. León Paredes. 
 * Doctor en Tecnologías de Información.
 * https://www.linkedin.com/in/gabrielleonp
 * 
 * @see https://www.arquitecturajava.com/usando-el-patron-factory/
 * @see JDBCCategoryDAO
 * @version 1.0
 */
public abstract class DAOFactory {
	protected static DAOFactory factory = new JDBCDAOFactory();

	public static DAOFactory getFactory() {
		return factory;
	}

	public abstract void createTables();

	public abstract CategoryDAO getCategoryDAO();

	public abstract UserDAO getUserDAO();

	public abstract UserDetailDAO getUserDetailDAO();

	public abstract ProductDAO getProductDAO();

	public abstract ShoppingBasketDAO getShoppingBasketDAO();

}
