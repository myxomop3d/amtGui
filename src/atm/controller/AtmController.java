package atm.controller;

import atm.model.atm.Atm;
import atm.model.atm.AtmException;
import atm.model.atm.CardException;
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
//        try {
            atm.insertCard();
////            atmWindow.showScreen("Вставили карточку. Считываю...", true);
////            readUserInfo();
//        } catch (AtmException e) {
////            atmWindow.showError(e.getMessage().toString(), false);
//            ejectCard();
//        } catch (CardException e) {
////            atmWindow.showError(e.getMessage().toString(), false);
//            ejectCard();
//        } catch (IOException e) {
////            atmWindow.showError(e.getMessage().toString());
////            atmWindow.showError("Ошибка. Не могу прочесть идентификатор карты", false);
//            ejectCard();
//        }
    }

    protected void getUserInfo() throws IOException, ClassNotFoundException {
//        try {
            atm.getUserInfo();
//        } catch (InvalidClassException e) {
////            IOController.showMsg("Ошибка счёта", "Информация по Вашей карте устарела. Обратитесь в отделение банка.");
////            atmWindow.showScreen("Карта устарела", true);
//            ejectCard();
//        } catch (ClassNotFoundException e) {
////            IOController.showMsg("Ошибка счёта", "Вы не являетесь клиентом нашего банка.\nДля получения карты обратитесь в отделение банка.");
////            atmWindow.showScreen("Чужой банк", true);
//            ejectCard();
//        } catch (IOException e) {
////            IOController.showMsg("Ошибка счёта", "Вы не являетесь клиентом нашего банка.\nДля получения карты обратитесь в отделение банка.");
////            atmWindow.showScreen("Чужой банк", true);
//            ejectCard();
//        }
    }

//    public void pinRequest() {
////        atmWindow.showScreen("Введите PIN", true);
//
////        int pinInputTries = 0;
////
////        while (!atm.verifyPin(getInputPin())) {
////            if(pinInputTries > 2) {
//////                IOController.showMsg("Ошибка ввода PIN", "Три неудачные попытки ввода Pin кода.");
////                atmWindow.showError("3 неудачные попытки", true);
////                ejectCard();
////                break;
////            }
////            pinInputTries++;
////        }
////        IOController.showMsg("Здравствуйте " + atm.getUserName());
//    }

    protected boolean verifyPin(int pin) {
        return atm.verifyPin(pin);
    }

    protected void ejectCard() {
        atm.ejectCard();
//        atmWindow.showScreen("Не забудьте вашу карточку", false);
    }


}
