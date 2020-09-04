package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuizDao;
import domain.Quiz;



/**
 * Servlet implementation class UpdateQuiz
 */
@WebServlet("/UpdateQuiz")
public class UpdateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QuizDao quizDao;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Quiz currentQuiz = (Quiz) session.getAttribute("currentQuiz");
		int quizID = (Integer) session.getAttribute("quizID");
		String btnType = request.getParameter("action");
		
		int currentQuestionIndex = currentQuiz.getCurrentQuestionIndex();
		boolean finished = false;
		quizDao = new QuizDao();
		// update user choice based on userAnswer
		session.setAttribute("finished", false);
		String radio = request.getParameter("userAnswer");
		int userSelection = -1;
		if (radio != null) {
			userSelection = Integer.parseInt(radio);
		}
		currentQuiz.updateUserChoice(currentQuiz.getQuestionID(currentQuestionIndex), userSelection);
		
		if (btnType.equals("next")) {
			currentQuiz.setCurrentQuestionIndex(currentQuestionIndex + 1);
		} else if (btnType.equals("prev")) {
			currentQuiz.setCurrentQuestionIndex(currentQuestionIndex - 1);
		} else {
			session.setAttribute("finished", true);
			// update the database
			quizDao.updateUserChoice(currentQuiz, quizID);
		}
		
		
		
		
		
		request.getRequestDispatcher("/pages/quizDetail.jsp").forward(request,response);
		 
		
		
	}

}
