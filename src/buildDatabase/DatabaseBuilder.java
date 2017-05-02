package buildDatabase;

import java.sql.Connection;
import java.sql.SQLException;

import main.DatabaseConnection;

/**
 * Builds the database
 * @author Brad Olah
 */
public class DatabaseBuilder
{

	public static void main(String[] args) throws SQLException
	{
		String username = "csc570-04";
		String password = "Password04";
		String dbLocation = "jdbc:mysql://db.cs.ship.edu:3306/csc570-04";

		DatabaseConnection.getInstance().makeConnection(dbLocation, username, password);
		Connection m_dbConn = DatabaseConnection.getInstance().getConnection();
		CreateTables.execute(m_dbConn);
		PopulateTables.execute(m_dbConn);

	}
}
