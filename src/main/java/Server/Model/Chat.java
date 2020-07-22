package Server.Model;

import Server.Model.Users.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat {
    private HashMap<Person, String> chat;
    public static ArrayList<Chat> allChats = new ArrayList<>();

    public Chat() {
        chat = new HashMap<>();
        allChats.add(this);
    }



}
