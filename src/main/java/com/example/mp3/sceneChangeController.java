package com.example.mp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneChangeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchtoScene(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoScene2(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}