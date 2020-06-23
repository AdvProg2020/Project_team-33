package Model.Users;

import Model.Cart;

public class NormalPerson extends Person {
    private Cart userCart  = new Cart();
    public NormalPerson() {
        super(null , null , null , null , null , null);
    }
    public Cart getCart()
    {
        return this.userCart;
    }
}

