package ui;

import domain.db.DbException;
import domain.db.ProductsInDb;
import domain.model.PartyEquipment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReturnProduct implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Scene originalScene;
    private ProductsInDb db;
    private Label errorLabel;
    private TextField idTextField = new TextField();
    private int id;

    public ReturnProduct(Stage primaryStage, Scene scene, ProductsInDb db) {
        this.primaryStage = primaryStage;
        this.originalScene = scene;
        this.db = db;
    }

    @Override
    public void handle(ActionEvent event) {
        this.idTextField.setText("");
        GridPane root = new GridPane();
        Scene scene = new Scene(root,this.originalScene.getWidth(),this.originalScene.getHeight());
        Button returnEquipment = new Button("Return");
        Button cancel = new Button("cancel");
        CheckBox damaged = new CheckBox("Damaged");
        errorLabel = new Label();
        this.idTextField.setText("");
        root.add(errorLabel,1,2);
        root.add(idTextField,1,1);
        root.add(damaged,2,1);
        root.add(returnEquipment,1,3);
        root.add(cancel,2,3);
        this.idTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    id = Integer.parseInt(newValue);
                }catch (NumberFormatException e){
                    wrongInput("give a valid number");
                }
            }
        });
        returnEquipment.setOnMouseClicked(event1 -> {
            try{
                PartyEquipment p = db.get(id);
                p.returnEquipment(damaged.isSelected());
                cancel();
            }catch (DbException | IllegalStateException e){
                wrongInput(e.getMessage());
            }
        });
        cancel.setOnMouseClicked(event1 -> {
            cancel();
        });

        this.primaryStage.setTitle("Rent Product");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    private void wrongInput(String message) {
        this.idTextField.setStyle("-fx-border-color: #F00");
        this.idTextField.clear();
        this.errorLabel.setText(message);
    }

    public void cancel(){
        this.primaryStage.setScene(this.originalScene);
    }
}
