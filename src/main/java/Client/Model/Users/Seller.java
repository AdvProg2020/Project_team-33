package Client.Model.Users;

import Client.Model.Logs.SellLog;
import Client.Model.Product;
import Client.Model.Requests.Request;
import Client.Model.Wallet;
import Server.Database.SaveData;

import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends Person implements Serializable {
    private ArrayList<SellLog> logs = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private String company;
    private boolean isOnline;
    long balance;
    private double minimumMoneyInWallet;
    private boolean usePublicSale;
    private double Wage;
    private Wallet wallet;
    String condition;
    private ArrayList<Request> sellerRequests = new ArrayList<>();
    SaveData saveData = new SaveData();

    public Seller(String username, String name, String family, String phone,
                  String email, String password, String company) {
        super(username, name, family, phone, email, password);
        this.company = company;
        this.balance = 0;
        this.condition = "Unknown";
        wallet = new Wallet(0, this);
        usePublicSale = false;
    }

    public Seller() {

    }

    public boolean isUsePublicSale() {
        return usePublicSale;
    }

    public void setUsePublicSale(boolean usePublicSale) {
        this.usePublicSale = usePublicSale;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public double getWage() {
        return Wage;
    }

    public void setWage(double wage) {
        Wage = wage;
    }

    public double getMinimumMoneyInWallet() {
        return minimumMoneyInWallet;
    }

    public void setMinimumMoneyInWallet(double minimumMoneyInWallet) {
        this.minimumMoneyInWallet = minimumMoneyInWallet;
    }

    public String getCompany() {
        return company;
    }

    public String getCanSellerCreate() {
        return condition;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void deleteProduct(Product product) {
        this.products.remove(product);
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

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void setSellerProducts(ArrayList<Product> sellerProducts) {
        this.products = sellerProducts;
    }

    public ArrayList<Product> getSellerProducts() {
        return products;
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
