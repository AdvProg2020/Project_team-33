package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommentTest {
    //TODO has to complete
    NormalPerson samplePerson = new NormalPerson();
    Product productSample = new Product("milk", "good for health", 5.5
            , "CONFIRMED", null, true);
    Comment comment = new Comment(samplePerson, productSample, false);

    @Test
    public void getProduct() {
        Product actualProduct = comment.getProduct();
        assertEquals(productSample, actualProduct);
    }

    @Test
    public void isPersonBuyProduct() {
        boolean actualResult = comment.isPersonBuyProduct();
        assertEquals(false,actualResult);
    }

    @Test
    public void getState() {
        String result = comment.getState();
        assertEquals("Pending", result);
    }

    @Test
    public void getPersonWhoCommented() {
        Person actualPerson = comment.getPersonWhoCommented();
        assertEquals(null, actualPerson);
    }
}