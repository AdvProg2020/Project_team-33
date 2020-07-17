package Controller.AuctionController;

import Model.Auction;
import Model.Category.Category;
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

    public static ArrayList<Product> getAllCategoryProducts(Category category) {
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Product product : category.getAllProduct()) {
            if (product.isInAuction()) {
                allProducts.add(product);
            }
        }
        return allProducts;
    }
}
