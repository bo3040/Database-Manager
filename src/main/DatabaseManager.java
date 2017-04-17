package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A Class that manages the given Database.
 * @author bo3040
 *
 */
public class DatabaseManager
{
	String username;
	String password;
	Connection dbConn;
	ArrayList<String> tableNames = new ArrayList<String>();
	ArrayList<Table> tables = new ArrayList<Table>();

	/**
	 * Initializes the DatabaseConnection singleton and sets its connection.
	 * Builds the table objects from the table definitions.
	 * @param username - The username for the database.
	 * @param password	- The password for the database.
	 * @param dbLocation	- The location of the database.
	 * @throws SQLException - Can throw a SQL exception if the connection fails.
	 */
	public DatabaseManager(String username, String password,String dbLocation) throws SQLException
	{
		this.username = username;
		this.password = password;

		DatabaseConnection.getInstance().makeConnection(dbLocation, username, password);
		dbConn = DatabaseConnection.getInstance().getConnection();
		getTableNames();
		buildTableObjects();

	}

	/**
	 * Uses the databases information schema to find the names of all of the tables in the database.
	 * @throws SQLException - Can throw if sql fails.
	 */
	public void getTableNames() throws SQLException
	{
		String selectTables ="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'";
		PreparedStatement stmt = dbConn.prepareStatement(selectTables);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			tableNames.add(rs.getString(1));
		}

	}

	/**
	 * Builds the table objects.
	 * The table objects will construct their details based on the table definitions.
	 * @throws SQLException - Can throw if sql fails.
	 */
	public void buildTableObjects() throws SQLException
	{
		for(String table : tableNames)
		{
			Table tableObject = new Table(table);
			tables.add(tableObject);
		}
	}

	/**
	 * @return an ArrayList of the table names.
	 */
	public ArrayList<String> returnTableNames()
	{
		return tableNames;
	}

	/**
	 * @return an ArrayList of tables.
	 */
	public ArrayList<Table> returnTables()
	{
		return tables;
	}
}

