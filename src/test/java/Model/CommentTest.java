//package Model;
//
//import Model.Category.Category;
//import Model.Users.NormalPerson;
//import Model.Users.Person;
//import Model.Users.Seller;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class CommentTest {
//    NormalPerson samplePerson = new NormalPerson();
////    Seller seller = new Seller("mohammad", "mohammad", "daviran",
////            "1234", "mr@gmail.com", " 123", "Apple");
//    ArrayList<String> details = new ArrayList<>();
//    Category diaryCategory = new Category("dairy", null, details);
//    Product productSample = new Product("98278", "milk", "pegah", 100, null, diaryCategory, "salam", "CONFIRMED");
//    Comment comment = new Comment(samplePerson, productSample, false, "awful");
//
//    @Test
//    public void getProduct() {
//        Product actualProduct = comment.getProduct();
//        assertEquals(productSample, actualProduct);
//    }
//
////    @Test
////    public void isPersonBuyProduct() {
////        boolean actualResult = comment.isPersonBuyProduct();
////        assertEquals(false, actualResult);
////    }
////
////    @Test
////    public void getState() {
////        String result = comment.getState();
////        assertEquals("Pending", result);
////    }
//
//    @Test
//    public void getPersonWhoCommented() {
//        Person actualPerson = comment.getPersonWhoCommented();
//        assertEquals(null, actualPerson);
//    }
//}