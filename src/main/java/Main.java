import Controller.RegisterAndLogin.PersonController;
import Model.Category.Category;
import Model.Discount;
import Model.Product;
import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Seller;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;


public class Main extends Application {
    public static Socket socket;
    public static DataInputStream inputStream = new DataInputStream(System.in);
    public static DataOutputStream dataOutputStream;

    public static void main(String[] args) throws IOException {
        new ClientImpl().run();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Manager mainManager = new Manager("amk_amir", "Amir Mahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321");
        PersonController.isManagerAccountCreate = true;
        PersonController.mainManager = mainManager;
        LoginMenu.currentPerson = new Buyer("saba_sk", "saba", "keshavarz", "09912310335", "saba@yahoo.com", "sabasasa");
        LoginMenu.currentPerson = mainManager;
        Seller seller = new Seller("amirsalar", "amirsalar", "ansari", "09131789201", "a@a.com", "09131789201", "yes");
//        LoginMenu.currentPerson = seller;
        Seller seller1 = new Seller("arya", "Arya", "Jalali", "09123456789", "a@a.com", "areare", "Apple");
        LocalTime localTime = LocalTime.of(21, 30);
        LocalTime localTime1 = LocalTime.of(22, 38);
        new Discount("981710", localTime, localTime1, (long) 50_000, 50);
        new Discount("981711", localTime, localTime1, (long) 50_000, 50);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Samsung");
        strings.add("Apple");
        strings.add("LG");
        Category category = new Category("Mobiles", null, strings);
        new Product("981710", "galaxy S6", "Samsung", 2000000, seller, category, "A good phone", "Unknown");
        new Product("981711", "Iphone x", "Apple", 2000000, seller1, category, "A good phone", "Unknown");
        new Product("981712", "Iphone xs", "Apple", 3000000, seller1, category, "A good phone", "Unknown");
        new Product("981713", "Iphone xs max", "Apple", 2000000, seller1, category, "A good phone", "Unknown");
        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("Asus");
        strings1.add("Apple");
        strings1.add("Lenovo");
        Category category1 = new Category("Laptops", null, strings1);
        new Product("981714", "Zenbook", "Asus", 2000000, seller1, category1, "A good laptop", "Unknown");
        new Product("981715", "macbook pro", "Apple", 1000000, seller1, category1, "A good laptop", "Unknown");
        Menu menu = new Menu();
        Menu.currentMenu = menu;
        Menu.previousMenu = menu;
        Menu.executeMainMenu();
    }

    static class ClientImpl{
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;

        public void run() throws IOException {
            socket = new Socket("localhost",8888);
            String line = inputStream.readLine();
            dataOutputStream.writeUTF(line);

            inputStream.close();
            dataOutputStream.close();
            socket.close();
        }

        private void handleConnection(){
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
