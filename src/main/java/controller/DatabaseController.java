package controller;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductModel;
import util.StringUtils;

public class DatabaseController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
//		Setting Up Database Connection with SQL
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/Laptop_Nexus";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}

//Fetching All Products Data
	public List<ProductModel> getAllProducts() {
		List<ProductModel> products = new ArrayList<>();
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_PRODUCTS);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				ProductModel allProducts = new ProductModel();
				allProducts.setName(rs.getString("name"));
				allProducts.setDescription(rs.getString("description"));
				allProducts.setPrice(rs.getFloat("price"));
				allProducts.setStock(rs.getInt("stock"));
				allProducts.setCategoryName(rs.getString("category_name"));
				allProducts.setProductId(rs.getInt("product_id"));
				products.add(allProducts);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
		}
		return products;
	}

//Filter Products With Price And Category
	public List<ProductModel> filterProducts(int minPrice, int maxPrice, int category) {
		List<ProductModel> products = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement(StringUtils.GET_FILTER_PRODUCTS)) {
				st.setInt(1, minPrice);
				st.setInt(2, maxPrice);
				st.setInt(3, category);
				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						ProductModel product = new ProductModel();
						product.setName(rs.getString("name"));
						product.setDescription(rs.getString("description"));
						product.setPrice(rs.getFloat("price"));
						product.setStock(rs.getInt("stock"));
						product.setCategoryName(rs.getString("category_name"));
						product.setProductId(rs.getInt("product_id"));
						;
						products.add(product);
					}
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return products;
	}

	public List<ProductModel> searchProducts(String Search) {
		Search = "%" + Search + "%";
		List<ProductModel> products = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement(StringUtils.GET_SEARCH_PRODUCTS)) {
				st.setString(1, Search);
				st.setString(2, Search);
				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						ProductModel product = new ProductModel();
						product.setName(rs.getString("name"));
						product.setDescription(rs.getString("description"));
						product.setPrice(Float.parseFloat(rs.getString("price")));
						product.setStock(Integer.parseInt(rs.getString("stock")));
						product.setCategoryName(rs.getString("category_name"));
						product.setProductId(rs.getInt("product_id"));
						products.add(product);
					}
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return products;
	}

	public List<ProductModel> getProduct(int productId) {
		List<ProductModel> product = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement(StringUtils.GET_PRODUCT)) {
				st.setInt(1, productId);
				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						ProductModel singleProduct = new ProductModel();
						singleProduct.setName(rs.getString("name"));
						singleProduct.setDescription(rs.getString("description"));
						singleProduct.setPrice(Float.parseFloat(rs.getString("price")));
						singleProduct.setStock(Integer.parseInt(rs.getString("stock")));
						singleProduct.setCategoryName(rs.getString("category_name"));
						singleProduct.setProductId(rs.getInt("product_id"));
						product.add(singleProduct);
					}
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return product;
	}

	public int addProduct(ProductModel product) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCT)) {
			st.setString(1, product.getName());
			st.setString(2, product.getDescription());
			st.setInt(3, product.getStock());
			st.setInt(4, product.getCategory());
			st.setFloat(5, product.getPrice());
			st.setString(6, product.getImageUrlFromPart());
			int rowsAffected = st.executeUpdate();
			return rowsAffected;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

}
