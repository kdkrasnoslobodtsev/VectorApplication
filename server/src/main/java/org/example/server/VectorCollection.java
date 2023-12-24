package org.example.server;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class VectorCollection {
    @Value("${database.url}")
    private String url;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;

    private Connection connection;

    public void setConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Unsuccessful database connection");
        }
    }

    public void addVector(String name, double x, double y, double z) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO vectors (name, x, y, z) " + "VALUES (\'" + name + "\', " + x + ", " + y + ", " + z + ")");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Boolean hasVector(String name) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM vectors WHERE name = '" + name + "'");
            Integer size = 0;
            while (result.next()){
                size = result.getInt(1);
            }
            return size > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public int size() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM vectors");
            Integer size = 0;
            while (result.next()){
                size = result.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public Vector3D getVector(String name) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM vectors WHERE name = '" + name + "'");
            result.next();
            Vector3D vector = new Vector3D(result.getString("name"),
                    result.getDouble("x"),
                    result.getDouble("y"),
                    result.getDouble("z")
            );
            return vector;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Vector3D> values() {
        try {
            List<Vector3D> vectors = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM vectors");
            while (result.next()) {
                vectors.add(new Vector3D(result.getString("name"), result.getDouble("x"), result.getDouble("y"), result.getDouble("z")));
            }
            return vectors;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void deleteVector(String name) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM vectors WHERE name = \'" + name + "\'");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
