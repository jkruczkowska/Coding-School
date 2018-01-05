package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User_group;

/**
 * Servlet implementation class ShowGroup
 */
@WebServlet("/ShowGroups")
public class ShowGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		User_group[] ug = User_group.loadAll();
		request.setAttribute("groups", ug);
//		response.getWriter().append(ug[0].getName());
//		response.getWriter().append("dupa");
		getServletContext().getRequestDispatcher("/view/showGroups.jsp").forward(request, response);
	}

}
