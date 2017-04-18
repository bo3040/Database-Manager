package gui;

import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.DatabaseManager;
import main.Table;

/**
 * A window for selecting the database to work based on and that will open prompts for Add, Edit, and deleting of Records.
 * Also supports executing a custom sql query.
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
	TableView<String[]> recordTable;

    /** (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws SQLException
    {
    	DatabaseManager dbManager = new DatabaseManager(username,password,dbLocation);

    	tableNames = FXCollections.observableArrayList(dbManager.returnTableNames());
    	tables = FXCollections.observableArrayList(dbManager.returnTables());

        Button btn = new Button();
        btn.setText("Add Record");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("Hello World!");
            }
        });

        HBox tableSelectBox = new HBox();

        ChoiceBox<String> tableSelector = new ChoiceBox<String>(tableNames);
        //A listener for when the tableSelector has a value selected.
        tableSelector.getSelectionModel().selectedIndexProperty().addListener
        		(new ChangeListener<Number>() {
        		      @Override
        		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
        		        loadTable((Integer)number2);
        		        btn.setDisable(false);
        		        recordTable.setDisable(false);

        		      }
        		});

        tableSelectBox.getChildren().add(new Label("Select Table: "));
        tableSelectBox.getChildren().add(tableSelector);

        recordTable = new TableView<String[]>();

        VBox root = new VBox();
        root.getChildren().add(tableSelectBox);
        root.getChildren().add(btn);
        root.getChildren().add(recordTable);
        if(tableSelector.getSelectionModel().isEmpty())
        {
        	btn.setDisable(true);
        	recordTable.setDisable(true);
        }
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Database Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * load
     * @param tableNumber
     */
    protected void loadTable(Integer tableNumber)
    {
    	Table selectedTable = tables.get(tableNumber);
    	loadColumnNames(selectedTable);
    	loadRecords(selectedTable);
	}


    /**
     * Loads the column names into the table for display.
     * @param selectedTable
     */
	private void loadColumnNames(Table selectedTable)
	{
		recordTable.getColumns().clear();
    	System.out.println(selectedTable.tableName);
    	ObservableList<String> columnNames = FXCollections.observableArrayList(selectedTable.getColumnNames());
    	for(int i=0; i <columnNames.size();i++)
    	//for(String colName:columnNames)
    	{
    		//TODO http://stackoverflow.com/questions/23008352/inserting-data-to-javafx-tableview-without-intermediate-class

    		final int current = i;
    		TableColumn column = new TableColumn(columnNames.get(i));
    		recordTable.getColumns().add(column);
    		column.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
    		    @Override
    		    public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
    		        return new ReadOnlyStringWrapper(data.getValue().get(current)) ;
    		    }
    		});
    	}
	}

	/**
	 * Loads the table records into the table for display.
	 * @param selectedTable
	 */
	private void loadRecords(Table selectedTable)
	{
		recordTable.getItems().clear();
		ObservableList<List<String>> records = FXCollections.observableArrayList(selectedTable.getRecords());
		System.out.println(records.toString());
		//recordTable.setItems(records);
		System.out.println(selectedTable);
		//recordTable.getItems().addAll(records.get(0));



	}

	public static void main(String[] args)
    {
        launch(args);
    }
}