package gui;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import main.Table;

public class AddBehavior implements SubmitBehavior
{
	@Override
	public void execute(Table selectedTable, ArrayList<String> values)
	{
		System.out.print("Insert info into database.");

	}
}
