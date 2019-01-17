package com.proftaak.VAT.Model;

import com.proftaak.VAT.Model.ConnectJDBC;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Cube extends Shape {
    private  Property<Double> lengthProperty = new SimpleObjectProperty<Double>(0.0);
    private  Property<Double> widthProperty = new SimpleObjectProperty<Double>(0.0);
    private  Property<Double> heightProperty = new SimpleObjectProperty<Double>(0.0);


    /**
     *
     * @param resultSet contains value for new cube
     * @return  new cube shape
     * @throws SQLException if there is an connection problem
     */
    protected static Shape from(ResultSet resultSet) throws SQLException{
        Cube cube = new Cube();
        cube.lengthProperty.setValue(resultSet.getDouble("lengte"));
        cube.widthProperty.setValue(resultSet.getDouble("breedte"));
        cube.heightProperty.setValue(resultSet.getDouble("hoogte"));
        return cube;
    }


    /**
     *
     * @return string heigth (cube details)
     */
    @Override
    public String getDetails() {
        return String.format("height = %s\nlength = %s\nwidth = %s\nvolume = %s",
                this.getHeightProperty().getValue(),
                this.getLengthProperty().getValue(),
                this.getWidthProperty().getValue(),
                this.getVolume()
        );
    }

    /**
     *
     * @return map with the value lengte, breedte en hoogte
     */
    @Override
    HashMap<String, Object> getProperties() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", "'Cube'");
        map.put("lengte", this.getLengthProperty().getValue());
        map.put("breedte", this.getWidthProperty().getValue());
        map.put("hoogte", this.getHeightProperty().getValue());
        return map;
    }

    /**
     *
     * @return calculation of Volume
     */
    @Override
    double getVolume() {
        return this.heightProperty.getValue() * this.lengthProperty.getValue() * this.widthProperty.getValue();
    }

    /**
     * Getters
     * @return  current value in length property
     */
    public Property<Double>getLengthProperty(){
        return this.lengthProperty;
    }

    /**
     *
      * @return current value in width property
     */
    public Property<Double>getWidthProperty(){
        return this.widthProperty;
    }


    /**
     *
     * @return current value in height property
     */
    public Property<Double> getHeightProperty(){
        return this.heightProperty;
    }


    /**
     *
     * @return string with blok or cube
     */
    @Override
    public String toString() {
        return "Blok (Cube)";
    }
}
