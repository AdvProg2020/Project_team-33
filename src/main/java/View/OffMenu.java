package View;

import Model.Buyer;
import Model.Manager;
import Model.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OffMenu extends Menu {
    private Manager manager;
    private Seller seller;
    private Buyer buyer;

    public OffMenu(Menu parentMenu) {
        super("OffMenu", parentMenu);
    }

    public void offs() {

    }

    public void showProduct(int productId) {

    }

    public void commandProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if ((matcher = getMatcher(input, "(show product (\\d+))")).find()) {
                showProduct(Integer.parseInt(matcher.group(2)));
            } else if (input.equalsIgnoreCase("help")) {
                help();
            } else if (input.equalsIgnoreCase("back")){
                parentMenu.commandProcess();
            }else {
                System.out.println("invalid command");
            }
        }
    }

    public void help() {

    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
