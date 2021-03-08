package com.ATM;

import com.ATM.Database.DBConnector;
import com.ATM.User.authorization.LoginUser;
import com.ATM.User.authorization.RegisterUser;
import com.ATM.User.User;
import com.ATM.User.authorization.UserByCardNum;
import com.ATM.money.Recharge;
import com.ATM.money.Transfer;
import com.ATM.money.Withdraw;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBConnector.initialize();
        Scanner sc = new Scanner(System.in);
        User user = null;
        while (true) {
            System.out.println("1. To login\n2. To register\n3. To exit");
            String command = sc.next();

            switch (command) {
                case "1" -> {
                    System.out.println("Input card num and pin code");
                    String cardnum = sc.next();
                    int pincode = sc.nextInt();
                    user = LoginUser.login(cardnum, pincode);
                }
                case "2" -> {
                    System.out.println("input first name");
                    String fname = sc.next();
                    System.out.println("input last name");
                    String lname = sc.next();
                    System.out.println("input date of birth");
                    String dob = sc.next();
                    System.out.println("input passport number");
                    String passport = sc.next();
                    System.out.println("input card number");
                    String cardn = sc.next();
                    System.out.println("input pin code");
                    int pin = sc.nextInt();
                    User register = new User(0, fname, lname, dob, passport, 0, cardn, pin);
                    RegisterUser.register(register);
                    user = LoginUser.login(cardn, pin);
                }
                case "3" -> System.exit(0);
                default -> System.err.println("Incorrect comand!");
            }
            while (true) {
                user = UserByCardNum.searchByCardNum(user.getCardn());
                System.out.println("1. To withdraw\n2. To Recharge\n 3. To transfer\n4. To get info\n5. To exit");
                String commandTwo = sc.next();
                switch (commandTwo) {
                    case "1" -> {
                        System.out.println("Input amount of money to withdraw!");
                        int money = sc.nextInt();
                        if (Withdraw.withdraw(money, user)) {
                            System.out.println("Successfully withdrawn " + money + "$!!\n\n");
                        }
                    }
                    case "2" -> {
                        System.out.println("Input money to recharge");
                        int money = sc.nextInt();
                        Recharge.recharge(money, user);
                        System.out.println("Successfully recharged " + money + "$!!\n\n");
                    }
                    case "3" -> {
                        System.out.println("Input card number of user you want to transfer money");
                        String cardn = sc.next();
                        User toUser = UserByCardNum.searchByCardNum(cardn);
                        System.out.println("Input amount of money to transfer");
                        int money = sc.nextInt();
                        if (Transfer.transfer(money, user, toUser)) {
                            System.out.println("Successfully transfered!\n\n");
                        } else {
                            System.err.println("Not transfered. Not enough money on the balance");
                        }
                    }
                    case "4" -> System.out.println(user);
                    case "5" -> System.exit(0);
                    default -> System.err.println("Incorrect command!");
                }
            }
        }
    }
}
