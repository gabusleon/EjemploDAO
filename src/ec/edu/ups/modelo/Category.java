package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase Category.
 * 
 * La clase Category ha sido generada dentro de la lógica de negocio para un
 * sistema que permite ejemplificar el uso del patrón de diseño DAO para la
 * persistencia de datos
 * 
 * @author Gabriel A. León Paredes 
 * 		   Doctor en Tecnologías de Información
 *         https://www.linkedin.com/in/gabrielleonp
 * @version 1.0
 */
public class Category implements Serializable {

	/**
	 * Atributo necesario para el envío de objetos a través de una comunicación, en
	 * este caso hacia la base de datos
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;

	public Category() {

	}

	// Generamos los getters y setters de sus atributos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Método toString que nos permite describir a un objeto
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
