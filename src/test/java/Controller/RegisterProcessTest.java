package Controller;

import Model.Seller;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterProcessTest {

    @Test
    public void createAccountForSeller() {
        Seller.allSellers.add(RegisterProcess.createAccountForSeller("Chandler", "Bing", "ch12",
                "1234567", "0918765268", "bing@gmail.com",
                "born in 1982", "Google"));
        assertEquals(true, Seller.isSellerWithThisUsernameExist("ch12"));
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
        boolean actualValidation = RegisterProcess.passwordTypeErr("12jdla$%");
        assertEquals(false, actualValidation);
    }

    @Test
    public void checkPasswordUseNumberAndAlphabet() {
        boolean actualValidation = RegisterProcess.checkPasswordUseNumberAndAlphabet("hello1234");
        assertEquals(true, actualValidation);
    }

    @Test
    public void checkLengthOfPassWord() {
        boolean actualValidation = RegisterProcess.checkLengthOfPassWord("1234567");
        assertEquals(false, actualValidation);
    }

    @Test
    public void usernameTypeErr() {
        boolean actualValidation = RegisterProcess.usernameTypeErr("mohammad_dn");
        assertEquals(true,actualValidation);
    }

    @Test
    public void emailTypeErr() {
        boolean actualValidation = RegisterProcess.emailTypeErr("joey@gmail.com");
        assertEquals(true,actualValidation);
    }

    @Test
    public void phoneTypeErr() {
        boolean actualValidation = RegisterProcess.phoneTypeErr("78789dsd7s8");
        assertEquals(false,actualValidation);
    }

    @Test
    public void existUsername() {
    }
}