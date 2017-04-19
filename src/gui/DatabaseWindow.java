package gui;

import java.util.ArrayList;
import java.util.List;

import main.Fields;
import main.Table;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The base class for a database window
 * @author Brad
 */
public class DatabaseWindow extends Stage 
{
	Table selectedTable;
	List<String> record;
	GridPane grid;
	/**
	 * Starts constructing a window for editing.
	 * @param selectedTable
	 * @param record
	 */
	public DatabaseWindow(Table selectedTable, List<String> record)
	{
		this.selectedTable = selectedTable;
		this.record = record;
		setTitle("Edit");
		init();
		fillFields();
	}

	/**
	 * Starts constructing a window for adding.
	 * @param selectedTable
	 */
	public DatabaseWindow(Table selectedTable)
	{
		this.selectedTable = selectedTable;
		setTitle("Add");
		init();
	}
	
	/**
	 * Initialization that happens for both types of window.
	 */
	private void init()
	{
		grid = new GridPane();
		Scene scene = new Scene(grid, 300, 250);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 5, 5, 15));
		setScene(scene);
		initModality(Modality.APPLICATION_MODAL);
		buildFields();
		
		
		show();
	}
	
	/**
	 * Builds the fields based on the tables columns.
	 */
	private void buildFields()
	{
		
		ArrayList<Fields> fields = selectedTable.getColumns();
		Label tableName = new Label("Accessing Table: "+selectedTable.tableName);
		grid.add(tableName, 0, 0,2,1);
		int row = 1;
		for(Fields field : fields)
		{
			HBox fieldBox = new HBox();
			Label fieldLabel = new Label(field.field);
			Control fieldControl = getFieldType(field);
			grid.add(fieldLabel,0,row);
			grid.add(fieldControl,1,row,2,1);
			row++;
//			fieldBox.getChildren().add(fieldLabel);
//			fieldBox.getChildren().add(fieldText);
//			grid.getChildren().add(fieldBox);
		}
	}
	
	/**
	 * Fills the fields with the initial values for editing a record.
	 */
	private void fillFields()
	{
		int row =1;
		for(int i =0; i <record.size();i++)
		{
			Control control = (Control) getNodeFromGridPane(grid,1,row);
			if(control.getClass().equals(TextField.class))
			{
				((TextInputControl) control).setText(record.get(i));
			}
			if(control.getClass().equals(ComboBox.class))
			{
				//TODO populate with foreign keys.
			}
			row++;
		}
	}

	/**
	 * Finds what type of field should be added based on the field definitions.
	 * @param field - The field to add a row for.
	 * @return the Control instance that corresponds to the field.
	 */
	private Control getFieldType(Fields field)
	{
		Control control = null; 
		if(field.key.equals("MUL"))
		{
			control = new ComboBox<String>();
		}else if(field.extra.equals("auto_increment"))
		{
			control = new TextField();
			((TextInputControl) control).setText("Auto Incremented");
			control.setDisable(true);
		}else
		{
			control = new TextField();
		}
		control.minWidth(100);
		control.autosize();
		return control;
	}
	
	/**
	 * Gets the node at the given location in the given grid.
	 * @param gridPane - The GridPane to look through
	 * @param col - The column in the GridPane to get.
	 * @param row - The row in the GridPane to get.
	 * @return
	 */
	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            return node;
	        }
	    }
	    return null;
	}
}
