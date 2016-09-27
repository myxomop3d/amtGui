package atm;

import atm.controller.OperationManager;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by myxom on 24.09.2016.
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        new OperationManager();
    }
}
