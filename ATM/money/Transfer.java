package com.ATM.money;

import com.ATM.User.User;

public class Transfer {
    public static boolean transfer(int number, User userFrom, User userTo) {
        boolean withdrawed = Withdraw.withdraw(number, userFrom);
        if (!withdrawed) return false;
        Recharge.recharge(number, userTo);
        return true;
    }
}
