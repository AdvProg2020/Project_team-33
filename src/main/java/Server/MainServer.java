package Server;

import Client.Controller.ProductController.ProductController;
import Client.Controller.RegisterAndLogin.PersonController;
import Client.Controller.RegisterAndLogin.RegisterProcess;
import Server.Controller.BuyerController.BuyerAbilitiesController;
import Server.Controller.CartAndPurchase.CartController;
import Server.Controller.CartAndPurchase.PurchaseController;
import Server.Controller.ManagerController.ManagerAbilitiesController;
import Server.Controller.SellerController.SellerAbilitiesController;
import Server.Model.*;
import Server.Model.Category.Category;
import Server.Model.Requests.Request;
import Server.Model.Users.*;
import com.google.gson.Gson;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;

public class MainServer {

    public static void main(String[] args) throws IOException {
        new ServerImpl().run();
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private Socket bankSocket;
        private DataOutputStream bankOutputStream;
        private DataInputStream bankInputStream;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;
        private ServerImpl server;
        private Person person;
        private Person loginPerson;
        private Cart cart = new Cart();
        private ArrayList<Person> allMembers;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream, ServerImpl server) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.server = server;
        }

        public void handleClient() {
            Manager mainManager = new Manager("amk_amir", "Amir Mahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321");
            PersonController.mainManager = mainManager;
            PersonController.isManagerAccountCreate = true;
            try {
                String input = "";
                while (true) {
                    input = dataInputStream.readUTF();
                    if (input.startsWith("createAccount")) {
                        String[] splitInput = input.split(",");
                        person = server.createAccount(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], splitInput[6], splitInput[7], dataOutputStream);
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
                        server.getAllMembers(dataOutputStream, allMembers);
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
                        server.getMainManager(dataOutputStream);
                    } else if (input.startsWith("deleteUser")) {
                        String[] splitInput = input.split(",");
                        server.deleteUser(splitInput[1]);
                    } else if (input.startsWith("createDiscount")) {
                        String[] splitInput = input.split(",");
                        server.createDiscount(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], dataOutputStream);
                    } else if (input.startsWith("getAllDiscounts")) {
                        server.getAllDiscounts(dataOutputStream);
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
                        server.getAllCategories(dataOutputStream);
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
                        server.getRequests(dataOutputStream);
                    } else if (input.startsWith("deleteRequest")) {
                        server.deleteRequest(dataInputStream);
                    } else if (input.startsWith("setRequestCondition")) {
                        String[] splitInput = input.split(",");
                        server.setRequestCondition(splitInput[1], dataInputStream);
                    } else if (input.startsWith("getProducts")) {
                        server.getProducts(dataOutputStream);
                    } else if (input.startsWith("deleteProduct")) {
                        String[] splitInput = input.split(",");
                        server.deleteProduct(splitInput[1]);
                    } else if (input.startsWith("setImageView")) {
                        String[] splitInput = input.split(",");
                        server.setImageView(splitInput[1], person);
                    } else if (input.startsWith("getProductsForSeller")) {
                        server.getProductsForSeller(person, dataOutputStream);
                    } else if (input.startsWith("addProduct")) {
                        String[] splitInput = input.split(",");
                        server.addProduct(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], person, dataOutputStream);
                    } else if (input.startsWith("productSetImageView")) {
                        String[] splitInput = input.split(",");
                        server.productSetImageView(splitInput[1], splitInput[2], dataOutputStream);
                    } else if (input.startsWith("sendEditProductRequest")) {
                        String[] splitInput = input.split(",");
                        server.sendEditProductRequest(splitInput[1], splitInput[2], splitInput[3], dataOutputStream, person);
                    } else if (input.startsWith("increaseProduct")) {
                        String[] splitInput = input.split(",");
                        server.increaseProduct(dataOutputStream, splitInput[1]);
                    } else if (input.startsWith("sendDeleteProductRequest")) {
                        String[] splitInput = input.split(",");
                        server.sendDeleteProductRequest(person, splitInput[1], dataOutputStream);
                    } else if (input.startsWith("getAllSellerRequests")) {
                        server.getAllSellerRequests(person, dataOutputStream);
                    } else if (input.startsWith("deleteSellerRequest")) {
                        server.deleteSellerRequest(dataInputStream, person);
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
                        server.getAllProductsInPublicSale(dataOutputStream);
                    } else if (input.startsWith("getToken")) {
                        String token = server.getToken();
                    } else if (input.startsWith("condition of seller with id")) {
                        server.getSellerCondition(input.substring(input.indexOf("-") + 1), dataOutputStream);
                    } else if (input.startsWith("participateInPublicSale")) {
                        String[] splitInput = input.split(",");
                        server.participateInPublicSale(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("isOnline")) {
                        server.isUserOnline(input.substring(input.indexOf("-") + 1), dataOutputStream);
                    } else if (input.startsWith("setOnline")) {
                        server.setOnlineOfUser(input.substring(input.indexOf("-") + 1, input.lastIndexOf("-")), input.substring(input.lastIndexOf("-") + 1));
                    } else if (input.startsWith("inputMoneyInPublicSale")) {
                        String[] splitInput = input.split(",");
                        server.inputMoneyInPublicSale(splitInput[1], splitInput[2], person, dataOutputStream);
                    } else if (input.startsWith("getPublicSaleChat")) {
                        String[] splitInput = input.split(",");
                        server.getPublicSaleChat(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("sendMessageInPublicSale")) {
                        String[] splitInput = input.split(",,");
                        server.sendMessageInPublicSale(splitInput[1], splitInput[2], person);
                    } else if (input.startsWith("getAllOnlineSupporters")) {
                        server.getAllOnlineSupporters(dataOutputStream);
                    } else if (input.startsWith("setSupporterForBuyer")) {
                        String[] splitInput = input.split(",");
                        server.setSupporterForBuyer(splitInput[1], person);
                    } else if (input.startsWith("getBuyerSupporterChat")) {
                        String[] splitInput = input.split(",");
                        server.getBuyerSupporterChat(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else {
//                        dataOutputStream.writeUTF("done");
//                        dataOutputStream.flush();
//                        clientSocket.close();
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            handleClient();
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ServerImpl {
        public ServerSocket serverSocket;
        public Socket bankSocket;
        DataInputStream bankDataInputStream;
        DataOutputStream bankDataOutputStream;

        private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        private void run() throws IOException {
            serverSocket = new ServerSocket(8000);
            Socket clientSocket;
            bankSocket = new Socket("localhost", 8999);
            bankDataInputStream = new DataInputStream(new BufferedInputStream(bankSocket.getInputStream()));
            bankDataOutputStream = new DataOutputStream(new BufferedOutputStream(bankSocket.getOutputStream()));
            while (true) {
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream, this).start();
            }


        }

        //Done
        private Person createAccount(String username, String name, String family, String email, String password, String reenterPassword,
                                     String phone, DataOutputStream dataOutputStream) throws IOException {
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

//            bankDataOutputStream.writeUTF("create_account " + name + " " + );

        }

        //Done
        public Person chooseBuyerRole(Person person, DataOutputStream dataOutputStream) throws IOException {
            Buyer buyer = RegisterProcess.createAccountForBuyer(person.getUsername(), person.getName(), person.getFamily(),
                    person.getPhone(), person.getEmail(), person.getPassword());
            Person.deleteUser(person);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
            return buyer;
        }

        //Done
        public Person chooseSellerRole(Person person, String company, DataOutputStream dataOutputStream) throws IOException {
            Seller seller = new Seller(person.getUsername(), person.getName(), person.getFamily(),
                    person.getPhone(), person.getEmail(), person.getPassword(), company);
            Person.deleteUser(person);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
            return seller;
        }

        //Done
        public void showFirstPage(Person person, DataOutputStream dataOutputStream) throws IOException {
            if (person instanceof Buyer) {
                dataOutputStream.writeUTF("buyer");
                dataOutputStream.flush();
            } else if (person instanceof Seller) {
                dataOutputStream.writeUTF("seller");
                dataOutputStream.flush();
            }
        }

        //Done
        public void checkMainManager(DataOutputStream dataOutputStream) throws IOException {
            if (PersonController.isManagerAccountCreate) {
                dataOutputStream.writeUTF("yes");
            } else {
                dataOutputStream.writeUTF("no");
            }
            dataOutputStream.flush();
        }

        //Done
        public void logout(Person person, DataOutputStream dataOutputStream) throws IOException {
            person.setOnline(false);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
            person = null;
        }

        //Done
        public void getPerson(DataOutputStream dataOutputStream, Person person) throws IOException {
            Gson gson = new Gson();
            if (person instanceof Buyer) {
                String json = "buyer-" + gson.toJson(person, Person.class);
                dataOutputStream.writeUTF(json);
            } else if (person instanceof Seller) {
                String json = "seller-" + gson.toJson(person, Person.class);
                dataOutputStream.writeUTF(json);
            } else if (person instanceof Manager) {
                String json = "manager-" + gson.toJson(person, Person.class);
                dataOutputStream.writeUTF(json);
            } else {
                String json = gson.toJson(person, Person.class);
                dataOutputStream.writeUTF(json);
            }
            dataOutputStream.flush();
        }

        //Done
        public void getSellerCondition(String id, DataOutputStream dataOutputStream) throws IOException {
            Seller seller = Seller.getSellerByUsername(id);
            dataOutputStream.writeUTF(seller.getCanSellerCreate());
            dataOutputStream.flush();
        }

        //Done
        public void isUserOnline(String id, DataOutputStream dataOutputStream) throws IOException {
            Person person1 = Person.getPersonByUsername(id);
            if (person1.isOnline()) {
                dataOutputStream.writeUTF("yes");
            } else {
                dataOutputStream.writeUTF("no");
            }
            dataOutputStream.flush();
        }

        //Done
        public void setOnlineOfUser(String id, String condition) {
            Person person1 = Person.getPersonByUsername(id);
            if (condition.equals("yes")) {
                person1.setOnline(true);
            } else {
                person1.setOnline(false);
            }
        }

        //Done
        public void getAllMembers(DataOutputStream dataOutputStream, ArrayList<Person> allMembers) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ManagerAbilitiesController.getAllMembers().size()));
            dataOutputStream.flush();
            for (Person member : ManagerAbilitiesController.getAllMembers()) {
                Gson gson = new Gson();
                String json = gson.toJson(member, Person.class);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        //ToDo
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

        //Done
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
                        person.setOnline(true);
                    } else if (seller.getCanSellerCreate().equals("Unknown")) {
                        answer.append("unknown");
                        person = null;
                    }
                } else if (person instanceof Buyer) {
                    answer.append("buyer");
                    person.setOnline(true);
                } else if (person instanceof Supporter) {
                    answer.append("supporter");
                    person.setOnline(true);
                } else {
                    answer.append("manager");
                    assert person != null;
                    person.setOnline(true);
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

        public void getMainManager(DataOutputStream dataOutputStream) throws IOException {
            Gson gson = new Gson();
            String json = gson.toJson(PersonController.mainManager);
            dataOutputStream.writeUTF(json);
            dataOutputStream.flush();
        }

        public void deleteUser(String username) {
            ManagerAbilitiesController.deleteUser(Person.getPersonByUsername(username));
        }

        //Done
        public void createDiscount(String code, String discount, String max, String start, String end, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();

            if (code.equals(" ")) {
                answer.append("1-");
                create = false;
            } else if (code.length() != 6) {
                answer.append("2-");
                create = false;
            } else if (Discount.isDiscountExist(code)) {
                answer.append("3-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (discount.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (max.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (start.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (end.equals(" ")) {
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

        //ToDo
        public void getAllDiscounts(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ManagerAbilitiesController.getAllDiscounts().size()));
            dataOutputStream.flush();
            for (Discount discount : ManagerAbilitiesController.getAllDiscounts()) {
                dataOutputStream.writeUTF(discount.getCode() + "-" + discount.getStartTime().toString() + "-" + discount.getEndTime().toString() + "-" + discount.getDiscountPercent() + "-" + discount.getMaxDiscount());
                dataOutputStream.flush();
            }
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

        public void getAllCategories(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ManagerAbilitiesController.getAllDiscounts().size()));
            dataOutputStream.flush();
            for (Category category : ManagerAbilitiesController.getAllCategories()) {
                Gson gson = new Gson();
                String json = gson.toJson(category);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
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

        //Done
        public void getRequests(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ManagerAbilitiesController.getAllRequests().size()));
            dataOutputStream.flush();
            for (Request request : ManagerAbilitiesController.getAllRequests()) {
                String json = request.getId() + "-" + request.getType() + "-" + request.getCondition() + "-" + request.getSender().getUsername();
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        //Done
        public void deleteRequest(DataInputStream dataInputStream) throws IOException {
            Request request = Request.getRequestById(Integer.parseInt(dataInputStream.readUTF()));
            ManagerAbilitiesController.deleteRequest(request);
        }

        public void setRequestCondition(String condition, DataInputStream dataInputStream) throws IOException, ClassNotFoundException {
            Request request = Request.getRequestById(Integer.parseInt(dataInputStream.readUTF()));
            if (condition.equals("Accept")) {
                ManagerAbilitiesController.setConditionForRequest(request, "Accept");
            } else {
                ManagerAbilitiesController.setConditionForRequest(request, "Decline");
            }
        }

        public void getProducts(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Product.getAllProducts().size()));
            dataOutputStream.flush();
            for (Product product : Product.getAllProducts()) {
                Gson gson = new Gson();
                String json = gson.toJson(product);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        public void deleteProduct(String id) throws ClassNotFoundException {
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

        public void getProductsForSeller(Person person, DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(SellerAbilitiesController.getAllProducts((Seller) person).size()));
            dataOutputStream.flush();
            for (Product product : SellerAbilitiesController.getAllProducts((Seller) person)) {
                Gson gson = new Gson();
                String json = gson.toJson(product);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
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

        public void sendEditProductRequest(String field, String newInput, String id, DataOutputStream dataOutputStream, Person person) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
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

        public void getAllSellerRequests(Person person, DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(SellerAbilitiesController.getAllSellerRequests((Seller) person).size()));
            dataOutputStream.flush();
            for (Request request : SellerAbilitiesController.getAllSellerRequests((Seller) person)) {
                Gson gson = new Gson();
                String json = gson.toJson(request);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        public void deleteSellerRequest(DataInputStream dataInputStream, Person person) throws IOException {
            Gson gson = new Gson();
            Request request = gson.fromJson(dataInputStream.readUTF(), Request.class);
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

        public void getAllProductsInPublicSale(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(PublicSale.getAllPublicSales().size()));
            dataOutputStream.flush();
            for (PublicSale publicSale : PublicSale.getAllPublicSales()) {
                Gson gson = new Gson();
                String json = gson.toJson(publicSale);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        public String getToken() {
            StringBuilder builder = new StringBuilder();
            int count = 16;
            while (count != 0) {
                int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
                count--;
            }
            return builder.toString();
        }

        public void participateInPublicSale(String id, Person person, DataOutputStream dataOutputStream) {
            PublicSale publicSale = PublicSale.getPublicSaleById(Integer.parseInt(id));
            publicSale.addParticipant((Buyer) person);
        }

        public void inputMoneyInPublicSale(String id, String money, Person person, DataOutputStream dataOutputStream) throws IOException {
            PublicSale publicSale = PublicSale.getPublicSaleById(Integer.parseInt(id));
            if (money.matches("\\d+")) {
                if (publicSale.setMoney((Buyer) person, Integer.parseInt(money))) {
                    dataOutputStream.writeUTF("pass");
                } else {
                    dataOutputStream.writeUTF("fail");
                }
            } else {
                dataOutputStream.writeUTF("fail");
            }
            dataOutputStream.flush();
        }

        public void getPublicSaleChat(String id, DataOutputStream dataOutputStream) throws IOException {
            PublicSale publicSale = PublicSale.getPublicSaleById(Integer.parseInt(id));
            dataOutputStream.writeUTF(String.valueOf(publicSale.getChats().size()));
            dataOutputStream.flush();
            for (Chat chat : publicSale.getChats()) {
                Gson gson = new Gson();
                String json = gson.toJson(chat, Chat.class);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        public void sendMessageInPublicSale(String id, String message, Person person) {
            Chat chat = new Chat(person, message);
            PublicSale publicSale = PublicSale.getPublicSaleById(Integer.parseInt(id));
            publicSale.addChat(chat);
        }

        public void getAllOnlineSupporters(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Supporter.getAllOnlineSupporters().size()));
            dataOutputStream.flush();
            for (Supporter supporter : Supporter.getAllOnlineSupporters()) {
                Gson gson = new Gson();
                String json = gson.toJson(supporter, Supporter.class);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        public void setSupporterForBuyer(String id, Person person) {
            Supporter supporter = Supporter.getSupporterById(Integer.parseInt(id));
            ((Buyer)person).setSupporter(supporter);
        }

        public void getBuyerSupporterChat(String id, Person person, DataOutputStream dataOutputStream) throws IOException {
            Supporter supporter = Supporter.getSupporterById(Integer.parseInt(id));
            assert supporter != null;
            ArrayList<Chat> chats = supporter.getBuyerChat(person);
            dataOutputStream.writeUTF(String.valueOf(chats.size()));
            dataOutputStream.flush();
            for (Chat chat : chats) {
                Gson gson = new Gson();
                String json = gson.toJson(chat, Chat.class);
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }

        }
    }

    private void updateDatabase() {

    }

}
