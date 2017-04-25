package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.FieldDetails;
import main.Table;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The base class for a database window
 * @author Brad Olah
 */
public class DatabaseWindow extends Stage
{
	Table selectedTable;
	List<String> record;
	GridPane grid;
	SubmitBehavior submitBehavior;
	MainWindow mainWindow;
	/**
	 * Starts constructing a window for editing.
	 * @param mainWindow - The main window that created this one. Used to refresh the main window when commands are ran.
	 * @param selectedTable - The table that the record is from.
	 * @param record - The record from the table.
	 */
	public DatabaseWindow(MainWindow mainWindow, Table selectedTable, List<String> record)
	{
		this.mainWindow = mainWindow;
		this.selectedTable = selectedTable;
		this.record = record;
		submitBehavior= new EditBehavior();
		setTitle("Edit");
		init();
		fillFields();

	}

	/**
	 * Starts constructing a window for adding.
	 * @param mainWindow - The window that made this popup.
	 * @param selectedTable - The table that the record is from.
	 */
	public DatabaseWindow(MainWindow mainWindow, Table selectedTable)
	{
		this.mainWindow = mainWindow;
		this.selectedTable = selectedTable;
		submitBehavior= new AddBehavior();
		setTitle("Add");
		init();
	}

	/**
	 * Initialization that happens for both types of window.
	 */
	private void init()
	{
		ScrollPane scrollPane = new ScrollPane();
		Scene scene = new Scene(scrollPane, 450, 250);
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 5, 5, 15));
		setScene(scene);
		initModality(Modality.APPLICATION_MODAL);
		buildFields();
		scrollPane.setContent(grid);
		show();
	}

	/**
	 * Builds the fields based on the tables columns.
	 */
	private void buildFields()
	{

		ArrayList<FieldDetails> fields = selectedTable.getColumns();
		Label tableName = new Label("Accessing Table: "+selectedTable.tableName);
		grid.add(tableName, 0, 0,2,1);
		int row = 1;
		for(FieldDetails field : fields)
		{
			Label fieldLabel = new Label(field.field);
			Control fieldControl = getFieldType(field);
			grid.add(fieldLabel,0,row);
			grid.add(fieldControl,1,row,2,1);
			row++;
		}
		HBox buttons = new HBox();
		Button submit = new Button("Submit");
		Button cancel = new Button("Cancel");
		buttons.getChildren().add(submit);
		if(submitBehavior.getClass().equals(EditBehavior.class))
		{
			Button reset = new Button("ResetValues");
			reset.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					fillFields();
				}
			});
			buttons.getChildren().add(reset);
		}
		buttons.getChildren().add(cancel);

		submit.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				submitValues();
				close();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				close();
			}
		});
		grid.add(buttons,0,row);
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
				int location = 0;
				ComboBox<String> controlBox = (ComboBox<String>) control;
				for(int j =0; j<controlBox.getItems().size();j++)
				{
					String splitFieldValue = controlBox.getItems().get(j).split(" ")[1];
					if(splitFieldValue.equals(record.get(i)))
					{
						location = j;
					}

				}
				controlBox.getSelectionModel().select(location);
			}
			row++;
		}
	}

	/**
	 * Finds what type of field should be added based on the field definitions.
	 * @param field - The field to add a row for.
	 * @return the Control instance that corresponds to the field.
	 */
	private Control getFieldType(FieldDetails field)
	{
		Control control = null;
		if(field.key.equals("MUL"))
		{
			control = makeForeignKeyField(field);
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
	 * Makes a ComboBox with the correct foreign keys.
	 * @param field
	 * @return
	 */
	private ComboBox<String> makeForeignKeyField(FieldDetails field)
	{
		ComboBox<String> foreignKeyComboBox = new ComboBox<String>(selectedTable.getForeginKeyValues(field));

		return foreignKeyComboBox;
	}

	/**
	 * returns the current values entered in the fields.
	 */
	private void submitValues()
	{
		boolean error = false;
		ArrayList<String> values = new ArrayList<String>();
		String notification = new String();
		for(int i=0; i<selectedTable.getColumns().size();i++)
		{
			Node node = getNodeFromGridPane(grid,1,i+1);
			if(node.getClass().equals(TextField.class))
			{
				TextField field = (TextField) node;
				if(field.getText().equals(""))
				{
					notification = notification+"Field "+(i+1)+" has no value\n";
					error = true;
				}else
				{
					values.add(field.getText());
					System.out.println(field.getText());
				}
			}else if(node.getClass().equals(ComboBox.class))
			{
				ComboBox comboBox = (ComboBox)node;
				if(comboBox.getSelectionModel().getSelectedItem() == null)
				{
					notification = notification+"Field "+(i+1)+" has no value\n";
					error = true;
				}else
				{
					values.add((String) comboBox.getSelectionModel().getSelectedItem());
				}
			}
		}
		if(error== false)
		{
			mainWindow.dbManager.runCustomQuery(submitBehavior.execute(selectedTable,values,record));
			//submitBehavior.execute(selectedTable,values,record);
			mainWindow.reloadCurrentTable();
			mainWindow.loadRecords(mainWindow.currentTable);


		}else
		{
			notificationAlert(notification);
		}
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

	/**
	 * A popup window asking for confirmation for a delete.
	 * @param record
	 * @throws SQLException
	 */
	private void notificationAlert(String message)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(message);
		Optional<ButtonType> result = alert.showAndWait();
	}
}
