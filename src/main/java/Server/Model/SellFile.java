package Server.Model;

import Server.Model.Category.Category;
import Server.Model.Users.Seller;

import java.io.File;
import java.util.ArrayList;

public class SellFile extends Product{
    private File file;
    private String type;
    private static ArrayList<SellFile> allFiles = new ArrayList<>();

    public SellFile(File file, String productID, String name, String company, long money, Seller seller,
                    Category category, String description, String type, String requestCondition) {
        super(productID, name, company, money, seller,
                category, description, requestCondition);
        this.file = file;
        this.seller = seller;
        this.type = type;
        allFiles.add(this);
    }

    public String getType() {
        return type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<SellFile> getAllFiles() {
        return allFiles;
    }

    public static void setAllFiles(ArrayList<SellFile> allFiles) {
        SellFile.allFiles = allFiles;
    }
}
