package Model;

import java.util.ArrayList;

public class Category {
    public static ArrayList<Category> allCategory = new ArrayList<Category>();
    private String name ;
    private ArrayList<String> quality = new ArrayList<String>();
    private ArrayList<Category> subCategory = new ArrayList<Category>();
    private Category superCategory ;
    private ArrayList<Product> products = new ArrayList<Product>();
    public Category (String name , Category superCategory){
        this.name = name ;
        allCategory.add(this);
        this.superCategory = superCategory ;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Category> getSubCategory() {
        return this.subCategory;
    }

    public Category getSuperCategory() {
        return this.superCategory;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getQuality() {
        return this.quality;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public boolean isCategoryHasSuper(){
        if (this.superCategory == null) return false ;
        return true ;
    }

    public void changeSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    public void addProduct(Product product){
        this.products.add(product);
        product.changeCategory(this);
    }

    public void removeProduct(Product product){
        for (Product eachproduct : this.products)
            if (eachproduct.equals(product)) this.products.remove(product);
    }

    public boolean isThisCategoryHasThisProduct(Product product){
        for (Product eachproduct : this.products)
            if (eachproduct.equals(product)) return true;
        return  false;
    }

    public void addQuality(String newQuality){
        this.quality.add(newQuality);
    }

    public boolean isCategoryHasThisQuality(String quality1){
        for (String eachQuality : this.quality)
            if (eachQuality.equals(quality1)) return  true ;
        return  false ;
    }

    public void removeQuality(String qualityThatWantToBeDelete)
    {
        for (String eachQuality : this.quality)
            if (eachQuality.equals(qualityThatWantToBeDelete)) this.quality.remove(qualityThatWantToBeDelete);
    }

    public boolean isCategoryHasThisSubCategory(Category sub)
    {
        for (Category eachSub : this.subCategory)
            if (eachSub.equals(sub)) return  true ;
        return false;
    }

    public void removeSubCategory(Category category){
        for (Category eachSub : this.subCategory)
            if (eachSub.equals(category))  this.subCategory.remove(category) ;
    }
    public void addSubCategory(Category category){
        this.subCategory.add(category);
    }

    public static Category getCategoryByName(String name){
        for (Category category : allCategory)
            if (category.getName().equals(name)) return category;
        return null ;
    }
}
