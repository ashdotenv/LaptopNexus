package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductServlet/*" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!(request.getRequestURI().split("/").length >= 4)) {
			String search = request.getParameter("name");
			try {
				DatabaseController db = new DatabaseController();
				request.setAttribute("Products", search != null ? db.searchProducts(search) : db.getAllProducts());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute(StringUtils.ERROR_MESSAGE, "Failed to retrieve products: " + e.getMessage());
			}
			request.getRequestDispatcher(StringUtils.PRODUCTS_PAGE).forward(request, response);
		} else if (request.getRequestURI().split("/").length >= 4) {
			int singleProduct = Integer.parseInt(request.getRequestURI().split("/")[3]);
			try {
				DatabaseController db = new DatabaseController();
				request.setAttribute("Product", db.getProduct(singleProduct));
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute(StringUtils.ERROR_MESSAGE, "Failed to retrieve products: " + e.getMessage());
			}
			request.getRequestDispatcher(StringUtils.PRODUCT_PAGE).forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
		int minPrice = Integer.parseInt(request.getParameter("minPrice"));
		int category = Integer.parseInt(request.getParameter("category"));

		try {
			DatabaseController db = new DatabaseController();
			request.setAttribute("Products", db.filterProducts(minPrice, maxPrice, category));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to retrieve products: " + e.getMessage());
		}
		request.getRequestDispatcher(StringUtils.PRODUCTS_PAGE).forward(request, response);
	}

}
