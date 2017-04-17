package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Holds all the information about a table that will be used to build the user interface.
 * @author Brad Olah
 */
public class Table
{
	public String tableName;
	ArrayList<Fields> columns = new ArrayList<Fields>();
	ArrayList<Constraint> constraints = new ArrayList<Constraint>();
	ArrayList<String[]> records = new ArrayList<String[]>();

	/**
	 * Constructs a new table object based on the given table name.
	 * @param tableName - The name from the Database of the table the object is based on.
	 * @throws SQLException - Can throw if database calls fail
	 */
	public Table(String tableName) throws SQLException
	{
		this.tableName = tableName;
		buildFields(tableName);
		extractConstraints(tableName);
		extractRecords(tableName);

	}

	/**
	 * Builds the field objects for the fields in the given table.
	 * @param tableName - The name of the table to build the fields from.
	 * @throws SQLException - Can throw if database calls fail
	 */
	private void buildFields(String tableName) throws SQLException
	{
		String selectTableDetails = "SHOW COLUMNS FROM "+tableName;
		PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectTableDetails);
		ResultSet rs = stmt.executeQuery();
		extractColumnInfo(rs);
	}

	private void extractColumnInfo(ResultSet tableColumnInfo) throws SQLException
	{
		System.err.println(tableName);
		while (tableColumnInfo.next())
        {
			String field= tableColumnInfo.getString("Field");
			String type = tableColumnInfo.getString("Type");
			String canBeNull = tableColumnInfo.getString("Null");
			String key = tableColumnInfo.getString("Key");
			String defaultValue = tableColumnInfo.getString("Default");
			String extra = tableColumnInfo.getString("Extra");
			Fields column = new Fields(field,type,canBeNull,key,defaultValue,extra);
			columns.add(column);
			System.out.println(column.toString());
        }
	}

	private void extractConstraints(String tableName) throws SQLException
	{
		String selectTableConstraints = "SELECT i.TABLE_NAME, i.CONSTRAINT_TYPE, i.CONSTRAINT_NAME, k.REFERENCED_TABLE_NAME, k.REFERENCED_COLUMN_NAME "
				+"FROM information_schema.TABLE_CONSTRAINTS i "
				+"LEFT JOIN information_schema.KEY_COLUMN_USAGE k ON i.CONSTRAINT_NAME = k.CONSTRAINT_NAME "
				+"WHERE i.CONSTRAINT_TYPE = 'FOREIGN KEY' "
				+"AND i.TABLE_SCHEMA = DATABASE() "
				+"AND i.TABLE_NAME = ?";

		PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectTableConstraints);
		//The result set holding the foreign key information for the table.
		stmt.setString(1, tableName);
		ResultSet rs = stmt.executeQuery();
		System.err.println(tableName+" Constraints");
		while(rs.next())
		{
			String constraintTableName = rs.getString(1);
			String constraintType = rs.getString(2);
			String constriantName = rs.getString(3);
			String referencedTableName = rs.getString(4);
			String referencedColumnName = rs.getString(5);
			Constraint constraint = new Constraint(constraintTableName,constraintType,constriantName,referencedTableName,referencedColumnName);
			constraints.add(constraint);
			System.out.println(constraint.toString());
		}

	}

	/**
	 * Extracts the records from the table and stores them in a string array.
	 * @param tableName - The name of the table to get the records from.
	 * @throws SQLException - Can throw if database calls fail
	 */
	private void extractRecords(String tableName) throws SQLException
	{
		String selectTableDetails = "SELECT * FROM "+tableName;
		PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectTableDetails);
		//The result set holding the records for the table.
		ResultSet rs = stmt.executeQuery();
		System.err.println(tableName+" Records");
		while(rs.next())
		{
			String[] record = new String[columns.size()];
			for(int i = 0; i<columns.size();i++)
			{
				record[i] = rs.getString(columns.get(i).field)+" ";
				System.out.print(columns.get(i).field+": "+record[i]);
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columnNames = new ArrayList<String>();
		for(Fields column: columns)
		{
			columnNames.add(column.field);
		}
		return columnNames;
	}
}
