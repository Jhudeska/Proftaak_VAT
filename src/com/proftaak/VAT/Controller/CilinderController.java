package com.proftaak.VAT.Controller;



import com.proftaak.VAT.Model.ConnectJDBC;
import com.proftaak.VAT.Model.Cylinder;
import com.proftaak.VAT.Model.DoubleSpinnerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;

public class CilinderController {

    @FXML
    public Spinner<Double> cilinderStraal;
    @FXML
    public Spinner<Double> cilinderHoogte;

    private Cylinder cylinder = new Cylinder();
    private Connection connection;

    /**
     * Intialize
     */
    public void initialize() {
        connection = ConnectJDBC.connect();
        cilinderStraal.setValueFactory(new DoubleSpinnerFactory());
        cilinderStraal.getValueFactory().valueProperty().bindBidirectional(cylinder.getRadiusProperty());

        cilinderHoogte.setValueFactory(new DoubleSpinnerFactory());
        cilinderHoogte.getValueFactory().valueProperty().bindBidirectional(cylinder.getHeightProperty());
    }


    /**
     *
     * @param event
     */
    public void onClearButtonClicked(ActionEvent event) {
        cilinderHoogte.getValueFactory().valueProperty().setValue(0.0);
        cilinderStraal.getValueFactory().valueProperty().setValue(0.0);
    }

    /**
     *
     * @param event
     */
    public void onSaveCilinder(ActionEvent event) {
        try {
            cylinder.insert(connection);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cylinder is aangemaakt", ButtonType.OK);
            alert.setHeaderText("Message");
            alert.setTitle("Cylinder");
            alert.showAndWait();
        } catch (SQLException exception) {
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Oeps, er is iets mis gegaan!", ButtonType.OK);
            alert.setHeaderText("Error!");
            alert.setTitle("Cylinder");
            alert.showAndWait();
        }
    }
}
