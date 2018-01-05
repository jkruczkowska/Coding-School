package pl.coderslab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User_group;


/**
 * Servlet implementation class AddGroup
 */
@WebServlet("/AddGroup")
public class AddGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		if (request.getParameter("idGroup") != null) {
//			String idGroup = request.getParameter("idGroup");
//			String name = User_group.loadById(Integer.parseInt(idGroup)).getName();
//			System.out.println(name);
//			request.setAttribute("idGroup", idGroup);
//			request.setAttribute("name", name);
//			getServletContext().getRequestDispatcher("/view/addGroup.jsp").forward(request, response);
//
//		} else {
//			getServletContext().getRequestDispatcher("/view/addGroup.jsp").forward(request, response);
//		}
	}

}
