package atm.controller;

import atm.view.AtmWindow;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by myxom on 27.09.2016.
 */

// TODO: 27.09.2016 идея - AtmController знает только о модели (class Atm), ViewController знает только о ГУИ (class AtmWindow), OperationManager принимает сигналы с ГУИ, хранит состояние сессии и управляет ViewController и AtmController */
public class OperationManager {

    ViewController view;
    AtmController atmController;

    public void cardBtn() {
    }

    public void backBtn() {
    }

    public void okBtn() {
    }

    enum AtmStatus {START, WAIT_FOR_PIN, CHOOSING_MENU, CASH_WITHDRAW}
    private AtmStatus status;

    public OperationManager() throws InvocationTargetException, InterruptedException {
        view = new ViewController(this);
        atmController = new AtmController();
        status = AtmStatus.START;
    }
}
