package com.ATM.User.authorization;

import com.ATM.Database.DBConnector;
import com.ATM.User.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserByCardNum {
    public static User searchByCardNum (String cardNum) {
        User user = null;
        Connection con = DBConnector.connect();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM users WHERE card_n = \'" + cardNum + '\'';
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getString("passport_n"),
                        rs.getInt("balance"),
                        cardNum,
                        rs.getInt("pin")
                );
                DBConnector.disconnect();

            } else {
                System.err.println("User not found!");
                System.exit(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();


        } finally {
            DBConnector.disconnect();
            return user;
        }

    }
}
