package com.ATM.User.authorization.validation;

import com.ATM.User.authorization.validation.exceptions.IncorrectNumberException;
import com.ATM.User.authorization.validation.exceptions.NumberLengthException;

public class PassportNumberValidator implements NumberValidatable{
    @Override
    public boolean validate(String number) throws NumberLengthException, IncorrectNumberException { //9
        if (number.length() != 9) {
            throw new NumberLengthException("Incorrect passport number length!");
        } else if (!number.matches("[0-9]+")) {
            throw new IncorrectNumberException("Passport number must contain only digits!");
        }
        return true;
    }
}
