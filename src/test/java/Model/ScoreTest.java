package Model;

import Model.Users.Buyer;
import Model.Users.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    Buyer exampleBuyer = new Buyer("Joey", "Tribbiani", "Joe_17",
            "1234", "0912679863", "joey@yahoo.com");
    Buyer exampleBuyer2 = new Buyer("Monica", "Geller", "mon25",
            "1234", "0912679863", "monica@yahoo.com");
    Category diaryCategory = new Category("dairy", null);
    Product productSample = new Product("milk", "good for health", 5.5
            , "CONFIRMED", diaryCategory, true);
    Score scoreSample = new Score(exampleBuyer, 5, productSample);

    @Test
    public void getProduct() {
        Product actualProduct = scoreSample.getProduct();
        assertEquals(productSample, actualProduct);
    }

    @Test
    public void setScore() {
        scoreSample.setScore(4);
        assertEquals(4, scoreSample.getScore());
    }

    @Test
    public void getPersonWhoGiveScore() {
        Person actualPerson = scoreSample.getPersonWhoGiveScore();
        assertEquals(exampleBuyer, actualPerson);
    }

    @Test
    public void isPersonScoredBefore() {
        boolean actualResult = Score.isPersonScoredBefore(exampleBuyer2, productSample);
        assertEquals(false, actualResult);
    }
}