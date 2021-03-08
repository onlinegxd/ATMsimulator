package com.ATM.money;

import com.ATM.Database.DBConnector;
import com.ATM.User.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Withdraw {
    public static boolean withdraw(int number, User user) {
        if (number <= 0) {
            System.err.println("Minimal withdrawal amount: 1");
            return false;
        }
        Connection con = DBConnector.connect();

        try {
            Statement st = con.createStatement();
            int currentbalance;
            String sql = "SELECT balance FROM users WHERE id = " + user.getId();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            currentbalance = rs.getInt("balance");
            if (currentbalance < number) {
                return false;
            } else {
                sql = "UPDATE users SET balance = balance - " + number + " WHERE id = " + user.getId();
                st.executeUpdate(sql);
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
}
