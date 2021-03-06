package Server.Controller.SellerController;

import Server.Model.Auction;
import Server.Model.Category.Category;
import Server.Model.Product;
import Server.Model.Requests.*;
import Server.Model.Users.Person;
import Server.Model.Users.Seller;

import java.util.ArrayList;

public class SellerAbilitiesController {

    public static void editPersonalInfo(Person person, String field, String newChange) {
        if (field.equalsIgnoreCase("password")) {
            person.setPassword(newChange);
        } else if (field.equalsIgnoreCase("name")) {
            person.setName(newChange);
        } else if (field.equalsIgnoreCase("family")) {
            person.setFamily(newChange);
        } else if (field.equalsIgnoreCase("phone")) {
            person.setPhone(newChange);
        } else if (field.equalsIgnoreCase("email")) {
            person.setEmail(newChange);
        } else if (field.equals("company")) {
            Seller seller = (Seller) person;
            seller.setCompany(newChange);
        }
    }

    public static ArrayList<Request> getAllSellerRequests(Seller seller) {
        return seller.getSellerRequests();
    }

    public static void deleteRequest(Seller seller, Request request) {
        seller.deleteRequest(request);
    }

    public static ArrayList<Product> getAllProducts(Seller seller) {
        return seller.getProducts();
    }

    public static void sendAddProductRequestToManager(String id, String name, long money, Seller seller, Category category, String description) {
        new Product(id, name, seller.getCompany(), money, seller, category, description, "Unknown");
    }

    public static void sendEditProductRequest(Person person, Product product, String field, String newChange) {
        if (field.equalsIgnoreCase("id")) {
            new RequestEditProduct("Edit product", "Unknown", person, product, "id", newChange);
        } else if (field.equalsIgnoreCase("name")) {
            new RequestEditProduct("Edit product", "Unknown", person, product, "name", newChange);
        } else if (field.equalsIgnoreCase("money")) {
            new RequestEditProduct("Edit product", "Unknown", person, product, "money", newChange);
        } else if (field.equalsIgnoreCase("category")) {
            new RequestEditProduct("Edit product", "Unknown", person, product, "category", newChange);
        } else if (field.equalsIgnoreCase("description")) {
            new RequestEditProduct("Edit product", "Unknown", person, product, "description", newChange);
        }
    }

    public static void sendDeleteProductRequest(Person person, Product product) {
        new RequestDeleteProduct("Delete product", "Unknown", person, product);
    }

    public static void sendAddAuctionRequest(Person person, Auction auction) {
        new RequestAddAuction("Add auction", "Unknown", person, auction);
    }

    public static void sendEditAuctionRequest(Person person, Auction auction, String field, String newChange) {
        new RequestEditAuction("Edit auction", "Unknown", person, auction, field, newChange);
    }

    public static ArrayList<Category> getAllCategories() {
        return Category.getAllCategory();
    }

    public static ArrayList<Auction> getAllSellerAuctions(Seller seller) {
        return seller.getSellerAuctions();
    }

}
