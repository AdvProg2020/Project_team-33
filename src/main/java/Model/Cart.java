package Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private HashMap<Product , Integer> userCart = new HashMap<Product , Integer>();
    public double getPrice()
    {
        return  0 ;
    }
    public boolean isProductAvailable(Product product)
    {
        return true ;
    }
    public void addProductToCart(Product product)
    {

    }
    public void addProductToCart(Product product , int num)
    {

    }
    public void deleteProductFromCart(Product product)
    {

    }
    public HashMap<Product , Integer> getCart()
    {
        return this.userCart;
    }
    public void decreaseNumberOfProduct(Product product , int num)
    {

    }

}
