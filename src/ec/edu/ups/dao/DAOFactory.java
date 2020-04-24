package ec.edu.ups.dao;

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
