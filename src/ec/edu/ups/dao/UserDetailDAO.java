package ec.edu.ups.dao;

import ec.edu.ups.modelo.UserDetail;

/**
 * Interface UserDetailDAO.
 * 
 * La interface UserDetailDAO ha sido creada como interface específica para la
 * persistencia de objetos de tipo Category en la base de datos. Dicha interface
 * hereda de la clase GenericDAO y será imlementada en una clase específica que
 * controlará la conexión a la base de datos de un sistema que permite
 * ejemplificar el uso del patrón de diseño DAO.
 * 
 * Además, en esta interface se pueden agregar método específicos para el manejo
 * del objeto UserDetail, por ejemplo: buscarUserDetailPorUser entre otras.
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 *
 */
public interface UserDetailDAO extends GenericDAO<UserDetail, Integer> {

}
