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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class attendanceController {
    int studentID= loginController.studentID;
    Stage stage = new Stage();
    @FXML
    TableView<com.example.mp3.attendance> table;
    @FXML
    TableColumn<attendance, Integer> id;
    @FXML
    TableColumn<attendance, String> name;
    @FXML
    TableColumn<attendance, String> attendanceCol;
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
    Button attendanceButton;
    public void setInfo(){
        attendanceButton.setStyle("-fx-background-color: #D0DDF2; -fx-background-radius: 0; -fx-text-fill: #2b6897");
        List<Integer> subIds = new ArrayList<>();
        List<String> subNames = new ArrayList<>();
        List<String> subAttendance = new ArrayList<>();

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

            for (Integer subId : subIds) {
                ResultSet resultSet3 = statement.executeQuery("select attendancePercent from attendance where Roll_No=" + studentID + " and sub_id=" + subId);
                while (resultSet3.next()) {
                    String attendance1 = resultSet3.getString("attendancePercent");
                    subAttendance.add(attendance1);
                }
                resultSet3.close();
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<attendance> data = FXCollections.observableArrayList();
        for(int i=0;i<subIds.size();i++){
            data.add(new attendance(subIds.get(i), subNames.get(i), subAttendance.get(i)));
        }

        id.setCellValueFactory(new PropertyValueFactory<attendance, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<attendance, String>("name"));
        attendanceCol.setCellValueFactory(new PropertyValueFactory<attendance, String>("attendance"));

        attendanceCol.setCellFactory(new Callback<TableColumn<attendance, String>, TableCell<attendance, String>>() {
            @Override
            public TableCell<attendance, String> call(TableColumn<attendance, String> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(item);
                            try {
                                double attendancePercentage = Double.parseDouble(item);
                                if (attendancePercentage < 75) {
                                    setStyle("-fx-background-color: red;-fx-text-fill: white;-fx-alignment: CENTER;");
                                } else {
                                    setStyle("-fx-alignment: CENTER;");
                                }
                            } catch (NumberFormatException e) {
                                setStyle("-fx-alignment: CENTER;");
                            }
                        }
                    }
                };
            }
        });

        table.setItems(data);
    }
}
