package launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.ShopUI;

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
