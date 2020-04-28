package ec.edu.ups.dao;

import java.util.Set;

import ec.edu.ups.modelo.Product;

/**
 * Interface ProductDAO.
 * 
 * La interface ProductDAO ha sido creada como interface específica para la
 * persistencia de objetos de tipo Category en la base de datos. Dicha interface
 * hereda de la clase GenericDAO y será imlementada en una clase específica que
 * controlará la conexión a la base de datos de un sistema que permite
 * ejemplificar el uso del patrón de diseño DAO.
 * 
 * Además, en esta interface se pueden agregar método específicos para el manejo
 * del objeto Product, por ejemplo: buscarProductPorDescripction entre otras.
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 * 
 * @see GenericDAO
 * @see Product
 * 
 * @version 1.0
 *
 */
public interface ProductDAO extends GenericDAO<Product, Integer> {

	public abstract Set<Product> findByShoppingBasketId(int shoppingBasketId);

}
