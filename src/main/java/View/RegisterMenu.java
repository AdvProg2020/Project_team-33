package View;

import Model.Buyer;
import Model.Cart;
import Model.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu extends Menu{
    private Seller seller;
    private Buyer buyer;
    private Cart cart;
    private static Matcher matcher;

    public RegisterMenu(){
        super("Register Menu",null);
    }

    public void createAccountProcess(Matcher matcher){
        String username=matcher.group(3);
        System.out.println("password: ");
        String password=Menu.scanner.nextLine();
        System.out.println("name: ");
        String name =Menu.scanner.nextLine();
        System.out.println("family: ");
        String family=Menu.scanner.nextLine();
        System.out.println("phone: ");
        String phone=Menu.scanner.nextLine();
        System.out.println("email: ");
        String email=Menu.scanner.nextLine();
        if(matcher.group(2).equals("seller")){
            seller=new Seller(name,family,username,password,phone,email);
        }else if(matcher.group(2).equals("buyer")){
            cart=new Cart();
            buyer=new Buyer(name,family,username,password,phone,email,cart);
        }
    }

    public void commandProcess(){
            if((matcher=getMatcher(Menu.scanner.nextLine(),"(create account )((seller||buyer)(\\S)")).find()){
                createAccountProcess(matcher);
            }
    }

    public void help(){
        System.out.println();
    }

    private static Matcher getMatcher(String input,String regex){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(input);
        return matcher;
    }
}
