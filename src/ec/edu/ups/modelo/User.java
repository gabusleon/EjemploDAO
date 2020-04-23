package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase User.
 * 
 * La clase User ha sido generada dentro de la lógica de negocio para un sistema
 * que permite ejemplificar el uso del patrón de diseño DAO para la persistencia
 * de datos
 * 
 * @author Gabriel A. León Paredes 
 * 		   Doctor en Tecnologías de Información
 *         https://www.linkedin.com/in/gabrielleonp
 * @version 1.0
 */
public class User implements Serializable {

	/**
	 * Atributo necesario para el envío de objetos a través de una comunicación, en
	 * este caso hacia la base de datos
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String password;
	private int level;
	private User user;

	public User() {

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Método toString que nos permite describir a un objeto
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", level=" + level + ", user=" + user
				+ "]";
	}

}
