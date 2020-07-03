package Model.Users;

import Model.Logs.BuyLog;
import Model.Cart;
import Model.Discount;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Buyer extends Person {
    private long money;
    private ImageView imageView;
    private final Image unknownPerson = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
    private final Image womanPerson = new Image(Paths.get("src/main/java/view/images/womanLogo.png").toUri().toString());
    private final Image manPerson = new Image(Paths.get("src/main/java/view/images/manLogo.png").toUri().toString());
    private ArrayList<BuyLog> logs = new ArrayList<>();
    private Cart cart;
    public static ArrayList<Buyer> allBuyers = new ArrayList<>();
    private ArrayList<Discount> discountCode = new ArrayList<>();

    public Buyer(String username, String name, String family, String phone,
                 String email, String password) {
        super(username, name, family, phone, email, password);
        this.imageView = new ImageView(unknownPerson);
        this.cart = new Cart();
        allBuyers.add(this);
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void addLog(BuyLog log) {
        this.logs.add(log);
    }

    public ArrayList<BuyLog> getLog() {
        return this.logs;
    }

    public ArrayList<Discount> getDiscountCode() {
        return this.discountCode;
    }

    public static ArrayList<Buyer> getAllBuyers() {
        return allBuyers;
    }

    public void setImageView(String sex) {
        if (sex.equals("man")) {
            this.imageView.setImage(manPerson);
        } else if (sex.equals("woman")) {
            this.imageView.setImage(womanPerson);
        } else {
            this.imageView.setImage(unknownPerson);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean isDiscountExist(String code) {
        for (Discount discount : this.discountCode) {
            if (discount.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void addDiscount(Discount discount) {
        this.discountCode.add(discount);
    }
}