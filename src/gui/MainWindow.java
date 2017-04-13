package gui;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.DatabaseManager;

public class MainWindow extends Application
{
	static String username = "csc570-04";
	static String password = "Password04";
	static String dbLocation = "jdbc:mysql://db.cs.ship.edu:3306/csc570-04";

	ObservableList<String> tableNames;

    @Override
    public void start(Stage primaryStage) throws SQLException
    {
    	DatabaseManager dbManager = new DatabaseManager(username,password,dbLocation);

    	tableNames = FXCollections.observableArrayList(dbManager.returnTableNames());

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
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
        tableSelectBox.getChildren().add(new Label("Select Table: "));
        tableSelectBox.getChildren().add(tableSelector);

        TableView<String[]> recordTable = new TableView<String[]>();


        VBox root = new VBox();
        root.getChildren().add(tableSelectBox);
        root.getChildren().add(btn);
        root.getChildren().add(recordTable);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Database Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}