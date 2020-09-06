package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDao;
import domain.Question;

/**
 * Servlet implementation class AdminQuestionServlet
 */
@WebServlet("/AdminQuestionServlet")
public class AdminQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionDao dao = new QuestionDao();
		List<Question> questionResult = dao.getAllQuestion();
		request.setAttribute("question", questionResult);
		request.getRequestDispatcher("pages/admin/questions.jsp").forward(request,response);
	}

	

}
