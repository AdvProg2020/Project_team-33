package Model.Users;

import Controller.PersonController;
import Database.SaveData;
import Model.Product;
import Model.Logs.SellLog;
import Model.Requests.Request;

import java.util.ArrayList;

public class Seller extends Person {
    public static ArrayList<Seller> allSellers = new ArrayList<>();
    private ArrayList<SellLog> logs = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private String company;
    long balance;
    String condition;
    private ArrayList<Product> sellerProducts = new ArrayList<>();
    private ArrayList<Request> sellerRequests = new ArrayList<>();
    SaveData saveData = new SaveData();


    public Seller(String username, String name, String family, String phone,
                  String email, String password, String company) {
        super(username, name, family, phone, email, password);
        this.company = company;
        this.balance = 0;
        this.condition = "Unknown";
        Person.deleteUser(this);
        PersonController.sendAddSellerRequestToManager(this);
    }

    public String getCompany() {
        return company;
    }

    public String getCanSellerCreate() {
        return condition;
    }

    public static void deleteSeller(Person person) {
        allSellers.remove((Seller) person);
    }

    public void setCondition(String canSellerCreate) {
        this.condition = canSellerCreate;
        if (canSellerCreate.equals("Decline")) {
            Person.deleteUser(this);
        } else if (canSellerCreate.equals("Accept")) {
            allSellers.add(this);
            Person.people.add(this);
        }
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void deleteProduct(Product product) {
        for (Product eachProduct : products)
            if (eachProduct.equals(product)) this.products.remove(product);
    }

    public boolean isThisSellerHasThisProduct(Product product) {
        for (Product eachProduct : products)
            if (eachProduct.equals(product)) return true;
        return false;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<SellLog> getLogs() {
        return this.logs;
    }

    public void addSellLog(SellLog log) {
        this.logs.add(log);
    }

    public static boolean isSellerWithThisUsernameExist(String username) {
        for (Seller eachSeller : allSellers)
            if (eachSeller.getUsername().equals(username)) return true;
        return false;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void setSellerProducts(ArrayList<Product> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }

    public ArrayList<Product> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerRequests(Request requests) {
        this.sellerRequests.add(requests);
    }

    public ArrayList<Request> getSellerRequests() {
        return sellerRequests;
    }

    public void deleteRequest(Request request) {
        this.getSellerRequests().remove(request);
    }
}
