package Model;

import Model.Users.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private ArrayList<BuyingProduct> productsInCart;

    public void setProductInCart(Product product , Seller seller) {
        this.productsInCart.add(new BuyingProduct(product , seller));
    }

    public ArrayList<BuyingProduct> getProductsInCart() {
        return productsInCart;
    }

    public void removeProductFromCart(Product product) {
        this.productsInCart.remove(product);
    }

    public Product getProductInCartById(int productID) {
        for (BuyingProduct product : this.productsInCart) {
//            if (product.getProduct().getProductID() == productID) {
                return product.getProduct();
//            }
        }
        return null;
    }

    public void setProductInCart(Product productInCartById) {
    }

        private HashMap<Product, Integer> userCart = new HashMap<Product, Integer>();

    public double getPrice() {
        return 0;
    }

    public boolean isProductAvailable(Product product) {
        return this.userCart.containsKey(product);
    }

    public void addProductToCart(Product product, int num) {
        int newNum = num;
        if (this.isProductAvailable(product)) {
            newNum += this.userCart.get(product);
        }
        this.userCart.put(product, newNum);
    }

    public void deleteProductFromCart(Product product) {
        this.userCart.remove(product);
    }

    public HashMap<Product, Integer> getCart() {
        return this.userCart;
    }

    public void decreaseNumberOfProduct(Product product, int num) {
        int newNum;
        newNum = this.userCart.get(product);
        if (newNum - num <= 0) deleteProductFromCart(product);
        else {
            this.userCart.put(product, newNum);
        }
    }

}
