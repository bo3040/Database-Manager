package gui;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import main.Table;

public interface SubmitBehavior
{
	public void execute(Table selectedTable, ArrayList<String> values);
}
