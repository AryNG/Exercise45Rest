package com.rest.services;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
public class ConnectionFactory {
	//Singleton Design Pattern
	
		//Properties
	static Properties props = new Properties();
	static InputStream in = null; 
			
	
		//Declare credentials variables
	static String driver="";
	static String urlServer="";
	static String userName="";
	static String password="";

		//2. Instance of the class must be private and static
	private static ConnectionFactory Connection = null;
	private static Connection conn =  null;
	
	public static Connection getConnection() {
		try {
		in = Connection.getClass().getClassLoader().getResourceAsStream("dao.properties");
		props.load(in);
		
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}

		
		if(props!=null) {
			driver = props.getProperty("driver");
			urlServer = props.getProperty("urlServer");
			userName = props.getProperty("userName");
			password = props.getProperty("password");
		}
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(urlServer, userName, password);
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

		//1.Private Constructor
	private void ConnectionFactory() {}
	
	
		//3. Create getInstance method
	public static ConnectionFactory getInstance() {
	if (Connection == null) {
		Connection =  new ConnectionFactory();
		} 
		return Connection;
	}
}
