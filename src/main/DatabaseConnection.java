package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
	private static DatabaseConnection instance = null;
	private static Connection dbConn;
	protected DatabaseConnection()
	{

	}
	public static DatabaseConnection getInstance()
	{
		if(instance == null)
		{
			instance = new DatabaseConnection();
		}
		return instance;
	}

	public void makeConnection(String dbLocation,String username,String password) throws SQLException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		dbConn = DriverManager.getConnection(dbLocation, username, password);
	}

	public Connection getConnection()
	{
		return dbConn;
	}
}
