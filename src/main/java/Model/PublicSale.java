package Model;

import Model.Users.Buyer;
import Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PublicSale {
    private LocalTime endTime;
    private Seller seller;
    private Product product;
    private HashMap<Buyer, Integer> participants;
    private Buyer winner;

    public PublicSale(Seller seller, LocalTime endTime) {
        this.endTime = endTime;
        this.seller = seller;
        participants= new HashMap<>();
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
