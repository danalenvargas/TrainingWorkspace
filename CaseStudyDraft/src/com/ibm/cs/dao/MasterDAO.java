package com.ibm.cs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MasterDAO {
	private static final String URL = "jdbc:mysql://localhost/cs_draft_db?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";
	
	public MasterDAO() {
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR in Connecting to Database.");
			e.printStackTrace();
		}
		return conn;
	}

}
