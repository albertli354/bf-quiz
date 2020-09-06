package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizDao;
import domain.Quiz;

/**
 * Servlet implementation class AdminQuizDetailServlet
 */
@WebServlet("/AdminQuizDetailServlet")
public class AdminQuizDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		String username = request.getParameter("username");
		String time = request.getParameter("time");
		String grade = request.getParameter("grade");
		QuizDao quizDao = new QuizDao();
		Map<Integer, String> questionMap = quizDao.getAdminQuestionMap(quizID);
		Map<Integer, String> choiceMap = quizDao.getChoiceMap(questionMap);
		Map<Integer, LinkedList<Integer>> questionChoice = quizDao.getMapping(questionMap);
		Map<Integer, Integer> correctAnswers = quizDao.getCorrectAnswers(questionMap);
		Map<Integer, Integer> userChoice = quizDao.getUserChoice(quizID, questionMap);
		for (int key : userChoice.keySet()) {
			System.out.println("the question id is " + key);
			System.out.println("the user choice id is " + userChoice.get(key));
			System.out.println();
		}
		System.out.println("********************");
		Quiz quiz = new Quiz("", time, time, -1, quizID, 
				questionMap, choiceMap, questionChoice, -1,
				userChoice, correctAnswers,username);
		for (int key : quiz.getUserChoice().keySet()) {
			System.out.println("the question id is " + key);
			System.out.println("the user choice id is " + userChoice.get(key));
			System.out.println();
		}
		request.setAttribute("currentQuiz", quiz);
		request.setAttribute("grade", grade);
		request.setAttribute("quizID", quizID);
		request.getRequestDispatcher("pages/admin/resultDetail.jsp").forward(request,response);
	}

}
