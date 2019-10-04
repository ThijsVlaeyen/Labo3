package launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.ShopUI;

import javax.swing.*;

public class ShopLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ShopUI shopUI = new ShopUI();
        shopUI.start(primaryStage);
    }
}
