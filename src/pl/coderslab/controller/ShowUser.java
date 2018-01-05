package pl.coderslab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

/**
 * Servlet implementation class ShowUser
 */
@WebServlet("/ShowUser")
public class ShowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		try {
		int users_id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("userName", User.loadById(users_id).getName());
		request.setAttribute("userEmail", User.loadById(users_id).getEmail());
		request.setAttribute("userGroup", User.loadById(users_id).getPerson_group_id());
		request.setAttribute("solutions", Solution.loadAllByUserId(users_id));
		getServletContext().getRequestDispatcher("/view/showUser.jsp").forward(request, response);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
