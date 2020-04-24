package ec.edu.ups.mysql.jdbc;

import ec.edu.ups.dao.GenericDAO;

public abstract class JDBCGenericDAO<T, ID> implements GenericDAO<T, ID> {
	protected ContextJDBC jdbc = ContextJDBC.getJDBC();
}
