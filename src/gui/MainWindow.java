package gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Record;

public class MainWindow extends Application
{
	ObservableList<String[]> records;
    @Override
    public void start(Stage primaryStage)
    {
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

        TableView<Record> recordTable = new TableView<Record>();


        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(recordTable);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args)
 {
        launch(args);
    }
}