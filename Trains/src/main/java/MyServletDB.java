

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServletDB
 */
@WebServlet("/MyServletDB")

	class MyServletDB extends HttpServlet {
	
	 String dns="ec2-3-133-84-78.us-east-2.compute.amazonaws.com";
	//String url = "jdbc:mysql://ec2-52-15-184-208.us-east-2.compute.amazonaws.com:3306/myDB"+"?useSSL=false";
	protected static String user = "admin";
	static String password = "root";
	Connection connection = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		connection = DriverManager.getConnection("jdbc:mysql://"+ dns+":3306/myDB", "naveen", "naveen");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String selectSQL = "SELECT * FROM myTable";
	PreparedStatement preparedStatement = null;
	try {
		preparedStatement = connection.prepareStatement(selectSQL);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ResultSet rs = null;
	try {
		rs = preparedStatement.executeQuery();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		while (rs.next()) {
		String id = rs.getString("ID");
		String username = rs.getString("MYUSER");
		String email = rs.getString("EMAIL");
		String phone = rs.getString("PHONE");
		response.getWriter().append("USER ID: " + id + ", ");
		response.getWriter().append("USER NAME: " + username + ", ");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}}

//}
