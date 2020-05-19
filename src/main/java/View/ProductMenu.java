package View;

import Controller.ProductController;
import Model.Product;
import Model.SellerOfProduct;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductMenu extends Menu {
    private Product product;

    public ProductMenu(Product product) {
        super("Product Menu");
        this.product = product;
    }

    public void digest() {
        showDigest();
        Matcher matcher;
        System.err.println("if you want to continue write your command and else write back");
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("add to cart")) {
                addToCartProcess();
            } else if ((matcher = getMatcher(input, "select seller (\\S+)")).find()) {
                selectSellerProcess(matcher.group(1));
            } else if (input.equalsIgnoreCase("back")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void showDigest() {
        System.out.println("name: ");
        System.out.println(product.getName());
        System.out.println("description: ");
        System.out.println(product.getDescription());
        System.out.println("sellers with their prices:");
        int counter = 1;
        for (SellerOfProduct sellerOfProduct : product.getAllSeller()) {
            System.out.print(counter + "." +sellerOfProduct.getSeller().getName() + " : ");
            System.out.println(sellerOfProduct.getPrice());
            counter++;
        }
        //TODO offs
        System.out.println("category: ");
        System.out.println(product.getProductCategory().getName());
        System.out.println("average score: ");
        System.out.println(ProductController.calculateAverageScore(product));
    }

    public void selectSellerProcess(String sellerUsername) {

    }

    public void addToCartProcess() {

    }

    public void attributesProcess() {

    }

    public void compare(int productId) {
        Product otherProduct = Product.getProductWithID(productId);
        if (otherProduct == null){
            System.out.println("product with this ID doesn't exist");
            return;
        }
        System.out.println("names: " + product.getName() + "\t:\t" + otherProduct.getName());
        System.out.println("minimum prices: " + product.findLeastPrice() + "\t:\t" + otherProduct.findLeastPrice());
        System.out.println("categories: " + product.getProductCategory().getName() + "\t:\t" +
                otherProduct.getProductCategory().getName());
        System.out.println("average score: " + ProductController.calculateAverageScore(product) + "\t:\t" +
                ProductController.calculateAverageScore(otherProduct));
        System.out.println("descriptions: " + product.getDescription() + "\t:\t" + otherProduct.getDescription());
    }

    public void comments() {

    }

    public void addCommentProcess() {

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
                comments();
            } else if (input.equalsIgnoreCase("Add comment")) {
                addCommentProcess();
            } else if (input.equalsIgnoreCase("help")) {
                help();
            } else if (input.equalsIgnoreCase("Exit")) {
                System.exit(1);
            } else if (input.equalsIgnoreCase("back")) {
                Menu.show();
            } else {
                System.out.println("invalid command");
            }
        }

    }

    public void help() {
        System.out.println();

    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}
