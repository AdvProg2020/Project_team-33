package Server.Model.Requests;

import Server.Model.Product;
import Server.Model.Users.Person;
import Server.Model.Users.Seller;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    public static int counter = 0;
    protected int id;
    protected String type;
    protected String condition;
    protected Person sender;
    public static ArrayList<Request> allRequests = new ArrayList<>();

    public Request(int id, String type, String condition, Person sender) {
        this.id = id;
        this.type = type;
        this.condition = condition;
        this.sender = sender;
        allRequests.add(this);
        ((Seller) sender).setSellerRequests(this);
    }

    public Request() {

    }

    public int getId() {
        return id;
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
        } else if (this instanceof RequestAddAuction) {
            RequestAddAuction requestAddAuction = (RequestAddAuction) this;
            if (condition.equals("Accept")) {
                requestAddAuction.getAuction().acceptAuction();
            } else if (condition.equals("Decline")) {
                Auction.getAllAuctions().remove(requestAddAuction.getAuction());
            }

        } else if (this instanceof RequestEditAuction) {
            RequestEditAuction requestEditAuction = (RequestEditAuction) this;
            if (condition.equals("Accept")) {
                requestEditAuction.setChanges(requestEditAuction.getField());
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

    public static Request getRequestById(int id) {
        for (Request allRequest : allRequests) {
            if (allRequest.getId() == id) {
                return allRequest;
            }
        }
        return null;
    }
}
