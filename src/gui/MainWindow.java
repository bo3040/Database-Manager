package gui;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.DatabaseManager;
import main.Table;

/**
 * A window for selecting the database to work based on and that will open
 * prompts for Add, Edit, and deleting of Records. Also supports executing a
 * custom sql query.
 *
 * @author Brad Olah
 *
 */
public class MainWindow extends Application
{
	static String username = "csc570-04";
	static String password = "Password04";
	static String dbLocation = "jdbc:mysql://db.cs.ship.edu:3306/csc570-04";

	ObservableList<String> tableNames;
	ObservableList<Table> tables;
	TableView<List<String>> recordTable;
	Table currentTable;
	DatabaseManager dbManager;

	/**
	 * (non-Javadoc)
	 *
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws SQLException
	{
		dbManager = new DatabaseManager(username, password,dbLocation);

		tableNames = FXCollections.observableArrayList(dbManager.returnTableNames());
		tables = FXCollections.observableArrayList(dbManager.returnTables());

		Button btn = new Button();
		btn.setText("Add Record");
		btn.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				@SuppressWarnings("unused")//This addWindow displays a window on its own.
				final Stage addWindow = new DatabaseWindow(currentTable);
			}
		});

		HBox tableSelectBox = new HBox();

		ChoiceBox<String> tableSelector = new ChoiceBox<String>(tableNames);
		// A listener for when the tableSelector has a value selected.
		tableSelector.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
				{
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number number, Number number2)
					{
						loadTable((Integer) number2);
						btn.setDisable(false);
						recordTable.setDisable(false);
						currentTable = tables.get((Integer) number2);
					}
				});

		tableSelectBox.getChildren().add(new Label("Select Table: "));
		tableSelectBox.getChildren().add(tableSelector);

		recordTable = new TableView<List<String>>();

		VBox customQueryBox = new VBox();

		TextArea textBox = new TextArea();
		Button submitQueryButton = new Button("Submit Query");
		submitQueryButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				textBox.setText(dbManager.runCustomQuery(textBox.getText()));
			}
		});
		customQueryBox.getChildren().add(new Label("Custom Query"));
		customQueryBox.getChildren().add(textBox);
		customQueryBox.getChildren().add(submitQueryButton);
		VBox root = new VBox();


		root.getChildren().add(tableSelectBox);
		root.getChildren().add(btn);
		root.getChildren().add(recordTable);
		root.getChildren().add(new Separator());
		root.getChildren().add(customQueryBox);
		if (tableSelector.getSelectionModel().isEmpty())
		{
			btn.setDisable(true);
			recordTable.setDisable(true);
		}
		Scene scene = new Scene(root, 600, 500);
		primaryStage.setTitle("Database Manager");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Load the selected table.
	 *
	 * @param tableNumber
	 */
	protected void loadTable(Integer tableNumber)
	{
		Table selectedTable = tables.get(tableNumber);
		loadColumnDetails(selectedTable);
		loadRecords(selectedTable);
	}

	/**
	 * Loads the column names into the table for display.
	 *
	 * @param selectedTable
	 */
	private void loadColumnDetails(Table selectedTable)
	{
		recordTable.getColumns().clear();
		System.out.println(selectedTable.tableName);
		ObservableList<String> columnNames = FXCollections.observableArrayList(selectedTable.getColumnNames());
		TableColumn editHeader = new TableColumn("Edit");
		TableColumn deleteHeader = new TableColumn("Delete");
		recordTable.getColumns().add(editHeader);
		recordTable.getColumns().add(deleteHeader);
		editHeader.setSortable(false);
		deleteHeader.setSortable(false);

		editHeader.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
		deleteHeader.setCellValueFactory(new PropertyValueFactory<>("DELETE"));

		editHeader.setCellFactory(editButtonCellFactory());
		deleteHeader.setCellFactory(deleteButtonCellFactory());

		for (int i = 0; i < columnNames.size(); i++)
		{
			final int current = i;
			TableColumn column = new TableColumn(columnNames.get(i));
			recordTable.getColumns().add(column);
			// Sets up the columns to populate with strings when they are given.
			column.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>()
			{
				@Override
				public ObservableValue<String> call(
						CellDataFeatures<List<String>, String> data)
				{
					return new ReadOnlyStringWrapper(data.getValue().get(
							current));
				}
			});
		}
	}

	/**
	 * Makes a cell factory that adds an edit button to the cells.
	 * @param button - The name of the button.
	 * @param window - The window to open.
	 * @return a cellFactory that adds an edit button.
	 */
	private Callback<TableColumn<String, String>, TableCell<String, String>> editButtonCellFactory()
	{
		Callback<TableColumn<String, String>, TableCell<String, String>> cellFactory = new Callback<TableColumn<String, String>, TableCell<String, String>>()
		{
			@Override
			public TableCell<String, String> call(final TableColumn<String, String> param)
			{
				final TableCell<String, String> cell = new TableCell<String, String>()
				{

					final Button btn = new Button("Edit");

					@Override
					public void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						if (empty)
						{
							setGraphic(null);
							setText(null);
						} else
						{
							btn.setOnAction((ActionEvent event) -> {
								int selectedIndex = getTableRow().getIndex();

								List<String> record = recordTable.getItems().get(selectedIndex);
								final Stage editWindow = new DatabaseWindow(currentTable,record);
								System.out.print(record);
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		return cellFactory;
	}

	/**
	 * Makes a cell factory that adds a delete button to the cells.
	 * @param button - The name of the button.
	 * @param window - The window to open.
	 * @return a cellFactory that adds a delete button.
	 */
	private Callback<TableColumn<String, String>, TableCell<String, String>> deleteButtonCellFactory()
	{
		Callback<TableColumn<String, String>, TableCell<String, String>> cellFactory = new Callback<TableColumn<String, String>, TableCell<String, String>>()
		{
			@Override
			public TableCell<String, String> call(final TableColumn<String, String> param)
			{
				final TableCell<String, String> cell = new TableCell<String, String>()
				{

					final Button btn = new Button("Delete");

					@Override
					public void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						if (empty)
						{
							setGraphic(null);
							setText(null);
						} else
						{
							btn.setOnAction((ActionEvent event) -> {
								int selectedIndex = getTableRow().getIndex();

								List<String> record = recordTable.getItems().get(selectedIndex);
								deleteAlert(record);
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		return cellFactory;
	}

	/**
	 * A popup window asking for confirmation for a delete.
	 * @param record
	 * @throws SQLException
	 */
	private void deleteAlert(List<String> record)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Deletion");
		alert.setHeaderText("Are you sure you want to delete this record?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			dbManager.delete(currentTable,record);
		} else
		{
			alert.close();
		}
	}

	/**
	 * Loads the table records into the table for display.
	 *
	 * @param selectedTable
	 */
	private void loadRecords(Table selectedTable)
	{
		recordTable.getItems().clear();
		ObservableList<List<String>> records = FXCollections.observableArrayList(selectedTable.getRecords());
		recordTable.setItems(records);
	}


	/**
	 * Starts the application
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}