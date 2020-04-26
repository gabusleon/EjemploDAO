package ec.edu.ups.test;

import ec.edu.ups.dao.CategoryDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.modelo.Category;

public class TestCategory {
	public static void main(String[] args) {
		CategoryDAO categoryDAO = DAOFactory.getFactory().getCategoryDAO();
		categoryDAO.createTable();

		Category c1, c2, c3, c4, c5;
		c1 = new Category(1, "uno", "Categoría uno");
		c2 = new Category(2, "dos", "Categoría dos");
		c3 = new Category(3, "tres", "Categoría tres");

		categoryDAO.create(c1);
		categoryDAO.create(c2);
		categoryDAO.create(c3);
		System.out.println("---Creación de tres categorías\n" + categoryDAO.find());
		
		categoryDAO.delete(c2);
		System.out.println("---Eliminación de categoría 2\n" + categoryDAO.find());
		
		c4 = categoryDAO.read(3);
		c4.setDescription("Nueva...");
		categoryDAO.update(c4);
		System.out.println("---Actualización de categoría 3\n" + categoryDAO.find());

		c5 = new Category(1, "uno", "Otro de nuevo");
		categoryDAO.update(c5);
		System.out.println("---Creación de categoría que ya existe\n" + categoryDAO.find());

		categoryDAO.create(new Category(6, "seis", "Categoría seis"));
		System.out.println("---Creación de categoría 6\n" + categoryDAO.find());
	}
}
