package atm.controller;

import atm.view.AtmWindow;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by myxom on 27.09.2016.
 */
public class ViewController {

    AtmWindow view;

    ViewController(OperationManager manager) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                view = new AtmWindow(manager);
            }
        });
        showMsg("Добро пожаловать", "Для работы ставьте карточку", true);
        clearInput();
    }

    protected String getInput() {
        System.err.println(view.getInput());
        return view.getInput();
    }

    protected void clearInput() {
        view.clearInput();
    }

    protected void showMsg(String msg) {
        view.showScreen(msg, true);
    }

    protected void showMsg(String msg, boolean clear) {
        view.showScreen(msg, clear);
    }

    protected void showMsg(String msg, String description) {
        view.showScreen(msg, description);
    }

    protected void showMsg(String msg, String description, boolean clear) {
        view.showScreen(msg, description, clear);
    }

}
