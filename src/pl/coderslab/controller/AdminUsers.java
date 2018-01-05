package pl.coderslab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User;
import pl.coderslab.model.User_group;

@WebServlet("/AdminUsers")
public class AdminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		request.setAttribute("groupList", User_group.loadAll());
		try {
			if (request.getParameter("action") == null) {
				request.setAttribute("adminUsers", User.loadAll());
				getServletContext().getRequestDispatcher("/view/adminUsers.jsp").forward(request, response);
			} else if (request.getParameter("action").equals("edit")) {
				String idUser = request.getParameter("idUser");
				String name = User.loadById(Integer.parseInt(idUser)).getName();
				String email = User.loadById(Integer.parseInt(idUser)).getEmail();
				String password = User.loadById(Integer.parseInt(idUser)).getPassword();
				String idGroup = String.valueOf(User.loadById(Integer.parseInt(idUser)).getPerson_group_id());
				request.setAttribute("idUser", idUser);
				request.setAttribute("name", name);
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("idGroup", idGroup);
				getServletContext().getRequestDispatcher("/view/addUser.jsp").forward(request, response);

			} else if (request.getParameter("action").equals("add")) {
				getServletContext().getRequestDispatcher("/view/addUser.jsp").forward(request, response);
			} else if (request.getParameter("action").equals("del")) {
				int idUser = Integer.parseInt(request.getParameter("idUser"));
				User user = new User();
				user = User.loadById(idUser);
				user.delete();
				request.setAttribute("adminUsers", User.loadAll());
				getServletContext().getRequestDispatcher("/view/adminUsers.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("addName");
		String email = request.getParameter("addEmail");
		String password = request.getParameter("addPassword");
		int idGroup = Integer.parseInt(request.getParameter("addIdGroup"));
		User user = null;
		if (request.getParameter("idUser") == null) {
			user = new User();
		} else {
			int idUser = Integer.parseInt(request.getParameter("idUser"));
			try {
				user = User.loadById(idUser);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPerson_group_id(idGroup);
		user.saveToDB();
		request.setAttribute("adminUsers", User.loadAll());
		getServletContext().getRequestDispatcher("/view/adminUsers.jsp").forward(request, response);
	}

}
