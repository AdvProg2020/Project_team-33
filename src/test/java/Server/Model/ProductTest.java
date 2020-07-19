//package Server.Model;
//
//import Client.Controller.RegisterAndLogin.RegisterProcess;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class ProductTest {
//    Category diaryCategory = new Category("dairy", null);
//    Product productSample = new Product("yogurt", "Greek", 10.5
//            , "EDITING", diaryCategory, false);
//    Seller exampleSeller = new Seller("Chandler", "Bing", "ch12",
//            "1234567", "0918765268", "bing@gmail.com",
//            "born in 1982", "Google");
//    Buyer exampleBuyer = new Buyer("Joey", "Tribbiani", "Joe_17",
//            "1234", "0912679863", "joey@yahoo.com", 1500, null);
//
//    @Test
//    public void getProductID() {
//        int actualId = productSample.getID();
//        assertEquals(Product.numberOfProductsFromBegin, actualId);
//    }
//
//    @Test
//    public void changeCategory() {
//        Category newCategory = new Category("calcium-rich", diaryCategory);
//        productSample.changeCategory(newCategory);
//        assertEquals(productSample.getProductCategory(), newCategory);
//    }
//
//    @Test
//    public void addSellerAndRemoveSeller() {
//        productSample.addSeller(exampleSeller);
//        assertEquals(true, productSample.getAllSeller().contains(exampleSeller));
//        productSample.removeSeller(exampleSeller);
//        assertEquals(false, productSample.getAllSeller().contains(exampleSeller));
//    }
//
//    @Test
//    public void changeDescription() {
//        productSample.changeDescription("French");
//        assertEquals("French", productSample.getDescription());
//    }
//
//    @Test
//    public void changePrice() {
//        productSample.changePrice(15.5);
//        assertEquals(15.5, productSample.getPrice(), 0.001);
//    }
//
//    @Test
//    public void isProductWithThisNameExist() {
//        boolean actualExistence = Product.isProductWithThisNameExist("yogurt");
//        assertEquals(true, actualExistence);
//    }
//
//    @Test
//    public void getProductWithName() {
//        Product actualProduct = Product.getProductWithName("yogurt");
//        assertEquals(productSample.getName(), actualProduct.getName());
//    }
//
//    @Test
//    public void getProductWithID() {
//        Product actualProduct = Product.getProductWithName("Coffee");
//        assertEquals(null, actualProduct);
//    }
//
//    @Test
//    public void getBuildingTime() {
//        LocalDate actualTime = productSample.getBuildingTime();
//        assertEquals(actualTime, productSample.getBuildingTime());
//    }
//
//    @Test
//    public void getIsProductAvailable() {
//        boolean actualValidation = productSample.getIsProductAvailable();
//        assertFalse(actualValidation);
//    }
//
//    @Test
//    public void getAllSeller() {
//        productSample.addSeller(exampleSeller);
//        ArrayList<Seller> actualSellers = productSample.getAllSeller();
//        assertEquals(true, actualSellers.contains(exampleSeller));
//    }
//
//    @Test
//    public void getAllComments() {
//        Comment commentSample = new Comment(exampleBuyer, productSample, true);
//        productSample.addComment(commentSample);
//        assertEquals(true, productSample.getAllComments().contains(commentSample));
//    }
//
//    @Test
//    public void getAllScores() {
//        Score scoreSample = new Score(exampleBuyer, 5, productSample);
//        productSample.addScore(scoreSample);
//        assertEquals(true, productSample.getAllScores().contains(scoreSample));
//    }
//
//    @Test
//    public void addBuyer() {
//        productSample.addBuyer(exampleBuyer);
//        assertEquals(true, productSample.getBuyers().contains(exampleBuyer));
//    }
//
//    @Test
//    public void testToString() {
//        String expectedResult = "Product{" +
//                "description='" + "Greek" + '\'' +
//                ", name='" + "yogurt" + '\'' +
//                ", productID=" + productSample.getID() +
//                ", price=" + 10.5 +
//                ", state=" + "EDITING" +
//                ", allSeller=" + productSample.getAllSeller() +
//                ", isProductAvailable=" + false +
//                '}';
//        assertEquals(expectedResult, productSample.toString());
//    }
//
//    @Test
//    public void isProductExistInCategory() {
//        boolean actualResult = productSample.isProductExistInCategory(diaryCategory);
//        assertEquals(true, actualResult);
//    }
//
//    @Test
//    public void getProductState() {
//        String actualResult = productSample.getProductState();
//        String expectedResult = "Product is Editing";
//        assertEquals(expectedResult, actualResult);
//    }
//}