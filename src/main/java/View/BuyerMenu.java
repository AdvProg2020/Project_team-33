package View;

import Model.Buyer;
import Model.Manager;
import Model.Seller;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;

public class BuyerMenu extends Menu{
    Buyer buyer;

    public BuyerMenu(Menu previousMenu) {
        super("Buyer Menu", previousMenu);
    }

    public void showPersonalInfo(){

    }

    public void editPersonalInfo(){

    }

    public void showProductBasket(){

    }

    public void showProductInBasket(){

    }

    public void viewProductInBasketProcess(){

    }

    public void increaseNumberOfProductsProcess(){

    }

    public void decreaseNumberOfProductsProcess(){

    }

    public void showTotalPriceOfProductsInBasket(){

    }

    public void purchaseProcess(){

    }

    public void viewOrder(){

    }

    public void showOrder(){

    }

    public void showRateOfProductProcess(){

    }

    public void viewBalance(){

    }

    public void showDiscountCodes(){

    }

    public void help(){

    }

    public void commandProcess(){
        Matcher matcher;
        while (true){
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("view personal info")){
                showPersonalInfo();
            }else if (input.equalsIgnoreCase("view company information")){

            }
        }
    }

}
