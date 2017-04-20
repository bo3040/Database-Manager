package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import main.FieldDetails;
import main.Table;

public class AddBehavior implements SubmitBehavior
{
	@Override
	public String execute(Table selectedTable, ArrayList<String> values,List<String>record)
	{
		String insertQuery ="INSERT INTO "+selectedTable.tableName+ " ";
		String fieldNames = new String();
		String recordValues = new String();
		ArrayList<FieldDetails> columns = selectedTable.getColumns();
		for(int i =0; i < selectedTable.getColumns().size();i++)
		{
			if(!values.get(i).equals("Auto Incremented"))
			{
				fieldNames += selectedTable.getColumns().get(i).field;
				if(columns.get(i).key.equals("MUL"))
				{
					String[] splitValues = values.get(i).split(" ");
					recordValues += splitValues[1];
				}else
				{
					recordValues += values.get(i);
				}
				if(i != selectedTable.getColumns().size()-1)
				{
					fieldNames += ",";
					recordValues += ",";
				}
			}
		}
		insertQuery += "("+fieldNames+") VALUES ("+recordValues+")";
		return insertQuery;

	}
}
