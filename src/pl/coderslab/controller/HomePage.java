package pl.coderslab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Solution;

/*Servlet implementation class HomePage
 */
@WebServlet("/home")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html; charset=UTF-8"); 
		Solution sol = new Solution();
//		try {
			
			int contextParam = Integer.parseInt(getServletContext().getInitParameter("solutions"));
			request.setAttribute("solutions", sol.loadAll(contextParam));
//			response.getWriter().append("param from config: " + (contextParam));
//			response.getWriter().append("Served at: ").append(request.getContextPath());
			getServletContext().getRequestDispatcher("/view/home.jsp").forward(request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	
	}

}
