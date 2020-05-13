package Model;

import java.util.ArrayList;

public class RequestEditProduct extends Request {
    private Seller seller;
    private Product product;
    public static ArrayList<RequestEditProduct> allAddProductRequest = new ArrayList<RequestEditProduct>();

    public RequestEditProduct(Seller seller, Product product,Auction auction) {
        super(seller, product, auction);
        this.seller = seller;
        this.product = product;
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }
}
