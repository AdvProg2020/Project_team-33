package Model;

import Model.Users.Buyer;
import Model.Users.NormalPerson;
import Model.Users.Person;

public class Comment {
    private Person personWhoGiveComment ;
    private Product product;
    private boolean isPersonBuyProduct;
    private String name = personWhoGiveComment.getName();
    private  boolean isPersonLogin ;
    private  CommentState state ;
    private String comment;
    public Comment(Person person , Product product , boolean isPersonBuyProduct, String comment)
    {
        this.product = product ;
        this.isPersonBuyProduct = isPersonBuyProduct;
        this.isPersonLogin = person instanceof Buyer;
        this.state = CommentState.PENDING;
        this.comment = comment;
    }

    public Product getProduct() {
        return this.product;
    }

    public boolean isPersonBuyProduct() {
        return this.isPersonBuyProduct;
    }

    public String getState() {
        switch (state)
        {
            case CONFIRMED:
                return  "Confirmed";
            case PENDING:
                return  "Pending";
            case REJECTED:
                return  "Rejected";
        }
        return null;
    }
    public Person getPersonWhoCommented()
    {
        if (isPersonLogin) return (Buyer) personWhoGiveComment;
        return (NormalPerson) personWhoGiveComment ;
    }

}
enum CommentState{
    REJECTED,
    PENDING,
    CONFIRMED
}
