package com.proftaak.VAT;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    /**
     *
     * @param primaryStage pass details of the primary stage
     * @throws Exception if method "start" failed to run
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/mainUI.fxml"));
        primaryStage.setTitle("Proftaak VAT Application");
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.show();
    }


    @Override
    public void stop() {
    }

    @Override
    public void init() {
    }


}
