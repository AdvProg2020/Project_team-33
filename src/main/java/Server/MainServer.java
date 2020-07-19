package Server;

import Server.Controller.BuyerController.BuyerAbilitiesController;
import Server.Controller.CartAndPurchase.CartController;
import Server.Controller.CartAndPurchase.PurchaseController;
import Server.Controller.ManagerController.ManagerAbilitiesController;
import Client.Controller.ProductController.ProductController;
import Client.Controller.RegisterAndLogin.PersonController;
import Client.Controller.RegisterAndLogin.RegisterProcess;
import Server.Controller.SellerController.SellerAbilitiesController;
import Server.Model.*;
import Server.Model.Category.Category;
import Server.Model.Requests.Request;
import Server.Model.Users.*;
import Client.View.LoginAndRegister.LoginMenu;
import Client.View.Menu;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

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
        private Cart cart = new Cart();
        private ArrayList<Person> allMembers;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream,
                             ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, ServerImpl server) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.objectOutputStream = objectOutputStream;
            this.objectInputStream = objectInputStream;
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
                        server.getPerson(dataOutputStream, person);
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
                                splitInput[5], splitInput[6], splitInput[7], dataOutputStream, splitInput[8]);
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
                        String[] splitInput = input.split(",");
                        server.deleteProduct(splitInput[1]);
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
                        server.productSetImageView(splitInput[1], splitInput[2], dataOutputStream);
                    } else if (input.startsWith("sendEditProductRequest")) {
                        String[] splitInput = input.split(",");
                        server.sendEditProductRequest(splitInput[1], splitInput[2], objectInputStream, dataOutputStream, person);
                    } else if (input.startsWith("increaseProduct")) {
                        String[] splitInput = input.split(",");
                        server.increaseProduct(dataOutputStream, splitInput[1]);
                    } else if (input.startsWith("sendDeleteProductRequest")) {
                        String[] splitInput = input.split(",");
                        server.sendDeleteProductRequest(person, splitInput[1], dataOutputStream);
                    } else if (input.startsWith("getAllSellerRequests")) {
                        server.getAllSellerRequests(person, objectOutputStream);
                    } else if (input.startsWith("deleteSellerRequest")) {
                        server.deleteSellerRequest(objectInputStream, person);
                    } else if (input.startsWith("addProductToCart")) {
                        String[] splitInput = input.split(",");
                        server.addProductToCart(person, cart, splitInput[1]);
                    } else if (input.startsWith("addComment")) {
                        String[] splitInput = input.split(",");
                        server.addComment(splitInput[1], splitInput[2], person, dataOutputStream);
                    } else if (input.startsWith("setScore")) {
                        String[] splitInput = input.split(",");
                        server.setScore(splitInput[1], dataOutputStream, person);
                    } else if (input.startsWith("scoreController")) {
                        String[] splitInput = input.split(",");
                        server.scoreController(splitInput[1], splitInput[2], person, dataOutputStream);
                    } else if (input.startsWith("clearCart")) {
                        cart.clear();
                        server.clearCart(dataOutputStream);
                    } else if (input.startsWith("getCart")) {
                        server.getCart(objectOutputStream, cart);
                    } else if (input.startsWith("getCategoryByName")) {
                        String[] splitInput = input.split(",");
                        server.getCategoryByName(splitInput[1], objectOutputStream);
                    } else if (input.startsWith("addToCart")) {
                        String[] splitInput = input.split(",");
                        server.addToCart(person, cart, splitInput[1]);
                    } else if (input.startsWith("getAllCategoryProducts")) {
                        String[] splitInput = input.split(",");
                        server.getAllCategoryProducts(splitInput[1], objectOutputStream);
                    } else if (input.startsWith("changeNumberOfProductsInHashMap")) {
                        String[] splitInput = input.split(",");
                        server.changeNumberOfProductsInHashMap(splitInput[1], splitInput[2], splitInput[3]);
                    } else if (input.startsWith("checkDiscount")) {
                        String[] splitInput = input.split(",");
                        server.checkDiscount(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("purchase")) {
                        String[] splitInput = input.split(",");
                        server.purchase(splitInput[1], splitInput[2], splitInput[3], splitInput[4],
                                splitInput[5], splitInput[6], person, splitInput[7], dataOutputStream);
                    } else if (input.startsWith("checkPublicSale")) {
                        server.checkPublicSale(person, dataOutputStream);
                    } else if (input.startsWith("addPublicSale")) {
                        String[] splitInput = input.split(",");
                        server.addPublicSale(splitInput[1], splitInput[2], person);
                    } else if (input.startsWith("getAllProductsInPublicSale")) {
                        server.getAllProductsInPublicSale(objectOutputStream);
                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

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
                ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream, objectOutputStream, objectInputStream, this).start();
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
                    PersonController.isManagerAccountCreate = true;
                    dataOutputStream.writeUTF(answer.toString());
                    dataOutputStream.flush();
                    return PersonController.mainManager;
                } else {
                    answer.append("2");
                    Person registeringPerson = new Person(username, name, family,
                            phone, email, password);
                    objectOutputStream.writeObject(registeringPerson);
                    objectOutputStream.flush();
                    String message = answer.toString();
                    dataOutputStream.writeUTF(message);
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

        public void getPerson(DataOutputStream dataOutputStream, Person person) throws IOException {
            Gson gson = new Gson();
            String json = gson.toJson(person);
            dataOutputStream.writeUTF("{" + json.substring(json.indexOf("name") - 1));
            dataOutputStream.flush();
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
                                  String reEnterPassword, String name, String family, DataOutputStream dataOutputStream, String type) throws IOException {
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
                if (type.equals("manager")) {
                    new Manager(username, name, family, phone, email, password);
                } else if (type.equals("supporter")) {
                    new Supporter(username, name, family, phone, email, password);
                }
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

        public void deleteProduct(String id) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
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

        public void productSetImageView(String kind, String id, DataOutputStream dataOutputStream) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
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

        public void increaseProduct(DataOutputStream dataOutputStream, String id) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
            product.setNumberOfProducts(product.getNumberOfProducts() + 1);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void sendDeleteProductRequest(Person person, String id, DataOutputStream dataOutputStream) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
            SellerAbilitiesController.sendDeleteProductRequest(person, product);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void getAllSellerRequests(Person person, ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(SellerAbilitiesController.getAllSellerRequests((Seller) person));
            objectOutputStream.flush();
        }

        public void deleteSellerRequest(ObjectInputStream objectInputStream, Person person) throws IOException, ClassNotFoundException {
            Request request = (Request) objectInputStream.readObject();
            SellerAbilitiesController.deleteRequest((Seller) person, request);
        }

        public void addProductToCart(Person person, Cart cart, String id) throws ClassNotFoundException {
            Product product = Product.getProductById(id);
            if (person instanceof Buyer) {
                ((Buyer) person).getCart().addProductToCart(product);
            } else if (person == null) {
                cart.addProductToCart(product);
            }
        }

        public void addComment(String id, String comment, Person person, DataOutputStream dataOutputStream) throws ClassNotFoundException, IOException {
            Product product = Product.getProductById(id);
            StringBuilder answer = new StringBuilder();
            if (comment.isEmpty()) {
                answer.append("1-");
            } else {
                answer.append("0-");
                if (person != null) {
                    answer.append("1-");
                    boolean isBuyerBoughtThisProduct;
                    if (person instanceof Buyer) {
                        assert product != null;
                        isBuyerBoughtThisProduct = product.isBuyerBoughtThisProduct((Buyer) person);
                    } else {
                        isBuyerBoughtThisProduct = false;
                    }
                    Comment newComment = new Comment(person, product,
                            isBuyerBoughtThisProduct, comment);
                    product.addComment(newComment);
                } else {
                    answer.append("0-");
                }
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void setScore(String id, DataOutputStream dataOutputStream, Person person) throws IOException {
            Product product = Product.getProductById(id);
            StringBuilder answer = new StringBuilder();
            if (person instanceof Buyer) {
                answer.append("1-");
                Buyer buyer = (Buyer) person;
                if (ProductController.isBuyerBuyThisProduct(buyer, product)) {
                    answer.append("0-");
                } else {
                    answer.append("1-");
                }
            } else {
                answer.append("0-");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void scoreController(String point, String id, Person person, DataOutputStream dataOutputStream) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
            ProductController.scoreController(Integer.parseInt(point), product, (Buyer) person);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        public void clearCart(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF("cleared");
            dataOutputStream.flush();
        }

        public void getCart(ObjectOutputStream objectOutputStream, Cart cart) throws IOException {
            objectOutputStream.writeObject(cart);
            objectOutputStream.flush();
        }

        public void getCategoryByName(String name, ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(Category.getCategoryByName(name));
            objectOutputStream.flush();
        }

        public void addToCart(Person person, Cart cart, String id) {
            Product product = Product.getProductById(id);
            if (person == null) {
                cart.addProductToCart(product);
            } else {
                ((Buyer) person).getCart().addProductToCart(product);
            }
        }

        public void getAllCategoryProducts(String categoryName, ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(ProductController.getAllCategoryProducts(Objects.requireNonNull(Category.getCategoryByName(categoryName))));
            objectOutputStream.flush();
        }

        public void changeNumberOfProductsInHashMap(String type, String cartNo, String productId) {
            Product product = Product.getProductById(productId);
            Cart cart = Cart.getCartByNo(Integer.parseInt(cartNo));
            if (type.equals("increase")) {
                if (product.getNumberOfProducts() >= CartController.getNumberOfProduct(cart, product) + 1) {
                    CartController.changeNumberOfProductsInHashMap(cart, product, CartController.getNumberOfProduct(cart, product) + 1);
                }
            } else {
                CartController.changeNumberOfProductsInHashMap(cart, product, CartController.getNumberOfProduct(cart, product) - 1);
            }
        }

        public void checkDiscount(String code, Person person, DataOutputStream dataOutputStream) throws IOException {
            StringBuilder answer = new StringBuilder();
            if (!code.isEmpty()) {
                answer.append("1-");
                if (code.length() != 6) {
                    answer.append("1");
                } else if (!PurchaseController.isCodeExistForBuyer((Buyer) person, code)) {
                    answer.append("0");
                }
            } else {
                answer.append("0-");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void purchase(String name, String family, String address, String phone, String email, String code, Person person, String discountType, DataOutputStream dataOutputStream) throws IOException {
            StringBuilder answer = new StringBuilder();
            AtomicBoolean discount = new AtomicBoolean();
            if (discountType.equals("true")) {
                discount.set(true);
            } else {
                discount.set(false);
            }
            boolean purchase = true;

            if (name.isEmpty()) {
                answer.append("1-");
                purchase = false;
            } else {
                answer.append("0-");
            }

            if (family.isEmpty()) {
                answer.append("1-");
                purchase = false;
            } else {
                answer.append("0-");
            }

            if (address.isEmpty()) {
                answer.append("1-");
                purchase = false;
            } else {
                answer.append("0-");
            }

            if (phone.isEmpty()) {
                answer.append("1-");
                purchase = false;
            } else if (!PersonController.phoneTypeErr(phone)) {
                answer.append("2-");
                purchase = false;
            } else {
                answer.append("0-");
            }

            if (email.isEmpty()) {
                answer.append("1-");
                purchase = false;
            } else if (!PersonController.emailTypeErr(email)) {
                answer.append("2-");
                purchase = false;
            } else {
                answer.append("0-");
            }

            if (!code.isEmpty()) {
                answer.append("1-");
                if (code.length() != 6) {
                    answer.append("1-");
                    purchase = false;
                    discount.set(false);
                } else if (!PurchaseController.isCodeExistForBuyer((Buyer) person, code)) {
                    answer.append("2-");
                    purchase = false;
                    discount.set(false);
                }
            } else {
                answer.append("0-");
                discount.set(false);
            }

            if (purchase) {
                answer.append("pass-");
                if (discount.get()) {
                    answer.append("1-");
                    double totalPrice = ((Buyer) person).getCart().getMoneyForPurchase();
                    double discountPercent = Discount.getDiscountByCode(code).getDiscountPercent();
                    double totalPriceAfterDiscount = (totalPrice * ((100 - discountPercent) / 100));
                    double discountMax = Discount.getDiscountByCode(code).getMaxDiscount();
                    if (totalPrice - totalPriceAfterDiscount > discountMax) {
                        answer.append("1-");
                        if (((Buyer) person).getMoney() >= totalPrice - discountMax) {
                            answer.append("1-");
                            PurchaseController.doPurchase((Buyer) person, discountMax);
                            ((Buyer) person).getCart().clear();
                        } else {
                            answer.append("0-");
                        }
                    } else {
                        answer.append("0-");
                        if (((Buyer) person).getMoney() >= totalPrice - (totalPrice * ((discountPercent) / 100))) {
                            answer.append("1-");
                            PurchaseController.doPurchase((Buyer) person, (totalPrice * ((discountPercent) / 100)));
                            ((Buyer) person).getCart().clear();
                        } else {
                            answer.append("0-");
                        }
                    }
                } else {
                    answer.append("0-");
                    if (((Buyer) person).getMoney() >= ((Buyer) person).getCart().getMoneyForPurchase()) {
                        answer.append("1-");
                        PurchaseController.doPurchase((Buyer) person, 0);
                        ((Buyer) person).getCart().clear();
                    } else {
                        answer.append("0-");
                    }
                }

            } else {
                answer.append("fail-");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();

        }

        public void checkPublicSale(Person person, DataOutputStream dataOutputStream) throws IOException {
            StringBuilder answer = new StringBuilder();
            if (!((Seller) person).isUsePublicSale()) {
                answer.append("yes");
            } else {
                answer.append("no");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        public void addPublicSale(String id, String time, Person person) {
            Product product = Product.getProductById(id);
            String[] input1 = time.split(":");
            LocalTime endTime = LocalTime.of(Integer.parseInt(input1[0]), Integer.parseInt(input1[1]));
            new PublicSale((Seller) person, product, endTime);
        }

        public void getAllProductsInPublicSale(ObjectOutputStream objectOutputStream) throws IOException {
            ArrayList<PublicSale> publicSales = PublicSale.getAllPublicSales();
            objectOutputStream.writeObject(publicSales);
        }
    }

    private void updateDatabase() {

    }

}
