package Client.View.DeletedClassesForPhase3;

//import Client.Controller.ProductController.ProductController;


public class DeletedManagerMenu {
//    private Manager manager;
//    private Matcher matcher;
//    Person person;
//
//    public ManagerMenu() {
////        super("Manager Menu");
//    }
//
//    public void help() {
//        System.out.println("Enter your command:");
//        System.out.println("1.view personal info");
//        System.out.println("\tedit [field]");
//        System.out.println("2.manage users");
//        System.out.println("\tview [username]\n" +
//                "\tdelete user [username]\n" +
//                "\tcreate manager profile");
//        System.out.println("3.manage all products");
//        System.out.println("\tremove [productId]");
//        System.out.println("4.create discount code");
//        System.out.println("5.view discount codes");
//        System.out.println("\tview discount code [code]\n" +
//                "\tedit discount code [code]\n" +
//                "\tremove discount code [code]");
//        System.out.println("6.manage requests");
//        System.out.println("\tdetails [requestId]\n" +
//                "\taccept [requestId]\n" +
//                "\tdecline [requestId]");
//        System.out.println("manage categories");
//        System.out.println("\tedit [category]\n" +
//                "\tadd [category]\n" +
//                "\tremove [category]");
//        System.out.println("7.back");
//        if (LoginMenu.currentPerson != null) {
//            System.out.println("logout");
//        }
//        System.out.println("8.exit");
//        commandProcess();
//    }
//
//    public void commandProcess() {
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if (input.equalsIgnoreCase("view personal info")) {
//                viewPersonalInfo();
//            } else if (input.equalsIgnoreCase("manage users")) {
//                manageUsers();
//            } else if (input.equalsIgnoreCase("manage all products")) {
//                manageAllProducts();
//            } else if (input.equalsIgnoreCase("create discount code")) {
//                createDiscount();
//            } else if (input.equalsIgnoreCase("view discount codes")) {
//                viewDiscountsProcessor();
//            } else if (input.equalsIgnoreCase("manage requests")) {
//                manageRequestsProcessor();
//            } else if (input.equalsIgnoreCase("manage categories")) {
//                manageCategoriesProcessor();
//            } else if (input.equalsIgnoreCase("logout")) {
//                LoginMenu.currentPerson = null;
////                Menu.show();
//            } else if (input.equalsIgnoreCase("back")) {
////                Menu.show();
//            } else if (input.equalsIgnoreCase("exit")) {
//                System.exit(1);
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    private void viewPersonalInfo() {
//        BuyerMenu.showPersonalInfo();
//    }
//
//    public void manageUsers() {
//        showPeople();
//        Matcher matcher;
//        System.out.println("write back if you dont want to continue this fields");
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(view )(\\S+)")).find()) {
//                viewPerson(matcher.group(2));
//            } else if ((matcher = getMatcher(input, "(delete user )(\\S+)")).find()) {
//                deleteUser(matcher.group(2));
//            } else if (input.equalsIgnoreCase("create manager profile")) {
//                createManager();
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    private void showPeople() {
//        System.out.println("users:");
//        for (Person person1 : PersonController.getPeople()) {
//            System.out.println(person1.getUsername());
//        }
//    }
//
//    private void viewPerson(String username) {
//        if (!PersonController.usernameTypeErr(username)) {
//            System.out.println("username type incorrect");
//            return;
//        } else if ((person = Person.getPersonByUsername(username)) == null) {
//            System.out.println("there is no user with this username exist");
//            return;
//        }
//        System.out.println(person.toString());
//    }
//
//    private void deleteUser(String username) {
//        if (!PersonController.usernameTypeErr(username)) {
//            System.out.println("username type incorrect");
//            return;
//        } else if ((person = Person.getPersonByUsername(username)) == null) {
//            System.out.println("there is no user with this username exist");
//            return;
//        }
//        PersonController.deleteUsers(username);
//        System.out.println("user has been deleted successfully");
//    }
//
//    private void createManager() {
//        System.out.println("name: ");
//        String name = Menu.scanner.nextLine();
//        System.out.println("family: ");
//        String family = Menu.scanner.nextLine();
//        System.out.println("username: ");
//        String username = Menu.scanner.nextLine();
//        if (!PersonController.usernameTypeErr(username)) {
//            System.out.println("username type incorrect");
//            return;
//        }
//        System.out.println("password: ");
//        String password = Menu.scanner.nextLine();
//        if (!PersonController.checkLengthOfPassWord(password)) {
//            System.out.println("password type incorrect");
//            return;
//        }
//        System.out.println("phone: ");
//        String phone = Menu.scanner.nextLine();
//        if (!PersonController.phoneTypeErr(phone)) {
//            System.out.println("phone type incorrect");
//            return;
//        }
//        System.out.println("email: ");
//        String email = Menu.scanner.nextLine();
//        if (!PersonController.emailTypeErr(email)) {
//            System.out.println("email type incorrect");
//            return;
//        }
//        System.out.println("manager created successfully");
//    }
//
//    private void manageAllProducts() {
//        System.out.println("products:");
//        for (Product product : PersonController.getProducts()) {
//            System.out.println(product.getID());
//        }
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(remove )(\\d+)")).find()) {
//                removeProduct(Integer.parseInt(matcher.group(2)));
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    private void removeProduct(int id) {
//        if (ProductController.checkIsNumberValid(id) != null) {
//            Product.removeProduct(id);
//            System.out.println("product removed successfully");
//        } else {
//            System.out.println("product with this Id does not exist");
//        }
//    }
//
//    private void createDiscount() {
//
//    }
//
//    private void viewDiscountsProcessor() {
//        showDiscounts();
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(view discount code )(\\d+)")).find()) {
//                viewDiscount(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(edit discount code )(\\d+)")).find()) {
//                editDiscount(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(remove discount code )(\\d+)")).find()) {
//                removeDiscount(Integer.parseInt(matcher.group(2)));
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else if (input.equalsIgnoreCase("exit")) {
//                System.exit(1);
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    private void showDiscounts() {
//
//    }
//
//    private void viewDiscount(int id) {
//
//    }
//
//    private void editDiscount(int id) {
//
//    }
//
//    private void removeDiscount(int id) {
//
//    }
//
//    private void manageRequestsProcessor() {
//        showRequests();
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(details )(\\d+)")).find()) {
//                requestDetails(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(accept )(\\d+)")).find()) {
//                acceptRequest(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(decline )(\\d+)")).find()) {
//                declineRequest(Integer.parseInt(matcher.group(2)));
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else if (input.equalsIgnoreCase("exit")) {
//                System.exit(1);
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    private void showRequests() {
//        for (Request request : Request.allRequests) {
//            System.out.println(request);
//        }
//    }
//
//    private void requestDetails(int id) {
//
//    }
//
//    private void acceptRequest(int id) {
//
//    }
//
//    private void declineRequest(int id) {
//
//    }
//
//    private void manageCategoriesProcessor() {
//        showCategories();
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(edit )(\\S+)")).find()) {
//                editCategoryProcess(matcher.group(2));
//            } else if ((matcher = getMatcher(input, "(add )(\\S+)")).find()) {
//                addCategoryProcessor(matcher.group(2));
//            } else if ((matcher = getMatcher(input, "(remove )(\\S+)")).find()) {
//                removeCategoryProcessor(matcher.group(2));
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else if (input.equalsIgnoreCase("exit")) {
//                System.exit(1);
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    private void showCategories() {
//        for (Category category : Category.allCategory) {
//            System.out.println(category.getName());
//        }
//    }
//
//    private void editCategoryProcess(String category) {
//        if (Category.getCategoryByName(category) != null) {
//            System.out.println("please write the field you want to edit from the list below: ");
//            System.out.println("name\n" + "super Category\n" + "quality\n");
//            String field = Menu.scanner.nextLine();
//            if (field.equalsIgnoreCase("name")) {
//                System.out.println("enter new name");
//                changeNameProcess(Menu.scanner.nextLine());
//            } else if (field.equalsIgnoreCase("super category")) {
//                System.out.println("enter new super category");
//                changeSuperCategoryProcess(Menu.scanner.nextLine());
//            } else if (field.equalsIgnoreCase("quality")) {
//                changeQualityProcess();
//            } else {
//                System.out.println("your entered field is not in the list");
//            }
//        } else {
//            System.out.println("there is no category with this name");
//        }
//    }
//
//    private void changeNameProcess(String newName) {
//
//    }
//
//    private void changeSuperCategoryProcess(String superCategory) {
//
//    }
//
//    private void changeQualityProcess() {
//
//    }
//
//
//    private void addCategoryProcessor(String category) {
//
//    }
//
//    private void removeCategoryProcessor(String category) {
//
//    }
//
//    private static Matcher getMatcher(String input, String regex) {
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(input);
//        return matcher;
//    }
//
}
