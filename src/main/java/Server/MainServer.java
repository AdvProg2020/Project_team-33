package Server;

import Server.Controller.ProductController.ProductController;
import Server.Controller.RegisterAndLogin.PersonController;
import Server.Controller.RegisterAndLogin.RegisterProcess;
import Server.Controller.BuyerController.BuyerAbilitiesController;
import Server.Controller.CartAndPurchase.CartController;
import Server.Controller.CartAndPurchase.PurchaseController;
import Server.Controller.ManagerController.ManagerAbilitiesController;
import Server.Controller.SellerController.SellerAbilitiesController;
import Server.Model.*;
import Server.Model.Category.Category;
import Server.Model.Logs.BuyLog;
import Server.Model.Logs.SellLog;
import Server.Model.Requests.Request;
import Server.Model.Users.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MainServer {
    public static HashMap<String, Person> personToken = new HashMap<>();
    public static ArrayList<Integer> blockedIp = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new ServerImpl().run();
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private String IP;
        private Socket bankSocket;
        private LocalTime time;
        private DataOutputStream bankOutputStream;
        private DataInputStream bankInputStream;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private ServerImpl server;
        private Person person;
        private String token;
        private String bankToken;
        private Person loginPerson;
        private Person personWithToken;
        private Cart cart = new Cart();
        private ArrayList<Person> allMembers;


        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream, DataOutputStream bankOutputStream, DataInputStream bankInputStream, ServerImpl server) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.bankOutputStream = bankOutputStream;
            this.bankInputStream = bankInputStream;
            this.server = server;
        }

        public void handleClient() {
//            Manager mainManager = new Manager("a", "Amir Mahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "a");
//            PersonController.mainManager = mainManager;
//            PersonController.isManagerAccountCreate = true;
//            LoginMenu.currentPerson = new Buyer("saba_sk", "saba", "keshavarz", "09912310335", "saba@yahoo.com", "sabasasa");
//            Seller seller = new Seller("b", "amirsalar", "ansari", "09131789201", "a@a.com", "b", "yes");
//            ArrayList<String> strings = new ArrayList<>();
//            strings.add("Samsung");
//            strings.add("Apple");
//            strings.add("LG");
//            Category category = new Category("a", null, strings);
//            new Product("981710", "galaxy S6", "Samsung", 2000000, seller, category, "A good phone", "Unknown");
//
//            Category categoryy = new Category("b", null, strings);

            try {
                String message = "";
                String input = "";
                while (true) {
                    message = dataInputStream.readUTF();
                    if (message.startsWith("createAccount") || message.startsWith("login") || message.startsWith("checkMainManager") || message.startsWith("getToken") || message.startsWith("getRole id-")) {
                        input = message;
                    } else {
                        String getToken = message.substring(message.lastIndexOf(",") + 1);
                        input = message.substring(0, message.lastIndexOf(","));
                    }

                    if (input.startsWith("createAccount")) {
                        String[] splitInput = input.split(",");
                        person = server.createAccount(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], splitInput[6], splitInput[7], dataOutputStream);
                    } else if (input.startsWith("createMainBankAccount")) {
                        String[] splitInput = input.split("-");
                        server.createBankAccountForManager(splitInput, bankOutputStream, bankInputStream, dataOutputStream);
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
                        token = "";
                        person = null;
                    } else if (input.startsWith("getPerson")) {
                        server.getPerson(dataOutputStream, person);
                    } else if (input.startsWith("getAllMembers")) {
                        server.getAllMembers(dataOutputStream, allMembers);
                    } else if (input.startsWith("editPersonalInfo")) {
                        String[] splitInput = input.split(",");
                        server.editPersonalInfo(splitInput[1], splitInput[2], person, dataOutputStream);
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
                        server.deleteDiscount(splitInput[1]);
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
                    } else if (input.equals("getProducts")) {
                        server.getProducts(dataOutputStream);
                    } else if (input.startsWith("deleteProduct")) {
                        String[] splitInput = input.split(",");
                        server.deleteProduct(splitInput[1]);
                    } else if (input.startsWith("setImageView")) {
                        String[] splitInput = input.split(",");
                        server.setImageView(splitInput[1], person);
                    } else if (input.equals("getProductsForSeller")) {
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
                    } else if (input.startsWith("getCategoryProducts")) {
                        String[] splitInput = input.split(",");
                        server.getCategoryProducts(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("addToCart")) {
                        String[] splitInput = input.split(",");
                        server.addToCart(person, cart, splitInput[1]);
                    } else if (input.startsWith("changeNumberOfProductsInHashMap")) {
                        String[] splitInput = input.split(",");
                        server.changeNumberOfProductsInHashMap(splitInput[1], splitInput[2], person, cart);
                    } else if (input.startsWith("checkDiscount")) {
                        String[] splitInput = input.split(",");
                        server.checkDiscount(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("checkPublicSale")) {
                        server.checkPublicSale(person, dataOutputStream);
                    } else if (input.startsWith("addPublicSale")) {
                        String[] splitInput = input.split(",");
                        server.addPublicSale(splitInput[1], splitInput[2], person);
                    } else if (input.startsWith("getAllProductsInPublicSale")) {
                        server.getAllProductsInPublicSale(dataOutputStream);
                    } else if (input.startsWith("getToken")) {
                        token = server.getToken(dataOutputStream);
                    } else if (input.startsWith("condition of seller with id")) {
                        server.getSellerCondition(input.substring(input.indexOf("-") + 1), dataOutputStream);
                    } else if (input.startsWith("participateInPublicSale")) {
                        String[] splitInput = input.split(",");
                        server.participateInPublicSale(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("isOnline")) {
                        server.isUserOnline(input.substring(input.indexOf("-") + 1), dataOutputStream);
                    } else if (input.startsWith("setOnline")) {
                        server.setOnlineOfUser(input.substring(input.indexOf("-") + 1, input.lastIndexOf("-")), input.substring(input.lastIndexOf("-") + 1));
                    } else if (input.startsWith("name of company")) {
                        String id = input.substring(input.indexOf("-") + 1);
                        server.getCompanyOfSeller(id, dataOutputStream);
                    } else if (input.startsWith("getBuyersOfProduct")) {
                        server.getProductBuyers(input.substring(input.indexOf("-") + 1), dataOutputStream);
                    } else if (input.startsWith("inputMoneyInPublicSale")) {
                        String[] splitInput = input.split(",");
                        server.inputMoneyInPublicSale(splitInput[1], splitInput[2], person, dataOutputStream);
                    } else if (input.startsWith("getPublicSaleChat")) {
                        String[] splitInput = input.split(",");
                        server.getPublicSaleChat(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("sendMessageInPublicSale")) {
                        String[] splitInput = input.split(",");
                        server.sendMessageInPublicSale(splitInput[1], splitInput[2], person);
                    } else if (input.startsWith("getAllOnlineSupporters")) {
                        server.getAllOnlineSupporters(dataOutputStream);
                    } else if (input.startsWith("setSupporterForBuyer")) {
                        String[] splitInput = input.split(",");
                        server.setSupporterForBuyer(splitInput[1], person);
                    } else if (input.startsWith("getBuyerSupporterChat")) {
                        String[] splitInput = input.split(",");
                        server.getBuyerSupporterChat(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("sendMessageBuyerSupporter")) {
                        String[] splitInput = input.split(",");
                        server.sendMessageBuyerSupporter(splitInput[1], splitInput[2], person);
                    } else if (input.startsWith("sendMessageSupporterBuyer")) {
                        String[] splitInput = input.split(",");
                        server.sendMessageSupporterBuyer(splitInput[1], splitInput[2], person);
                    } else if (input.startsWith("balanceOfSeller")) {
                        server.getSellerBalance(input.substring(input.indexOf("-")), dataOutputStream);
                    } else if (input.startsWith("getBuyerDiscounts")) {
                        server.getBuyerDiscounts(dataOutputStream, person);
                    } else if (input.startsWith("getCartProducts")) {
                        server.getProductsOfCart(dataOutputStream, person, cart);
                    } else if (input.startsWith("getNumberOfProductInCart")) {
                        server.getNumberOfProductsInCart(dataOutputStream, person, cart, input.substring(input.indexOf("-") + 1));
                    } else if (input.startsWith("getPurchaseMoney")) {
                        server.getPurchaseMoney(dataOutputStream, person, cart);
                    } else if (input.startsWith("getSupporterBuyerChat")) {
                        String[] splitInput = input.split(",");
                        server.getSupporterBuyerChat(splitInput[1], person, dataOutputStream);
                    } else if (input.startsWith("getAllBuyersWithSupporter")) {
                        String[] splitInput = input.split(",");
                        server.getAllBuyersWithSupporter(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("expirePublicSale")) {
                        String[] splitInput = input.split(",");
                        server.expirePublicSale(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("setLeastMoney")) {
                        String[] splitInput = input.split(",");
                        server.setLeastMoney(splitInput[1]);
                    } else if (input.startsWith("setWage")) {
                        String[] splitInput = input.split(",");
                        server.setWage(splitInput[1]);
                    } else if (input.startsWith("getTokenFromBank")) {
                        String[] splitInput = input.split(",");

                    } else if (input.startsWith("buyLogs")) {
                        server.getBuyerBuyLogs(dataOutputStream, person);
                    } else if (input.startsWith("buylogProducts")) {
                        server.getBuyLogProducts(dataOutputStream, input.substring(input.indexOf("-") + 1));
                    } else if (input.startsWith("sellLogs")) {
                        server.getSellLog(dataOutputStream, person);
                    } else if (input.startsWith("isAuctionExist")) {
                        server.isAuctionExist(dataOutputStream, input.substring(input.indexOf("-") + 1));
                    } else if (input.startsWith("isProductInAuction")) {
                        server.productInAuction(dataOutputStream, input.substring(input.indexOf("-") + 1));
                    } else if (input.startsWith("sendAddAuctionRequest")) {
                        String[] strings = input.split("-");
                        server.sendAddAuctionRequest(person, strings, dataOutputStream, dataInputStream);
                    } else if (input.startsWith("auctionsOfSeller")) {
                        server.getSellerAuctions(person, dataOutputStream);
                    } else if (input.startsWith("editAuction")) {
                        String[] strings = input.split("-");
                        server.editAuctionInfo(strings, person);
                    } else if (input.startsWith("productsOfAuction")) {
                        server.getAuctionProducts(input.substring(input.indexOf("-") + 1), dataOutputStream);
                    } else if (input.startsWith("removeProductOfAuction")) {
                        String[] strings = input.split("-");
                        server.removeProductOfAuction(dataOutputStream, strings);
                    } else if (input.startsWith("discountOfProduct")) {
                        server.getProductDiscount(dataOutputStream, input.substring(input.indexOf("-") + 1));
                    } else if (input.startsWith("getRole")) {
                        server.getRole(dataOutputStream, input.substring(input.indexOf("-") + 1));
                    } else if (input.startsWith("allLogs")) {
                        server.getAllBuyLogs(dataOutputStream);
                    } else if (input.startsWith("setDeliveryOfLog")) {
                        String[] strings = input.split("-");
                        server.setDeliveryOfLog(strings[1], strings[2]);
                    } else if (input.startsWith("address")) {
                        server.getBuyLogAddress(dataOutputStream, input.substring(input.indexOf("-") + 1));
                    } else if (input.startsWith("getBankBalance")) {
                        String[] strings = input.split("-");
                        server.getBalance(bankOutputStream, bankInputStream, dataOutputStream, strings);
                    } else if (input.startsWith("createBankAccount")) {
                        String[] splitInput = input.split("-");
                        server.createBankAccount(splitInput, bankOutputStream, bankInputStream, dataOutputStream, person);
                    } else if (input.startsWith("getPublicSaleEndTime")) {
                        String[] splitInput = input.split(",");
                        server.getPublicSaleEndTime(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("addFile")) {
                        String[] splitInput = input.split(",");
                        server.addFile(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], splitInput[6], dataOutputStream);
                    } else if (input.startsWith("uploadFile")) {
                        String[] splitInput = input.split(",");
                        server.uploadFile(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], splitInput[6], person, dataInputStream);
                    } else if (input.startsWith("getAllCommentsForProduct")) {
                        String[] splitInput = input.split(",");
                        server.getAllCommentsForProduct(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("getScoreForProduct")) {
                        String[] splitInput = input.split(",");
                        server.getScoreForProduct(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("getNumberForProduct")) {
                        String[] splitInput = input.split(",");
                        server.getNumberForProduct(splitInput[1], dataOutputStream);
                    } else if (input.startsWith("getAccountId")) {
                        server.getAccountId(dataOutputStream, person);
                    } else if (input.startsWith("getWalletMoney")) {
                        server.getMoneyInWallet(dataOutputStream, person);
                    } else if (input.startsWith("increaseBalance")) {
                        String[] strings = input.split("-");
                        server.increaseBalance(dataOutputStream, bankOutputStream, bankInputStream, strings);
                    } else if (input.startsWith("decreaseBalance")) {
                        String[] strings = input.split("-");
                        server.decreaseBalance(dataOutputStream, bankOutputStream, bankInputStream, strings);
                    } else if (input.startsWith("chargeWallet")) {
                        String[] strings = input.split("-");
                        server.chargeWallet(dataOutputStream, bankOutputStream, bankInputStream, strings, person);
                    } else if (input.startsWith("decreaseWallet")) {
                        String[] strings = input.split("-");
                        server.decreaseWallet(dataOutputStream, strings, person);
                    } else if (input.startsWith("getManagerBalance")) {
                        String[] strings = input.split("-");
                        server.getManagerBalance(dataOutputStream, strings, bankOutputStream, bankInputStream);
                    } else if (input.startsWith("getProductById")) {
                        String[] strings = input.split(",");
                        server.getProductById(strings[1], dataOutputStream);
                    } else if (input.startsWith("haveEnoughMoneyForPurchaseInWallet")) {
                        server.getMoneyForPurchaseInWallet(dataOutputStream, person);
                    } else if (input.startsWith("haveEnoughMoneyForPurchaseInBank")) {
                        String[] strings = input.split("-");
                        server.getMoneyForPurchaseInBank(dataOutputStream, person, strings, bankInputStream, bankOutputStream);
                    } else if (input.startsWith("doPurchaseWithWallet")) {
                        String[] strings = input.split("-");
                        server.doPurchaseWithWallet(person, strings[1], strings[2]);
                    } else if (input.startsWith("doPurchaseWithBank")) {
                        String[] strings = input.split("-");
                        server.doPurchaseWithBank(person, bankOutputStream, bankInputStream, strings, dataOutputStream);
                    } else if (input.startsWith("doPurchaseForPublicSale")) {
                        String[] strings = input.split("-");
                        server.doPurchaseForPublicSale(person, strings[1], strings[2], strings[3]);
                    } else if (input.startsWith("isFileOrNot")) {
                        String[] strings = input.split(",");
                        server.isFileOrNot(strings[1], dataOutputStream);
                    } else if (input.startsWith("downloadFile")) {
                        String[] strings = input.split(",");
                        server.downloadFile(strings[1], dataOutputStream);
                    } else if (input.startsWith("productScore")) {
                        server.getProductScore(dataOutputStream, input.substring(input.lastIndexOf("-") + 1));
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

        private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        private void run() throws IOException {
            serverSocket = new ServerSocket(8881);
            bankSocket = new Socket("127.0.0.1", 2222);
            Socket clientSocket;

            while (true) {
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                DataInputStream bankInputStream = new DataInputStream(new BufferedInputStream(bankSocket.getInputStream()));
                DataOutputStream bankOutputStream = new DataOutputStream(new BufferedOutputStream(bankSocket.getOutputStream()));
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream, bankOutputStream, bankInputStream,
                        this).start();
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
            } else if (person instanceof Supporter) {
                String json = "supporter-" + gson.toJson(person, Person.class);
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

        //DOne
        public void editPersonalInfo(String field, String newInput, Person person, DataOutputStream dataOutputStream) throws IOException {
            BuyerAbilitiesController.editPersonalInfo(person, field, newInput);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
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

            if (username.equals(" ")) {
                answer.append("1-");
                login = false;
            } else {
                answer.append("0-");
            }

            if (password.equals(" ")) {
                answer.append("1-");
                login = false;
            } else {
                answer.append("0-");
            }

            if ((Person.getPersonByUsername(username) != null) && !Person.getPersonByUsername(username).getPassword().equals(password) && (!password.equals(" "))) {
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

        //Done
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
                    Manager manager = new Manager(username, name, family, phone, email, password);
                    manager.setOnline(false);
                } else if (type.equals("supporter")) {
                    Supporter supporter = new Supporter(username, name, family, phone, email, password);
                    supporter.setOnline(false);
                }
            } else {
                answer.append("fail");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }

        //Done
        public void getMainManager(DataOutputStream dataOutputStream) throws IOException {
            Gson gson = new Gson();
            String json = gson.toJson(PersonController.mainManager);
            dataOutputStream.writeUTF(json);
            dataOutputStream.flush();
        }

        //Done
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

        //Done
        public void getAllDiscounts(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ManagerAbilitiesController.getAllDiscounts().size()));
            dataOutputStream.flush();
            for (Discount discount : ManagerAbilitiesController.getAllDiscounts()) {
                dataOutputStream.writeUTF(discount.getCode() + "-" + discount.getStartTime().toString() + "-" + discount.getEndTime().toString() + "-" + discount.getDiscountPercent() + "-" + discount.getMaxDiscount());
                dataOutputStream.flush();
            }
        }

        //DOne
        public void addDiscountToBuyer(String username, String code, DataOutputStream dataOutputStream) throws IOException {
            if (Buyer.getBuyerByUsername(username) != null) {
                Buyer buyer = (Buyer) Person.getPersonByUsername(username);
                buyer.addDiscount(Discount.getDiscountByCode(code));
                dataOutputStream.writeUTF("pass");
            } else {
                dataOutputStream.writeUTF("fail");
            }
            dataOutputStream.flush();
        }

        //Done
        public void deleteDiscount(String code) {
            ManagerAbilitiesController.deleteDiscount(Discount.getDiscountByCode(code));
        }

        //Done
        public void editDiscount(String code, String field, String newField, DataOutputStream dataOutputStream) throws IOException {
            ManagerAbilitiesController.editDiscount(Discount.getDiscountByCode(code), field, newField);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        //Done
        public void getAllCategories(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ManagerAbilitiesController.getAllCategories().size()));
            dataOutputStream.flush();
            for (Category category : ManagerAbilitiesController.getAllCategories()) {
                dataOutputStream.writeUTF(category.getName() + "-" + category.getDetail1() + "-" + category.getDetail2() + "-" + category.getDetail3());
                dataOutputStream.flush();
            }
        }

        //Done
        public void deleteCategory(String name) {
            ManagerAbilitiesController.deleteCategory(Category.getCategoryByName(name));
        }

        //Done
        public void addCategory(String name, String detail1, String detail2, String detail3, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();

            if (name.equals(" ")) {
                answer.append("1-");
                create = false;
            } else if (Category.isCategoryExist(name)) {
                answer.append("2-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (detail1.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (detail2.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (detail3.equals(" ")) {
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

        //Done
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

        //Done
        public void setRequestCondition(String condition, DataInputStream dataInputStream) throws IOException, ClassNotFoundException {
            Request request = Request.getRequestById(Integer.parseInt(dataInputStream.readUTF()));
            if (condition.equals("Accept")) {
                ManagerAbilitiesController.setConditionForRequest(request, "Accept");
            } else {
                ManagerAbilitiesController.setConditionForRequest(request, "Decline");
            }
        }

        //Done
        public void getCompanyOfSeller(String id, DataOutputStream dataOutputStream) throws IOException {
            Seller seller = (Seller) Person.getPersonByUsername(id);
            dataOutputStream.writeUTF(seller.getCompany());
            dataOutputStream.flush();
        }

        //Done
        public void getProducts(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Product.getAllProducts().size()));
            dataOutputStream.flush();
            for (Product product : Product.getAllProducts()) {
                dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                        product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                        "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                        product.getNumberOfProducts());
                dataOutputStream.flush();
            }
        }

        //Done
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
            }
        }

        //Done
        public void getProductsForSeller(Person person, DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(SellerAbilitiesController.getAllProducts((Seller) person).size()));
            dataOutputStream.flush();
            for (Product product : SellerAbilitiesController.getAllProducts((Seller) person)) {
                dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                        product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                        "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                        product.getNumberOfProducts());
                dataOutputStream.flush();
            }
        }

        //Done
        public void addProduct(String id, String name, String price, String category, String description, Person person, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();
            if (id.equals(" ")) {
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

            if (name.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (price.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (category.equals(" ")) {
                answer.append("1-");
                create = false;
            } else if (!Category.isCategoryExist(category)) {
                answer.append("2-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (description.equals(" ")) {
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

        //Done
        public void getProductBuyers(String id, DataOutputStream dataOutputStream) throws IOException {
            Product product = Product.getProductById(id);
            dataOutputStream.writeUTF(String.valueOf(product.getAllBuyers().size()));
            dataOutputStream.flush();
            for (Buyer allBuyer : product.getAllBuyers()) {
                dataOutputStream.writeUTF(allBuyer.getUsername());
                dataOutputStream.flush();
            }
        }

        //Done
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

        //Done
        public void increaseProduct(DataOutputStream dataOutputStream, String id) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
            product.setNumberOfProducts(product.getNumberOfProducts() + 1);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        //Done
        public void sendDeleteProductRequest(Person person, String id, DataOutputStream dataOutputStream) throws IOException, ClassNotFoundException {
            Product product = Product.getProductById(id);
            SellerAbilitiesController.sendDeleteProductRequest(person, product);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
        }

        //Done
        public void getAllSellerRequests(Person person, DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(SellerAbilitiesController.getAllSellerRequests((Seller) person).size()));
            dataOutputStream.flush();
            for (Request request : SellerAbilitiesController.getAllSellerRequests((Seller) person)) {
                String json = request.getId() + "-" + request.getType() + "-" + request.getCondition() + "-" + request.getSender().getUsername();
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        //Done
        public void deleteSellerRequest(DataInputStream dataInputStream, Person person) throws IOException {
            Gson gson = new Gson();
            Request request = gson.fromJson(dataInputStream.readUTF(), Request.class);
            SellerAbilitiesController.deleteRequest((Seller) person, request);
        }

        //Done
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

        //Done
        public void clearCart(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF("cleared");
            dataOutputStream.flush();
        }

        //Done
        public void getCategoryProducts(String name, DataOutputStream dataOutputStream) throws IOException {
            Category category = Category.getCategoryByName(name);
            dataOutputStream.writeUTF(String.valueOf(category.getAllProduct().size()));
            dataOutputStream.flush();
            for (Product product : category.getAllProduct()) {
                dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                        product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                        "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                        product.getNumberOfProducts());
                dataOutputStream.flush();
            }
        }

        //Done
        public void addToCart(Person person, Cart cart, String id) {
            Product product = Product.getProductById(id);
            if (person == null) {
                cart.addProductToCart(product);
            } else {
                ((Buyer) person).getCart().addProductToCart(product);
            }
        }

        //Done
        public void changeNumberOfProductsInHashMap(String type, String productId, Person person, Cart cart) {
            Product product = Product.getProductById(productId);
            if (person == null) {
                if (type.equals("increase")) {
                    if (product.getNumberOfProducts() >= CartController.getNumberOfProduct(cart, product) + 1) {
                        CartController.changeNumberOfProductsInHashMap(cart, product, CartController.getNumberOfProduct(cart, product) + 1);
                    }
                } else {
                    CartController.changeNumberOfProductsInHashMap(cart, product, CartController.getNumberOfProduct(cart, product) - 1);
                }
            } else {
                if (type.equals("increase")) {
                    if (product.getNumberOfProducts() >= CartController.getNumberOfProduct(((Buyer) person).getCart(), product) + 1) {
                        CartController.changeNumberOfProductsInHashMap(((Buyer) person).getCart(), product, CartController.getNumberOfProduct(((Buyer) person).getCart(), product) + 1);
                    }
                } else {
                    CartController.changeNumberOfProductsInHashMap(((Buyer) person).getCart(), product, CartController.getNumberOfProduct(((Buyer) person).getCart(), product) - 1);

                }
            }
        }

        //Done
        public void getNumberOfProductsInCart(DataOutputStream dataOutputStream, Person person, Cart cart, String produuctId) throws IOException {
            if (person == null) {
                dataOutputStream.writeUTF(String.valueOf(cart.getNumberOfProductsInPage(Product.getProductById(produuctId))));
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF(String.valueOf(((Buyer) person).getCart().getNumberOfProductsInPage(Product.getProductById(produuctId))));
                dataOutputStream.flush();
            }

        }

        //Done
        public void checkDiscount(String code, Person person, DataOutputStream dataOutputStream) throws IOException {
            if (PurchaseController.isCodeExistForBuyer((Buyer) person, code)) {
                dataOutputStream.writeUTF("exist");
            } else {
                dataOutputStream.writeUTF("not exist");
            }
            dataOutputStream.flush();
        }

        public void checkPublicSale(Person person, DataOutputStream dataOutputStream) throws IOException {
            StringBuilder answer = new StringBuilder();
            if (!((Seller) person).isUsePublicSale()) {
                answer.append("yes");
                ((Seller) person).setUsePublicSale(true);
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
                Product product = publicSale.getProduct();
                String expired;
                if (publicSale.isExpired()) {
                    expired = "true";
                } else {
                    expired = "false";
                }
                String json = product.getNumberOfProducts() + "-" + expired + "-" + product.getProductID() + "-" + product.getName() + "-"
                        + product.getMoney() + "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" + publicSale.getId();
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        public String getToken(DataOutputStream dataOutputStream) throws IOException {
            StringBuilder builder = new StringBuilder();
            int count = 16;
            while (count != 0) {
                int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
                count--;
            }
            dataOutputStream.writeUTF(builder.toString());
            dataOutputStream.flush();
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
                dataOutputStream.writeUTF(chat.getMessage() + "--" + chat.getPerson().getUsername());
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
                dataOutputStream.writeUTF(supporter.getUsername() + "-" + supporter.getName() + "-" +
                        supporter.getFamily() + "-" + supporter.getPhone() + "-" + supporter.getEmail() +
                        "-" + supporter.getPassword());
                dataOutputStream.flush();
            }
        }

        public void setSupporterForBuyer(String id, Person person) {
            Person supporter = Person.getPersonByUsername(id);
            ((Buyer) person).setSupporter((Supporter) supporter);
            if (!((Supporter) supporter).chatExisted(person)) {
                ArrayList<Chat> chats = new ArrayList<>();
                ((Supporter) supporter).putChat(chats, person);
            } else {
                ((Supporter) supporter).clearChat(person);
            }
        }

        public void getBuyerSupporterChat(String username, Person person, DataOutputStream dataOutputStream) throws IOException {
            Supporter supporter = (Supporter) Person.getPersonByUsername(username);
            ArrayList<Chat> chats = supporter.getBuyerChat(person);
            dataOutputStream.writeUTF(String.valueOf(chats.size()));
            dataOutputStream.flush();
            for (Chat chat : chats) {
                dataOutputStream.writeUTF(chat.getMessage() + "--" + chat.getPerson().getUsername());
                dataOutputStream.flush();
            }
        }

        public void sendMessageBuyerSupporter(String username, String message, Person person) {
            Supporter supporter = (Supporter) Person.getPersonByUsername(username);
            Chat chat = new Chat(person, message);
            supporter.addChat(person, chat);
        }

        public void sendMessageSupporterBuyer(String username, String message, Person person) {
            Person buyer = Person.getPersonByUsername(username);
            Chat chat = new Chat(person, message);
            ((Supporter) person).addChat(buyer, chat);
        }

        //Done
        public void getSellerBalance(String substring, DataOutputStream dataOutputStream) throws IOException {
            Seller seller = Seller.getSellerByUsername(substring);
            dataOutputStream.writeUTF(String.valueOf(seller.getBalance()));
            dataOutputStream.flush();
        }

        //Done
        public void getBuyerDiscounts(DataOutputStream dataOutputStream, Person person) throws IOException {
            Buyer buyer = (Buyer) person;
            dataOutputStream.writeUTF(String.valueOf(buyer.getDiscountCode().size()));
            dataOutputStream.flush();
            for (Discount discount : buyer.getDiscountCode()) {
                dataOutputStream.writeUTF(discount.getCode() + "-" + discount.getStartTime().toString() + "-" + discount.getEndTime().toString() + "-" + discount.getDiscountPercent() + "-" + discount.getMaxDiscount());
                dataOutputStream.flush();
            }
        }

        //Done
        public void getProductsOfCart(DataOutputStream dataOutputStream, Person person, Cart cart) throws IOException {
            if (person == null) {
                dataOutputStream.writeUTF(String.valueOf(cart.getProductsInCart().size()));
                dataOutputStream.flush();
                for (Product product : cart.getProductsInCart()) {
                    dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                            product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                            "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                            product.getNumberOfProducts());
                    dataOutputStream.flush();
                }
            } else {
                dataOutputStream.writeUTF(String.valueOf(((Buyer) person).getCart().getProductsInCart().size()));
                dataOutputStream.flush();
                for (Product product : ((Buyer) person).getCart().getProductsInCart()) {
                    dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                            product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                            "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                            product.getNumberOfProducts());
                    dataOutputStream.flush();
                }
            }
        }


        public void getSupporterBuyerChat(String username, Person person, DataOutputStream dataOutputStream) throws IOException {
            Supporter supporter = (Supporter) Person.getPersonByUsername(person.getUsername());
            Person buyer = Person.getPersonByUsername(username);
            assert supporter != null;
            ArrayList<Chat> chats = supporter.getBuyerChat(buyer);
            dataOutputStream.writeUTF(String.valueOf(chats.size()));
            dataOutputStream.flush();
            for (Chat chat : chats) {
                dataOutputStream.writeUTF(chat.getMessage() + "--" + chat.getPerson().getUsername());
                dataOutputStream.flush();
            }
        }

        public void getAllBuyersWithSupporter(String username, DataOutputStream dataOutputStream) throws IOException {
            Supporter supporter = (Supporter) Person.getPersonByUsername(username);
            ArrayList<String> userNames = new ArrayList<>(supporter.getAllUserNames());
            dataOutputStream.writeUTF(String.valueOf(userNames.size()));
            dataOutputStream.flush();
            for (String userName : userNames) {
                dataOutputStream.writeUTF(userName);
                dataOutputStream.flush();
            }
        }

        //ToDo
        public void expirePublicSale(String id, DataOutputStream dataOutputStream) throws IOException {
            PublicSale publicSale = PublicSale.getPublicSaleById(Integer.parseInt(id));
            publicSale.setExpired(true);
            publicSale.getSeller().setUsePublicSale(false);
            Buyer buyer = publicSale.getWinner();
            publicSale.returnMoney(buyer);
            dataOutputStream.writeUTF(buyer.getName() + "-" + buyer.getUsername());
            dataOutputStream.flush();
        }

        //Done
        public void getPurchaseMoney(DataOutputStream dataOutputStream, Person person, Cart cart) throws IOException {
            if (person == null) {
                dataOutputStream.writeUTF(String.valueOf(cart.getMoneyForPurchase()));
            } else {
                dataOutputStream.writeUTF(String.valueOf(((Buyer) person).getCart().getMoneyForPurchase()));
            }
            dataOutputStream.flush();
        }

        //Done
        public void setLeastMoney(String leastMoney) {
            Wallet.setMinimumMoneyInWallet(Long.parseLong(leastMoney));
        }

        public void setWage(String wage) {
            Seller.setWage(Double.parseDouble(wage));
        }

        public void exit() throws IOException {
            bankSocket.close();
        }

        //Done
        public void getBuyerBuyLogs(DataOutputStream dataOutputStream, Person person) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(((Buyer) person).getLog().size()));
            dataOutputStream.flush();

            for (BuyLog buyLog : ((Buyer) person).getLog()) {
                String json = buyLog.getLogId() + "-" + buyLog.getLocalTime().toLocalTime().toString() + "-" + buyLog.getMoneyThatPaid() + "-" + buyLog.getDiscount() + "-" + buyLog.getProductReceived();
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        //Done
        public void getBuyLogProducts(DataOutputStream dataOutputStream, String substring) throws IOException {
            BuyLog buyLog = BuyLog.getBuyLogById(substring);
            dataOutputStream.writeUTF(String.valueOf(buyLog.getProducts().size()));
            dataOutputStream.flush();
            for (Product product : buyLog.getProducts()) {
                if (product instanceof SellFile) {
                    dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                            product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                            "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                            product.getNumberOfProducts());
                } else {
                    dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                            product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                            "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                            product.getNumberOfProducts());
                }
                dataOutputStream.flush();
            }
        }

        //Done
        public void getSellLog(DataOutputStream dataOutputStream, Person person) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(((Seller) person).getLogs().size()));
            dataOutputStream.flush();

            for (SellLog log : ((Seller) person).getLogs()) {
                String json = log.getLogId() + "-" + log.getLocalTime().toString() + "-" + log.getMoneyThatPaid() + "-" + log.getDiscount() + "-" + log.getProduct().getProductID() + "-" + log.getBuyer().getUsername() + "-" + log.getProductReceived();
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        //Done
        public void sendAddAuctionRequest(Person person, String[] input, DataOutputStream dataOutputStream, DataInputStream dataInputStream) throws IOException {
            LocalTime start1 = LocalTime.of(Integer.parseInt(input[2].substring(0, 2)), Integer.parseInt(input[2].substring(3)));
            LocalTime end1 = LocalTime.of(Integer.parseInt(input[3].substring(0, 2)), Integer.parseInt(input[3].substring(3)));
            dataOutputStream.writeUTF("getOffProducts");
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Product> offProducts = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Product product = Product.getProductById(dataInputStream.readUTF());
                offProducts.add(product);
            }
            SellerAbilitiesController.sendAddAuctionRequest(person, new Auction((Seller) person, input[1], offProducts, start1, end1, Integer.parseInt(input[4])));
        }

        //Done
        public void isAuctionExist(DataOutputStream dataOutputStream, String id) throws IOException {
            if (Auction.isIdExist(id)) {
                dataOutputStream.writeUTF("yes");
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF("no");
                dataOutputStream.flush();
            }
        }

        //Done
        public void productInAuction(DataOutputStream dataOutputStream, String substring) throws IOException {
            Product product = Product.getProductById(substring);
            if (product.isInAuction()) {
                dataOutputStream.writeUTF("yes");
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF("no");
                dataOutputStream.flush();
            }
        }

        //Done
        public void getSellerAuctions(Person person, DataOutputStream dataOutputStream) throws IOException {
            Seller seller = (Seller) person;
            dataOutputStream.writeUTF(String.valueOf(seller.getSellerAuctions().size()));
            dataOutputStream.flush();
            for (Auction sellerAuction : seller.getSellerAuctions()) {
                String json = sellerAuction.getSeller().getUsername() + "-" + sellerAuction.getId() + "-" + sellerAuction.getStart().toString() + "-" + sellerAuction.getEnd().toString() + "-" + sellerAuction.getDiscountPercent();
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        //Done
        public void editAuctionInfo(String[] input, Person person) {
            Auction auction = Auction.getAuctionById(input[1]);
            SellerAbilitiesController.sendEditAuctionRequest(person, auction, input[2], input[3]);
        }

        //Done
        public void getAuctionProducts(String substring, DataOutputStream dataOutputStream) throws IOException {
            Auction auction = Auction.getAuctionById(substring);
            dataOutputStream.writeUTF(String.valueOf(auction.getProducts().size()));
            for (Product product : auction.getProducts()) {
                dataOutputStream.writeUTF(product.getProductID() + "-" + product.getName() + "-" +
                        product.getCompany() + "-" + product.getMoney() + "-" + product.getSeller().getUsername() +
                        "-" + product.getCategory().getName() + "-" + product.getDescription() + "-" +
                        product.getNumberOfProducts());
                dataOutputStream.flush();
            }

        }

        //Done
        public void removeProductOfAuction(DataOutputStream dataOutputStream, String[] strings) {
            Auction auction = Auction.getAuctionById(strings[1]);
            Product product = Product.getProductById(strings[2]);
            auction.deleteProduct(product);
        }

        //Done
        public void getProductDiscount(DataOutputStream dataOutputStream, String substring) throws IOException {
            Product product = Product.getProductById(substring);
            dataOutputStream.writeUTF(String.valueOf(product.getDiscount()));
            dataOutputStream.flush();
        }

        //Done
        public void getRole(DataOutputStream dataOutputStream, String substring) throws IOException {
            Person person = Person.getPersonByUsername(substring);

            if (person instanceof Buyer) {
                dataOutputStream.writeUTF("buyer");
            } else if (person instanceof Seller) {
                dataOutputStream.writeUTF("seller");

            } else if (person instanceof Manager) {
                dataOutputStream.writeUTF("manager");

            } else if (person instanceof Supporter) {
                dataOutputStream.writeUTF("supporter");

            } else {
                dataOutputStream.writeUTF("null person");
            }
            dataOutputStream.flush();
        }

        //Done
        public void getAllBuyLogs(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(BuyLog.getAllBuyLogs().size()));
            dataOutputStream.flush();

            for (BuyLog buyLog : BuyLog.getAllBuyLogs()) {
                String json = buyLog.getLogId() + "-" + buyLog.getLocalTime().toLocalTime().toString() + "-" + buyLog.getMoneyThatPaid() + "-" + buyLog.getDiscount() + "-" + buyLog.getProductReceived();
                dataOutputStream.writeUTF(json);
                dataOutputStream.flush();
            }
        }

        //Done
        public void setDeliveryOfLog(String string, String string1) {
            BuyLog buyLog = BuyLog.getBuyLogById(string);
            buyLog.setProductReceived(string1);
        }

        //Done
        public void getBuyLogAddress(DataOutputStream dataOutputStream, String substring) throws IOException {
            BuyLog buyLog = BuyLog.getBuyLogById(substring);
            dataOutputStream.writeUTF(buyLog.getAddress());
            dataOutputStream.flush();
        }

        public void getPublicSaleEndTime(String id, DataOutputStream dataOutputStream) throws IOException {
            PublicSale publicSale = PublicSale.getPublicSaleById(Integer.parseInt(id));
            LocalTime localTime = publicSale.getEndTime();
            String[] splitInput = localTime.toString().split(":");
            dataOutputStream.writeUTF(splitInput[0] + "-" + splitInput[1]);
            dataOutputStream.flush();
        }

        public void addFile(String id, String name, String price, String category, String description, String url, DataOutputStream dataOutputStream) throws IOException {
            boolean create = true;
            StringBuilder answer = new StringBuilder();
            if (id.equals(" ")) {
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

            if (name.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (price.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (category.equals(" ")) {
                answer.append("1-");
                create = false;
            } else if (!Category.isCategoryExist(category)) {
                answer.append("2-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (description.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (url.equals(" ")) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (create) {
                answer.append("pass");
            } else {
                answer.append("fail");
            }
            dataOutputStream.writeUTF(answer.toString());
            dataOutputStream.flush();
        }


        public void uploadFile(String id, String name, String price, String category, String description, String type,
                               Person person, DataInputStream dataInputStream) throws IOException {
            String path = "src/main/java/Server/SellerFiles/" + name + type;
            File deliveredFile = new File(path);

            try {
                byte[] byteArray = new byte[999999999];
                FileOutputStream fileOutputStream = new FileOutputStream(deliveredFile, false);
                int bytesRead = dataInputStream.read(byteArray, 0, byteArray.length);
                fileOutputStream.write(byteArray, 0, bytesRead);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Seller seller = (Seller) person;
            Category category1 = Category.getCategoryByName(category);

            new SellFile(deliveredFile, id, name, seller.getCompany(), Long.parseLong(price), seller, category1, description, type, "Unknown");
        }


        private void downloadFile(String id, DataOutputStream dataOutputStream) throws FileNotFoundException {
            SellFile sellFile = (SellFile) BuyLog.getBuyLogById(id).getProducts().get(0);
            String path = "src/main/java/Server/SellerFiles/" + sellFile.getName() + sellFile.getType();

            File myFile = new File(path);
            byte[] myByteArray = new byte[(int) myFile.length()];
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(myFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.read(myByteArray, 0, myByteArray.length);
                dataOutputStream.write(myByteArray, 0, myByteArray.length);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Done
        public void createBankAccountForManager(String[] input, DataOutputStream bankOutputStream, DataInputStream bankInputStream, DataOutputStream dataOutputStream) throws IOException {
            String message = "create_account " + input[1] + " " + input[2] + " " + input[3] + " " + input[4] + " " + input[5];
            bankOutputStream.writeUTF(message);
            bankOutputStream.flush();
            String id = bankInputStream.readUTF();
            if (id.equalsIgnoreCase("username is not available")) {
                dataOutputStream.writeUTF(id);
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF(id);
                dataOutputStream.flush();
                Manager.setBankAccountId(Integer.parseInt(id));
            }
        }

        //Done
        public void getBalance(DataOutputStream bankOutputStream, DataInputStream bankInputStream, DataOutputStream dataOutputStream, String[] strings) throws IOException {
            bankOutputStream.writeUTF("get_token " + strings[1] + " " + strings[2]);
            bankOutputStream.flush();
            String input = bankInputStream.readUTF();
            if (input.equalsIgnoreCase("invalid username or password")) {
                dataOutputStream.writeUTF(input);
            } else {
                bankOutputStream.writeUTF("get_balance " + input);
                bankOutputStream.flush();
                String string = bankInputStream.readUTF();
                dataOutputStream.writeUTF(string);
            }
            dataOutputStream.flush();
        }

        //Done
        public void createBankAccount(String[] input, DataOutputStream bankOutputStream, DataInputStream bankInputStream, DataOutputStream dataOutputStream, Person person) throws IOException {
            String message = "create_account " + input[1] + " " + input[2] + " " + input[3] + " " + input[4] + " " + input[5];
            bankOutputStream.writeUTF(message);
            bankOutputStream.flush();
            String id = bankInputStream.readUTF();
            dataOutputStream.writeUTF(id);
            dataOutputStream.flush();
            if (id.matches("\\d+")) {
                person.setAccountId(id);
            }

        }

        //Done
        public void getAccountId(DataOutputStream dataOutputStream, Person person) throws IOException {
            if (person instanceof Manager) {
                if (Manager.getBankAccountId() == 0) {
                    dataOutputStream.writeUTF("");
                    dataOutputStream.flush();
                } else {
                    dataOutputStream.writeUTF(String.valueOf(Manager.getBankAccountId()));
                    dataOutputStream.flush();
                }
            } else {
                if (person.getAccountId() == null) {
                    dataOutputStream.writeUTF("");
                    dataOutputStream.flush();
                } else {
                    dataOutputStream.writeUTF(person.getAccountId());
                    dataOutputStream.flush();
                }
            }
        }

        //Done
        public void getMoneyInWallet(DataOutputStream dataOutputStream, Person person) throws IOException {
            if (person instanceof Buyer) {
                Buyer buyer = (Buyer) person;
                dataOutputStream.writeUTF(String.valueOf(buyer.getWallet().getMoney()));
                dataOutputStream.flush();
            } else if (person instanceof Seller) {
                Seller seller = (Seller) person;
                dataOutputStream.writeUTF(String.valueOf(seller.getWallet().getMoney()));
                dataOutputStream.flush();
            }
        }

        //Done
        public void increaseBalance(DataOutputStream dataOutputStream, DataOutputStream bankOutputStream, DataInputStream bankInputStream, String[] input) throws IOException {
            bankOutputStream.writeUTF("get_token " + input[1] + " " + input[2]);
            bankOutputStream.flush();
            String token = bankInputStream.readUTF();
            String message = "create_receipt " + token + " " + "deposit " + input[4] + " " + "-1 " + input[3] + " " + "deposit money";
            bankOutputStream.writeUTF(message);
            bankOutputStream.flush();
            String get = bankInputStream.readUTF();
            if (get.matches("\\d+")) {
                bankOutputStream.writeUTF("pay " + get);
                bankOutputStream.flush();
                dataOutputStream.writeUTF(bankInputStream.readUTF());
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF(get);
                dataOutputStream.flush();
            }
        }

        //Done
        public void decreaseBalance(DataOutputStream dataOutputStream, DataOutputStream bankOutputStream, DataInputStream bankInputStream, String[] input) throws IOException {
            bankOutputStream.writeUTF("get_token " + input[1] + " " + input[2]);
            bankOutputStream.flush();
            String token = bankInputStream.readUTF();
            String message = "create_receipt" + " " + token + " " + "withdraw" + " " + input[4] + " " + input[3] + " " + "-1" + " " + "withdraw money";
            bankOutputStream.writeUTF(message);
            bankOutputStream.flush();
            String get = bankInputStream.readUTF();
            if (get.matches("\\d+")) {
                bankOutputStream.writeUTF("pay " + get);
                bankOutputStream.flush();
                dataOutputStream.writeUTF(bankInputStream.readUTF());
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF(get);
                dataOutputStream.flush();
            }
        }

        public void chargeWallet(DataOutputStream dataOutputStream, DataOutputStream bankOutputStream, DataInputStream bankInputStream, String[] input, Person person) throws IOException {
            bankOutputStream.writeUTF("get_token " + input[1] + " " + input[2]);
            bankOutputStream.flush();
            String token = bankInputStream.readUTF();
            String message = "create_receipt " + token + " " + "withdraw " + input[4] + " " + input[3] + " -1 " + "withdraw money";
            bankOutputStream.writeUTF(message);
            bankOutputStream.flush();
            String get = bankInputStream.readUTF();
            if (get.matches("\\d+")) {
                bankOutputStream.writeUTF("pay " + get);
                bankOutputStream.flush();
                String msg = bankInputStream.readUTF();
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
                if (!msg.equalsIgnoreCase("source account does not have enough money")) {
                    if (person instanceof Buyer) {
                        Buyer buyer = (Buyer) person;
                        buyer.getWallet().chargeWallet(Long.parseLong(input[4]));
                    } else if (person instanceof Seller) {
                        Seller seller = (Seller) person;
                        seller.getWallet().chargeWallet(Long.parseLong(input[4]));
                    }
                }
            } else {
                dataOutputStream.writeUTF(get);
                dataOutputStream.flush();
            }
        }

        //Done
        public void decreaseWallet(DataOutputStream dataOutputStream, String[] strings, Person person) throws IOException {
            if (Long.parseLong(strings[1]) < ((Seller) person).getWallet().getMoneyInAccess()) {
                ((Seller) person).getWallet().withdraw(Long.parseLong(strings[1]));
                dataOutputStream.writeUTF("done");
                dataOutputStream.flush();
            } else {
                dataOutputStream.writeUTF("Not Enough Money In Wallet");
                dataOutputStream.flush();
            }

        }

        //Done
        public void getManagerBalance(DataOutputStream dataOutputStream, String[] strings, DataOutputStream bankOutputStream, DataInputStream bankInputStream) throws IOException {
            bankOutputStream.writeUTF("get_token " + strings[1] + " " + strings[2]);
            bankOutputStream.flush();
            String input = bankInputStream.readUTF();
            if (input.equalsIgnoreCase("invalid username or password")) {
                dataOutputStream.writeUTF(input);
            } else {
                bankOutputStream.writeUTF("get_balance " + input);
                bankOutputStream.flush();
                String string = bankInputStream.readUTF();
                long money = Long.parseLong(string);
                for (Wallet wallet : Wallet.getWallets()) {
                    money += wallet.getMoney();
                }
                for (Double wage : Manager.wages) {
                    money += wage;
                }
                dataOutputStream.writeUTF(String.valueOf(money));
            }
            dataOutputStream.flush();
        }

        public void getAllCommentsForProduct(String id, DataOutputStream dataOutputStream) throws IOException {
            Product product = Product.getProductById(id);

            dataOutputStream.writeUTF(String.valueOf(product.getAllComments().size()));
            dataOutputStream.flush();
            for (Comment comment : product.getAllComments()) {
                dataOutputStream.writeUTF(comment.getName() + "-" + comment.getBuyCondition() + "-" + comment.getComment());
                dataOutputStream.flush();
            }

        }

        public void getScoreForProduct(String id, DataOutputStream dataOutputStream) throws IOException {
            Product product = Product.getProductById(id);
            dataOutputStream.writeUTF(String.valueOf(product.getScore()));
            dataOutputStream.flush();
        }

        public void getNumberForProduct(String id, DataOutputStream dataOutputStream) throws IOException {
            Product product = Product.getProductById(id);
            dataOutputStream.writeUTF(String.valueOf(product.getNumberOfProducts()));
            dataOutputStream.flush();
        }

        public void getProductById(String id, DataOutputStream dataOutputStream) throws IOException {
            Product product = Product.getProductById(id);
            assert product != null;
            String output = product.getName() + "," + product.getCategory().getName() + "," + product.getScore() +
                    "," + product.getMoney() + "," + product.getDescription() + "," + product.getNumberOfProducts();
            dataOutputStream.writeUTF(output);
            dataOutputStream.flush();
        }

        //Done
        public void getMoneyForPurchaseInWallet(DataOutputStream dataOutputStream, Person person) throws IOException {
            Buyer buyer = (Buyer) person;
            double totalPrice = buyer.getCart().getMoneyForPurchase();
            if (buyer.getWallet().getMoneyInAccess() >= totalPrice) {
                dataOutputStream.writeUTF("yes");
            } else {
                dataOutputStream.writeUTF("no");
            }
            dataOutputStream.flush();

        }

        //Done
        public void getMoneyForPurchaseInBank(DataOutputStream dataOutputStream, Person person, String[] input, DataInputStream bankInputStream, DataOutputStream bankOutputStream) throws IOException {
            bankOutputStream.writeUTF("get_token " + input[1] + " " + input[2]);
            bankOutputStream.flush();
            String msg = bankInputStream.readUTF();
            if (msg.equalsIgnoreCase("invalid username or password")) {
                dataOutputStream.writeUTF(msg);
            } else {
                bankOutputStream.writeUTF("get_balance " + msg);
                bankOutputStream.flush();
                String string = bankInputStream.readUTF();
                Buyer buyer = (Buyer) person;
                double totalPrice = buyer.getCart().getMoneyForPurchase();
                if (Double.parseDouble(string) >= totalPrice) {
                    dataOutputStream.writeUTF("yes");
                } else {
                    dataOutputStream.writeUTF("no");
                }
            }
            dataOutputStream.flush();
        }

        //Done
        public void doPurchaseWithWallet(Person person, String code, String address) {
            Buyer buyer = (Buyer) person;
            double totalPrice = buyer.getCart().getMoneyForPurchase();
            if (code.equalsIgnoreCase("0")) {
                PurchaseController.doPurchase(buyer, 0, address, totalPrice);
                buyer.getWallet().withdraw((long) totalPrice);
                buyer.getCart().clear();
            } else {
                double discountPercent = Discount.getDiscountByCode(code).getDiscountPercent();
                double totalPriceAfterDiscount = (totalPrice * ((100 - discountPercent) / 100));
                double discountMax = Discount.getDiscountByCode(code).getMaxDiscount();
                if (totalPriceAfterDiscount <= totalPrice - discountMax) {
                    PurchaseController.doPurchase(buyer, discountMax, address, totalPrice - discountMax);
                    buyer.getWallet().withdraw((long) ((long) totalPrice - discountMax));
                    buyer.getCart().clear();
                } else {
                    PurchaseController.doPurchase(buyer, discountPercent, address, totalPriceAfterDiscount);
                    buyer.getWallet().withdraw((long) totalPriceAfterDiscount);
                    buyer.getCart().clear();
                }
            }
        }

        //Done
        public void doPurchaseWithBank(Person person, DataOutputStream bankOutputStream, DataInputStream bankInputStream, String[] input, DataOutputStream dataOutputStream) throws IOException {
            Buyer buyer = (Buyer) person;
            double totalPrice = buyer.getCart().getMoneyForPurchase();
            if (input[4].equalsIgnoreCase("0")) {
                bankOutputStream.writeUTF("get_token " + input[1] + " " + input[2]);
                bankOutputStream.flush();
                String token = bankInputStream.readUTF();
                String money = String.valueOf((int) totalPrice);
                String message = "create_receipt" + " " + token + " " + "withdraw" + " " + money + " " + input[3] + " " + "-1" + " " + "withdraw money";
                bankOutputStream.writeUTF(message);
                bankOutputStream.flush();
                String get = bankInputStream.readUTF();
                bankOutputStream.writeUTF("pay " + get);
                bankOutputStream.flush();
                String msg = bankInputStream.readUTF();
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
                if (msg.equalsIgnoreCase("done successfully")) {
                    PurchaseController.doPurchase(buyer, 0, input[5], totalPrice);
                    buyer.getCart().clear();
                }
            } else {
                double discountPercent = Discount.getDiscountByCode(input[4]).getDiscountPercent();
                double totalPriceAfterDiscount = (totalPrice * ((100 - discountPercent) / 100));
                double discountMax = Discount.getDiscountByCode(input[4]).getMaxDiscount();
                if (totalPriceAfterDiscount <= totalPrice - discountMax) {
                    bankOutputStream.writeUTF("get_token " + input[1] + " " + input[2]);
                    bankOutputStream.flush();
                    String token = bankInputStream.readUTF();
                    String money = String.valueOf((int) (totalPrice - discountMax));
                    String message = "create_receipt" + " " + token + " " + "withdraw" + " " + money + " " + input[3] + " " + "-1" + " " + "withdraw money";
                    bankOutputStream.writeUTF(message);
                    bankOutputStream.flush();
                    String get = bankInputStream.readUTF();
                    bankOutputStream.writeUTF("pay " + get);
                    bankOutputStream.flush();
                    String msg = bankInputStream.readUTF();
                    dataOutputStream.writeUTF(msg);
                    dataOutputStream.flush();
                    if (msg.equalsIgnoreCase("done successfully")) {
                        PurchaseController.doPurchase(buyer, discountMax, input[5], totalPrice - discountMax);
                        buyer.getCart().clear();
                    }
                } else {
                    bankOutputStream.writeUTF("get_token " + input[1] + " " + input[2]);
                    bankOutputStream.flush();
                    String token = bankInputStream.readUTF();
                    String money = String.valueOf((int) totalPriceAfterDiscount);
                    String message = "create_receipt" + " " + token + " " + "withdraw" + " " + money + " " + input[3] + " " + "-1" + " " + "withdraw money";
                    bankOutputStream.writeUTF(message);
                    bankOutputStream.flush();
                    String get = bankInputStream.readUTF();
                    bankOutputStream.writeUTF("pay " + get);
                    bankOutputStream.flush();
                    String msg = bankInputStream.readUTF();
                    dataOutputStream.writeUTF(msg);
                    dataOutputStream.flush();
                    if (msg.equalsIgnoreCase("done successfully")) {
                        PurchaseController.doPurchase(buyer, discountPercent, input[5], totalPriceAfterDiscount);
                        buyer.getCart().clear();
                    }
                }
            }
        }

        public void doPurchaseForPublicSale(Person person, String code, String address, String id) {
            Buyer buyer = (Buyer) person;
            PublicSale publicSale = PublicSale.getPublicSaleById(Integer.parseInt(id));
            PurchaseController.purchaseForPublicSale(buyer, 0, address, publicSale.getProduct());
        }

        public void isFileOrNot(String id, DataOutputStream dataOutputStream) throws IOException {
            BuyLog buyLog = BuyLog.getBuyLogById(id);
            if (buyLog.getProducts().get(0) instanceof SellFile) {
                dataOutputStream.writeUTF("yes");
            } else {
                dataOutputStream.writeUTF("no");
            }
            dataOutputStream.flush();
        }

        public void getProductScore(DataOutputStream dataOutputStream, String substring) throws IOException {
            Product product = Product.getProductById(substring);
            dataOutputStream.writeUTF(String.valueOf(product.getAverageScore()));
            dataOutputStream.flush();
        }
    }

    private void updateDatabase() {

    }

}
