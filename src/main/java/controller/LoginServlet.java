package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password, 0);
		PrintWriter writer = response.getWriter();
		Gson gson = new Gson();
		
		// check whether user already logged in
		
		String userName = "";
		Cookie[] cookies = request.getCookies();
		if (cookies !=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("user")) {
					userName = cookie.getValue();
				}
			}
		}
		
        if (userName != "") {   //checking whether the session exists
        	String respJson = gson.toJson("logged");
			writer.append(respJson);
			return;
        }
        
        userDao = new UserDao();
		try {
			int result = userDao.loginUser(user);
			if (result == 1) {
				Cookie loginCookie = new Cookie("user", user.getUserName());
				loginCookie.setMaxAge(30 * 60);
				response.addCookie(loginCookie);
				String respJson = gson.toJson("true");
				writer.append(respJson);
				HttpSession session=request.getSession();
			    session.setAttribute("user",username);
			} else {
				String respJson = gson.toJson("false");
				writer.append(respJson);
			}
			
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
