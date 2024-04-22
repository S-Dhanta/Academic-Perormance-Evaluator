package com.example.mp3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class application extends Application {
    Stage primaryStage;
    @FXML
    AnchorPane scenePane;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Academic Performance Evaluator");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event ->{
            event.consume();
            closeHandler();
        });
    }

    public void closeHandler(){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to close the app?");
        ButtonType yesButton = new ButtonType("Exit");
        ButtonType noButton = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Button yesButtonNode = (Button) alert.getDialogPane().lookupButton(yesButton);
//        yesButtonNode.setStyle("-fx-background-color: default;");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            Stage stage2 = (Stage) primaryStage.getScene().getWindow();
            stage2.close();
        }
//        if(alert.showAndWait().get() == ButtonType.OK) {
//            stage2 = (Stage) scenePane.getScene().getWindow();
//            stage2.close();
//        }
    }

    public static void main(String[] args) {
        launch();
    }
}