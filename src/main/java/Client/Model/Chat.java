package Client.Model;

import Client.Model.Users.Person;

import java.util.ArrayList;

public class Chat {
    Person person;
    private String message;

    public Chat(Person person, String message) {
        this.person = person;
        this.message = message;
    }

    public Chat(){

    }

    public String getMessage() {
        return message;
    }

    public Person getPerson() {
        return person;
    }
}