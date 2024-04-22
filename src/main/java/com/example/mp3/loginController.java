package com.example.mp3;

import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class loginController {
    static int studentID;
    boolean once=false;
    Stage stage;
    @FXML
    Label wrongIdLabel;
    @FXML
    TextField idTextField;
    @FXML
    Button loginButton;
    @FXML
    public void setLoginActive(){
        loginButton.setDisable(false);
        idTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    login(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @FXML
    public void login(KeyEvent event) throws IOException {
        String temp = idTextField.getText();
        int id = Integer.parseInt(temp);
        studentID=id;
        boolean once=false;
        List<Integer> studentList = new ArrayList<>();
        if(once==false){
            once=true;
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acadper", "root", "sql4data@Dhanta4Saatvik");
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery("select * from students");
                while (resultSet.next()) {
                    int data = resultSet.getInt("Roll_no");
                    studentList.add(data);
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(studentList.contains(id)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
            welcomeController cont = new welcomeController();
            fxmlLoader.setController(cont);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
            wrongIdLabel.setVisible(false);
            cont.displayName();
        }
        else{
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.3), e -> wrongIdLabel.setVisible(true)),
                    new KeyFrame(Duration.seconds(0.35), e -> wrongIdLabel.setVisible(false)),
                    new KeyFrame(Duration.seconds(0.4), e -> wrongIdLabel.setVisible(true))
            );
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
    public void loginClick(MouseEvent event) throws IOException {
        String temp = idTextField.getText();
        int id = Integer.parseInt(temp);
        studentID=id;

        List<Integer> studentList = new ArrayList<>();
        if(once==false){
            once=true;
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acadper", "root", "sql4data@Dhanta4Saatvik");
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery("select * from students");
                while (resultSet.next()) {
                    int data = resultSet.getInt("Roll_no");
                    studentList.add(data);
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(studentList.contains(id)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
            welcomeController cont = new welcomeController();
            fxmlLoader.setController(cont);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
            wrongIdLabel.setVisible(false);
            cont.displayName();
        }
        else{
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.3), e -> wrongIdLabel.setVisible(true)),
                    new KeyFrame(Duration.seconds(0.35), e -> wrongIdLabel.setVisible(false)),
                    new KeyFrame(Duration.seconds(0.4), e -> wrongIdLabel.setVisible(true))
            );
            timeline.setCycleCount(1);
            timeline.play();
        }
    }



}
