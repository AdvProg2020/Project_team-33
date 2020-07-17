import Controller.BuyerController.BuyerAbilitiesController;
import Controller.ManagerController.ManagerAbilitiesController;
import Controller.RegisterAndLogin.PersonController;
import Controller.RegisterAndLogin.RegisterProcess;
import Controller.SellerController.SellerAbilitiesController;
import Model.Category.Category;
import Model.Discount;
import Model.Product;
import Model.Requests.Request;
import Model.Users.*;
import View.BuyerMenu.BuyerMenu;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import View.Menu;
import View.SellerMenu.SellerMenu;
import View.SupporterMenu.SupporterMenu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) throws IOException {
        new ServerImpl().run();
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;
        private ServerImpl server;
        private Person person;
        private ArrayList<Person> allMembers;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream,
                             ObjectOutputStream objectOutputStream, ServerImpl server) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.objectOutputStream = objectOutputStream;
            this.server = server;
        }

        public void handleClient() {
            try {
                String input = "";
                while (true) {
                    input = dataInputStream.readUTF();
                    if (input.startsWith("createAccount")) {
                        String[] splitInput = input.split(",");
                        person = server.createAccount(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], splitInput[6], splitInput[7], dataOutputStream, objectOutputStream);
                    } else if (input.startsWith("chooseRole,buyer")) {
                        String[] splitInput = input.split(",");
                        person = server.chooseBuyerRole(person, dataOutputStream);
                    } else if (input.startsWith("chooseRole,seller")) {
                        String[] splitInput = input.split(",");
                        person = server.chooseSellerRole(person, splitInput[2], dataOutputStream);
                    } else if (input.startsWith("showFirstPage")) {
                        server.showFirstPage(person, dataOutputStream);
                    } else if (input.startsWith("checkMainManager")) {
                        server.checkMainManager(dataOutputStream);
                    } else if (input.startsWith("logout")) {
                        server.logout(person, dataOutputStream);
                        person = null;
                    } else if (input.startsWith("getPerson")) {
                        server.getPerson(objectOutputStream, person);
                    } else if (input.startsWith("getAllMembers")) {
                        server.getAllMembers(objectOutputStream, allMembers);
                    } else if (input.startsWith("editPersonalInfo")) {
                        String[] splitInput = input.split(",");
                        server.editPersonalInfo(splitInput[1], splitInput[2], person, dataOutputStream);
                    } else if (input.startsWith("setMoney")) {
                        String[] splitInput = input.split(",");
                        server.setMoney(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("login")) {
                        String[] splitInput = input.split(",");
                        person = server.login(person, splitInput[1], splitInput[2], dataOutputStream);
                    } else if (input.startsWith("createManager")) {
                        String[] splitInput = input.split(",");
                        server.createManager(person, splitInput[1], splitInput[2], splitInput[3], splitInput[4],
                                splitInput[5], splitInput[6], splitInput[7], dataOutputStream);
                    } else if (input.startsWith("getMainManager")) {
                        server.getMainManager(objectOutputStream);
                    } else if (input.startsWith("deleteUser")) {
                        String[] splitInput = input.split(",");
                        server.deleteUser(splitInput[1]);
                    } else if (input.startsWith("createDiscount")) {
                        String[] splitInput = input.split(",");
                        server.createDiscount(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], dataOutputStream);
                    } else if (input.startsWith("getAllDiscounts")) {
                        server.getAllDiscounts(objectOutputStream);
                    } else if (input.startsWith("addDiscountToBuyer")) {
                        String[] splitInput = input.split(",");
                        server.addDiscountToBuyer(splitInput[1], splitInput[2], dataOutputStream);
                    } else if (input.startsWith("deleteDiscount")) {
                        String[] splitInput = input.split(",");
                        server.deleteDiscount(splitInput[1], objectOutputStream);
                    } else if (input.startsWith("editDiscount")) {
                        String[] splitInput = input.split(",");
                        server.editDiscount(splitInput[1], splitInput[2], splitInput[3], dataOutputStream);
                    } else if (input.startsWith("getAllCategories")) {
                        server.getAllCategories(objectOutputStream);
                    } else if (input.startsWith("deleteCategory")) {
                        String[] splitInput = input.split(",");
                        server.deleteCategory(splitInput[1]);
                    } else if (input.startsWith("addCategory")) {
                        String[] splitInput = input.split(",");
                        server.addCategory(splitInput[1], splitInput[2], splitInput[3], splitInput[4], dataOutputStream);
                    } else if (input.startsWith("editCategory")) {
                        String[] splitInput = input.split(",");
                        server.editCategory(splitInput[1], splitInput[2], splitInput[3], dataOutputStream);
                    } else if (input.startsWith("getRequests")) {
                        server.getRequests(objectOutputStream);
                    } else if (input.startsWith("deleteRequest")) {
                        server.deleteRequest(objectInputStream);
                    } else if (input.startsWith("setRequestCondition")) {
                        String[] splitInput = input.split(",");
                        server.setRequestCondition(splitInput[1], objectInputStream);
                    } else if (input.startsWith("getProducts")) {
                        server.getProducts(objectOutputStream);
                    } else if (input.startsWith("deleteProduct")) {
                        server.deleteProduct(objectInputStream);
                    } else if (input.startsWith("setImageView")) {
                        String[] splitInput = input.split(",");
                        server.setImageView(splitInput[1], person);
                    } else if (input.startsWith("getProductsForSeller")) {
                        server.getProductsForSeller(person, objectOutputStream);
                    } else if (input.startsWith("addProduct")) {
                        String[] splitInput = input.split(",");
                        server.addProduct(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], person, dataOutputStream);
                    } else if (input.startsWith("productSetImageView")) {
                        String[] splitInput = input.split(",");
                        server.productSetImageView(splitInput[1], dataOutputStream, objectInputStream);
                    } else if (input.startsWith("sendEditProductRequest")) {
                        String[] splitInput = input.split(",");
                        server.sendEditProductRequest(splitInput[1], splitInput[2], objectInputStream, dataOutputStream, person);
                    } else if (input.startsWith("increaseProduct")) {
                        server.increaseProduct(dataOutputStream, objectInputStream);
                    } else if (input.startsWith("sendDeleteProductRequest")) {
                        server.sendDeleteProductRequest(person, objectInputStream);
                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else {
                        dataOutputStream.writeUTF("done");
                        dataOutputStream.flush();
                        clientSocket.close();
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            handleClient();
        }
    }

    static class ServerImpl {
        public ServerSocket serverSocket;

        private void run() throws IOException {
            serverSocket = new ServerSocket(8888);
            Socket clientSocket;
            while (true) {
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream, objectOutputStream, this).start();
            }

        }

        private Person createAccount(String username, String name, String family, String email, String password, String reenterPassword,
                                     String phone, DataOutputStream dataOutputStream, ObjectOutputStream objectOutputStream) throws IOException {
            StringBuilder answer = new StringBuilder();
            boolean create = true;

            if (!PersonController.usernameTypeErr(username)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (PersonController.existUsername(username)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (username.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!PersonController.emailTypeErr(email)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (email.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (PersonController.checkLengthOfPassWord(password)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!reenterPassword.equals(password)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (password.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (reenterPassword.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!PersonController.phoneTypeErr(phone)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (phone.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (create) {
                answer.append("pass-");
                if (!PersonController.isManagerAccountCreate) {
                    answer.append("1");
                    PersonController.mainManager = RegisterProcess.createAccountForMainManager(username, name, family,
                            phone, email, password);
                    LoginMenu.currentPerson = PersonController.mainManager;
                    PersonController.isManagerAccountCreate = true;
                    Menu.currentMenu = Menu.previousMenu;
                    dataOutputStream.writeUTF(answer.toString());
                    dataOutputStream.flush();
                    return PersonController.mainManager;
                } else {
                    answer.append("2");
                    Person registeringPerson = new Person(username, name, family,
                            phone, email, password);
                    objectOutputStream.writeObject(registeringPerson);
                    dataOutputStream.writeUTF(answer.toString());
                    dataOutputStream.flush();
                    return registeringPerson;
                }
            } else {
                answer.append("fail");
                dataOutputStream.writeUTF(answer.toString());
                dataOutputStream.flush();
                return null;
            }

        }

        public Person chooseBuyerRole(Person person, DataOutputStream dataOutputStream) throws IOException {
            Person.deleteUser(person);
            LoginMenu.currentPerson = RegisterProcess.createAccountForBuyer(person.getUsername(), person.getName(), person.getFamily(),
                    person.getPhone(), person.getEmail(), person.getPassword());
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
            return LoginMenu.currentPerson;
        }

        public Person chooseSellerRole(Person person, String company, DataOutputStream dataOutputStream) throws IOException {
            Seller seller = new Seller(person.getUsername(), person.getName(), person.getFamily(),
                    person.getPhone(), person.getEmail(), person.getPassword(), company);
            Person.deleteUser(person);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
            return seller;
        }

        public void showFirstPage(Person person, DataOutputStream dataOutputStream) throws IOException {
            if (person instanceof Buyer) {
                dataOutputStream.writeUTF("buyer");
                dataOutputStream.flush();
            } else if (person instanceof Seller) {
                dataOutputStream.writeUTF("seller");
                dataOutputStream.flush();
            }
        }

        public void checkMainManager(DataOutputStream dataOutputStream) throws IOException {
            if (PersonController.isManagerAccountCreate) {
                dataOutputStream.writeUTF("yes");
            } else {
                dataOutputStream.writeUTF("no");
            }
            dataOutputStream.flush();
        }

        public void logout(Person person, DataOutputStream dataOutputStream) throws IOException {
            if (person instanceof Buyer) {
                ((Buyer) person).setOnline(false);
            } else if (person instanceof Seller) {
                ((Seller) person).setOnline(false);
            } else if (person instanceof Manager) {
                ((Manager) person).setOnline(false);
            } else if (person instanceof Supporter) {
                ((Supporter) person).setOnline(false);
            }
            LoginMenu.currentPerson = null;
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void getPerson(ObjectOutputStream objectOutputStream, Person person) throws IOException {
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
        }


        public void getAllMembers(ObjectOutputStream objectOutputStream, ArrayList<Person> allMembers) throws IOException {
            objectOutputStream.writeObject(ManagerAbilitiesController.getAllMembers());
            objectOutputStream.flush();
        }

        public void editPersonalInfo(String field, String newInput, Person person, DataOutputStream dataOutputStream) throws IOException {
            BuyerAbilitiesController.editPersonalInfo(person, field, newInput);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void setMoney(String money, Person person, DataOutputStream dataOutputStream) throws IOException {
            if (person instanceof Buyer) {
                ((Buyer) person).setMoney(Long.parseLong(money));
                dataOutputStream.writeUTF("done");
                dataOutputStream.flush();
            }
        }

        public Person login(Person person, String username, String password, DataOutputStream dataOutputStream) throws IOException {
            boolean login = true;
            StringBuilder answer = new StringBuilder();
            if (!PersonController.usernameTypeErr(username)) {
                answer.append("1-");
                login = false;
            } else {
                answer.append("0-");
            }

            if (!PersonController.existUsername(username)) {
                answer.append("1-");
                login = false;
            } else {
                answer.append("0-");
            }

            if (username.isEmpty()) {
                answer.append("1-");
                login = false;
            } else {
                answer.append("0-");
            }

            if (password.isEmpty()) {
                answer.append("1-");
                login = false;
            } else {
                answer.append("0-");
            }

            if ((Person.getPersonByUsername(username) != null) && !Person.getPersonByUsername(username).getPassword().equals(password) && (!password.isEmpty())) {
                answer.append("1-");
                login = false;
            } else {
                answer.append("0-");
            }

            if (login) {
                answer.append("pass-");
                person = Person.getPersonByUsername(username);
                if (person instanceof Seller) {
                    answer.append("seller-");
                    Seller seller = (Seller) person;
                    if (seller.getCanSellerCreate().equals("Accept")) {
                        answer.append("accept");
                        ((Seller) person).setOnline(true);
                    } else if (seller.getCanSellerCreate().equals("Unknown")) {
                        answer.append("unknown");
                        person = null;
                    }
                } else if (person instanceof Buyer) {
                    answer.append("buyer");
                    ((Buyer) person).setOnline(true);
                } else if (person instanceof Supporter) {
                    answer.append("supporter");
                    ((Supporter) person).setOnline(true);
                } else {
                    answer.append("manager");
                    assert person != null;
                    ((Manager) person).setOnline(true);
                }
            } else {
                answer.append("fail-");
            }

            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
            return person;
        }

        public void createManager(Person person, String username, String password, String email, String phone,
                                  String reEnterPassword, String name, String family, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();

            if (username.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (email.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (phone.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (password.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (reEnterPassword.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if ((!password.isEmpty() && !reEnterPassword.isEmpty()) &&
                    (!password.equals(reEnterPassword))) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (PersonController.existUsername(username)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!PersonController.emailTypeErr(email)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!PersonController.phoneTypeErr(phone)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (PersonController.checkLengthOfPassWord(password)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (create) {
                answer.append("pass");
                new Manager(username, name, family, phone, email, password);
            } else {
                answer.append("fail");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void getMainManager(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(PersonController.mainManager);
            objectOutputStream.flush();
        }

        public void deleteUser(String username) {
            ManagerAbilitiesController.deleteUser(Person.getPersonByUsername(username));
        }

        public void createDiscount(String code, String discount, String max, String start, String end, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();

            if (code.isEmpty()) {
                answer.append("1-");
                create = false;
            } else if (code.length() <= 6) {
                answer.append("2-");
                create = false;
            } else if (Discount.isDiscountExist(code)) {
                answer.append("3-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (discount.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (max.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (start.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (end.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (create) {
                answer.append("pass");
                String[] input = start.split(":");
                LocalTime startTime = LocalTime.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                String[] input1 = end.split(":");
                LocalTime endTime = LocalTime.of(Integer.parseInt(input1[0]), Integer.parseInt(input1[1]));
                new Discount(code, startTime, endTime, Long.parseLong(max), Integer.parseInt(discount));
            } else {
                answer.append("fail");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void getAllDiscounts(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(ManagerAbilitiesController.getAllDiscounts());
            objectOutputStream.flush();
        }

        public void addDiscountToBuyer(String username, String code, DataOutputStream dataOutputStream) throws IOException {
            if (Person.isAccountWithThisUsernameExist(username)) {
                Buyer buyer = (Buyer) Person.getPersonByUsername(username);
                buyer.addDiscount(Discount.getDiscountByCode(code));
                dataOutputStream.writeUTF("pass");
            } else {
                dataOutputStream.writeUTF("fail");
            }
            dataOutputStream.flush();
        }

        public void deleteDiscount(String code, ObjectOutputStream objectOutputStream) {
            ManagerAbilitiesController.deleteDiscount(Discount.getDiscountByCode(code));
        }


        public void editDiscount(String code, String field, String newField, DataOutputStream dataOutputStream) throws IOException {
            ManagerAbilitiesController.editDiscount(Discount.getDiscountByCode(code), field, newField);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void getAllCategories(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(ManagerAbilitiesController.getAllCategories());
            objectOutputStream.flush();
        }

        public void deleteCategory(String name) {
            ManagerAbilitiesController.deleteCategory(Category.getCategoryByName(name));
        }

        public void addCategory(String name, String detail1, String detail2, String detail3, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();

            if (name.isEmpty()) {
                answer.append("1-");
                create = false;
            } else if (Category.isCategoryExist(name)) {
                answer.append("2-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (detail1.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (detail2.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (detail3.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (create) {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(detail1);
                strings.add(detail2);
                strings.add(detail3);
                new Category(name, null, strings);
                answer.append("pass");
            } else {
                answer.append("fail");
            }

            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void editCategory(String name, String field, String newInput, DataOutputStream dataOutputStream) throws IOException {
            ManagerAbilitiesController.editCategory(Category.getCategoryByName(name), field, newInput);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void getRequests(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(ManagerAbilitiesController.getAllRequests());
        }

        public void deleteRequest(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            Request request = (Request) objectInputStream.readObject();
            ManagerAbilitiesController.deleteRequest(request);
        }

        public void setRequestCondition(String condition, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            Request request = (Request) objectInputStream.readObject();
            if (condition.equals("Accept")) {
                ManagerAbilitiesController.setConditionForRequest(request, "Accept");
            } else {
                ManagerAbilitiesController.setConditionForRequest(request, "Decline");
            }
        }

        public void getProducts(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(Product.getAllProducts());
        }

        public void deleteProduct(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            Product product = (Product) objectInputStream.readObject();
            Product.deleteProduct(product);
        }

        public void setImageView(String kind, Person person) {
            if (person instanceof Seller) {
                ((Seller) person).setImageView(kind);
            } else if (person instanceof Buyer) {
                ((Buyer) person).setImageView(kind);
            } else if (person instanceof Supporter) {
                ((Supporter) person).setImageView(kind);
            } else if (person instanceof Manager) {
                ((Manager) person).setImageView(kind);
            }
        }

        public void getProductsForSeller(Person person, ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(SellerAbilitiesController.getAllProducts((Seller) person));
        }

        public void addProduct(String id, String name, String price, String category, String description, Person person, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();
            if (id.isEmpty()) {
                answer.append("1-");
                create = false;
            } else if (id.length() != 6) {
                answer.append("2-");
                create = false;
            } else if (Product.isProductExist(id)) {
                answer.append("3-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (name.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (price.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (category.isEmpty()) {
                answer.append("1-");
                create = false;
            } else if (!Category.isCategoryExist(category)) {
                answer.append("2-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (description.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (create) {
                answer.append("pass");
                Seller seller = (Seller) person;
                Category category1 = Category.getCategoryByName(category);
                SellerAbilitiesController.sendAddProductRequestToManager(id, name, Long.parseLong(price), seller, category1, description);
            } else {
                answer.append("fail");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void productSetImageView(String kind, DataOutputStream dataOutputStream, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            Product product = (Product) objectInputStream.readObject();
            product.setImageView(kind);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void sendEditProductRequest(String field, String newInput, ObjectInputStream objectInputStream, DataOutputStream dataOutputStream, Person person) throws IOException, ClassNotFoundException {
            Product product = (Product) objectInputStream.readObject();
            if (field.equals("category")) {
                if (!Category.isCategoryExist(newInput)) {
                    dataOutputStream.writeUTF("fail");
                } else {
                    SellerAbilitiesController.sendEditProductRequest(person, product, "category", newInput);
                    dataOutputStream.writeUTF("done");
                }
            } else {
                SellerAbilitiesController.sendEditProductRequest(person, product, field, newInput);
                dataOutputStream.writeUTF("done");
            }
            dataOutputStream.flush();
        }

        public void increaseProduct(DataOutputStream dataOutputStream, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            Product product = (Product) objectInputStream.readObject();
            product.setNumberOfProducts(product.getNumberOfProducts() + 1);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void sendDeleteProductRequest(Person person, ObjectInputStream objectInputStream, DataOutputStream dataOutputStream) throws IOException, ClassNotFoundException {
            Product product = (Product) objectInputStream.readObject();
            SellerAbilitiesController.sendDeleteProductRequest(person, product);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }
    }

    private void updateDatabase() {

    }

}
