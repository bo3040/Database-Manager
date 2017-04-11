package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Table
{
	String tableName;
	ArrayList<Fields> columns = new ArrayList<Fields>();

	public Table(String tableName, ResultSet tableColumnInfo) throws SQLException
	{
		this.tableName = tableName;
		extractColumnInfo(tableColumnInfo);

	}
	private void extractForeignKeys(String tableName)
	{
//		SELECT i.TABLE_NAME, i.CONSTRAINT_TYPE, i.CONSTRAINT_NAME, k.REFERENCED_TABLE_NAME, k.REFERENCED_COLUMN_NAME
//		FROM information_schema.TABLE_CONSTRAINTS i
//		LEFT JOIN information_schema.KEY_COLUMN_USAGE k ON i.CONSTRAINT_NAME = k.CONSTRAINT_NAME
//		WHERE i.CONSTRAINT_TYPE = 'FOREIGN KEY'
//		AND i.TABLE_SCHEMA = DATABASE()
//		AND i.TABLE_NAME = '<yourtable>';
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
}
