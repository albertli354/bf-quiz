package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizResultDao;
import domain.QuizResult;


/**
 * Servlet implementation class AdminQuizServlet
 */
@WebServlet("/AdminQuizServlet")
public class AdminQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuizResultDao dao = new QuizResultDao();
		List<QuizResult> quizResult = dao.getAllQuiz();
		request.setAttribute("quizResult", quizResult);
		request.getRequestDispatcher("pages/admin/quizzes.jsp").forward(request,response);
	}


}
