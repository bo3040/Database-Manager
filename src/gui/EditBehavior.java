package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import main.FieldDetails;
import main.Table;

public class EditBehavior implements SubmitBehavior
{

	@Override
	public String execute(Table selectedTable, ArrayList<String> values,List<String> record)
	{
		String updateQuery ="Update "+selectedTable.tableName+" SET ";

		String changes = new String();

		String recordValues = new String();
		String whereClause = new String();
		ArrayList<FieldDetails> columns = selectedTable.getColumns();
		for(int i =0; i < selectedTable.getColumns().size();i++)
		{
			if(!values.get(i).equals("Auto Incremented"))
			{

				if(columns.get(i).key.equals("MUL"))
				{
					String[] splitValues = values.get(i).split(" ");
					changes += selectedTable.getColumnNames().get(i)+" = "+splitValues[1];
				}else
				{
					changes += selectedTable.getColumnNames().get(i)+" = "+values.get(i);
				}
				whereClause += selectedTable.getColumns().get(i).field +" = "+ record.get(i);
				if(i != selectedTable.getColumns().size()-1)
				{
					changes += ",";
					whereClause +=" AND ";
				}
			}
		}
		updateQuery += changes;
		updateQuery += " WHERE "+whereClause;
		return updateQuery;

	}
}
