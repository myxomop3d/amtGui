package atm.model.atm;

import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Scanner;

/**
 * Created by igor on 14.08.16.
 */
public class Operation {
    public Atm atm;

    public Operation() {
        atm =  new Atm();
    }

    public void startOperation() {
        try {
            atm.insertCard();
        } catch (AtmException e) {
            e.printStackTrace();
        } catch (CardException e) {
            System.out.println(e.getMessage());
            atm.ejectCard();
        } catch (IOException e) {
//            IOController.showMsg("Ошибка карты", "Не могу прочесть идентификатор карты");
            atm.ejectCard();
        }
    }

    public void readUserInfo() {
        try {
            atm.getUserInfo();
        } catch (InvalidClassException e) {
//            IOController.showMsg("Ошибка счёта", "Информация по Вашей карте устарела. Обратитесь в отделение банка.");
            atm.ejectCard();
        } catch (ClassNotFoundException e) {
//            IOController.showMsg("Ошибка счёта", "Вы не являетесь клиентом нашего банка.\nДля получения карты обратитесь в отделение банка.");
            atm.ejectCard();
        } catch (IOException e) {
//            IOController.showMsg("Ошибка счёта", "Вы не являетесь клиентом нашего банка.\nДля получения карты обратитесь в отделение банка.");
            atm.ejectCard();
        }
    }

//    public void pinRequest() {
//        int pinInputTries = 0;
//
//        do {
//            if(pinInputTries > 2) {
//                IOController.showMsg("Ошибка ввода PIN", "Три неудачные попытки ввода Pin кода.");
//                atm.ejectCard();
//            }
//            pinInputTries++;
//        } while (!atm.verifyPin());
//        IOController.showMsg("Здравствуйте " + atm.getUserName());
//    }

    public void doOperation() {

        while(true) {
            Scanner sc = new Scanner(System.in);
//            IOController.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4");

            if(sc.hasNextInt()) {
                int input = sc.nextInt();
                switch (input) {
                    case 1: atm.showBalance();
                        break;
                    case 2: atm.withdraw();
                        break;
                    case 3: atm.changePin();
                        break;
                    case 4: atm.ejectCard();
                        break;
                    default:
//                        IOController.showMsg("Неизвестная операция");
                }
            } else {
//                IOController.showMsg("Ошибка запроса", "Вы ввели не целое число");
            }
        }

    }
}
