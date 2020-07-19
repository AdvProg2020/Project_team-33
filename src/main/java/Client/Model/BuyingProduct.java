package Client.Model;

import Client.Model.Users.Seller;

public class BuyingProduct {
    private Product product ;
    private Seller seller ;
    private double price ;

    public BuyingProduct(Product product , Seller seller){
        this.product = product ;
        this.seller = seller;
//        this.price = product.findPriceOfThisSeller(seller);
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public double getPrice() {
        return this.price;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
