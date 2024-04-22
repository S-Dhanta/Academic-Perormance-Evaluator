package com.example.mp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class welcomeController {
    Stage stage;

    int id= loginController.studentID;

    @FXML
    Button goToButton;
    @FXML
    Label welcomeLabel = new Label("");

    @FXML
    public void displayName() {
        String name = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acadper", "root", "sql4data@Dhanta4Saatvik");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("select * from students where Roll_No=" + id);
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        welcomeLabel.setText("Welcome " + name);
        goToButton.setOnAction(event -> {
            try {
                goToHome(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            homeController cont = new homeController();
            fxmlLoader.setController(cont);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
            cont.setInfo();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
