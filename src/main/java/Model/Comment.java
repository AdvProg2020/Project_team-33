package Model;

public class Comment {
    private Person personWhoGiveComment ;
    private Product product;
    private boolean isPersonBuyProduct;
    private  boolean isPersonLogin ;
    private  CommentState state ;
    public Comment(Person person , Product product , boolean isPersonBuyProduct)
    {
        this.product = product ;
        this.isPersonBuyProduct = isPersonBuyProduct;
        if (person instanceof Buyer) this.isPersonLogin = true ;
        else this.isPersonLogin = false ;
        this.state = CommentState.PENDING ;
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