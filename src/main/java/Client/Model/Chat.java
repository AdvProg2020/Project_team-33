package Client.Model;

import Client.Model.Users.Person;

import java.util.ArrayList;

public class Chat {
    Person person;
    private ArrayList<String> message = new ArrayList<>();

    public Chat(Person person, String message) {
        this.person = person;
        this.message.add(message);
    }

    public ArrayList<String> getMessages() {
        return message;
    }

    public Person getPerson() {
        return person;
    }
}