package com.example.mp3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class marksController {
    int studentID= loginController.studentID;
    Stage stage = new Stage();
    @FXML
    TableView<marks> table;
    @FXML
    TableColumn<marks, Integer> id;
    @FXML
    TableColumn<marks, String> name;
    @FXML
    TableColumn<marks, Integer> credits;
    @FXML
    TableColumn<marks, String> marks;
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
    Button marksButton;
    public void setInfo(){
        marksButton.setStyle("-fx-background-color: #D0DDF2; -fx-background-radius: 0; -fx-text-fill: #2b6897");
        List<Integer> subIds = new ArrayList<>();
        List<String> subNames = new ArrayList<>();
        List<Integer> subCredits = new ArrayList<>();
        List<String> subMarks = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/acadper", "root", "sql4data@Dhanta4Saatvik");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet1 = statement.executeQuery("select sub_id from subjects");
            while (resultSet1.next()) {
                int subId = resultSet1.getInt("sub_id");
                subIds.add(subId);
            }
            resultSet1.close();

            ResultSet resultSet2 = statement.executeQuery("select sub_name from subjects");
            while (resultSet2.next()) {
                String subName = resultSet2.getString("sub_name");
                subNames.add(subName);
            }
            resultSet2.close();

            ResultSet resultSet3 = statement.executeQuery("select credits from subjects");
            while (resultSet3.next()) {
                int cred = resultSet3.getInt("credits");
                subCredits.add(cred);
            }
            resultSet3.close();

            for (Integer subId : subIds) {
                ResultSet resultSet4 = statement.executeQuery("select marks from marks where Roll_No=" + studentID + " and sub_id=" + subId);
                while (resultSet4.next()) {
                    String marks1 = resultSet4.getString("marks");
                    subMarks.add(marks1);
                }
                resultSet4.close();
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<marks> data = FXCollections.observableArrayList();
        for(int i=0;i<subIds.size();i++){
            data.add(new marks(subIds.get(i), subNames.get(i),subCredits.get(i),subMarks.get(i)));
        }

        id.setCellValueFactory(new PropertyValueFactory<marks, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<marks, String>("name"));
        credits.setCellValueFactory(new PropertyValueFactory<marks, Integer>("credits"));
        marks.setCellValueFactory(new PropertyValueFactory<marks, String>("marks"));

        table.setItems(data);
    }
}
