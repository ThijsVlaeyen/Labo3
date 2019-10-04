package ui;

import domain.db.ProductsInDb;
import domain.model.PartyEquipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class AvailableItems implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Scene originalScene;
    private ProductsInDb db;
    public AvailableItems(Stage primaryStage, Scene scene, ProductsInDb db) {
        this.primaryStage = primaryStage;
        this.originalScene = scene;
        this.db = db;
    }


    @Override
    public void handle(ActionEvent event) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root,this.originalScene.getWidth(),this.originalScene.getHeight());
        Button cancel = new Button("cancel");
        root.add(cancel,2,1);
        cancel.setOnMouseClicked(event1 -> {
            cancel();
        });
        ObservableList<PartyEquipment> items = FXCollections.observableArrayList(db.getAll());
        ListView<PartyEquipment> itemsList = new ListView<>(items);
        root.add(itemsList,1,1);
        primaryStage.setTitle("Available items");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cancel(){
        this.primaryStage.setScene(this.originalScene);
    }
}
