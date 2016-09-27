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
//        view = new AtmWindow(manager);
    }

    private String getInput() {
        return view.getInput();
    }

}
