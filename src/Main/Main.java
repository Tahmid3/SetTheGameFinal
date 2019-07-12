package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Set");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //MainMenu panel = new MainMenu();
        //primaryStage.setOnShowing(e -> panel.test());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
