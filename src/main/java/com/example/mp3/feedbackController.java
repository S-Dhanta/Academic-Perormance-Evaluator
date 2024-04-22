package com.example.mp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class feedbackController {
    Stage stage = new Stage();
    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        homeController cont = new homeController();
        fxmlLoader.setController(cont);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        cont.setInfo();
    }

    @FXML
    public void switchToMarks(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marks.fxml"));
        marksController cont = new marksController();
        fxmlLoader.setController(cont);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        cont.setInfo();
    }

    @FXML
    public void switchToAttendance(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("attendance.fxml"));
        attendanceController cont = new attendanceController();
        fxmlLoader.setController(cont);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        cont.setInfo();
    }

    @FXML
    public void switchToFeedback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("feedback.fxml"));
        feedbackController cont = new feedbackController();
        fxmlLoader.setController(cont);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        cont.setInfo();
    }
    @FXML
    Button logoutButton;
    @FXML
    AnchorPane scenePane;
    @FXML
    public void logoutApp(ActionEvent event)throws IOException{
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
            Stage stage = new Stage();
            application obj = new application();
            obj.start(stage);
        }
    }

    @FXML
    Button feedbackButton;
    @FXML
    TextArea feedbackText;
    @FXML
    Label textLabel;

    int studentID= loginController.studentID;
    public void setInfo() {
        feedbackButton.setStyle("-fx-background-color: #D0DDF2; -fx-background-radius: 0; -fx-text-fill: #2b6897");
    }

    public void submit(ActionEvent event) throws IOException{
        String text = feedbackText.getText();
        if(text.equals("")){
            textLabel.setStyle("-fx-text-fill: Red;");
            textLabel.setText("*Text field cannot be empty*");
        }
        else {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acadper", "root", "sql4data@Dhanta4Saatvik");
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                statement.execute("insert into feedback values(" + studentID + ",\"" + text + "\");");
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            textLabel.setStyle("-fx-text-fill: black;");
            textLabel.setText("Feedback Submitted.");
        }
    }
}
