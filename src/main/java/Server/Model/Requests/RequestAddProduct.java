package Server.Model.Requests;

import Server.Model.Product;
import Server.Model.Users.Person;
import Server.Model.Users.Seller;

import java.util.ArrayList;

public class RequestAddProduct extends Request {
    private Product product;
    public static ArrayList<RequestAddProduct> allAddProductRequest = new ArrayList<>();

    public RequestAddProduct(String type, String condition, Person sender, Product product) {
        super(type, condition, sender);
        this.product = product;
        allAddProductRequest.add(this);
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
