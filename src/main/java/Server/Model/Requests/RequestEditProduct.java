package Server.Model.Requests;

import Server.Model.Category.Category;
import Server.Model.Product;
import Server.Model.Users.Person;
import Server.Model.Users.Seller;

import java.util.ArrayList;

public class RequestEditProduct extends Request {
    private Product product;
    private String field;
    private String newChange;
    public static ArrayList<RequestEditProduct> allAddProductRequest = new ArrayList<>();


    public RequestEditProduct(String type, String condition, Person sender, Product product, String field, String newChange) {
        super(Request.counter, type, condition, sender);
        Request.counter++;
        this.product = product;
        this.field = field;
        this.newChange = newChange;
        allAddProductRequest.add(this);
        this.sendRequestInSellerRequests();
    }

    private void sendRequestInSellerRequests() {
        Seller seller = (Seller) this.getSender();
        seller.setSellerRequests(this);
    }

    public String getField() {
        return field;
    }

    public Product getProduct() {
        return product;
    }

    public String getNewChange() {
        return newChange;
    }

    public void setChanges() {
        if (this.getField().equalsIgnoreCase("id")) {
            this.getProduct().setProductID(this.getNewChange());
        } else if (this.getField().equalsIgnoreCase("name")) {
            this.getProduct().setName(this.getNewChange());
        } else if (this.getField().equalsIgnoreCase("money")) {
            this.getProduct().setMoney(Long.parseLong(this.getNewChange()));
        } else if (this.getField().equalsIgnoreCase("category")) {
            Category.changeProductCategory(this.getProduct().getCategory(), Category.getCategoryByName(this.getNewChange()), this.getProduct());
            this.getProduct().setCategory(Category.getCategoryByName(this.getNewChange()));
        } else if (this.getField().equalsIgnoreCase("description")) {
            this.getProduct().setDescription(this.getNewChange());
        }
    }
}
