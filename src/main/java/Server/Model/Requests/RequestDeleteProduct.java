package Server.Model.Requests;

import Server.Model.Product;
import Server.Model.Users.Person;
import Server.Model.Users.Seller;

import java.util.ArrayList;

public class RequestDeleteProduct extends Request {
    private Product product;


    public RequestDeleteProduct(String type, String condition, Person sender, Product product) {
        super(Request.counter, type, condition, sender);
        this.product = product;
        Request.counter++;
        this.sendRequestInSellerRequests();
    }

    private void sendRequestInSellerRequests() {
        Seller seller = (Seller) this.getSender();
        seller.setSellerRequests(this);
    }

    public Product getProduct() {
        return product;
    }
}
