package com.proftaak.VAT.Controller;

import com.proftaak.VAT.Model.ConnectJDBC;
import com.proftaak.VAT.Model.Shape;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
//import javafx.scene.control.SelectionModel<T>;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static java.time.zone.ZoneRulesProvider.refresh;


public class MainUIController {

    @FXML
    private Button refreshButton;
    @FXML
    private Button cilinderButton;
    @FXML
    private Button bolButton;
    @FXML
    private Button blokButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ListView<Shape> savedVormListView;
    @FXML
    private TextArea savedVormInfo;

    private Connection connection;

    private Property<String> shapeDetailsProperty = new SimpleObjectProperty<String>("");


    public void initialize() {
        connection = ConnectJDBC.connect();
        savedVormListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        savedVormListView.selectionModelProperty().getValue().selectedItemProperty().addListener(new ChangeListener<Shape>() {
            @Override
            public void changed(ObservableValue<? extends Shape> observable, Shape oldValue, Shape newValue) {
                if (observable.getValue() != null) {
                    shapeDetailsProperty.setValue(observable.getValue().getDetails());
                } else {
                    shapeDetailsProperty.setValue("");
                }
            }
        });
        savedVormInfo.textProperty().bindBidirectional(shapeDetailsProperty);
        refresh();
    }


    /**
     * Refresh list of shapes
     */
    private void refresh() {
        try {
            savedVormListView.getItems().clear();
            savedVormListView.getItems().setAll(Shape.getItems(connection));
            savedVormListView.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param event passed clickactions of the cilinder, bol and blok.
     * @throws Exception if action failed
     */
    public void onButtonClicked(ActionEvent event) throws Exception {
        if (event.getSource() == cilinderButton) {
            this.openNew("../View/cilinderUI.fxml", "Cilinder ");
        } else if (event.getSource() == bolButton) {
            this.openNew("../View/bolUI.fxml", "Bol ");
        } else if (event.getSource() == blokButton) {
            this.openNew("../View/blokUI.fxml", "Blok ");
        }
    }


    public void openNew(String resource, String title) throws IOException {
        Parent window = FXMLLoader.load(getClass().getResource(resource));
        Scene scene = new Scene(window, 450, 300);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param actionEvent refresh button
     */
    public void onRefreshClicked(ActionEvent actionEvent) {
        refresh();
    }


    public final int getSelectedIndex(){
        return 3;
    }

    /**
     * @param actionEvent delete button
     */
    public void onDeleteClicked(ActionEvent actionEvent) {

    }
}


