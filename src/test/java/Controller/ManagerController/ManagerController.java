package Controller.ManagerController;

import Model.Users.Manager;
import org.junit.Test;
import static org.junit.Assert.*;


public class ManagerController {

    @Test
    public void editPersonalInfoForManager() {
        Manager manager = new Manager("amk_amir", "AmirMahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321");
        ManagerAbilitiesController.editPersonalInfo(manager, "phone", "09999999999");
        assertEquals("09999999999",manager.getPhone());
    }

    @Test
    public void getProductsForManager() {
        assertEquals(null,ManagerAbilitiesController.getAllMembers());
    }
}
