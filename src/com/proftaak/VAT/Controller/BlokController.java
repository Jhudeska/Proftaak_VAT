package com.proftaak.VAT.Controller;

import com.proftaak.VAT.Model.ConnectJDBC;
import com.proftaak.VAT.Model.Cube;
import com.proftaak.VAT.Model.DoubleSpinnerFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;

import java.sql.Connection;
import java.sql.SQLException;

public class BlokController {

    public Spinner<Double> cubeLength;
    public Spinner<Double> cubeWidth;
    public Spinner<Double> cubeHeight;

    private Cube cube = new Cube();
    private Connection connection;

    public void initialize() {
        connection = ConnectJDBC.connect();
        cubeLength.setValueFactory(new DoubleSpinnerFactory());
        cubeLength.getValueFactory().valueProperty().bindBidirectional(cube.getLengthProperty());

        cubeWidth.setValueFactory(new DoubleSpinnerFactory());
        cubeWidth.getValueFactory().valueProperty().bindBidirectional(cube.getWidthProperty());

        cubeHeight.setValueFactory(new DoubleSpinnerFactory());
        cubeHeight.getValueFactory().valueProperty().bindBidirectional(cube.getHeightProperty());
    }

    /**
     *
     * @param event
     */
    public void onClear(ActionEvent event) {
        cubeLength.getValueFactory().valueProperty().setValue(0.0);
        cubeWidth.getValueFactory().valueProperty().setValue(0.0);
        cubeHeight.getValueFactory().valueProperty().setValue(0.0);
    }

    /**
     *
     * @param event
     */
    public void onSave(ActionEvent event) {
        try {
            cube.insert(connection);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Er is een Blok aangemaakt.", ButtonType.OK);
            alert.setHeaderText("Message");
            alert.setTitle("Blok");
            alert.showAndWait();
        } catch (SQLException exception) {
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Oeps, er is iets misgegaan", ButtonType.OK);
            alert.setHeaderText("Error!");
            alert.setTitle("Blok");
            alert.showAndWait();
        }
    }
}
