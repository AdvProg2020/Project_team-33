package View.ProductPage;

import Controller.ProductController;
import Model.Category;
import View.LoginAndRegister.LoginMenu;
import View.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsMenu extends Menu {
    public ProductsMenu() {
//        super("Products Menu");
    }

    public void help() {
        System.out.println("1.view categories");
        System.out.println("2.filtering");
        System.out.println("\tshow available filters");
        System.out.println("\tfilter [an available filter]");
        System.out.println("\tcurrent filters");
        System.out.println("\tdisable filter [a selected filter]");
        System.out.println("3.sorting");
        System.out.println("\tshow available sorts");
        System.out.println("\tsort [an available sort]");
        System.out.println("\tcurrent sort");
        System.out.println("\tdisable sort");
        System.out.println("4.show products");
        System.out.println("5.show product [productId]");
        System.out.println("6.Back");
        if (LoginMenu.currentPerson != null) {
            System.out.println("logout");
        }
        System.out.println("Exit");
//        commandProcess();
    }

//    public void commandProcess() {
//        System.out.print("Please Enter Your Command :");
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if (input.equals("view categories")) {
//                viewCategories();
//            }
//            else if (input.equals("filtering")) {
//                filtering();
//            }
//            else if (input.equals("sorting")) {
//
//            } else if (input.equals("show products")) {
//
//                return;
//            } else if (input.equals("show product (\\d+)")) {
//
//            } else if (input.equalsIgnoreCase("back")) {
////                Menu.show();
//            }
//            else if (input.equalsIgnoreCase("exit")) {
//                System.exit(1);
//            }
//            else if (input.equalsIgnoreCase("help")) {
//                System.out.println("1.view categories");
//                System.out.println("2.filtering");
//                System.out.println("\tshow available filters");
//                System.out.println("\tfilter [an available filter]");
//                System.out.println("\tcurrent filters");
//                System.out.println("\tdisable filter [a selected filter]");
//                System.out.println("3.sorting");
//                System.out.println("\tshow available sorts");
//                System.out.println("\tsort [an available sort]");
//                System.out.println("\tcurrent sort");
//                System.out.println("\tdisable sort");
//                System.out.println("4.show products");
//                System.out.println("5.show product [productId]");
//                System.out.println("6.Back");
//                if (LoginMenu.currentPerson != null) {
//                    System.out.println("logout");
//                }
//                System.out.println("Exit");
//            }
//            else if (input.equals("logout")) {
//                LoginMenu.currentPerson = null;
////                Menu.show();
//            }
//            else {
//                System.out.println("Invalid command");
//            }
//        }
//    }

    public void viewCategories() {
        for (Category category : Category.allCategory) {
            System.out.println(category.getName());
        }
    }

//    public void filterWithAnAvailableFilterProcess() {
//        while (true) {
//            showAvailableFilters();
//            System.out.print("Please Enter Your Number:");
//            int input = scanner.nextInt();
//            if (input == 1) {
//                filteringWithCategory();
//            } else if (input == 2) {
//                filteringWithName();
//            } else if (input == 3) {
//                filterWithSellerName();
//            } else if (input == 4) {
//                filterWithPrice();
//            } else if (input == 5) {
//                filtering();
//            } else System.out.println("Invalid Number");
//        }
//    }

//    public void filteringWithCategory() {
//        System.out.println("Please Choose your Category :");
//        int i = 1;
//        for (Category category : Category.allCategory)
//            System.out.println(i++ + " " + category.getName());
//        int input = scanner.nextInt();
//        if (input > Category.allCategory.size()) System.out.println("The Number is invalid");
//        else ProductController.addFilter("Category", Category.allCategory.get(input - 1).getName());
//    }
//
//    public void filteringWithName() {
//        System.out.print("Please Enter your Name:");
//        scanner.nextLine();
//        String name = scanner.nextLine();
//        ProductController.addFilter("Name", name);
//    }
//
//    public void filterWithSellerName() {
//        System.out.print("Please Enter your Name:");
//        scanner.nextLine();
//        String name = scanner.nextLine();
//        ProductController.addFilter("Seller", name);
//    }
//
//    public void filterWithPrice() {
//        System.out.print("Please enter your min price :");
//        int min = scanner.nextInt();
//        System.out.print("Please enter your max price :");
//        int max = scanner.nextInt();
//        if (ProductController.checkMinAndMax(min, max)) {
//            ProductController.FinalPrice = max;
//            ProductController.startPrice = min;
//        } else System.out.println("Are you joking:))");
//    }

    public void showAvailableFilters() {
        System.out.println("1.Category\n" +
                "2.Name\n" +
                "3.SellerName\n" +
                "4.Price\n" +
                "5.back");
    }

    public void showCurrentFilters() {
        if (ProductController.startPrice > 0 && ProductController.FinalPrice > 0)
            System.out.println("Price :" + ProductController.startPrice + "-" + ProductController.FinalPrice);
        for (int i = 0; i < ProductController.filtersType.size(); ++i)
            System.out.println(ProductController.filtersType.get(i) + ":" + ProductController.filtersName.get(i));
    }

//    public void disableFilterProcess() {
//        int i;
//        for (i = 0; i < ProductController.filtersType.size(); ++i)
//            System.out.println(i + 1 + "." + ProductController.filtersType.get(i) + ":" + ProductController.filtersName.get(i));
//        if (ProductController.FinalPrice > 0)
//            System.out.println(i + 1 + "." + "Price :" + ProductController.startPrice + "-" + ProductController.FinalPrice);
//        System.out.println("Please Enter the Number Of Filter You Want to delete :");
//        int input = scanner.nextInt();
//        if (input <= i)
//            ProductController.removeFilter(i);
//        else if (input == i + 1 && ProductController.FinalPrice > 0) ProductController.deletePrice();
//        else System.out.println("Your number is incorrect");
//        filtering();
//    }

    public void sortWithAnAvailableFilterProcess() {

    }

    public void showAvailableSorts() {

    }

    public void disableSortProcess() {

    }

    public void showCurrentSort() {

    }

//    public void filtering() {
//        System.out.println("1.show available filters\n" +
//                "2.filter [an available filter]\n" +
//                "3.current filters\n" +
//                "4.disable filter [a selected filter]\n" +
//                "5.back");
//        while (true) {
//            System.out.print("Please Enter Your Number :");
//            int input = Menu.scanner.nextInt();
//            if (input == 1) {
//                showAvailableFilters();
//            } else if (input == 2) {
//                filterWithAnAvailableFilterProcess();
//            } else if (input == 3) {
//                showCurrentFilters();
//            } else if (input == 4) {
//                disableFilterProcess();
//            } else if (input == 5) {
//                help();
//            } else {
//                System.out.println("Invalid Number");
//            }
//        }
//    }

    /*public void showProductProcess() {
        System.out.print("Please Enter ProductId :");
        int Id = Menu.scanner.nextInt();
        if (ProductController.checkIsNumberValid(Id)) System.out.println(Product.allProducts.get(Id - 1).getName() + "   " + Product.allProducts.get(Id - 1).getPrice());
        else System.out.println("This Id is not exist between product");
    }

    public void showProducts() {
        for (Product product : Product.allProducts)
            System.out.println(product.getName() + "   "  + product.getPrice());
    }*/


    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
