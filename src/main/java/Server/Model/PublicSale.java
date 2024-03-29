package Server.Model;

import Server.Model.Users.Buyer;
import Server.Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PublicSale {
    private LocalTime endTime;
    public static int counter = 1;
    private int id;
    private Seller seller;
    private Product product;
    private boolean isExpired;
    private HashMap<Buyer, Integer> participants;
    private Buyer winner;
    private ArrayList<Chat> chats;
    private static ArrayList<PublicSale> allPublicSales = new ArrayList<>();

    public PublicSale(Seller seller, Product product, LocalTime endTime) {
        this.endTime = endTime;
        this.product = product;
        this.seller = seller;
        product.setInAuction(true);
        participants = new HashMap<>();
        allPublicSales.add(this);
        this.chats = new ArrayList<>();
        this.id = counter;
        counter++;
    }

    public static PublicSale getPublicSaleById(int id) {
        for (PublicSale publicSale : allPublicSales) {
            if (publicSale.getId() == id) {
                return publicSale;
            }
        }
        return null;
    }

    public static boolean isProductExpired(Product product) {
        for (PublicSale publicSale : allPublicSales) {
            if (publicSale.getProduct().equals(product)) {
                return publicSale.isExpired;
            }
        }
        return false;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void addChat(Chat chat) {
        chats.add(chat);
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

    public void addParticipant(Buyer buyer) {
        participants.put(buyer, 0);
    }

    public int getMoney(Buyer buyer) {
        for (Map.Entry<Buyer, Integer> entry : participants.entrySet()) {
            if (entry.getKey().equals(buyer)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    public boolean setMoney(Buyer buyer, int money) {
        for (Map.Entry<Buyer, Integer> entry : participants.entrySet()) {
            if (entry.getKey().getUsername().equals(buyer.getUsername())) {
                if (entry.getValue() < money && entry.getKey().getWallet().getMoneyInAccess() > money) {
                    entry.setValue(money);
                    entry.getKey().getWallet().withdraw(money);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public Buyer getWinner() {
        Buyer buyer = null;
        int max = 0;
        int flag = 0;
        for (Map.Entry<Buyer, Integer> entry : participants.entrySet()) {
            if (flag == 0) {
                buyer = entry.getKey();
                max = entry.getValue();
                flag = 1;
            }
            if(max < entry.getValue()){
                max = entry.getValue();
                buyer = entry.getKey();
            }
        }
        return buyer;
    }

    public void setWinner(Buyer winner) {
        this.winner = winner;
    }

    public void returnMoney(Buyer winner) {
        for (Map.Entry<Buyer, Integer> entry : participants.entrySet()) {
           if (!entry.getKey().getUsername().equals(winner.getUsername())){
               entry.getKey().getWallet().chargeWallet(entry.getValue());
            }
        }
    }
}
