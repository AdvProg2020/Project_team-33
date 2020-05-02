package Model;

public class Person {
    protected String name , username , password, email , phone , family ;
    protected int money ;
    public Person(String name , String family , String username , String password , String phone , String email)
    {
        this.name = name ;
        this.family = family ;
        this.email = email ;
        this.password = password ;
        this.username = username ;
        this.phone = phone ;
    }
    public void changeName(String newName)
    {
        this.name = newName ;
    }
    public void changeFamily(String newFamily)
    {
        this.family = newFamily ;
    }
    public void changePassword(String newPassword)
    {
        this.password = newPassword ;
    }
    public void changeEmail(String newEmail)
    {
        this.email = newEmail ;
    }
    public void changePhone(String newPhone)
    {
        this.phone = newPhone ;
    }
    public String getName()
    {
        return  this.name;
    }
    public String getFamily()
    {
        return  this.family;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getUsername()
    {
        return this.username ;
    }
    public  String getEmail()
    {
        return this.email;
    }
    public  String getPhone()
    {
        return this.phone;
    }
}