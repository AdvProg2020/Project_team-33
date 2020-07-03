package Model;

import Model.Users.Buyer;
import Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;

public class PublicSale {
    private LocalTime endTime;
    private Seller seller;
    private ArrayList<Buyer> participants;
    private Buyer winner;

    public PublicSale(Seller seller, LocalTime endTime) {
        this.endTime = endTime;
        this.seller = seller;
        participants= new ArrayList<>();
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
        participants.add(buyer);
    }

    public Buyer getWinner() {
        return winner;
    }

    public void setWinner(Buyer winner) {
        this.winner = winner;
    }
}
