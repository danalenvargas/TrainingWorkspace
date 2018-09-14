package com.ibm.cs.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public abstract class MasterDAO {
	// private static final String URL =
	// "jdbc:mysql://localhost/cs_draft_db?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private static final String USER = "root";
	// private static final String PASSWORD = "admin";
	protected Connection conn;
	private Properties properties = new Properties();

	// a connection is opened at DAO construction, but new connection can also be created using openConnection() method
	public MasterDAO() {
		this.conn = getConnection(); 
	}

	protected Connection getConnection() {
		Connection con = null;
		try {
			properties.load(getClass().getResourceAsStream("config.properties"));
			String url = properties.getProperty("DB_URL");
			String user = properties.getProperty("DB_USER");
			String password = properties.getProperty("DB_PASS");
			
			Class.forName(properties.getProperty("DB_DRIVER")); // register the driver
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("ERROR in Connecting to Database.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return con;
	}

	// connections are opened and closed at Service level to allow a single connection for multiple DAO method calls
	public void openConnection() {
		this.conn = getConnection();
	}

	public void closeConnection() {
		if (this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// PreparedStatements and ResultSets are closed after every use/method
	protected void closeResources(ResultSet rs, PreparedStatement pst) {
		if (rs != null) {
            try {rs.close();} catch (SQLException e) { e.printStackTrace();}
        }
        if (pst != null) {
            try {pst.close();} catch (SQLException e) {e.printStackTrace();}
        }
	}
	
	protected void closeResources(PreparedStatement pst) {
        if (pst != null) {
            try {pst.close();} catch (SQLException e) {e.printStackTrace();}
        }
	}
}
