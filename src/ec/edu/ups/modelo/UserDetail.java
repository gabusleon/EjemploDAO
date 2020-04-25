package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase UserDetail.
 * 
 * La clase UserDetail ha sido generada dentro de la lógica de negocio para un
 * sistema que permite ejemplificar el uso del patrón de diseño DAO para la
 * persistencia de datos
 * 
 * @author Gabriel A. León Paredes 
 * Doctor en Tecnologías de Información
 * https://www.linkedin.com/in/gabrielleonp
 * @version 1.0
 */
public class UserDetail implements Serializable {

	/**
	 * Atributo necesario para el envío de objetos a través de una comunicación, en
	 * este caso hacia la base de datos
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String detail;
	private User user;

	public UserDetail() {

	}

	public UserDetail(int id, String detail) {
		super();
		this.id = id;
		this.detail = detail;
	}

	// Generamos los getters y setters de sus atributos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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
		return "UserDetail [id=" + id + ", detail=" + detail + ", user=" + user + "]";
	}

}
