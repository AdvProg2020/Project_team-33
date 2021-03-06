package Client.Model.Users;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Supporter extends Person {
    private ImageView imageView;
    private final Image unknownPerson = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
    private final Image womanPerson = new Image(Paths.get("src/main/java/view/images/womanLogo.png").toUri().toString());
    private final Image manPerson = new Image(Paths.get("src/main/java/view/images/manLogo.png").toUri().toString());
    private boolean isOnline;
    private int id;
    private static int count = 1;

    public Supporter(String username, String name, String family, String phone, String email, String password) {
        super(username, name, family, phone, email, password);
        this.id = count;
        count++;
    }

    public Supporter(){

    }

    public int getId() {
        return id;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
