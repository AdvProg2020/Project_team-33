package Controller.CartAndPurchase;

import Model.Cart;
import Model.Product;

import java.util.ArrayList;

public class CartController {

    public static ArrayList<Product> getAllProductsInCart(Cart cart) {
        return cart.getProductsInCart();
    }

    public static void changeNumberOfProductsInHashMap(Cart cart, Product product, int newNumber) {
        cart.setNumberOfProductsInPage(product, newNumber);
    }

    public static int getNumberOfProduct(Cart cart, Product product) {
        return cart.getNumberOfProductsInPage(product);
    }
}
