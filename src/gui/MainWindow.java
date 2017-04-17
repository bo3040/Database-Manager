package gui;

import java.sql.SQLException;

import javafx.application.Application;
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
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.DatabaseManager;
import main.Table;

public class MainWindow extends Application
{
	static String username = "csc570-04";
	static String password = "Password04";
	static String dbLocation = "jdbc:mysql://db.cs.ship.edu:3306/csc570-04";

	ObservableList<String> tableNames;
	ObservableList<Table> tables;
	TableView<String> recordTable;

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

        		      }
        		});

        tableSelectBox.getChildren().add(new Label("Select Table: "));
        tableSelectBox.getChildren().add(tableSelector);

        recordTable = new TableView<String>();

        VBox root = new VBox();
        root.getChildren().add(tableSelectBox);
        root.getChildren().add(btn);
        root.getChildren().add(recordTable);

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
    	recordTable.getColumns().clear();
    	Table selectedTable = tables.get(tableNumber);
    	System.out.println(tables.get(tableNumber).tableName);
    	ObservableList<String> columnNames = FXCollections.observableArrayList(selectedTable.getColumnNames());
    	for(String colName:columnNames)
    	{
    		recordTable.getColumns().add(new TableColumn(colName));
    	}
	}

	public static void main(String[] args)
    {
        launch(args);
    }
}