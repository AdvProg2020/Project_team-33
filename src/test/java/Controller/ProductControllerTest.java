package Controller;

import Model.Category;
import Model.Users.Person;
import Model.Product;
import org.junit.Test;

import java.util.logging.Filter;

import static org.junit.Assert.*;

public class ProductControllerTest {
    Category diaryCategory = new Category("dairy", null);
    Product productSample1 = new Product("milk", "good for health", 5.5
            , "CONFIRMED", diaryCategory, true);

//    @Test
//    public void checkIsNumberValid() {
//        boolean actualNumberValidation = ProductController.checkIsNumberValid(3);
//        assertEquals(false, actualNumberValidation);
//    }

    @Test
    public void addFilterAndRemoveFilter() {
        ProductController.addFilter("Category", "diaryFilter");
        assertEquals(true,ProductController.filtersName.contains("diaryFilter"));
        assertEquals(true,ProductController.filtersType.contains("Category"));
        ProductController.removeFilter(1);
        assertNotEquals(true,ProductController.filtersName.contains("diaryFilter"));
        assertNotEquals(true,ProductController.filtersType.contains("Category"));
    }

}