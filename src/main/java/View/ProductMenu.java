package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductMenu extends Menu {

    public ProductMenu(Menu previousMenu) {
        super("Product Menu", previousMenu);
    }

    public void digest() {

        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("add to cart")) {
                addToCartProcess();
            } else if ((matcher = getMatcher(input, "(select seller (\\S+))")).find()) {
                selectSellerProcess(matcher.group(1));
            }
        }
    }

    public void showDigest() {

    }

    public void selectSellerProcess(String sellerUsername) {

    }

    public void addToCartProcess() {

    }

    public void attributesProcess() {

    }

    public void compare(int productId) {

    }

    public void commentsProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("Add comment")) {
                addCommentProcess();
            } else if (input.equalsIgnoreCase("back")) {
                previousMenu.commandProcess();
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void addCommentProcess() {
        System.out.println("Title: \nContent");
    }

    public void addScoreProcess(int score) {

    }

    public void commandProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("digest")) {
                digest();
            } else if (input.equalsIgnoreCase("attributes")) {
                attributesProcess();
            } else if ((matcher = getMatcher(input, "(compare (\\d+))")).find()) {
                compare(Integer.parseInt(matcher.group(2)));
            } else if (input.equalsIgnoreCase("Comments")) {
                commentsProcess();
            } else if (input.equalsIgnoreCase("back")) {
                previousMenu.commandProcess();
            } else if (input.equalsIgnoreCase("help")) {
                help();
            } else {
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
