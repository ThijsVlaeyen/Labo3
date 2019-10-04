package ui;
import domain.db.DbException;
import domain.db.ProductsInDb;
import domain.model.PartyEquipment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class AddProduct implements EventHandler<ActionEvent> {
    private Double price;
    private String name;
    private Stage primaryStage;
    private Scene originalScene;
    private ProductsInDb db;
    private TextField nameTextField = new TextField("");
    private TextField priceTextField = new TextField("");
    private Label errorLabel = new Label();
    public AddProduct(Stage primarystage, Scene originalScene,ProductsInDb db){
        this.primaryStage = primarystage;
        this.originalScene = originalScene;
        this.db = db;
    }


    private void wrongInput(String message){
        this.price = null;
        this.name = null;
        this.nameTextField.clear();
        this.priceTextField.clear();
        this.nameTextField.setStyle("-fx-border-color: #F00");
        this.priceTextField.setStyle("-fx-border-color: #F00");
        this.errorLabel.setText(message);
    }

    @Override
    public void handle(ActionEvent event) {
        this.priceTextField.setText("");
        this.nameTextField.setText("");
        GridPane root = new GridPane();
        Scene scene = new Scene(root,originalScene.getWidth(),originalScene.getHeight());
        Button add = new Button("add product");
        Button cancel  = new Button("cancel");
        root.add(add,1,3);
        nameTextField.setPromptText("name of the product");
        priceTextField.setPromptText("price of the product");
        root.add(errorLabel,1,4);
        root.add(nameTextField,1,1);
        root.add(priceTextField,1,2);
        nameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                name = newValue;
            }
        });
        priceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    price = Double.parseDouble(newValue);
                }catch (NumberFormatException e){
                    priceTextField.clear();
                }

            }
        });
        add.setOnMouseClicked(event1 -> {
            try {
                db.add(new PartyEquipment(this.price, this.name));
                cancel();
            }catch (DbException | IllegalArgumentException e){
                wrongInput(e.getMessage());
            }
        });
        cancel.setOnMouseClicked(event1 -> {
            cancel();
        });
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("add Product");
        this.primaryStage.show();
    }

    public void cancel(){
        this.primaryStage.setScene(this.originalScene);
    }
}
