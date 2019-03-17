package com.courier.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.courier.exception.CourierException;
public class ConnectionUtil {

	public ConnectionUtil() {
		// TODO Auto-generated constructor stub
	}
	public static Connection getConnection() throws CourierException {
		Connection connObj = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connObj = DriverManager.getConnection("jdbc:mysql://localhost:3306/fcs", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CourierException("Error in connection Extablishment : " + e.getMessage());
		}
		if (connObj != null) {
			System.out.println("We got connection");
		} else {
			System.out.println("No Conection");
		}
		return connObj;
	}

}
