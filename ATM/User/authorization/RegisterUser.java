package com.ATM.User.authorization;

import com.ATM.Database.DBConnector;
import com.ATM.User.User;
import com.ATM.User.authorization.validation.CardNumberValidator;
import com.ATM.User.authorization.validation.PassportNumberValidator;
import com.ATM.User.authorization.validation.exceptions.IncorrectNumberException;
import com.ATM.User.authorization.validation.exceptions.NumberLengthException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterUser {
    public static boolean register(User user) {
        Connection con = DBConnector.connect();

        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM users WHERE card_n = \'" + user.getCardn() + "\' OR passport_n = \'" + user.getPassportn() + '\'';
            ResultSet rs = st.executeQuery(sql);

            if (!rs.next()) {
                CardNumberValidator v1 = new CardNumberValidator();
                PassportNumberValidator v2 = new PassportNumberValidator();
                v1.validate(user.getCardn());
                v2.validate(user.getPassportn());

                sql = "INSERT INTO public.users(\n" +
                        " first_name, last_name, dob, passport_n, card_n, pin, balance)" +
                        "VALUES (\'" +
                        user.getfName() + "\', \'" +
                        user.getlName() + "\', \'" +
                        user.getDob() + "\', \'" +
                        user.getPassportn() + "\', \'" +
                        user.getCardn() + "\', " +
                        user.getPin() + ", " +
                        user.getBalance() +
                        ");";
                st.execute(sql);
                System.out.println("Succsessfully registered!");
            } else {
                System.err.println("User with this card number or passport number already exist!");
                System.exit(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IncorrectNumberException e) {
            e.printStackTrace();
            System.exit(122);
        } catch (NumberLengthException e) {
            e.printStackTrace();
            System.exit(123);
        } finally {
            DBConnector.disconnect();
        }
        return false;
    }
}
