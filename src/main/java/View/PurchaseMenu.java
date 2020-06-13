package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseMenu {

    public static void receiveInformation() {
        System.out.println("address:");
        System.out.println("city:");
        if (getMatcher("[A-Z]+", Menu.scanner.nextLine()).find()) {
            System.out.println("street:");
            if (getMatcher("[A-Z]+", Menu.scanner.nextLine()).find()) {
                System.out.println("alley:");
                if (getMatcher("[A-Z]+", Menu.scanner.nextLine()).find()) {
                    System.out.println("plaque:");
                    if (getMatcher("\\d+", Menu.scanner.nextLine()).find()) {
                        System.out.println("phone number:");
                        if (getMatcher("09\\d{9}", Menu.scanner.nextLine()).find()) {
                            System.out.println("postal code:");
                            if (getMatcher("\\d+", Menu.scanner.nextLine()).find()) {
                                getDiscountCode();
                            } else {
                                System.out.println("wrong postal code");
                            }
                        } else {
                            System.out.println("wrong phone number");
                        }

                    } else {
                        System.out.println("wrong plaque");
                    }
                } else {
                    System.out.println("wrong alley");
                }
            } else {
                System.out.println("wrong street");
            }
        } else {
            System.out.println("wrong city");
        }
    }

    private static void getDiscountCode() {
        System.out.println("do you have discount code?");
        while (true) {
            if (Menu.scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("write your code");
                if (getMatcher("\\d+", Menu.scanner.nextLine()).find()) {

                } else {
                    System.out.println("you can only write numbers");
                }
            } else if (Menu.scanner.nextLine().equalsIgnoreCase("no")) {
                payment();
                return;
            } else {
                System.out.println("invalid answer");
            }
        }
    }

    private static void payment() {
        System.out.println("");
    }

    private static Matcher getMatcher(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
