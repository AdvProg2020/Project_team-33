package Server.Model.Users;

import Server.Model.Chat;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Supporter extends Person{
    private static ArrayList<Supporter> allSupporters = new ArrayList<>();
    private ImageView imageView;
    private final Image unknownPerson = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
    private final Image womanPerson = new Image(Paths.get("src/main/java/view/images/womanLogo.png").toUri().toString());
    private final Image manPerson = new Image(Paths.get("src/main/java/view/images/manLogo.png").toUri().toString());
    private  HashMap<Person, ArrayList<Chat>> chatHashMap;
    private int id;
    private static int count = 1;

    public Supporter(String username, String name, String family, String phone, String email, String password) {
        super(username, name, family, phone, email, password);
        chatHashMap = new HashMap<>();
        id = count;
        allSupporters.add(this);
        count++;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Chat> getBuyerChat(Person person){
        for (Map.Entry<Person, ArrayList<Chat>> entry : chatHashMap.entrySet()) {
            if (entry.getKey().getUsername().equals(person.getUsername())){
                return entry.getValue();
            }
        }
        return null;
    }

    public void addChat(Person person, Chat chat){
        for (Map.Entry<Person, ArrayList<Chat>> entry : chatHashMap.entrySet()) {
            if (entry.getKey().getUsername().equals(person.getUsername())){
                entry.getValue().add(chat);
                return;
            }
        }
    }

    public void clearChat(Person person){

    }

    public static Supporter getSupporterById(int id){
        for (Supporter supporter : allSupporters) {
            if (supporter.getId() == id){
                return supporter;
            }
        }
        return null;
    }

    public HashMap<Person, ArrayList<Chat>> getChatHashMap() {
        return chatHashMap;
    }

    public static ArrayList<Supporter> getAllSupporters() {
        return allSupporters;
    }

    public static ArrayList<Supporter> getAllOnlineSupporters() {
        ArrayList<Supporter> onlineSupporters = new ArrayList<>();
        for (Supporter supporter : allSupporters) {
            if (supporter.isOnline()){
                onlineSupporters.add(supporter);
            }
        }
        return onlineSupporters;
    }



    public boolean isOnline() {
        return isOnline;
    }

    public void setImageView(String sex) {
        if (sex.equals("man")) {
            this.imageView.setImage(manPerson);
        } else if (sex.equals("woman")) {
            this.imageView.setImage(womanPerson);
        } else {
            this.imageView.setImage(unknownPerson);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}
