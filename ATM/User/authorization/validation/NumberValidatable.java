package com.ATM.User.authorization.validation;

import com.ATM.User.authorization.validation.exceptions.IncorrectNumberException;
import com.ATM.User.authorization.validation.exceptions.NumberLengthException;

public interface NumberValidatable {
    boolean validate(String number) throws NumberLengthException, IncorrectNumberException;
}
