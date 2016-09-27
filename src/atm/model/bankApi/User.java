package atm.model.bankApi;

import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by mohov on 01.09.2016.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5L;

    private UUID GUID;
    private int pinCode;
    private String lastName;
    private String firstName;
    private Date createdDate;
    private Date modifiedDate;
    private int balance;
    private final String USER_DB_PATH = "src/atm/model/bankApi/userDB/";

    public User(String lastName, String firstName, int balance) {
        this.lastName = lastName.toUpperCase();
        this.firstName = firstName.toUpperCase();
        this.balance = balance;

        Random rnd = new Random();
        this.pinCode = Integer.parseInt(Integer.toString(rnd.nextInt(8) + 1) + Integer.toString(rnd.nextInt(9)) + Integer.toString(rnd.nextInt(9)) + Integer.toString(rnd.nextInt(9)));
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.GUID = UUID.randomUUID();

        // Предполагается, что юзеров заводят в банке, поэтому оставил тут такую заглушку, ибо выводить эту информацию во View тоже не планируется.
        System.out.println("UUID = " + this.GUID.toString());
        System.out.println("Pin = " + this.pinCode);
        System.out.println("Balance = " + this.balance);
    }

    public UUID getGUID() {
        return GUID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public int getBalance() {
        return balance;
    }

    public void setModifiedDate() {
        this.modifiedDate = new Date();
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
        this.save();
    }

    public void save() {
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(USER_DB_PATH + GUID))))
        {
            this.modifiedDate = new Date();
            out.writeObject(this);
            out.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void withdraw(int amount) throws UserException {
        if(balance >= amount) {
            balance -= amount;
            this.save();
        } else {
            throw new UserException("Недостаточно средств");
        }
    }

    public boolean isCorrectPin(int code) {
        if (this.pinCode == code){
            return true;
        } else {
            return false;
        }
    }
}
