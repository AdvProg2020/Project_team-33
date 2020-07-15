package Controller.AuctionController;

import Model.Auction;
import Model.Product;

import java.util.ArrayList;

public class AuctionController {

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Auction allAuction : Auction.getAllAuctions()) {
            allProducts.addAll(allAuction.getProducts());
        }
        return allProducts;
    }
}
