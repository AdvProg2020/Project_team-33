package Model;

public class SellerOfProduct {
    private Seller seller;
    private double price;

    public SellerOfProduct(Seller seller , double price){
        this.price = price ;
        this.seller = seller;
    }
    public double getPrice() {
        return this.price;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}

