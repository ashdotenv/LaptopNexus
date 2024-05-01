
package controller.servlets;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.ProductModel;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/AddProductServlet" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(StringUtils.ADD_PRODUCTS_PAGE).forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseController db = new DatabaseController();
		try (Connection con = db.getConnection()) {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			float price = Float.parseFloat(request.getParameter("price")); // Changed to float
			int stock = Integer.parseInt(request.getParameter("stock"));
			int category = Integer.parseInt(request.getParameter("category")); // Changed to int
			Part imageData = request.getPart("image");
			ProductModel product = new ProductModel(name, description, stock, price, category, imageData);
			db.addProduct(product);
			return;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			response.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

}
