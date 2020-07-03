package Model;

import Model.Users.Buyer;
import Model.Users.NormalPerson;
import Model.Users.Person;
import javafx.scene.image.Image;

public class Comment {
    private Person personWhoGiveComment;
    private Product product;
    private String buyCondition;
    private String name;
    private boolean isPersonLogin;
    private CommentState state;
    private String comment;

    public Comment(Person person, Product product, boolean isPersonBuyProduct, String comment) {
        this.product = product;
        this.personWhoGiveComment = person;
        if(isPersonBuyProduct){
//            buyCondition = new Image(getClass().getResourceAsStream("src/main/java/View/images/true.jpg"));
            buyCondition = "yes";
        }
        else{
//            buyCondition = new Image(getClass().getResourceAsStream("src/main/java/View/images/false.jpg"));
            buyCondition = "no";
        }
        this.isPersonLogin = person instanceof Buyer;
        this.state = CommentState.PENDING;
        this.comment = comment;
        this.name = personWhoGiveComment.getName();
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

enum CommentState {
    REJECTED,
    PENDING,
    CONFIRMED
}
