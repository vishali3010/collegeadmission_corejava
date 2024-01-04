package com.collageadmission.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	public static void main(String[] args) {
		getConnection();
		System.out.println("connection created");
	}

	public static Connection getConnection() {
		Connection con = null;

		String url, userName, passWord;

		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

//		String url = "jdbc:mysql://localhost:3306/collageadmission";
//		String userName = "root";
//		String passWord = "123456";

		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			e.printStackTrace();
//			throw new RuntimeException("Unable to connect to the database");
			throw new RuntimeException(e.getMessage());
		}
		return con;
	}
}
