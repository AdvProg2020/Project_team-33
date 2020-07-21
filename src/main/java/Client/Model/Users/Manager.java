package Client.Model.Users;

import Server.Database.SaveData;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Manager extends Person {
    private ImageView imageView;
    private boolean isOnline;
    private static Long storeAccount = (long) 0;
    private final Image unknownPerson = new Image(Paths.get("src/main/java/Client/view/images/unknownPerson.jpg").toUri().toString());
    private final Image womanPerson = new Image(Paths.get("src/main/java/Client/view/images/womanLogo.png").toUri().toString());
    private final Image manPerson = new Image(Paths.get("src/main/java/Client/view/images/manLogo.png").toUri().toString());


//    @JsonCreator
//    public Manager(@JsonProperty("username") String username, @JsonProperty("name") String name, @JsonProperty("family") String family, @JsonProperty("phone") String phone,
//                   @JsonProperty("email") String email, @JsonProperty("password") String password) {
//        super(username, name, family, phone, email, password);
//    }

    public Manager(String username, String name, String family, String phone,
                   String email, String password) {
        super(username, name, family, phone, email, password);
//        this.imageView = new ImageView(unknownPerson);
//        allManagers.add(this);
    }

    public Manager() {

    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
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
