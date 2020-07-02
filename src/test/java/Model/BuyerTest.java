package Model;

import Model.Users.Buyer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BuyerTest {

    @Test
    public void deleteBuyer(){
        ArrayList<Buyer> allBuyer = new ArrayList<>();
        Buyer buyer = new Buyer("Ali" , "ali" , "vanaki" , "09107270737" , "ali.vanaki100@gmail.com" , "123");
        allBuyer.add(buyer);
        Buyer buyer1 = new Buyer("mahdi" , "mahdi" , "man" , "09123456789" , "man@gmail.com" , "456");
        allBuyer.add(buyer1);
        allBuyer.remove(buyer);
//        Buyer.deleteBuyer(buyer);
        assertEquals(allBuyer , Buyer.getAllBuyers());
    }
}
