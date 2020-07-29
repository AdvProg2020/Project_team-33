package Server.Model.Users;

import Server.Database.SaveData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Manager extends Person implements Serializable {
    private static Long storeAccount = (long) 0;
    public static ArrayList<Manager> allManagers = new ArrayList<>();
    private static int bankAccountId;
    public static ArrayList<Double> wages = new ArrayList<>();

    public Manager(String username, String name, String family, String phone,
                   String email, String password) {
        super(username, name, family, phone, email, password);
        allManagers.add(this);
    }

    public Manager() {

    }

    public boolean isOnline() {
        return isOnline;
    }

    public static Long getStoreAccount() {
        return storeAccount;
    }

    public static void addToStoreAccount(Long money) {
        Manager.storeAccount += money;
    }

    public static void removeFromStoreAccount(Long money) {
        Manager.storeAccount -= money;
    }

    public static void deleteManager(Person person) {
        allManagers.remove((Manager) person);
    }

    public static void setBankAccountId(int bankAccountId) {
        Manager.bankAccountId = bankAccountId;
    }

    public static int getBankAccountId() {
        return bankAccountId;
    }
}
