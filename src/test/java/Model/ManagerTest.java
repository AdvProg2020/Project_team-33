package Model;

import Controller.RegisterProcess;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerTest {

    @Test
    public void sampleTestForConstructor() {
        Manager managerExample = new Manager("Rachel", "Green", "rachelG","1234","0912679863","rachel@yahoo.com");
        assertEquals(true, Manager.allManagers.contains(managerExample));
    }

}