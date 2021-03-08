package com.ATM.User.authorization.validation;

import com.ATM.User.authorization.validation.exceptions.IncorrectNumberException;
import com.ATM.User.authorization.validation.exceptions.NumberLengthException;

public class CardNumberValidator implements NumberValidatable {
    @Override
    public boolean validate(String number) throws NumberLengthException, IncorrectNumberException { //16
        if (number.length() != 16) {
            throw new NumberLengthException("Incorrect card number length!");
        } else if (!number.matches("[0-9]+")) {
            throw new IncorrectNumberException("Card number must contain only digits!");
        }
        return true;
    }
}
