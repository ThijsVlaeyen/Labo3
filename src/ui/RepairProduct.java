package ui;

import domain.db.DbException;
import domain.db.ProductsInDb;
import domain.model.PartyEquipment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RepairProduct implements EventHandler<ActionEvent> {

    private Stage primaryStage;
    private Scene originalScene;
    private ProductsInDb db;
    private Label errorLabel;
    private TextField idTextField = new TextField();
    private int id;

    public RepairProduct(Stage primaryStage, Scene scene, ProductsInDb db) {
        this.primaryStage = primaryStage;
        this.originalScene = scene;
        this.db = db;
    }

    @Override
    public void handle(ActionEvent event) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root,this.originalScene.getWidth(),this.originalScene.getHeight());
        Button repair = new Button("Repair");
        Button cancel = new Button("cancel");
        errorLabel = new Label();
        root.add(idTextField,1,1);
        root.add(repair,1,3);
        root.add(cancel,2,3);
        root.add(errorLabel,1,2);
        this.idTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    id = Integer.parseInt(newValue);
                }catch (NumberFormatException e){
                    wrongInput(e.getMessage());
                }
            }
        });

        repair.setOnMouseClicked(event1 -> {
            try{
                PartyEquipment p = db.get(id);
                p.repair();
                cancel();
            }catch (DbException | IllegalStateException e){
                wrongInput(e.getMessage());
            }
        });
        cancel.setOnMouseClicked(event1 -> {
            cancel();
        });
        primaryStage.setTitle("Repair product");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void cancel(){
        this.primaryStage.setScene(this.originalScene);
    }

    private void wrongInput(String message) {
        this.idTextField.setStyle("-fx-border-color: #F00");
        this.idTextField.clear();
        this.errorLabel.setText(message);
    }
}
