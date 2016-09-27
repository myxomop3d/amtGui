package atm;


import atm.model.bankApi.User;

import java.util.Random;

/**
 * Created by mohov on 02.09.2016.
 */
public class CreateUser {
    public static void main(String[] args) {

        new User("Ivanov", "Ivan", new Random().nextInt(9999)).save();
    }
}
