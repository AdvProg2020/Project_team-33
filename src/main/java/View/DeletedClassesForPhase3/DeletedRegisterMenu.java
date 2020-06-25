package View.DeletedClassesForPhase3;

public class DeletedRegisterMenu {
    //
//    private static int registerManagerAccountCounter = 0;
//
//    public RegisterMenu() {
////        super("Register Menu");
//    }
//
//    public void help() {
//        System.out.println("Enter your command:");
//        System.out.println("create account [type] [username]");
//        System.out.println("Back");
//        System.out.println("Exit");
//        commandProcess();
//    }
//
//    public void commandProcess() {
//        String command;
//        while (true) {
//            command = Menu.scanner.nextLine();
//            Matcher matcher;
//            if ((matcher = getMatcher(command)).find()) {
//                createAccountProcess(matcher);
//            } else if (command.equalsIgnoreCase("help")) {
//                System.out.println("Enter your command:");
//                System.out.println("create account [type] [username]");
//                System.out.println("Back");
//                System.out.println("Exit");
//            } else if (command.equalsIgnoreCase("Exit")) {
//                System.exit(1);
//            } else if (command.equalsIgnoreCase("back")) {
////                Menu.show();
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//
//    protected boolean getUsernameOfAccount(String username) {
//        if (!RegisterProcess.usernameTypeErr(username)) {
//            System.out.println("Your username is not Valid.");
//            System.out.println("You can use only numbers or alphabet in your username");
//            return false;
//        }
//        if (RegisterProcess.existUsername(username)) {
//            System.out.println("This username is used before");
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean getPasswordOfAccount(String password) {
//        if (!RegisterProcess.checkLengthOfPassWord(password)) {
//            System.out.println("Your password should have at least 8 character");
//            return false;
//        }
////        if (!RegisterProcess.checkPasswordUseNumberAndAlphabet(password)) {
////            System.out.println("You should use at least one number and alphabet in your password");
////            return false;
////        }
//        if (!RegisterProcess.passwordTypeErr(password)) {
//            System.out.println("your password is invalid");
//            return false;
//        }
//        return true;
//    }
//
//    protected static boolean getPhoneOfAccount(String phone) {
//        if (!RegisterProcess.phoneTypeErr(phone)) {
//            System.out.println("Your mobile number is invalid");
//            return false;
//        }
//        return true;
//    }
//
//    protected static boolean getEmailOfAccount(String email) {
//        if (!RegisterProcess.emailTypeErr(email)) {
//            System.out.println("Your email address is not valid");
//            return false;
//        }
//        return true;
//    }
//
//    private static Matcher getMatcher(String input) {
//        Pattern pattern = Pattern.compile("create account (seller|buyer|manager) (\\S+)");
//        return pattern.matcher(input);
//    }
}
