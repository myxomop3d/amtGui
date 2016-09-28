package atm.controller;

import atm.model.atm.AtmException;
import atm.model.atm.CardException;
import atm.model.bankApi.UserException;
import atm.view.AtmWindow;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by myxom on 27.09.2016.
 */

// TODO: 27.09.2016 идея - AtmController знает только о модели (class Atm), ViewController знает только о ГУИ (class AtmWindow), OperationManager принимает сигналы с ГУИ, хранит состояние сессии и управляет ViewController и AtmController */
public class OperationManager {

    ViewController view;
    AtmController atmController;

    enum AtmStatus {START, WAIT_FOR_PIN, CHOOSING_MENU, CASH_WITHDRAW, OPERATING}
    private AtmStatus status;

    public OperationManager() throws InvocationTargetException, InterruptedException {
        view = new ViewController(this);
        atmController = new AtmController();
        status = AtmStatus.START;
    }

    synchronized public void cardBtn() {
//        AtmStatus prevStatus = status;
//        status = AtmStatus.OPERATING;
        switch (status) {
            case START:
                status = AtmStatus.OPERATING;
                try {
                    view.clearInput();
                    atmController.readCard();
                    view.showMsg("Вставили карточку. Считываю...");
                    atmController.getUserInfo();
                    pause(2);
                    view.showMsg("Введите PIN");
                    status = AtmStatus.WAIT_FOR_PIN;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    atmController.ejectCard();
                    status = AtmStatus.START;
                } catch (AtmException e) {
                    view.showMsg(e.getMessage().toString());
                    atmController.ejectCard();
                    status = AtmStatus.START;
                } catch (CardException e) {
                    view.showMsg(e.getMessage().toString());
                    atmController.ejectCard();
                    status = AtmStatus.START;
                } catch (IOException e) {
                    view.showMsg("Ошибка. Не могу прочесть идентификатор карты");
                    atmController.ejectCard();
                    status = AtmStatus.START;
                }
                break;

            default:
//                status = prevStatus;
                break;
        }
    }

    public void backBtn() {
    }

    synchronized public void okBtn() {
//        AtmStatus prevStatus = status;
//        status = AtmStatus.OPERATING;
        switch (status) {
            case WAIT_FOR_PIN:
                status = AtmStatus.OPERATING;
                try {
                    int input = Integer.parseInt(view.getInput());
                    if (input >= 1000 && input <= 9999) {
                        if (atmController.verifyPin(input)) {
                            view.showMsg("Здравствуйте " + atmController.getUserName());
                            pause(2);
                            view.clearInput();
                            view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4");
                            status = AtmStatus.CHOOSING_MENU;
                        } else {
                            view.clearInput();
                            view.showMsg("Ошибка", "Неправильный Pin");
                            status = AtmStatus.WAIT_FOR_PIN;
                        }
                    } else {
                        view.clearInput();
                        view.showMsg("Неверный формат");
                        status = AtmStatus.WAIT_FOR_PIN;
                    }
                } catch (NumberFormatException e) {
                    view.clearInput();
                    view.showMsg("Неверный формат");
                    status = AtmStatus.WAIT_FOR_PIN;
                }
                break;

            case CHOOSING_MENU:
                status = AtmStatus.OPERATING;
                switch (view.getInput()) {
                    case "1":
                        String balance = Integer.toString(atmController.getBalance());
                        view.showMsg("Ваш баланс = " + balance, true);
                        pause(2);
                        view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                        view.clearInput();
                        status = AtmStatus.CHOOSING_MENU;
                        break;
                    
                    case "2":
                        view.clearInput();
                        view.showMsg("Введите сумму");
                        status = AtmStatus.CASH_WITHDRAW;
                        break;
                    
                    case "3":
                        view.showMsg("Функционал в разработке", true);
                        pause(2);
                        view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                        view.clearInput();
                        status = AtmStatus.CHOOSING_MENU;
                        break;
                    
                    case "4":
                        view.showMsg("Не забудьте Вашу карточку");
                        atmController.ejectCard();
                        pause(2);
                        view.showMsg("Добро пожаловать", "Для работы ставьте карточку", true);
                        view.clearInput();
                        status = AtmStatus.START;
                        break;
                    
                    default:
                        view.showMsg("Ошибка! Выберете пункт", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4");
                        status = AtmStatus.CHOOSING_MENU;
                        break;
                }
                break;

            case CASH_WITHDRAW:
                status = AtmStatus.OPERATING;
                try {
                    int input = Integer.parseInt(view.getInput());
                    atmController.withdraw(input);
                    view.showMsg("Списано " + input, true);
                    pause(2);
                    view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                    view.clearInput();
                    status = AtmStatus.CHOOSING_MENU;
                } catch (NumberFormatException e) {
                    view.showMsg("Неверный формат", true);
                    pause(2);
                    view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                    view.clearInput();
                    status = AtmStatus.CHOOSING_MENU;
                } catch (UserException e) {
                    view.showMsg("Недостаточно средств", true);
                    pause(2);
                    view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                    view.clearInput();
                    status = AtmStatus.CHOOSING_MENU;
                }
                break;
                
            default:
//                status = prevStatus;
                break;
        }
    }

    protected void pause(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
