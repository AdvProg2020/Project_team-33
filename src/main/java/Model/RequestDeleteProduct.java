package Model;

import java.util.ArrayList;

public class RequestDeleteProduct extends Request {
    private Seller seller;
    private Product product;
    public static ArrayList<RequestDeleteProduct> allDeleteProductRequest = new ArrayList<RequestDeleteProduct>();

    public RequestDeleteProduct(Seller seller, Product product, Auction auction) {
        super(seller, product, auction);
        this.seller = seller;
        this.product = product;
        allDeleteProductRequest.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }
}
