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

    enum AtmStatus {START, WAIT_FOR_PIN, CHOOSING_MENU, CASH_WITHDRAW}
    private AtmStatus status;

    public OperationManager() throws InvocationTargetException, InterruptedException {
        view = new ViewController(this);
        atmController = new AtmController();
        status = AtmStatus.START;
    }

    public void cardBtn() {
        switch (status) {
            case START:
                try {
                    view.clearInput();
                    atmController.readCard();
                    view.showMsg("Вставили карточку. Считываю...");
                    atmController.getUserInfo();
                    status = AtmStatus.WAIT_FOR_PIN;
                    view.showMsg("Введите PIN");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    atmController.ejectCard();
                } catch (AtmException e) {
                    view.showMsg(e.getMessage().toString());
                    atmController.ejectCard();
                } catch (CardException e) {
                    view.showMsg(e.getMessage().toString());
                    atmController.ejectCard();
                } catch (IOException e) {
                    view.showMsg("Ошибка. Не могу прочесть идентификатор карты");
                    atmController.ejectCard();
                }
                break;

            default:
                break;
        }
    }

    public void backBtn() {
    }

    public void okBtn() {
        switch (status) {
            case WAIT_FOR_PIN:
                try {
                    System.err.println("WAIT_FOR_PIN");
                    int input = Integer.parseInt(view.getInput());
                    if (input >= 1000 && input <= 9999) {
                        System.err.println("1");
                        if (atmController.verifyPin(input)) {
                            view.showMsg("Здравствуйте " + atmController.getUserName());
                            status = AtmStatus.CHOOSING_MENU;
                            view.clearInput();
                            view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4");
                        } else {
                            view.clearInput();
                            view.showMsg("Ошибка", "Неправильный Pin");
                        }
                    } else {
                        view.clearInput();
                        view.showMsg("Неверный формат");
                    }
                } catch (NumberFormatException e) {
                    view.clearInput();
                    view.showMsg("Неверный формат");
                }
                break;

            case CHOOSING_MENU:
                switch (view.getInput()) {
                    case "1":
                        String balance = Integer.toString(atmController.getBalance());
                        view.showMsg("Ваш баланс = " + balance, true);
                        view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                        view.clearInput();
                        break;
                    
                    case "2":
                        view.clearInput();
                        view.showMsg("Введите сумму");
                        status = AtmStatus.CASH_WITHDRAW;
                        break;
                    
                    case "3":
                        view.showMsg("Функционал в разработке", true);
                        view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                        view.clearInput();
                        break;
                    
                    case "4":
                        view.showMsg("Не забудьте Вашу карточку");
                        atmController.ejectCard();
                        // todo тут должна быть пауза
                        view.showMsg("Добро пожаловать", "Для работы ставьте карточку", true);
                        status = AtmStatus.START;
                        view.clearInput();
                        break;
                    
                    default:
                        view.showMsg("Ошибка! Выберете пункт", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4");
                        break;
                }
                break;

            case CASH_WITHDRAW:
                try {
                    int input = Integer.parseInt(view.getInput());
                    atmController.withdraw(input);
                    view.showMsg("Списано " + input, true);
                    view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                    status = AtmStatus.CHOOSING_MENU;
                    view.clearInput();
                } catch (NumberFormatException e) {
                    view.showMsg("Неверный формат", true);
                    view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                    status = AtmStatus.CHOOSING_MENU;
                    view.clearInput();
                } catch (UserException e) {
                    view.showMsg("Недостаточно средств", true);
                    view.showMsg("Выберете интересующую Вас операцию", "Отобразить остаток => 1\nСнятие наличных ====> 2\nСменить Pin ========> 3\nВернуть карточку ===> 4", false);
                    status = AtmStatus.CHOOSING_MENU;
                    view.clearInput();
                }
                break;
                
            default:
                break;
        }
    }


}
