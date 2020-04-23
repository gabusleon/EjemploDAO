package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase Product.
 * 
 * La clase Product ha sido generada dentro de la lógica de negocio para un
 * sistema que permite ejemplificar el uso del patrón de diseño DAO para la
 * persistencia de datos
 * 
 * @author Gabriel A. León Paredes 
 * 		   Doctor en Tecnologías de Información
 *         https://www.linkedin.com/in/gabrielleonp
 * @version 1.0
 */
public class Product implements Serializable {

	/**
	 * Atributo necesario para el envío de objetos a través de una comunicación, en
	 * este caso hacia la base de datos
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int amount;
	private String description;
	private ShoppingBasket shoppingBasket;

	public Product() {

	}

	// Generamos los getters y setters de sus atributos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ShoppingBasket getShoppingBasket() {
		return shoppingBasket;
	}

	public void setShoppingBasket(ShoppingBasket shoppingBasket) {
		this.shoppingBasket = shoppingBasket;
	}

	// Método toString que nos permite describir a un objeto
	@Override
	public String toString() {
		return "Product [id=" + id + ", amount=" + amount + ", description=" + description + ", shoppingBasket="
				+ shoppingBasket + "]";
	}

}
