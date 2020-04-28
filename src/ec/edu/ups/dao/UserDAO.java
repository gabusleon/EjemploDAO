package ec.edu.ups.dao;

import ec.edu.ups.modelo.User;

/**
 * Interface UserDAO.
 * 
 * La interface UserDAO ha sido creada como interface específica para la
 * persistencia de objetos de tipo Category en la base de datos. Dicha interface
 * hereda de la clase GenericDAO y será imlementada en una clase específica que
 * controlará la conexión a la base de datos de un sistema que permite
 * ejemplificar el uso del patrón de diseño DAO.
 * 
 * Además, en esta interface se pueden agregar método específicos para el manejo
 * del objeto User, por ejemplo: buscarUserPorName,
 * buscarUserPorDetail, entre otras.
 * 
 * @author Gabriel A. León Paredes.
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 * 
 * @see GenericDAO
 * @see User
 *
 * @version 1.0
 */
public interface UserDAO extends GenericDAO<User, Integer>{

}
