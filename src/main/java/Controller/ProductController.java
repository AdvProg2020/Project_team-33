package Controller;

import Model.Product;
import Model.Score;

import java.util.ArrayList;

//public class ProductController {
//    public static String sort ;
//    public static ArrayList<String> filtersType = new ArrayList<String>();
//    public static ArrayList<String> filtersName = new ArrayList<String>();
//    public static int startPrice , FinalPrice ;
//    public static Product checkIsNumberValid(int Id){
//        return Product.getProductWithID(Id);
//    }
//
//    public static void removeProduct(int id) {
//        Product.removeProduct(id);
//    }
//
//    public static void addFilter(String filterType , String name){
//        filtersName.add(name);
//        filtersType.add(filterType);
//    }
//
//    public static void removeFilter(int i){
//        filtersType.remove(i-1);
//        filtersName.remove(i-1);
//    }
//
//    public static  boolean checkMinAndMax(int min , int max){
//        if (min > max) return false;
//        return  true ;
//    }
//
//    public  static  void deletePrice(){
//        startPrice = -1 ;
//        FinalPrice = -1 ;
//    }
//
//    public static  void reset(){
//        FinalPrice = -1 ;
//        startPrice = -1 ;
//        filtersType = null ;
//        filtersName = null ;
//        sort = "Seen" ;
//    }
//
//    public static double calculateAverageScore(Product product){
//        int sum = 0;
//        int number = 0;
//        for (Score score : product.getAllScores()) {
//            sum += score.getScore();
//            number++;
//        }
//        return (double)sum / number;
//    }
//}
