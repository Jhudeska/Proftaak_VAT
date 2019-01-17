package com.proftaak.VAT.Controller;

import com.proftaak.VAT.Model.ConnectJDBC;
import com.proftaak.VAT.Model.DoubleSpinnerFactory;
import com.proftaak.VAT.Model.Sphere;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;

import java.sql.Connection;
import java.sql.SQLException;

public class BolController {
    public Spinner<Double> sphereRadius;
    private Sphere sphere = new Sphere();
    private Connection connection;

    public void initialize() {
        connection = ConnectJDBC.connect();
        sphereRadius.setValueFactory(new DoubleSpinnerFactory());
        sphereRadius.getValueFactory().valueProperty().bindBidirectional(sphere.getRadiusProperty());
    }

    /**
     *
     * @param event
     */
    public void onSave(ActionEvent event) {
        try {
            sphere.insert(connection);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Er is een nieuwe vorm bol aangemaakt.", ButtonType.OK);
            alert.setHeaderText("Message");
            alert.setTitle("Bol");
            alert.showAndWait();
        } catch (SQLException exception) {
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Oeps, er is iets mis gegaan.", ButtonType.OK);
            alert.setHeaderText("Error!");
            alert.setTitle("Bol");
            alert.showAndWait();
        }
    }


    /**
     *
     * @param event
     */
    public void onClear(ActionEvent event) {
        sphereRadius.getValueFactory().valueProperty().setValue(0.0);
    }
}