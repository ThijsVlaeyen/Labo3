package ui;

import domain.db.ProductsInDb;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;

public class ShopUI {
    public static void main(String[] args) {
    }

    public void start(Stage primaryStage){
        ProductsInDb db = new ProductsInDb();
        GridPane root = new GridPane();
        Scene scene = new Scene(root,300,400);
        Button addProduct = new Button("Add product");
        Button removeProduct = new Button("Remove product");
        Button rentProduct = new Button("Rent product");
        Button returnProduct = new Button("Return product");
        Button repairProduct = new Button("Repair product");
        Button availableItems = new Button("Show available products");
        root.add(addProduct,1,1);
        root.add(removeProduct,1,2);
        root.add(rentProduct,1,3);
        root.add(returnProduct,1,4);
        root.add(repairProduct,1,5);
        root.add(availableItems,1,6);
        addProduct.setOnAction(new AddProduct(primaryStage,scene,db));
        removeProduct.setOnAction(new DeleteProduct(primaryStage,scene,db));
        rentProduct.setOnAction(new RentProduct(primaryStage,scene,db));
        returnProduct.setOnAction(new ReturnProduct(primaryStage,scene,db));
        repairProduct.setOnAction(new RepairProduct(primaryStage,scene,db));
        availableItems.setOnAction(new AvailableItems(primaryStage,scene,db));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shop");
        primaryStage.show();
    }
}
