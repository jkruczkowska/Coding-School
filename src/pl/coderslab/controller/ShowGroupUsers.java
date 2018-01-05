package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User;

/**
 * Servlet implementation class ShowGroupUsers
 */
@WebServlet("/ShowGroupUsers")
public class ShowGroupUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int group_id = Integer.parseInt(request.getParameter("groupId"));
		request.setAttribute("groupUsers", User.loadAllByGrupId(group_id));
		
		getServletContext().getRequestDispatcher("/view/showGroupUsers.jsp").forward(request, response);
	}

}
