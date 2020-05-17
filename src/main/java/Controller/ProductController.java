package Controller;

import Model.Product;

import java.util.ArrayList;

public class ProductController {
    public static String sort ;
    public static ArrayList<String> filtersType = new ArrayList<String>();
    public static ArrayList<String> filtersName = new ArrayList<String>();
    public static int startPrice , FinalPrice ;
    public static boolean checkIsNumberValid(int Id){
        if (Product.allProducts.size() < Id) return false;
        return true;
    }

    public static void addFilter(String filterType , String name){
        filtersName.add(name);
        filtersType.add(filterType);
    }

    public static void removeFilter(int i){
        filtersType.remove(i-1);
        filtersName.remove(i-1);
    }
}
