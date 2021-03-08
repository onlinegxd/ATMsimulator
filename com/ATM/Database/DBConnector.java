package com.ATM.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector connector = null;

    private DBConnector() {
    }

    ;

    private Connection connection = null;

    public static boolean initialize() {
        if (connector == null) {
            connector = new DBConnector();
            return true;
        } else {
            return false;
        }
    }

    private Connection innerConnect() {
        String sqlLinl = "jdbc:postgresql://localhost:5432/atm";
        String sqlName = "postgres";
        String sqlPass = "123321";
        try {
            connector.connection = DriverManager.getConnection(sqlLinl, sqlName, sqlPass);
            return connector.connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private boolean innerDisconnect() {
        if (connector.connection == null) return false;
        try {
            connector.connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static Connection connect() {
        return connector.innerConnect();
    }

    public static boolean disconnect() {
        return connector.innerDisconnect();
    }


}
