import Controller.PersonController;
import View.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (!PersonController.isManagerAccountCreate) {
            Pane pane = new Pane();
            stage.setTitle("Create Manger Account");
            Scene scene = new Scene(pane, 500, 400);
            stage.setScene(scene);
            stage.show();
        }
    }
}
