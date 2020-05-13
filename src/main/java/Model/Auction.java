package Model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    public static ArrayList<Auction> allAuctions;
    private Date beginningTime;
    private Date finishingTime;
    private ArrayList<Product> products;
    private AuctionStatue auctionStatue;
    private int discount;

    public Auction(Date beginningTime, Date finishingTime, ArrayList<Product> products, int discount) {
        this.beginningTime = beginningTime;
        this.finishingTime = finishingTime;
        this.products = products;
        this.discount = discount;
    }

    boolean isThisProductExistInAuction(Product product) {
        for (Product eachProduct : products) {
            if (eachProduct.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public Date getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(Date beginningTime) {
        this.beginningTime = beginningTime;
    }

    public Date getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(Date finishingTime) {
        this.finishingTime = finishingTime;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        for (Product eachProduct : products)
            if (eachProduct.equals(product)) this.products.remove(product);
    }

    public Auction getAuctionById(int id) {
        if (id > allAuctions.size()) {
            return null;
        }
        return allAuctions.get(id);
    }

}

enum AuctionStatue {
    BUILDING,
    EDITING,
    CONFIRMED
}
