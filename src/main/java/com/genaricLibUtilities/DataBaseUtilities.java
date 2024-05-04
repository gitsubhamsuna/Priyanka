package com.genaricLibUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

//import com.mysql.cj.jdbc.Driver;

public class DataBaseUtilities {
	Driver driver;
	Connection connection;

	/**
	 * This method is to Register the Driver and Connection to database
	 * 
	 * @throws SQLException
	 */
	public void connectToDB() throws SQLException {
		// 1. Register the driver
		driver = new Driver();
		DriverManager.registerDriver(driver);
		// 2. get the connection to database
		connection = DriverManager.getConnection(IpathConstant.DB_URL, IpathConstant.DB_USERNAME,
				IpathConstant.DB_PASSWORD);
	}

	/**
	 * This method is used for create the statement and execute the query
	 * 
	 * @param query
	 * @param colIndex
	 * @param exptData
	 * @return
	 * @throws SQLException
	 */
	public String executeAndgetData(String query, int colIndex, String exptData) throws SQLException {
		// 3 Create the Statement
		Statement st = connection.createStatement();
		// 4.Execute and Update query
		ResultSet result = connection.createStatement().executeQuery(query);
		boolean flag = false;
		while (result.next()) {
			String actual = result.getString(colIndex);
			if (actual.contains(exptData)) {
				flag = true;
				System.out.println("-- Data Varified ---");
				break;
			}
		}
		if (flag == false) {
			System.out.println("-- Expected Data is not Present---");
		}
		return "";
	}

	/**
	 * This method is used for Execute and Update the database
	 * 
	 * @param query
	 * @param updateQuery
	 * @return
	 * @throws SQLException
	 */
	public int executeAndUpdate(String query, String updateQuery) throws SQLException {
		// 3 Create the Statement
		Statement st = connection.createStatement();
		// 4.Execute and Update query
		int result = st.executeUpdate(updateQuery);

		if (result > 1) {
			System.out.println("data added successfully");
		} else {
			System.out.println("data is not added");
		}
		return result;

	}

	/**
	 * this method is used for close the database connection
	 * 
	 * @throws SQLException
	 */
	public void closeDatabase() throws SQLException {
		// 5. close the database
		connection.close();
	}

}
