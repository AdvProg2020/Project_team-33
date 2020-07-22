package Client.Model.Users;

public class Person {
    protected String name, username, password, email, phone, family;
    protected boolean isOnline;

    public Person(String username, String name, String family, String phone, String email, String password) {
        this.username = username;
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Person() {

    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setFamily(String newFamily) {
        this.family = newFamily;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }


    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    public String getFamily() {
        return this.family;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", family='" + family +
                '}';
    }
}