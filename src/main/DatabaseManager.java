package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager
{
	String username;
	String password;
	Connection dbConn;
	ArrayList<String> tableNames = new ArrayList<String>();
	ArrayList<Table> tables = new ArrayList<Table>();

	public DatabaseManager(String username, String password,String dbLocation) throws SQLException
	{
		this.username = username;
		this.password = password;

		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		dbConn = DriverManager.getConnection(dbLocation, username, password);
		getTableNames();
		buildTableObjects();

	}

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

	public void buildTableObjects() throws SQLException
	{
		for(String table : tableNames)
		{
			String selectTableDetails = "SHOW COLUMNS FROM "+table;
			PreparedStatement stmt = dbConn.prepareStatement(selectTableDetails);
			ResultSet rs = stmt.executeQuery();
			Table tableObject = new Table(table,rs);
			tables.add(tableObject);
		}
	}
}

