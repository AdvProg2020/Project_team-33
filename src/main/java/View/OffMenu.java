package View;

import Model.Buyer;
import Model.Manager;
import Model.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OffMenu extends Menu{
    private Manager manager;
    private Seller seller;
    private Buyer buyer;

    public OffMenu(Menu previousMenu) {
        super("OffMenu", previousMenu);
    }

    public void offs(){

    }

    public void showProduct(int productId){

    }

    public void commandProcess(){
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if ((matcher = getMatcher(input,"(show product (\\d+))")).find()){
                showProduct(Integer.parseInt(matcehr.group(2)));
            }
        }
    }

    public void help(){

    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
