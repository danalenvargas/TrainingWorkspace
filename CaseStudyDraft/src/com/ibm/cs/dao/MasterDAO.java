package com.ibm.cs.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Base abstract DAO class inherited by all concrete DAO classes
 * 
 * @author Dan Alejandro A. Vargas
 * @see UserDAO
 * @see CategoryDAO
 * @see ProductDAO
 * @see BatchDAO
 * @see ItemDAO
 */
public abstract class MasterDAO {
	// private static final String URL = "jdbc:mysql://localhost/cs_draft_db?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private static final String USER = "root";
	// private static final String PASSWORD = "admin";
	protected Connection conn;
	private Properties properties = new Properties();

	/**
	 * Default constructor. A connection is opened at DAO construction, but new
	 * connection can also be created using openConnection() method in case it is
	 * closed. <br>
	 * Connections are opened and closed at Service level to allow a single
	 * connection for multiple method calls
	 */
	public MasterDAO() throws Exception{
		this.conn = getConnection();
	}

	/**
	 * Utility method to get a connection to be assigned to the DAO's connection
	 * variable;
	 * 
	 * @return Connection created using the properties on the config file
	 */
	protected Connection getConnection() throws Exception {
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
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return con;
	}

	/**
	 * Assigns an open connection to a DAO's connection variable. <br>
	 * Connections are opened and closed at Service level to allow a single
	 * connection for multiple method calls.
	 */
	public void openConnection() throws Exception {
		this.conn = getConnection();
	}

	/**
	 * Closes a DAO's connection. <br>
	 * Connections are opened and closed at Service level to allow a single
	 * connection for multiple method calls.
	 */
	public void closeConnection() {
		if (this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Utility method to close PreparedStatements and ResultSets after every
	 * use/method.
	 * 
	 * @param rs
	 *            Resultset to be closed
	 * @param pst
	 *            PreparedStatement to be closed
	 */
	protected void closeResources(ResultSet rs, PreparedStatement pst) throws SQLException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * Utility method to close PreparedStatements after every use/method
	 * 
	 * @param pst
	 *            PreparedStatement to be closed
	 */
	protected void closeResources(PreparedStatement pst) throws SQLException {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
}
