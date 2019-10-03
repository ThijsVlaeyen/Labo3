package launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();







        primaryStage.setTitle("labo 3");
        primaryStage.setScene(new Scene(root,500,800));
        primaryStage.show();
    }
}
