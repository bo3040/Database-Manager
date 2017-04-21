package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;
import main.Table;

/**
 * An interface for behaviors used when submitting the fields.
 * @author Brad
 *
 */
public interface SubmitBehavior
{
	/**
	 * Returns a string that has a query to be ran.
	 * @param selectedTable the currently selected table
	 * @param values the current values of the fields
	 * @param record the record that is currently open
	 * @return A string with a query to run.
	 */
	public String execute(Table selectedTable, ArrayList<String> values, List<String> record);
}
