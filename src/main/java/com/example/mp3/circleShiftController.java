package com.example.mp3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class circleShiftController {
    @FXML
    private Circle myCircle;
    private double x,y;

    public void up(ActionEvent e) {
        System.out.println("UP");
        myCircle.setCenterY(y-=1);
    }

    public void down(ActionEvent e) {
        System.out.println("DOWN");
        myCircle.setCenterY(y+=1);
    }

    public void left(ActionEvent e) {
        System.out.println("LEFT");
        myCircle.setCenterX(x-=1);
    }

    public void right(ActionEvent e) {
        System.out.println("RIGHT");
        myCircle.setCenterX(x+=1);
    }
}