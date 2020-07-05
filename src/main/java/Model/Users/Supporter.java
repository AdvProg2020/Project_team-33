package Model.Users;

import java.util.ArrayList;

public class Supporter extends Person{
    private static ArrayList<Supporter> allSupporters = new ArrayList<>();

    public Supporter(String username, String name, String family, String phone, String email, String password) {
        super(username, name, family, phone, email, password);
        allSupporters.add(this);
    }

    public static ArrayList<Supporter> getAllSupporters() {
        return allSupporters;
    }

}
