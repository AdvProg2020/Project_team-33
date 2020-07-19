package Server.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private static ArrayList<Cart> allCarts = new ArrayList<>();
    private HashMap<Product, Integer> numberOfProductsInPage;
    private ArrayList<Product> productsInCart;
    private int cartNo;

    public Cart() {
        this.productsInCart = new ArrayList<>();
        this.numberOfProductsInPage = new HashMap<>();
        allCarts.add(this);
        cartNo++;
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
            money += product.getMoney();
        }
        return money;
    }

    public int getCartNo() {
        return cartNo;
    }

    public static Cart getCartByNo(int num){
        for (Cart cart : allCarts) {
            if (cart.cartNo == num){
                return cart;
            }
        }
        return null;
    }

    //    public ArrayList<BuyingProduct> getProductsInCart() {
//        return productsInCart;
//    }
//
//    public void removeProductFromCart(Product product) {
//        this.productsInCart.remove(product);
//    }
//
//    public Product getProductInCartById(int productID) {
//        for (BuyingProduct product : this.productsInCart) {
////            if (product.getProduct().getProductID() == productID) {
//                return product.getProduct();
////            }
//        }
//        return null;
//    }
//
//    public void setProductInCart(Product productInCartById) {
//    }
//
//        private HashMap<Product, Integer> userCart = new HashMap<Product, Integer>();
//
//    public double getPrice() {
//        return 0;
//    }
//
//    public boolean isProductAvailable(Product product) {
//        return this.userCart.containsKey(product);
//    }
//
//    public void addProductToCart(Product product, int num) {
//        int newNum = num;
//        if (this.isProductAvailable(product)) {
//            newNum += this.userCart.get(product);
//        }
//        this.userCart.put(product, newNum);
//    }
//
//    public void deleteProductFromCart(Product product) {
//        this.userCart.remove(product);
//    }
//
//    public HashMap<Product, Integer> getCart() {
//        return this.userCart;
//    }
//
//    public void decreaseNumberOfProduct(Product product, int num) {
//        int newNum;
//        newNum = this.userCart.get(product);
//        if (newNum - num <= 0) deleteProductFromCart(product);
//        else {
//            this.userCart.put(product, newNum);
//        }
//    }

}
