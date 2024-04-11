package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtils;

@WebFilter("/logout")
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
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		if (uri.endsWith("Login") && session != null) {
			session.setAttribute("user", "admin");
			res.sendRedirect(req.getContextPath() + StringUtils.PRODUCT_SERVLET);
			return;
		}
		if (uri.endsWith("Logout") && session != null) {
			session.invalidate();
			res.sendRedirect(req.getContextPath() + StringUtils.PRODUCT_SERVLET);
			return;
		}
		if ((uri.endsWith("AdminServlet") | (uri.endsWith("AdminDashboard.jsp"))
				&& session.getAttribute("user") != "admin")) {
			req.setAttribute(StringUtils.AUTHORIZATION_ERROR, "Opps Looks Like You Are not an Admin");
			res.sendRedirect(req.getContextPath() + StringUtils.PRODUCT_SERVLET);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
