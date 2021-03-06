package Client.Model;

import Client.Model.Users.Buyer;
import Client.Model.Users.Seller;

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

    //ToDo
    public PublicSale(Seller seller, Product product, LocalTime endTime) {
        this.endTime = endTime;
        this.product = product;
        this.seller = seller;
//        product.setInAuction(true);
        isExpired = false;
        participants= new HashMap<>();
        this.id = counter;
        counter++;
    }

    public PublicSale(){

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
