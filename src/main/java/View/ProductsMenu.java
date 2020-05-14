package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsMenu extends Menu {
    public ProductsMenu( ) {
        super("Products Menu");
    }

    public void viewCategories() {

    }

    public void filteringProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("show available filters")) {
                showAvailableFilters();
            } else if ((matcher = getMatcher(input, "filter (\\S+)")).find()) {
                filterWithAnAvailableFilterProcess(matcher.group(1));
            } else if (input.equalsIgnoreCase("current filters")) {
                showCurrentFilters();
            } else if ((matcher = getMatcher(input, "(disable filter (\\S+))")).find()) {
                disableFilterProcess(matcher.group(1));
//            } else if (input.equalsIgnoreCase("back")) {
//                parentMenu.commandProcess();
//            } else if (input.equalsIgnoreCase("Exit")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void filterWithAnAvailableFilterProcess(String filterType) {

    }

    public void showAvailableFilters() {

    }

    public void showCurrentFilters() {

    }

    public void printProductWithFilters() {

    }

    public void disableFilterProcess(String filter) {

    }

    public void sortingProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("show available sorts")) {
                showAvailableSorts();
            } else if ((matcher = getMatcher(input, "sort (\\S+)")).find()) {
                sortWithAnAvailableFilterProcess(matcher.group(1));
            } else if (input.equalsIgnoreCase("current sort")) {
                showCurrentSort();
            } else if (input.equalsIgnoreCase("disable sort")) {
                disableSortProcess();
//            } else if (input.equalsIgnoreCase("back")) {
//                parentMenu.commandProcess();
//            } else if (input.equalsIgnoreCase("Exit")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void sortWithAnAvailableFilterProcess(String sortType) {

    }

    public void showAvailableSorts() {

    }

    public void disableSortProcess() {

    }

    public void showCurrentSort() {

    }

    public void showProductProcess(int productId) {

    }

    public void showProducts() {

    }

    public void commandProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("view categories")) {
                viewCategories();
            } else if (input.equalsIgnoreCase("filtering")) {
                filteringProcess();
            } else if (input.equalsIgnoreCase("sorting")) {
                sortingProcess();
            } else if (input.equalsIgnoreCase("show products")) {
                showProducts();
            } else if ((matcher = getMatcher(input, "show product (\\d+)")).find()) {
                showProductProcess(Integer.parseInt(matcher.group(1)));
//            } else if (input.equalsIgnoreCase("back")) {
//                parentMenu.commandProcess();
//            } else if (input.equalsIgnoreCase("Exit")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void help() {
        System.out.println("Enter your command:");
        System.out.println("products:");
        System.out.println("view categories");
        System.out.println("filtering");
        System.out.println("\tshow available filters\n\t" +
                "filter [an available filter]\n\t" +
                "current filters\n\t" + "disable filter [a selected filter]");
        System.out.println("sorting");
        System.out.println("\tshow available sorts\n\t" +
                "sort [an available sort]\n\t" +
                "current sort\n\t" +
                "disable sort");
        System.out.println("show products");
        System.out.println("show product [productId]");

    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
