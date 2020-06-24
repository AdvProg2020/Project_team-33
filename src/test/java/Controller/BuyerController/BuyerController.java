package Controller.BuyerController;

import Controller.SellerController.SellerAbilitiesController;
import Model.Users.Buyer;
import Model.Users.Seller;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuyerController {
    @Test
    public void editPersonalInfoForManager() {
        Buyer buyer = new Buyer("amk_amir", "AmirMahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321");
        BuyerAbilitiesController.editPersonalInfo(buyer, "name", "Amir");
        assertEquals("Amir", buyer.getName());
    }
}
