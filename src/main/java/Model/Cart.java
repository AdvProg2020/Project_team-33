package Model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> productsInCart;

    public Cart(ArrayList<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }


    //    private HashMap<Product, Integer> userCart = new HashMap<Product, Integer>();
//
////    public double getPrice() {
////        return 0;
////    }
////
////    public boolean isProductAvailable(Product product) {
////        return this.userCart.containsKey(product);
////    }
////
////    public void addProductToCart(Product product, int num) {
////        int newNum = num;
////        if (this.isProductAvailable(product)) {
////            newNum += this.userCart.get(product);
////        }
////        this.userCart.put(product, newNum);
////    }
////
////    public void deleteProductFromCart(Product product) {
////        this.userCart.remove(product);
////    }
////
////    public HashMap<Product, Integer> getCart() {
////        return this.userCart;
////    }
////
////    public void decreaseNumberOfProduct(Product product, int num) {
////        int newNum;
////        newNum = this.userCart.get(product);
////        if (newNum - num <= 0) deleteProductFromCart(product);
////        else {
////            this.userCart.put(product, newNum);
////        }
////    }

}
