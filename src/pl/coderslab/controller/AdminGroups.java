package pl.coderslab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User_group;

/**
 * Servlet implementation class AdminGroups
 */
@WebServlet("/AdminGroups")
public class AdminGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		if (request.getParameter("action") == null) {
			request.setAttribute("adminGroups", User_group.loadAll());
			getServletContext().getRequestDispatcher("/view/adminGroups.jsp").forward(request, response);
		}
		else if (request.getParameter("action").equals("edit")) {
			String idGroup = request.getParameter("idGroup");
			String name = User_group.loadById(Integer.parseInt(idGroup)).getName();
			request.setAttribute("idGroup", idGroup);
			request.setAttribute("name", name);
			getServletContext().getRequestDispatcher("/view/addGroup.jsp").forward(request, response);

		} else if (request.getParameter("action").equals("add")) {
			getServletContext().getRequestDispatcher("/view/addGroup.jsp").forward(request, response);
		}
		else if (request.getParameter("action").equals("del")) {
			int idGroup = Integer.parseInt(request.getParameter("idGroup"));
			User_group ug = new User_group();
			ug = User_group.loadById(idGroup);
			ug.delete();
			request.setAttribute("adminGroups", User_group.loadAll());
			getServletContext().getRequestDispatcher("/view/adminGroups.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		String name = request.getParameter("addName");
		User_group ug = null;
		if (request.getParameter("idGroup") == null) {
			ug = new User_group();
		} else {
			int idGroup = Integer.parseInt(request.getParameter("idGroup"));
			ug = User_group.loadById(idGroup);
		}
		ug.setName(name);
		ug.saveToDB();
		request.setAttribute("adminGroups", User_group.loadAll());
		getServletContext().getRequestDispatcher("/view/adminGroups.jsp").forward(request, response);
	}

}
