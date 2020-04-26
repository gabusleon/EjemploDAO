package ec.edu.ups.test;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.dao.ShoppingBasketDAO;
import ec.edu.ups.modelo.Product;
import ec.edu.ups.modelo.ShoppingBasket;

public class TestShoppingBasket {
	public static void main(String[] args) {
		ShoppingBasketDAO shoppingBasketDAO = DAOFactory.getFactory().getShoppingBasketDAO();
		ProductDAO productDAO = DAOFactory.getFactory().getProductDAO();

		shoppingBasketDAO.createTable();

		ShoppingBasket sb1, sb2, sb3;

		sb1 = new ShoppingBasket(1, new GregorianCalendar(2020, 3, 20));
		sb2 = new ShoppingBasket(2, new GregorianCalendar(2020, 3, 21));
		sb3 = new ShoppingBasket(3, new GregorianCalendar(2020, 3, 22));

		Product p1 = new Product(1, 1, "Product 1");
		Product p2 = new Product(2, 2, "Product 2");
		Product p3 = new Product(3, 3, "Product 3");
		Product p4 = new Product(4, 4, "Product 4");
		Product p5 = new Product(5, 5, "Product 5");
		Product p6 = new Product(6, 6, "Product 6");

		p1.setShoppingBasket(sb1);
		p2.setShoppingBasket(sb1);
		p3.setShoppingBasket(sb2);
		p4.setShoppingBasket(sb2);
		p5.setShoppingBasket(sb3);
		p6.setShoppingBasket(sb3);

		Set<Product> products1 = new HashSet<Product>();
		Set<Product> products2 = new HashSet<Product>();
		Set<Product> products3 = new HashSet<Product>();

		products1.add(p1);
		products1.add(p2);

		products2.add(p3);
		products2.add(p4);

		products3.add(p5);
		products3.add(p6);

		sb1.setProducts(products1);
		sb2.setProducts(products2);
		sb3.setProducts(products3);

		shoppingBasketDAO.create(sb1);
		shoppingBasketDAO.create(sb2);
		shoppingBasketDAO.create(sb3);

		System.out.print("---Creación de carrito de compras\n");
		for (ShoppingBasket shoppingBasket : shoppingBasketDAO.find()) {
			System.out.println(shoppingBasket);
			for (Product product : shoppingBasket.getProducts()) {
				System.out.println(product);
			}
		}

		Product p7 = new Product(7, 7, "Product 7");
		p7.setShoppingBasket(sb1);
		productDAO.create(p7);
		System.out.print("\n---Creación de producto\n");
		System.out.println(productDAO.find());

		System.out.print("\nBuscar carrito de compras 2\n");
		ShoppingBasket sb4 = shoppingBasketDAO.read(2);
		System.out.println(sb4);
		for (Product product : sb4.getProducts()) {
			System.out.println(product);
		}

		System.out.print("\nBuscar productos 5\n");
		System.out.println(productDAO.read(5));

		sb4.setProducts(null);
		shoppingBasketDAO.update(sb4);

		System.out.print("\n---Actualización de carrito de compras\n");
		for (ShoppingBasket shoppingBasket : shoppingBasketDAO.find()) {
			System.out.println(shoppingBasket);
			for (Product product : shoppingBasket.getProducts()) {
				System.out.println(product);
			}
		}

		productDAO.delete(p7);
		System.out.print("\n---Elminación de producto\n");
		for (Product product : productDAO.findByShoppingBasketId(sb1.getId())) {
			System.out.println(product);
		}
		
		shoppingBasketDAO.delete(sb3);
		System.out.print("\n---Eliminación de carrito de compras\n");
		for (ShoppingBasket shoppingBasket : shoppingBasketDAO.find()) {
			System.out.println(shoppingBasket);
			for (Product product : shoppingBasket.getProducts()) {
				System.out.println(product);
			}
		}
		
	}

}
