package Model.Requests;

import Model.Auction;
import Model.Product;
import Model.Users.Seller;

import java.util.ArrayList;

public class RequestAddProduct  {
    private Seller seller;
    private Product product;
    public static ArrayList<RequestAddProduct> allAddProductRequest = new ArrayList<RequestAddProduct>();

    public RequestAddProduct(Seller seller, Product product, Auction auction) {
//        super(seller, product, auction);
        this.seller = seller;
        this.product = product;
        allAddProductRequest.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }
}
