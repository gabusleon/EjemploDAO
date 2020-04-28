package ec.edu.ups.dao;

import java.util.List;

/**
 * Interface GenericDAO.
 * 
 * La interface GenericDAO ha sido creada como interface con los métodos
 * genericos para realizar la persistencia en la base de datos. Dicha interface
 * será imlementada en cada clase específica que controlará la persistencia a la
 * base de datos un sistema que permite ejemplificar el uso del patrón de diseño
 * DAO usando JDBC como coexión a la misma.
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 *
 * @param <T>  la entidad o modelo que será persistida en la base de datos
 * @param <ID> el id o llave primaria por la cuál se identificara al modelo como
 *             valor único
 * @version 1.0
 */
public interface GenericDAO<T, ID> {

	public void createTable();

	public void create(T entity);

	public T read(ID id);

	public void update(T entity);

	public void delete(T entity);

	public List<T> find();

}
