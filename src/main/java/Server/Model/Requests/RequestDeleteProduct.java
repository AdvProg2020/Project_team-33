package Server.Model.Requests;

import Server.Model.Product;
import Server.Model.Users.Person;

import java.util.ArrayList;

public class RequestDeleteProduct extends Request {
    private Product product;
    public static ArrayList<RequestDeleteProduct> allDeleteProductRequest = new ArrayList<>();


    public RequestDeleteProduct(String type, String condition, Person sender, Product product) {
        super(Request.counter, type, condition, sender);
        this.product = product;
        Request.counter++;
    }

    public Product getProduct() {
        return product;
    }
}
