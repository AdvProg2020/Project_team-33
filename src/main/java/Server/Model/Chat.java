package Server.Model;

import Server.Model.Users.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat {
//    private HashMap<Person, String> chat;
    Person person;
    private ArrayList<String> messages = new ArrayList<>();
    public static ArrayList<Chat> allChats = new ArrayList<>();

    public Chat(Person person, String message) {
        this.person = person;
        messages.add(message);
        allChats.add(this);
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public static ArrayList<Chat> getAllChats() {
        return allChats;
    }
}
