package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import model.UserModel;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.REGISTER_SERVLET })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
//		response.sendRedirect(request.getContextPath() + StringUtils.REGISTER_PAGE);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		Long phoneNumber = Long.parseLong(request.getParameter("phone_number"));
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		System.out.println(phoneNumber);
		UserModel userModel = new UserModel(firstName, lastName, userName, email, phoneNumber, password);

		int result = dbController.addUser(userModel);
		if (password.equals(retypePassword)) {
			switch (result) {
			case 1:
				request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
				request.getRequestDispatcher(StringUtils.LOGIN_PAGE);
				break;
			case 0:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_REGISTER_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				break;
			case -1:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				break;
			case -2:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.USERNAME_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				break;
			case -3:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.EMAIL_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				break;
			case -4:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PHONE_NUMBER_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				break;
			default:
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			}
		} else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PASSWORD_UNMATCHED_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
		}
	}
}
