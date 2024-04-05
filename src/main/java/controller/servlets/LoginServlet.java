package controller.servlets;

import java.io.IOException;

import java.net.HttpCookie;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserModel;
import controller.DatabaseController;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = StringUtils.LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.sendRedirect(request.getContextPath()+ StringUtils.LOGIN_PAGE);
		request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		// Check login credentials
		int loginResult = dbController.getUserLoginInfo(userName, password);

		if (loginResult == 1) {

			HttpSession userSession = request.getSession();
			userSession.setAttribute("user", userName);
			userSession.setMaxInactiveInterval(30 * 3);

			Cookie userCookie = new Cookie("user", userName);
			userCookie.setMaxAge(30 * 60);
			response.addCookie(userCookie);
			userCookie.isHttpOnly();
			UserModel user = null;
			try {
				user = dbController.getUserDetails(userName);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (user != null) {
//                // Set student details as attributes in the request object
//            	String firstName = student.getFirstName();
//            	String lastName = student.getLastName();
//            	
//            	String name = firstName + " " + lastName;
//                request.setAttribute("name", name);
				// Set success message and redirect to welcome page
				request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_LOGIN_MESSAGE);
				response.sendRedirect(request.getContextPath() + StringUtils.WELCOME_PAGE);
			} else {
				// If student details are not found, handle the error appropriately
				request.setAttribute(StringUtils.ERROR_MESSAGE, "User details not found");
				request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
			}
		} else if (loginResult == 0) {
			// Set error message for invalid credentials and forward to login page
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_LOGIN_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		} else {
			// Set server error message and forward to login page
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}
	}
}
