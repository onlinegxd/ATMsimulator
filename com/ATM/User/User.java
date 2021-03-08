package com.ATM.User;

public class User extends Person{
    private String dob;
    private String passportn;
    private int balance;
    private String cardn;
    private int pin;

    public User(int id, String fName, String lName, String dob, String passportn, int balance, String cardn, int pin) {
        super(id, fName, lName);
        this.dob = dob;
        this.passportn = passportn;
        this.balance = balance;
        this.cardn = cardn;
        this.pin = pin;
    }

    public String getDob() {
        return dob;
    }

    public String getPassportn() {
        return passportn;
    }

    public int getBalance() {
        return balance;
    }

    public String getCardn() {
        return cardn;
    }

    public int getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.getId() +
                ", fName='" + this.getfName() + '\'' +
                ", lName='" + this.getlName() + '\'' +
                ", dob='" + dob + '\'' +
                ", passportn='" + passportn + '\'' +
                ", balance=" + balance +
                ", cardn='" + cardn + '\'' +
                ", pin=" + pin +
                '}';
    }
}
