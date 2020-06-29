package Controller.SellerController;

import Model.Users.Seller;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SellerController {
    @Test
    public void editPersonalInfoForManager() {
        Seller seller = new Seller("amk_amir", "AmirMahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321","google");
        SellerAbilitiesController.editPersonalInfo(seller, "company", "Microsoft");
        assertEquals("Microsoft",seller.getCompany());
    }
}
