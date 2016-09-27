package atm.model.atm;

import java.io.*;
import java.util.Scanner;

/**
 * Created by mohov on 07.09.2016.
 */
public class IOController {
    public static void showMsg (String msg) {
        System.out.println("=================================================");
        for (int i = 1; i < (50 - msg.length()) / 2 - 1; i++) {
            System.out.print('=');
        }
        System.out.print(' ' + msg.toUpperCase() + ' ');
        for (int i = (50 - msg.length()) / 2 + msg.length() + 1; i < 50; i++) {
            System.out.print('=');
        }
        System.out.println();
        System.out.println("=================================================");
    }

    public static void showMsg (String msg, String info) {
        System.out.println("=================================================");
        for (int i = 1; i < (50 - msg.length()) / 2 - 1; i++) {
            System.out.print('=');
        }
        System.out.print(' ' + msg.toUpperCase() + ' ');
        for (int i = (50 - msg.length()) / 2 + msg.length() + 1; i < 50; i++) {
            System.out.print('=');
        }
        System.out.println();
        System.out.println("=================================================");
        System.out.println();
        System.out.println(info);
    }

    public static Object readObject(String s) throws IOException, ClassNotFoundException {
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(s))))
        {
            return in.readObject();
        }
    }

    public static int inputPositiveInt() throws Exception {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()) {
            int input = sc.nextInt();
            if (input > 0) {
                return input;
            }
        }
        throw new Exception("Ошибка запроса");
    }
}
