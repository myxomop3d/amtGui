package atm.model.atm;

import atm.model.bankApi.User;
import atm.model.bankApi.UserException;

import java.io.*;
import java.util.Scanner;

/**
 * Created by igor on 14.08.16.
 */
public class Atm {
    private Card currentCard;// = null;
    private User currentUser;
    private final String USER_DB_PATH = "src/atm/model/bankApi/userDB/";

    public void insertCard() throws AtmException, CardException, IOException {
        if (currentCard == null) {
            currentCard = new Card();
        } else {
            throw new AtmException("Карточка уже в банкомате");
        }
    }

public void getUserInfo() throws IOException, ClassNotFoundException {
        currentUser = (User) IOController.readObject(USER_DB_PATH + currentCard.getGUID().toString());
        System.err.println("Данные получены");
    }

    public void ejectCard() {
        currentCard = null;
        if(currentUser != null) {
            currentUser.save();
            currentUser = null;
        }
    }


    public void withdraw(int amount) throws UserException {
            currentUser.withdraw(amount);
    }


    public int getBalance() {
        return currentUser.getBalance();
    }

    public boolean verifyPin(int pin) {
        if(currentUser.isCorrectPin(pin)) {
            return true;
        } else {
            return false;
        }
    }


    public String getUserName() {
        return currentUser.getLastName() + " " + currentUser.getFirstName();
    }

//    public void changePin() {
//        Scanner sc = new Scanner(System.in);
////        IOController.showMsg("ВВЕДИТЕ СТАРЫЙ PIN");
//
//        int inputPin;
//
//        if(sc.hasNextInt()) {
//            inputPin = sc.nextInt();
//            if(inputPin >= 1000 && inputPin <= 9999 && currentUser.isCorrectPin(inputPin)) {
////                IOController.showMsg("ВВЕДИТЕ НОВЫЙ PIN");
//
//                if(sc.hasNextInt()) {
//                    int newPin = sc.nextInt();
//                    if(newPin >= 1000 && newPin <= 9999) {
//                        currentUser.setPinCode(newPin);
////                        IOController.showMsg("PIN СОХРАНЁН");
//                    } else if (newPin < 1000) {
////                        IOController.showMsg("Ошибка ввода PIN", "Pin не может начитаться с \"0\"");
//                    } else {
//
////                        IOController.showMsg("Ошибка ввода PIN", "Pin должен состоять из 4-х цифр. Первая цифра не может быть \"0\"");
//                    }
//                } else {
////                    IOController.showMsg("Ошибка ввода PIN", "Вы ввели не целое число");
//                }
//            } else {
////                IOController.showMsg("Ошибка ввода PIN", "Неверный Pin.");
//                ejectCard();
//            }
//        } else {
////            IOController.showMsg("Ошибка ввода PIN", "Вы ввели не целое число");
//        }
//    }

}
