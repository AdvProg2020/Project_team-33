package Server.Model;

import Server.Model.Category.Category;
import Server.Model.Category.SubCategory;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class CategoryTest {

    @Test
    public void constructorTest() {
        Category category = new Category("toy", null, null);
        assertTrue(Category.getAllCategory().contains(category));
        category.setName("dairy");
        assertEquals("dairy", category.getName());
        Category.deleteCategory(category);
        assertFalse(Category.getAllCategory().contains(category));
    }

    @Test
    public void getCategoryByNameTest() {
        Category.getAllCategory().clear();
        ArrayList<String> details = new ArrayList<>();
        Category category = new Category("dairy", null, null);
        Category actual = Category.getCategoryByName("dairy");
        assertEquals(category, actual);
        assertNull(Category.getCategoryByName("salam"));
    }

    @Test
    public void detailsTest() {
        ArrayList<String> details = new ArrayList<>();
        details.add("hich1");
        details.add("hich2");
        details.add("hich3");
        Category category = new Category("laptop", null, details);
        category.setDetail1("1");
        category.setDetail2("2");
        category.setDetail3("3");
        assertEquals("1", category.getDetail1());
        assertEquals("2", category.getDetail2());
        assertEquals("3", category.getDetail3());
    }

    @Test
    public void subCategoryTest() {
        ArrayList<String> details = new ArrayList<>();
        details.add("digital");
        Category category = new Category("Digital accessories", null, null);
        SubCategory subCategory = new SubCategory("laptop", category, details);
//        category.addSubCategory(subCategory);
//        assertTrue(category.getSubCategories().contains(subCategory));
    }


}
