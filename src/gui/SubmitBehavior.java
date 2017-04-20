package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;
import main.Table;

public interface SubmitBehavior
{
	public String execute(Table selectedTable, ArrayList<String> values, List<String> record);
}
