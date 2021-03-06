package Server.Model;

import Server.Model.Requests.Request;
import Server.Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private Seller seller;
    private String id;
    private ArrayList<Product> products;
    private String state;
    private LocalTime start;
    private LocalTime end;
    private int discountPercent;
    private static ArrayList<Auction> allAuctions = new ArrayList<>();


    public Auction(Seller seller, String id, ArrayList<Product> products, LocalTime start, LocalTime end, int discountPercent) {
        this.seller = seller;
        this.id = id;
        this.products = products;
        this.state = "Creating";
        this.start = start;
        this.end = end;
        this.discountPercent = discountPercent;
        allAuctions.add(this);
    }

    public static boolean isIdExist(String id) {
        for (Auction allAuction : allAuctions) {
            if (id.equals(allAuction.getId())) {
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public Seller getSeller() {
        return seller;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void acceptAuction() {
        this.getSeller().addAuctionForSeller(this);
        for (Product product : this.getProducts()) {
            product.setInAuction(true);
            product.setDiscount(this.getDiscountPercent());
        }
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public static ArrayList<Auction> getAllAuctions() {
        checkTime();
        return allAuctions;
    }

    private static void checkTime() {
        for (Auction allAuction : allAuctions) {
            if (LocalTime.now().compareTo(allAuction.getStart()) >= 0) {
                if (LocalTime.now().compareTo(allAuction.getEnd()) >= 0) {
                    allAuctions.remove(allAuction);
                }
            }
        }
    }

    public static Auction getAuctionById(String id) {
        for (Auction allAuction : allAuctions) {
            if (allAuction.getId().equals(id)) {
                return allAuction;
            }
        }
        return null;
    }

    public void deleteProduct(Product product) {
        this.products.remove(product);
    }
}

enum AuctionStatue {
    BUILDING,
    EDITING,
    CONFIRMED
}
