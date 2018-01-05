package pl.coderslab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Exercise;

/**
 * Servlet implementation class AdminExercises
 */
@WebServlet("/AdminExercises")
public class AdminExercises extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		try {
		if (request.getParameter("action") == null) {
			request.setAttribute("adminExercises", Exercise.loadAll());
			getServletContext().getRequestDispatcher("/view/adminExercises.jsp").forward(request, response);
		}
		else if (request.getParameter("action").equals("edit")) {
			String idExercise = request.getParameter("idExercise");
			String title = Exercise.loadById(Integer.parseInt(idExercise)).getTitle();
			String description = Exercise.loadById(Integer.parseInt(idExercise)).getDescription();
			request.setAttribute("idExercise", idExercise);
			request.setAttribute("title", title);
			request.setAttribute("description", description);
			getServletContext().getRequestDispatcher("/view/addExercise.jsp").forward(request, response);

		} else if (request.getParameter("action").equals("add")) {
			getServletContext().getRequestDispatcher("/view/addExercise.jsp").forward(request, response);
		}
		else if (request.getParameter("action").equals("del")) {
			int idExercise = Integer.parseInt(request.getParameter("idExercise"));
			Exercise ex = new Exercise();
			ex = Exercise.loadById(idExercise);
			ex.delete();
			request.setAttribute("adminExercises", Exercise.loadAll());
			getServletContext().getRequestDispatcher("/view/adminExercises.jsp").forward(request, response);
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String title = request.getParameter("addTitle");
		String description = request.getParameter("addDescription");
		Exercise ex = null;
		if (request.getParameter("idExercise") == null) {
			ex = new Exercise();
		} else {
			int idExercise = Integer.parseInt(request.getParameter("idExercise"));
			try {
				ex = Exercise.loadById(idExercise);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ex.setTitle(title);
		ex.setDescription(description);
		ex.saveToDB();
		request.setAttribute("adminExercises", Exercise.loadAll());
		getServletContext().getRequestDispatcher("/view/adminExercises.jsp").forward(request, response);
	}

}
