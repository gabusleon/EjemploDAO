package ec.edu.ups.test;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.dao.UserDetailDAO;
import ec.edu.ups.modelo.User;
import ec.edu.ups.modelo.UserDetail;

public class TestUser {
	public static void main(String[] args) {
		UserDAO userDAO = DAOFactory.getFactory().getUserDAO();
		UserDetailDAO userDetailDAO = DAOFactory.getFactory().getUserDetailDAO();

		userDAO.createTable();

		User u1, u2, u3, u4;
		u1 = new User(1, 1, "user1", "paasword1");
		u2 = new User(2, 2, "user2", "paasword2");
		u3 = new User(3, 3, "user3", "paasword3");

		UserDetail ud1, ud2, ud3, ud4;
		ud1 = new UserDetail(1, "detail1");
		ud2 = new UserDetail(2, "detail2");
		ud3 = new UserDetail(3, "detail3");

		ud1.setUser(u1);
		ud2.setUser(u2);
		ud3.setUser(u3);

		u1.setDetail(ud1);
		u2.setDetail(ud2);
		u3.setDetail(ud3);

		userDAO.create(u1);
		userDAO.create(u2);
		userDAO.create(u3);

		System.out.println("---Creación de usuarios\n" + userDAO.find());
		System.out.println("---Creación de detalles de usuario\n" + userDetailDAO.find());

		System.out.println("---Busqueda de usuario 1\n" + userDAO.read(u1.getId()));
		System.out.println("---Busqueda de detalles de usuario 3\n" + userDetailDAO.read(ud3.getId()));

		userDAO.delete(u2);
		System.out.println("---Eliminación de usuario 2\n" + userDAO.find());
		userDetailDAO.delete(ud3);
		System.out.println("---Eliminación de detalle de usuario 3\n" + userDAO.find());

		u4 = userDAO.read(1);
		u4.setName("Nuevo...");
		ud4 = u4.getDetail();
		ud4.setDetail("Nuevo...");
		u4.setDetail(ud4);
		userDAO.update(u4);
		System.out.println("---Actualización de usuario 1\n" + userDAO.find());

		UserDetail ud5 = userDetailDAO.read(1);
		ud5.setDetail("Otro nuevo");
		userDetailDAO.update(ud5);
		System.out.println("---Creación de detalle de usuaro que ya existe\n" + userDetailDAO.find());

	}
}
