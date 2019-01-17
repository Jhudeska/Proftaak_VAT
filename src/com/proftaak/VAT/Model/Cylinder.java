package com.proftaak.VAT.Model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Cylinder extends Shape{

    private Property<Double> radiusProperty = new SimpleObjectProperty<Double>(0.0);
    private Property<Double> heightProperty = new SimpleObjectProperty<Double>(0.0);


    /**
     *
     * @param resultSet contains value for new cilinder
     * @return new cylinder
     * @throws SQLException if there is an connection problem
     */
    protected static Shape from(ResultSet resultSet) throws SQLException{
        Cylinder cylinder = new Cylinder();
        cylinder.radiusProperty.setValue(resultSet.getDouble("straal"));
        cylinder.heightProperty.setValue(resultSet.getDouble("hoogte"));
        return cylinder;
    }


    /**
     *
     * @return Hashmap map with the value of cylinder (straal, hoogte)
     */
    @Override
    HashMap<String, Object> getProperties() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", "'Cylinder'");
        map.put("straal", this.getRadiusProperty().getValue());
        map.put("hoogte", this.getHeightProperty().getValue());
        return map;
    }


    /**
     *
     * @return calculation of the cylinder volume (PI * straal * 2)  * hoogte
     */
    @Override
    double getVolume() {
        return (Math.PI * (this.radiusProperty.getValue() * 2)) * this.getHeightProperty().getValue();
    }
    /**
     *
     * @return string format for height
     */
    @Override
    public String getDetails() {
        return String.format("height = %s\nradius = %s\nvolume = %s",
                this.getHeightProperty().getValue(),
                this.getRadiusProperty().getValue(),
                this.getVolume()
        );
    }

    /**
     *
     * @return current value in radius property field
     */
    public Property<Double> getRadiusProperty(){
        return this.radiusProperty;
    }


    /**
     *
     * @return current value in height property field
     */
    public Property<Double> getHeightProperty(){
        return  this.heightProperty;
    }



    /**
     *
     * @return String with cylinder
     */
    @Override
    public String toString() {

        return "Cilinder (Cylinder)";
    }
}




