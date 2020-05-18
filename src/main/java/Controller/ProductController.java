package Controller;

import Model.Product;

import java.util.ArrayList;

public class ProductController {
    public static String sort ;
    public static ArrayList<String> filtersType = new ArrayList<String>();
    public static ArrayList<String> filtersName = new ArrayList<String>();
    public static int startPrice , FinalPrice ;
    public static boolean checkIsNumberValid(int Id){
        return Product.allProducts.size() >= Id;
    }

    public static void addFilter(String filterType , String name){
        filtersName.add(name);
        filtersType.add(filterType);
    }

    public static void removeFilter(int i){
        filtersType.remove(i-1);
        filtersName.remove(i-1);
    }

    public static  boolean checkMinAndMax(int min , int max){
        if (min > max) return false;
        return  true ;
    }

    public  static  void deletePrice(){
        startPrice = -1 ;
        FinalPrice = -1 ;
    }

    public static  void reset(){
        FinalPrice = -1 ;
        startPrice = -1 ;
        filtersType = null ;
        filtersName = null ;
        sort = "Seen" ;
    }
}
