package Controller;

import Model.Seller;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterProcessTest {

    @Test
    public void createAccountForSeller() {
        RegisterProcess.createAccountForSeller("Chandler", "Bing", "ch12",
                "1234567", "0918765268", "bing@gmail.com",
                "born in 1982", "Google");
        assertEquals(true, Seller.isSellerWithThisUsernameExist("Chandler"));
    }

    @Test
    public void createAccountForBuyer() {
    }

    @Test
    public void login() {
    }

    @Test
    public void createAccountForManager() {
    }

    @Test
    public void passwordTypeErr() {
    }

    @Test
    public void checkPasswordUseNumberAndAlphabet() {
    }

    @Test
    public void checkLengthOfPassWord() {
    }

    @Test
    public void usernameTypeErr() {
    }

    @Test
    public void emailTypeErr() {
    }

    @Test
    public void phoneTypeErr() {
    }

    @Test
    public void existUsername() {
    }
}