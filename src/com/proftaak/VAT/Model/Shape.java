package com.proftaak.VAT.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

abstract public class Shape {

    /**
     *
     * @param connection
     * @return all shapes in table "vormen"
     * @throws SQLException if there is an connection issue
     */
    public static List<Shape> getItems(Connection connection) throws SQLException {
        List<Shape> shapes = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM vormen");
        while (resultSet.next()) {
            shapes.add(decodeResult(resultSet));
        }
        return shapes;
    }


    /**
     *
     * @param result
     * @return
     * @throws SQLException
     */
    private static Shape decodeResult(ResultSet result) throws SQLException {
        String type = result.getString("type");
        switch (type) {
            case "Cube": return Cube.from(result);
            case "Sphere": return Sphere.from(result);
            case "Cylinder": return Cylinder.from(result);
            default: return Shape.from(result);
        }
    }


    /**
     *
     * @return
     */
    public abstract String getDetails();
    abstract HashMap<String, Object> getProperties();
    abstract double getVolume();


    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    protected static Shape from(ResultSet resultSet) throws SQLException {
        // Shape.from() should be implemented on subclasses, static abstract functions are not a thing :(
        return null;
    }

    /**
     *
     * @param connection
     * @throws SQLException
     */
    public void insert(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(this.getInsertQuery());
    }


    /**
     *
     * @return
     */
    private String getInsertQuery() {
        HashMap<String, Object> properties = this.getProperties();
        return String.format("INSERT INTO vormen (%s) VALUES (%s)",
                this.makeCommaSeparated(properties.keySet()),
                this.makeCommaSeparated(properties.values())
        );
    }

    private String getQueryToBeDeleted() {
        return null;
    }


    public void delete(Connection connection) throws SQLException {

    }

    private String getQueryToBeDeleted1() {
        //HashMap<String, Object> properties = this.getProperties();
        //return String.format("INSERT INTO vormen (%s) VALUES (%s)",
        //  this.makeCommaSeparated(properties.keySet()),
        //    this.makeCommaSeparated(properties.values())
        //);
        return null;

    }

    /**
     *
     * @param collection
     * @param <T>
     * @return
     */
    private<T> String makeCommaSeparated(Iterable<T> collection) {
        StringBuilder builder = new StringBuilder();
        for (T item : collection) {
            builder.append(item).append(", ");
        }
        String result = builder.toString();
        if (result.endsWith(", ")) {
            result = result.substring(0, result.lastIndexOf(", "));
        }
        return result;
    }

}

