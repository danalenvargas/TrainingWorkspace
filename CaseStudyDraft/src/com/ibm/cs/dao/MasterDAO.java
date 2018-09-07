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

	public MasterDAO() {
		getConnection(); //temporary band-aid, to remove later
	}

	protected Connection getConnection() {
		Connection conn = null;
		try {
			properties.load(getClass().getResourceAsStream("config.properties"));
			String url = properties.getProperty("DB_URL");
			String user = properties.getProperty("DB_USER");
			String password = properties.getProperty("DB_PASS");
			
//			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); // register the driver
			Class.forName(properties.getProperty("DB_DRIVER")); // register the driver
			conn = DriverManager.getConnection(url, user, password); // get a connection
		} catch (SQLException e) {
			System.out.println("ERROR in Connecting to Database.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}
	
	public void openConnection() {
		conn = getConnection();
	}
	
//	public void startTransaction() {
//		try {
//			conn = getConnection();
//			conn.setAutoCommit(false);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void endTransaction(boolean isSuccessful) {
//		try {
//			if(isSuccessful) {
//				conn.commit();
//			} else {
//				conn.rollback();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		closeConnection();
//	}

	public void closeConnection() {
		if (this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
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
