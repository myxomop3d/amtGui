package atm.controller;

import atm.model.atm.Atm;
import atm.model.atm.AtmException;
import atm.model.atm.CardException;
import atm.model.bankApi.UserException;
import atm.view.AtmWindow;

import java.io.IOException;
import java.io.InvalidClassException;

/**
 * Created by myxom on 24.09.2016.
 */
public class AtmController {
    private Atm atm;



    protected AtmController() {
        this.atm = new Atm();
    }

    protected void readCard() throws AtmException, IOException, CardException {
            atm.insertCard();
    }
    protected void getUserInfo() throws IOException, ClassNotFoundException {
            atm.getUserInfo();
    }
    protected boolean verifyPin(int pin) {
        return atm.verifyPin(pin);
    }

    protected void ejectCard() {
        atm.ejectCard();
    }

    protected String getUserName() {
        return atm.getUserName();
    }

    protected int getBalance() {
        return atm.getBalance();
    }
    protected void withdraw(int amount) throws UserException {
        atm.withdraw(amount);
    }


}
