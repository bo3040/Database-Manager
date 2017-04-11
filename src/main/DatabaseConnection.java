package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A singleton to hold the database access.
 * @author bo3040
 *
 */
public class DatabaseConnection
{
	private static DatabaseConnection instance = null;
	private static Connection dbConn;
	protected DatabaseConnection()
	{

	}

	/**
	 * Returns the instance of this singleton.
	 * @return instance - the instance of this singleton
	 */
	public static DatabaseConnection getInstance()
	{
		if(instance == null)
		{
			instance = new DatabaseConnection();
		}
		return instance;
	}

	/**
	 * Sets up the initial connection to the database.
	 * @param dbLocation - The database address
	 * @param username - The username for the database
	 * @param password - The password for the database
	 * @throws SQLException - Can throw a SQL exception if the connection fails.
	 */
	public void makeConnection(String dbLocation,String username,String password) throws SQLException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		dbConn = DriverManager.getConnection(dbLocation, username, password);
	}

	/**
	 * Returns the connection that the singleton holds.
	 * @return dbConn - The database connection
	 */
	public Connection getConnection()
	{
		return dbConn;
	}
}
