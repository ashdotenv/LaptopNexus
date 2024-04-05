package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtils;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURL().toString();
		if (uri.endsWith("RegisterServlet")) {
			chain.doFilter(req, res);
			return;
		}
		if (uri.endsWith("adminDashboard.jsp")) {
			System.out.println(uri);
			chain.doFilter(req, res);
			return;
		}
		boolean isLogin = uri.endsWith("login.jsp");
		boolean isLoginServlet = uri.endsWith("LoginServlet");
		boolean isLogoutServlet = uri.endsWith("LogoutServlet");
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = session != null && session.getAttribute("user") != null;

		if (isLoggedIn && isLogin) {
			System.out.println("Hello 11");
			res.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
		}
		if (isLoggedIn && (uri.endsWith("register.jsp") | uri.endsWith("login.jsp"))) {
			res.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
		}

		if (!isLoggedIn && !(isLogin || isLoginServlet)) {
			res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
		} else if (isLoggedIn && !(!isLogin || isLogoutServlet)) {
			res.sendRedirect(req.getContextPath() + "/pages/home.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
