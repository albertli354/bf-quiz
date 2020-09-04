package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.UserDao;
import domain.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userDao = new UserDao();
		PrintWriter writer = response.getWriter();
		Gson gson = new Gson();
		
		User user = new User(username, password, 0);
		try {
			int result = userDao.signupUser(user);
			if (result == 1) {
				// get the old session and invalidate
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}
				
				// generate a new session
				Cookie loginCookie = new Cookie("user", user.getUserName());
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				
				String respJson = gson.toJson("true");
				writer.append(respJson);
			} else {
				String respJson = gson.toJson("false");
				writer.append(respJson);
			}
			
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
				
		
	}

}
