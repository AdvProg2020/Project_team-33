package Client.Model;

import Client.Model.Users.Buyer;
import Client.Model.Users.Person;

public class Comment {
    private Person personWhoGiveComment;
    private Product product;
    private String buyCondition;
    private String name;
    private boolean isPersonLogin;
    private String comment;

    public Comment(String name, Product product, String buyCondition, String comment) {
        this.product = product;
        this.name = name;
//        this.personWhoGiveComment = person;
        this.buyCondition = buyCondition;

//        this.isPersonLogin = person instanceof Buyer;
        this.comment = comment;
//        this.name = personWhoGiveComment.getName();
    }

    public Product getProduct() {
        return this.product;
    }

    public String getName() {
        return name;
    }

    public String getBuyCondition() {
        return buyCondition;
    }

    public String getComment() {
        return comment;
    }

    public Person getPersonWhoCommented() {
        return personWhoGiveComment;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
