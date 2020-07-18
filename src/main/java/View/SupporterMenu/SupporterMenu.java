package View.SupporterMenu;

import View.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;

public class SupporterMenu extends Menu {
    public void show() throws IOException, ClassNotFoundException {
        showPersonalArea();
    }

    public void showPersonalArea() throws IOException, ClassNotFoundException {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Your Account");
        label.setFont(new Font(30));
        label.setLayoutX(90);
        label.setLayoutY(120);
        parent.getChildren().add(label);
        createPersonalInfoPanel(parent);
        createChatPanel(parent);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private void createPersonalInfoPanel(Pane parent) {

    }

    private void createChatPanel(Pane parent) {

    }

}
