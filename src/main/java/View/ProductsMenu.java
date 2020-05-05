package View;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsMenu extends Menu {
    public ProductsMenu(Menu previousMenu) {
        super("Products Menu", previousMenu);
    }

    public void viewCategories() {

    }

    public void filteringProcess(Matcher matcher) {

    }

    public void showAvailableFilters() {

    }

    public void showCurrentFilters() {

    }

    public void printProductWithFilters() {

    }

    public void disableFilterProcess(Matcher matcher) {

    }

    public void sortingProcess(Matcher matcher) {

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
            String input = Menu.scanner.nextLine()
            if (input.equalsIgnoreCase("view categories")) {
                viewCategories();
//            } else if (input.equalsIgnoreCase("filtering")) {
            } else if (input.equalsIgnoreCase("show available filters")) {
                showAvailableFilters();
            } else if ((matcher = getMatcher(input, "filter (\\S+)")).find()) {
                filteringProcess(matcher);
            } else if (input.equalsIgnoreCase("current filters")) {
                showCurrentFilters();
            } else if ((matcher = getMatcher(input, "(disable filter (\\S+))")).find()) {
                disableFilterProcess(matcher);
//            } else if (input.equalsIgnoreCase("sorting")) {
            } else if (input.equalsIgnoreCase("show available sorts")) {
                showAvailableSorts();
            } else if ((matcher = getMatcher(input, "sort (\\S+)")).find()) {
                sortingProcess(matcher);
            } else if (input.equalsIgnoreCase("current sort")) {
                showCurrentSort();
            } else if (input.equalsIgnoreCase("disable sort")) {
                disableSortProcess();
            } else if (input.equalsIgnoreCase("show products")) {
                showProducts();
            } else if ((matcher = getMatcher(input, "show product (\\d+)")).find()) {
                showProductProcess(Integer.parseInt(matcher.group(1)));
            } else if (input.equalsIgnoreCase("Exit")) {
                return;
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
