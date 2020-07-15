package Model;

import Model.Users.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> numberOfProductsInPage;
    private ArrayList<Product> productsInCart;

    public Cart() {
        this.productsInCart = new ArrayList<>();
        this.numberOfProductsInPage = new HashMap<>();
    }

    public void addProductToCart(Product product) {
        this.productsInCart.add(product);
        numberOfProductsInPage.put(product, 1);
    }

    public void clear() {
        this.productsInCart.clear();
    }

    public ArrayList<Product> getProductsInCart() {
        return productsInCart;
    }

    public void setNumberOfProductsInPage(Product product, int number) {
        if (number <= 0) {
            productsInCart.remove(product);
            numberOfProductsInPage.remove(product);
        } else {
            numberOfProductsInPage.replace(product, number);
        }
    }

    public int getNumberOfProductsInPage(Product product) {
        return numberOfProductsInPage.get(product);
    }

    public double getMoneyForPurchase() {
        double money = 0;
        for (Product product : this.productsInCart) {
            money += product.getMoney()-product.getDiscount();
        }
        return money;
    }

}
