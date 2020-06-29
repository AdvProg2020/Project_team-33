package Model.Users;

import Controller.PersonController;
import Database.SaveData;
import Model.Product;
import Model.Logs.SellLog;

import java.util.ArrayList;

public class Seller extends Person {
    public static ArrayList<Seller> allSellers = new ArrayList<>();
    private ArrayList<SellLog> logs = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private String company;
    long money;
    String condition;
    SaveData saveData = new SaveData();


    public Seller(String username, String name, String family, String phone,
                  String email, String password, String company) {
        super(username, name, family, phone, email, password);
        this.company = company;
        this.condition = "Unknown";
        allSellers.add(this);
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

    public Product getProductById(int productId) {
        for (Product product : this.products) {
            if (product.getProductID() == productId) {
                return product;
            }
        }

        return null;
    }

    public static boolean isSellerWithThisUsernameExist(String username) {
        for (Seller eachSeller : allSellers)
            if (eachSeller.getUsername().equals(username)) return true;
        return false;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getMoney() {
        return money;
    }
}
