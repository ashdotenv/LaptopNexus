package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PasswordEncryptionWIthAes;
import model.UserModel;
import util.StringUtils;

public class DatabaseController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/Laptop_Nexus";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}

	public int addUser(UserModel userModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_USER);

			PreparedStatement checkUsernameSt = con.prepareStatement(StringUtils.GET_USERNAME);
			checkUsernameSt.setString(1, userModel.getUsername());
			ResultSet checkUsernameRs = checkUsernameSt.executeQuery();

			checkUsernameRs.next();

			if (checkUsernameRs.getInt(1) > 0) {
				return -2; // Username already exists
			}

			PreparedStatement checkPhoneSt = con.prepareStatement(StringUtils.GET_PHONE);
			checkPhoneSt.setLong(1, userModel.getPhoneNumber());
			ResultSet checkPhoneRs = checkPhoneSt.executeQuery();

			checkPhoneRs.next();

			if (checkPhoneRs.getInt(1) > 0) {
				return -4; // Phone Number already exists
			}

			PreparedStatement checkEmailSt = con.prepareStatement(StringUtils.GET_EMAIL);
			checkEmailSt.setString(1, userModel.getEmail());
			ResultSet checkEmailRs = checkEmailSt.executeQuery();

			checkEmailRs.next();

			if (checkEmailRs.getInt(1) > 0) {
				return -3; // Email already exists
			}
			String encryptedPassword = PasswordEncryptionWIthAes.encryptPassword(userModel.getPassword(),
					"U3CdwubLD5yQbUOG92ZnHw==");
			st.setString(1, userModel.getUsername());
			st.setString(2, userModel.getFirstName());
			st.setString(3, userModel.getLastName());
			st.setString(4, userModel.getEmail());
			st.setLong(5, Long.parseLong("977" + userModel.getPhoneNumber().toString()));
			st.setString(6, encryptedPassword);

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getUserLoginInfo(String username, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String userDb = rs.getString("username");
				String encryptedPassword = rs.getString("password");

				// Decrypt password from database and compare
				String decryptedPassword = PasswordEncryptionWIthAes.decryptPassword(encryptedPassword,
						"U3CdwubLD5yQbUOG92ZnHw==");

				if (decryptedPassword != null && userDb.equals(username) && decryptedPassword.equals(password)) {
					System.out.println("Login Success");
					return 1; // Login successful
				} else {
					return 0; // Password mismatch
				}
			} else {
				// No matching record found
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public UserModel getUserDetails(String userName) throws ClassNotFoundException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(StringUtils.GET_LOGIN_USER_INFO)) {
			statement.setString(1, userName);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Fetch user details from the result set and create a UserModel object
					UserModel user = new UserModel();
					user.setFirstName(resultSet.getString("first_name"));
					user.setLastName(resultSet.getString("last_name"));
					// Populate other fields as needed
					return user;
				} else {
					// No user found with the given username
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle or log the exception as needed
			return null;
		}
	}

	public List<UserModel> getAllUsers() {
		List<UserModel> users = new ArrayList<>();
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_USERS);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				// Populate other fields as needed
				users.add(user);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
		}
		return users;
	}

}
