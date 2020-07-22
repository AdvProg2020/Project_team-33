package Server.Model;

import Server.Model.Users.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat {
    Person person;
    private String message;
    public static ArrayList<Chat> allChats = new ArrayList<>();

    public Chat(Person person, String message) {
        this.person = person;
        this.message = message;
        allChats.add(this);
    }

    public String getMessage() {
        return message;
    }

    public static ArrayList<Chat> getAllChats() {
        return allChats;
    }

    public Person getPerson() {
        return person;
    }
}
