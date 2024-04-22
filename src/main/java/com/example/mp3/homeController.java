package com.example.mp3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.AccessFlag;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class homeController {
    Stage stage = new Stage();
    @FXML
    Button homeButton;

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

    int id= loginController.studentID;
    @FXML
    Label nameLabel = new Label("");
    @FXML
    Label rollLabel = new Label("");
    @FXML
    Label fnameLabel = new Label("");
    @FXML
    Label genderLabel = new Label("");
    @FXML
    Label ageLabel = new Label("");
    @FXML
    Label addressLabel = new Label("");
    @FXML
    Label bgLabel = new Label("");
    @FXML
    Label sectionLabel = new Label("");
    @FXML
    Label courseLabel = new Label("");
    @FXML
    Label streamLabel = new Label("");
    public void setInfo(){
        String name = "";
        String fname="";
        String gender="";
        int age=0;
        String address="";
        String bloodG="";
        String section="";
        String course="";
        String stream="";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acadper", "root", "sql4data@Dhanta4Saatvik");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("select * from students where Roll_No=" + id);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                fname = resultSet.getString("f_name");
                gender = resultSet.getString("gender");
                age = resultSet.getInt("age");
                address = resultSet.getString("address");
                bloodG = resultSet.getString("blood_g");
                section = resultSet.getString("section");
                course = resultSet.getString("course");
                stream = resultSet.getString("stream");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        homeButton.setStyle("-fx-background-color: #D0DDF2; -fx-background-radius: 0; -fx-text-fill: #2b6897");
        nameLabel.setText("Name: " + name);
        rollLabel.setText("Roll No: " + id);
        fnameLabel.setText("Father's Name: " + fname);
        genderLabel.setText("Gender: " + gender);
        ageLabel.setText("Age: " + age);
        addressLabel.setText("Address: " + address);
        bgLabel.setText("Blood Group: " + bloodG);
        sectionLabel.setText("Section: " + section);
        courseLabel.setText("Course: " + course);
        streamLabel.setText("Stream: " + stream);
    }


}
