package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Communes.fxml"));
        primaryStage.setScene(new Scene(root, 1300, 900));

        primaryStage.setTitle("Corona stats"); // set title for GUI
        primaryStage.getIcons().add(new Image("GUIImage.png")); // set icon for GUI

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//test github.