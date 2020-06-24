package Controller;

import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Person;
import Model.Users.Seller;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterProcessTest {
    Seller exampleSeller = RegisterProcess.createAccountForSeller("Chandler", "Bing", "ch12",
            "1234567", "0918765268", "bing@gmail.com",
            "born in 1982");

    @Test
    public void createAccountForSeller() {
        Seller.allSellers.add(exampleSeller);
        assertEquals(true, Seller.isSellerWithThisUsernameExist("ch12"));
    }

    @Test
    public void createAccountForBuyer() {
        Buyer.allBuyers.add(RegisterProcess.createAccountForBuyer("Joey", "Tribbiani", "Joe_17",
                "1234", "0912679863", "joey@yahoo.com"));
        assertEquals(true, Buyer.isAccountWithThisUsernameExist("Joe_17"));
    }

    @Test
    public void login() {
        Person.people.add(exampleSeller);
//        Person actualPerson1 = RegisterProcess.login("ch12","12345");
//        assertEquals(null,actualPerson1);
//        Person actualPerson2 = RegisterProcess.login("ch12","1234567");
//        assertEquals(exampleSeller, actualPerson2);
    }

    @Test
    public void createAccountForManager() {
        Manager sampleManager = RegisterProcess.createAccountForMainManager("Rachel", "Green",
                "rachelG", "1234", "0912679863", "rachel@yahoo.com");
        Manager.allManagers.add(sampleManager);
        assertEquals(true, Manager.allManagers.contains(sampleManager));
    }

    @Test
    public void passwordTypeErr() {
        boolean actualValidation = PersonController.phoneTypeErr("12jdla$%");
        assertEquals(false, actualValidation);
    }

//    @Test
//    public void checkPasswordUseNumberAndAlphabet() {
//        boolean actualValidation1 = RegisterProcess.checkPasswordUseNumberAndAlphabet("hello1234");
//        assertEquals(true, actualValidation1);
//        boolean actualValidation2 = RegisterProcess.checkPasswordUseNumberAndAlphabet("1234");
//        assertEquals(false,actualValidation2);
//    }

    @Test
    public void checkLengthOfPassWord() {
        boolean actualValidation1 = PersonController.checkLengthOfPassWord("1234567");
        assertEquals(false, actualValidation1);
        boolean actualValidation2 = PersonController.checkLengthOfPassWord("12345678");
        assertEquals(true, actualValidation2);
    }

    @Test
    public void usernameTypeErr() {
        boolean actualValidation = PersonController.usernameTypeErr("mohammad_dn");
        assertEquals(true, actualValidation);
    }

    @Test
    public void emailTypeErr() {
        boolean actualValidation = PersonController.emailTypeErr("joey@gmail.com");
        assertEquals(true, actualValidation);
    }

    @Test
    public void phoneTypeErr() {
        boolean actualValidation = PersonController.phoneTypeErr("78789dsd7s8");
        assertEquals(false, actualValidation);
    }

    @Test
    public void existUsername() {
        boolean actualExistence = PersonController.existUsername("ch12");
        assertEquals(true, actualExistence);
    }
}