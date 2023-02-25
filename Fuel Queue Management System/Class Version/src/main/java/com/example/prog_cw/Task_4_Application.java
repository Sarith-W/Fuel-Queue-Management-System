package com.example.prog_cw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Task_4_Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Task_4_Application.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 400);
        stage.setTitle("Fuel Queue Management System");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();

    }
}