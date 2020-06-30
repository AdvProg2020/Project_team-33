package Model.Requests;

import Model.Auction;
import Model.Product;
import Model.Users.Person;
import Model.Users.Seller;

import java.util.ArrayList;

public class RequestDeleteProduct extends Request {
    private Product product;
    public static ArrayList<RequestDeleteProduct> allDeleteProductRequest = new ArrayList<>();


    public RequestDeleteProduct(String type, String condition, Person sender, Product product) {
        super(type, condition, sender);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
