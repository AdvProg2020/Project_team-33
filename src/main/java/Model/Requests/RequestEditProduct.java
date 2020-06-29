package Model.Requests;

import Model.Auction;
import Model.Product;
import Model.Users.Seller;

import java.util.ArrayList;

public class RequestEditProduct  {
    private Seller seller;
    private Product product;
    public static ArrayList<RequestEditProduct> allAddProductRequest = new ArrayList<RequestEditProduct>();

    public RequestEditProduct(Seller seller, Product product, Auction auction) {
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
