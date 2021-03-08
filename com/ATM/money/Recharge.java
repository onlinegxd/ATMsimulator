package com.ATM.money;

import com.ATM.Database.DBConnector;
import com.ATM.User.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Recharge {
    public static void recharge(int number, User user) {
        if (number <= 0) {
            System.err.println("Minimal rechargable amount: 1");
            return;
        }
        Connection con = DBConnector.connect();

        try {
            Statement st = con.createStatement();
            String sql = "UPDATE users SET balance = balance + " + number + " WHERE id = " + user.getId();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
