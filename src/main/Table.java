package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Holds all the information about a table that will be used to build the user interface.
 * @author Brad Olah
 */
public class Table
{
	/**
	 *
	 */
	public String tableName;
	ArrayList<FieldDetails> columns = new ArrayList<FieldDetails>();
	ArrayList<Constraint> constraints = new ArrayList<Constraint>();
	ObservableList<List<String>> records = FXCollections.observableArrayList();

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

	/**
	 * Finds and stores the field information from the given table.
	 * @param tableColumnInfo
	 * @throws SQLException
	 */
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
			FieldDetails column = new FieldDetails(field,type,canBeNull,key,defaultValue,extra);
			columns.add(column);
			System.out.println(column.toString());
        }
	}

	/**
	 * Finds and stores the constraints from the given table.
	 * @param tableName
	 * @throws SQLException
	 */
	private void extractConstraints(String tableName) throws SQLException
	{
		String selectTableConstraints = "SELECT i.TABLE_NAME, i.CONSTRAINT_TYPE, i.CONSTRAINT_NAME, k.REFERENCED_TABLE_NAME, k.REFERENCED_COLUMN_NAME,k.COLUMN_NAME "
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
			String columnName = rs.getString(6);
			Constraint constraint = new Constraint(constraintTableName,constraintType,constriantName,referencedTableName,referencedColumnName,columnName);
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
			List<String> record = new ArrayList<String>();
			for(int i = 0; i<columns.size();i++)
			{
				record.add(rs.getString(columns.get(i).field));
				System.out.print(columns.get(i).field+": "+record.get(i));

			}
			records.add(record);
			System.out.println("");
		}
		System.out.println("");
	}

	/**
	 * @return An ArrayList of column names.
	 */
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columnNames = new ArrayList<String>();
		for(FieldDetails column: columns)
		{
			columnNames.add(column.field);
		}
		return columnNames;
	}

	/**
	 * @return the column information.
	 */
	public ArrayList<FieldDetails> getColumns()
	{
		return columns;
	}

	/**
	 * @return An ArrayList of records.
	 */
	public ObservableList<List<String>> getRecords()
	{
		return records;
	}

	/**
	 * @param row the row in the display table the record is in.
	 * @return The record that is in the column.
	 */
	public List<String> getRecordByRow(int row)
	{
		return records.get(row);
	}

	/**
	 * Returns the foreign key values for the given field.
	 * @param field - The field to get the foreign keys from.
	 * @return the list of values of the foreign key.
	 */
	public ObservableList<String> getForeginKeyValues(FieldDetails field)
	{
		ObservableList<String> foreignKeys = FXCollections.observableArrayList();
		for(Constraint constraint : constraints)
		{
			if(constraint.columnName.equals(field.field))
			{
				System.out.println("Getting foreign key values");


				try
				{
					String selectForeignKeyDetails = "SELECT "+constraint.referencedColumnName+ " FROM "+constraint.referencedTableName;
					PreparedStatement stmt;
					stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectForeignKeyDetails);
					//The result set holding the records for the table.
					ResultSet rs = stmt.executeQuery();

					while(rs.next())
					{
						foreignKeys.add(constraint.referencedTableName+": "+rs.getString(1));
						System.out.println(constraint.referencedTableName+": "+rs.getString(1));
					}

				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}
		return foreignKeys;
	}
}
