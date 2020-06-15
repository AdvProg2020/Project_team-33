package Model;

import java.util.ArrayList;

public class Seller extends Person {
    public static ArrayList<Seller> allSellers = new ArrayList<>();
    private ArrayList<SellLog> logs = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private String company;
    long money;

    public Seller(String username, String name, String family, String phone,
                  String email, String password, String company) {
        super(name, family, username, password, phone, email);
        this.company = company;
        allSellers.add(this);
    }

    public String getCompany() {
        return company;
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
