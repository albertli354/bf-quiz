package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;

import com.google.gson.Gson;

import dao.QuizDao;
import dao.UserDao;
import domain.Quiz;
import domain.User;

/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizDao quizDao;
	private UserDao userDao;
	private Map<Integer, String> questionMap;
	private Map<Integer, String> choiceMap;
	private Map<Integer, LinkedList<Integer>> questionChoice;
	private Map<Integer, Integer> correctAnswers;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String quizType = request.getParameter("quizType");
		String userName = request.getParameter("userName");
		quizDao = new QuizDao();
		userDao = new UserDao();
		User user = new User(userName);
		int userID = 0;
		// get user id first
		try {
			userID = userDao.getUserID(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		// insert a new quiz
//		System.out.println(userID);
		Quiz quiz = new Quiz(quizType, userID);
		try {
			// get question set
			questionMap = quizDao.getQuestionMap(quiz);
			
			// choice set
			choiceMap = quizDao.getChoiceMap(questionMap);
			
			// mapping question to choice
			// key is quesitonID, value is choice id
			questionChoice = quizDao.getMapping(questionMap);
			
			// get correct answers
			correctAnswers = quizDao.getCorrectAnswers(questionMap);
			
			
			// pass time
			long now = System.currentTimeMillis();
			long end = now + TimeUnit.MINUTES.toMillis(10);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = formatter.format(now);
			String endTime = formatter.format(end);

			// pass user choice, current index, question ID
			// keep track of next and prev question
			int currentQuestionIndex = 0;
			Map<Integer, Integer> userChoice = new HashMap<>();
			
			
			// create a new quiz in database
			int quizID = quizDao.createNewQuiz(questionChoice, userID, quizType);
			
			
			// create Quiz object
			Quiz currentQuiz = new Quiz(quizType, currentTime, endTime, userID, quizID, 
					questionMap, choiceMap, questionChoice, currentQuestionIndex,
					userChoice, correctAnswers);
			
			

			// pass data to JSP through session
			request.getSession().setAttribute("currentQuiz", currentQuiz);
			request.getSession().setAttribute("quizID", quizID);
			request.getSession().setAttribute("finished", false);

			
			PrintWriter writer = response.getWriter();
			Gson gson = new Gson();
			String respJson = gson.toJson("200");
			writer.append(respJson);
			return;
			
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}


}
