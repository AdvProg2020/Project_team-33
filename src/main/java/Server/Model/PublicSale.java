package Server.Model;

import Server.Model.Users.Buyer;
import Server.Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PublicSale {
    private LocalTime endTime;
    public static int counter = 1;
    private int id;
    private Seller seller;
    private Product product;
    private boolean isExpired;
    private HashMap<Buyer, Integer> participants;
    private Buyer winner;
    private static ArrayList<PublicSale> allPublicSales = new ArrayList<>();

    public PublicSale(Seller seller, Product product, LocalTime endTime) {
        this.endTime = endTime;
        this.product = product;
        this.seller = seller;
        participants= new HashMap<>();
        allPublicSales.add(this);
        this.id = counter;
        counter++;
    }

    public static boolean isProductExpired(Client.Model.Product product){
        for (PublicSale publicSale : allPublicSales) {
            if (publicSale.getProduct().equals(product)){
                return publicSale.isExpired;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public static ArrayList<PublicSale> getAllPublicSales() {
        return allPublicSales;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void addParticipant(Buyer buyer){
        participants.put(buyer, 0);
    }

    public Buyer getWinner() {
        return winner;
    }

    public void setWinner(Buyer winner) {
        this.winner = winner;
    }
}
