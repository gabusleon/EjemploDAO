package ec.edu.ups.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

/**
 * Clase ShoppingBasket.
 * 
 * La clase ShoppingBaket ha sido generada dentro de la lógica de negocio para
 * un sistema que permite ejemplificar el uso del patrón de diseño DAO para la
 * persistencia de datos
 * 
 * @author Gabriel A. León Paredes Doctor en Tecnologías de Información
 *         https://www.linkedin.com/in/gabrielleonp
 * @version 1.0
 */
public class ShoppingBasket implements Serializable {

	/**
	 * Atributo necesario para el envío de objetos a través de una comunicación, en
	 * este caso hacia la base de datos
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Calendar date;
	private Set<Product> products;

	public ShoppingBasket() {

	}

	public ShoppingBasket(int id, Calendar date) {
		super();
		this.id = id;
		this.date = date;
	}

	// Generamos los getters y setters de sus atributos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	// Método toString que nos permite describir a un objeto
	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyy");

		return "ShoppingBasket [id=" + id + ", date=" + formato.format(date.getTime()) + "]";
	}

}
