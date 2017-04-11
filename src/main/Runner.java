package main;

import java.sql.SQLException;

public class Runner
{
	static String username = "csc570-04";
	static String password = "Password04";
	static String dbLocation = "jdbc:mysql://db.cs.ship.edu:3306/csc570-04";


	public static void main(String[] args) throws SQLException
	{
		DatabaseManager dbManager = new DatabaseManager(username,password,dbLocation);
	}

}
