package gui;

import java.util.ArrayList;
import java.util.List;

import main.FieldDetails;
import main.Table;

/**
 * A Behavior that builds the insert query from given information.
 * @author Brad Olah
 *
 */
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
					recordValues += "'"+splitValues[1]+"'";
				}else
				{
					recordValues += "'"+values.get(i)+"'";
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
