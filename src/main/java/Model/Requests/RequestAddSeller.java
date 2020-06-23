package Model.Requests;

import java.util.ArrayList;

public class RequestAddSeller extends Request {

    private String username;
    private String password;
    private String phone;
    private String name;
    private String family;
    private String email;
    private String company;
    private String description;
    public static ArrayList<RequestAddSeller> allAddSellerRequest = new ArrayList<>();

    public RequestAddSeller(String username, String password, String phone, String name, String family,
                            String email, String company, String description) {
        super(null, null, null);
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.family = family;
        this.email = email;
        this.company = company;
        this.description = description;
        allAddSellerRequest.add(this);
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

}
