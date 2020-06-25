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
    public static ArrayList<RequestAddSeller> allAddSellerRequest = new ArrayList<>();

    public RequestAddSeller(String username, String name, String family, String phone, String email,
                            String password, String company) {
        super(null, null, null);
        this.username = username;
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.company = company;
        allAddSellerRequest.add(this);
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
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
