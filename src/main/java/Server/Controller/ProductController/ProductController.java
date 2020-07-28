package Server.Controller.ProductController;


import Server.Model.Logs.BuyLog;
import Server.Model.Product;
import Server.Model.Score;
import Server.Model.Users.Buyer;

import java.util.ArrayList;

public class ProductController {

    public static boolean isBuyerBuyThisProduct(Buyer buyer, Product product) {
        ArrayList<Product> products = new ArrayList<>();
        for (BuyLog buyLog : buyer.getLog()) {
            for (Product product1 : buyer.getProductsInLog(buyLog)) {
                products.add(product1);
            }
        }

        for (Product product1 : products) {
            if (product == product1) {
                return true;
            }
        }
        return false;
    }

    public static void scoreController(int point, Product product, Buyer buyer) {
        if (!Score.isPersonScoredBefore(buyer, product)) {
            Score score = new Score(buyer, point, product);
            product.addScore(score);
        }
    }

}
