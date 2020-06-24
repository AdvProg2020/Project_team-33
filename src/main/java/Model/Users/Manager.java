package Model.Users;

import Database.SaveData;

import java.util.ArrayList;

public class Manager extends Person {
    public static ArrayList<Manager> allManagers = new ArrayList<>();
    SaveData saveData = new SaveData();

    public Manager(String username, String name, String family, String phone,
                   String email, String password) {
        super(name, family, username, password, phone, email);
        allManagers.add(this);
    }
}
