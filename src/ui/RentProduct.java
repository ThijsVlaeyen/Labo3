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

public class RentProduct implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Scene originalScene;
    private ProductsInDb db;
    private Label errorLabel;
    private TextField idTextField = new TextField();
    private int id;

    public RentProduct(Stage primaryStage, Scene originalScene, ProductsInDb db) {
        this.primaryStage = primaryStage;
        this.originalScene = originalScene;
        this.db = db;
    }

    public void wrongInput(String message){
        this.idTextField.setStyle("-fx-border-color: #F00");
        this.idTextField.clear();
        this.errorLabel.setText(message);
    }

    @Override
    public void handle(ActionEvent event) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root,originalScene.getWidth(),originalScene.getHeight());
        idTextField= new TextField();
        this.idTextField.setText("");
        Button rent = new Button("rent");
        Button cancel = new Button("cancel");
        errorLabel = new Label();
        idTextField.setPromptText("id of the product you want to rent");
        root.add(idTextField,1,1);
        root.add(rent,1,3);
        root.add(cancel,2,3);
        root.add(errorLabel,1,2);
        idTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    id = Integer.parseInt(newValue);
                }catch (NumberFormatException e){
                    wrongInput("put in a valid number");
                }
            }
        });
        rent.setOnMouseClicked(event1 -> {
            try{
                PartyEquipment p = db.get(id);
                p.loan();
                cancel();
            }catch (DbException | IllegalStateException e){
                wrongInput(e.getMessage());
            }
        });
        cancel.setOnMouseClicked(event1 -> {
            cancel();
        });
        primaryStage.setTitle("delete product");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void cancel(){
        this.primaryStage.setScene(this.originalScene);
    }
}
