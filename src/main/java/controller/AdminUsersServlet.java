package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import domain.User;

/**
 * Servlet implementation class AdminUsersServlet
 */
@WebServlet("/AdminUsersServlet")
public class AdminUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		List<User> users = userDao.getAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("pages/admin/users.jsp").forward(request,response);
	}


}
