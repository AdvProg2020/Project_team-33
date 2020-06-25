package Model.Users;

import Database.SaveData;

import java.util.ArrayList;

public class Manager extends Person {
    public static ArrayList<Manager> allManagers = new ArrayList<>();
    SaveData saveData = new SaveData();

    public Manager(String username, String name, String family, String phone,
                   String email, String password) {
        super(username, name, family, phone, email, password);
        allManagers.add(this);
    }
}
