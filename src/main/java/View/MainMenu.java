package View;

import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu", null);
        HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, new RegisterMenu(this));
        subMenus.put(2, new LoginMenu(this));
        subMenus.put(3, new BuyerMenu(this));
        subMenus.put(4, new ManagerMenu(this));
        subMenus.put(5, new OffMenu(this));
        subMenus.put(6, new ProductMenu(this));
        subMenus.put(7, new ProductsMenu(this));
        subMenus.put(8, new SellerMenu(this));
    }

    @Override
    public void show() {
        if (LoginMenu.currentPerson == null) {
            System.out.println("1.Register Menu");
            System.out.println("2.Login Menu");
            System.out.println("3.Product Menu");
            System.out.println("4.Products Menu");
        } else {
            System.out.println("1.Buyer Menu");
            System.out.println("2.Manager Menu");
            System.out.println("3.Off Menu");
            System.out.println("4.Product Menu");
            System.out.println("5.Products Menu");
            System.out.println("6.Seller Menu");


        }
    }
}
