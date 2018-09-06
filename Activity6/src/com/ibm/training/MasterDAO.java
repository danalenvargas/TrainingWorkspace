package com.ibm.training;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public abstract class MasterDAO {
	protected PreparedStatement pst;
	protected ResultSet rs;
	protected Connection conn;
	private Properties properties = new Properties();

	public MasterDAO() {
		
	}

	protected Connection getConnection() {
		Connection conn = null;
		try {
			properties.load(getClass().getResourceAsStream("config.properties"));
			String url = properties.getProperty("DB_URL");
			String user = properties.getProperty("DB_USER");
			String password = properties.getProperty("DB_PASS");
			
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

	protected void closeConnection() {
		if (this.rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (this.pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
