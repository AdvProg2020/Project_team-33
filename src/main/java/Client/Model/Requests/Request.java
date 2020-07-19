package Client.Model.Requests;

import Client.Model.Product;
import Client.Model.Users.Person;
import Client.Model.Users.Seller;

import java.util.ArrayList;

public class Request {
    String type;
    String condition;
    Person sender;
    public static ArrayList<Request> allRequests = new ArrayList<>();

    public Request(String type, String condition, Person sender) {
        this.type = type;
        this.condition = condition;
        this.sender = sender;
        allRequests.add(this);
    }

    public void setCondition(String condition) {
        this.condition = condition;
        if (this instanceof RequestAddSeller) {
            if (condition.equals("Accept")) {
                Seller seller = (Seller) this.getSender();
                seller.setCondition("Accept");
            } else if (condition.equals("Decline")) {
                Seller seller = (Seller) this.getSender();
                seller.setCondition("Decline");
            }
        } else if (this instanceof RequestAddProduct) {
            if (condition.equals("Accept")) {
                RequestAddProduct requestAddProduct = (RequestAddProduct) this;
                requestAddProduct.getProduct().setCondition("Accept");
            } else if (condition.equals("Decline")) {
                RequestAddProduct requestAddProduct = (RequestAddProduct) this;
                requestAddProduct.getProduct().setCondition("Decline");
            }
        } else if (this instanceof RequestEditProduct) {
            if (condition.equals("Accept")) {
                RequestEditProduct requestEditProduct = (RequestEditProduct) this;
                requestEditProduct.setChanges();
            }
        } else if (this instanceof RequestDeleteProduct) {
            if (condition.equals("Accept")) {
                RequestDeleteProduct requestDeleteProduct = (RequestDeleteProduct) this;
                Seller seller = (Seller) this.getSender();
                seller.deleteProduct(((RequestDeleteProduct) this).getProduct());
                Product.deleteProduct(requestDeleteProduct.getProduct());
            }
        }

    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }

    public Person getSender() {
        return sender;
    }

    public static void deleteRequest(Request request) {
        allRequests.remove(request);
    }


}
