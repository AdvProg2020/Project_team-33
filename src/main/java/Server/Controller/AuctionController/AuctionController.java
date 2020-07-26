package Server.Controller.AuctionController;

import Server.Model.Category.Category;
import Server.Model.Product;

import java.util.ArrayList;

public class AuctionController {

    public static ArrayList<Product> getAllProducts() {
//        ArrayList<Product> allProducts = new ArrayList<>();
//        for (Auction allAuction : Auction.getAllAuctions()) {
//            allProducts.addAll(allAuction.getProducts());
//        }
//        return allProducts;
        return null;
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
