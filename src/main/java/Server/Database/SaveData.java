package Server.Database;

import Server.Model.Discount;
import Server.Model.Product;
import Server.Model.Users.*;
import com.google.gson.Gson;

import java.io.*;

public class SaveData {
    private static String path = "src/main/Sources/";
    private Gson gson;
    File file;

    public SaveData() {
        gson = new Gson();
//        file = new File("src/main/Sources/Users/");
    }

    public void addUser(Person person) {
//        String json = "{\"user name\":\"name\":\"family\":\"phone\":\"email\":\"password}";
//        gson.fromJson(String.valueOf(person), Person.class);
        File fileName = new File(path + "Users/" + person.getUsername() + ".txt");
//        if (!fileName.exists()) {
//            try {
//                fileName.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            String role;
            if (person instanceof Manager) {
                role = "manager";
            } else if (person instanceof Buyer) {
                role = "buyer";
            } else {
                role = "seller";
            }
            fileWriter.write(gson.toJson(person).substring(0, gson.toJson(person).indexOf("saveData") - 2) + "}");
            fileWriter.close();

//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            PrintWriter printWriter = new PrintWriter(bufferedWriter);
//            printWriter.flush();
//            printWriter.close();
//            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addManager(Manager manager) throws IOException {
        String path = "src/main/Sources/Users/managers/" + manager.getUsername() + ".json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(manager, Manager.class));
        fileWriter.close();


//        String json = "{\"user name\":\"name\":\"family\":\"phone\":\"email\":\"password}";
//        gson.fromJson(String.valueOf(person), Person.class);
//        File fileName = new File(path + "Users/" + person.getUsername() + ".txt");
//        if (!fileName.exists()) {
//            try {
//                fileName.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        try {
//            FileWriter fileWriter = new FileWriter(fileName);
//            fileWriter.write(gson.toJson(person).substring(0, gson.toJson(person).indexOf("saveData") - 2) + "}");
//            fileWriter.close();
//
////            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
////            PrintWriter printWriter = new PrintWriter(bufferedWriter);
////            printWriter.flush();
////            printWriter.close();
////            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void addSeller(Seller seller) throws IOException {
        String path = "src/main/Sources/Users/sellers/" + seller.getUsername() + ".json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(seller, Seller.class));
        fileWriter.close();

//        String json = "{\"user name\":\"name\":\"family\":\"phone\":\"email\":\"password}";
//        gson.fromJson(String.valueOf(person), Person.class);
//        File fileName = new File(path + "Users/" + person.getUsername() + ".txt");
//        if (!fileName.exists()) {
//            try {
//                fileName.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        try {
//            FileWriter fileWriter = new FileWriter(fileName);
//            fileWriter.write(gson.toJson(person).substring(0, gson.toJson(person).indexOf("saveData") - 2) + "}");
//            fileWriter.close();
//
////            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
////            PrintWriter printWriter = new PrintWriter(bufferedWriter);
////            printWriter.flush();
////            printWriter.close();
////            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void addBuyer(Buyer buyer) throws IOException {
        String path = "src/main/Sources/Users/buyers/" + buyer.getUsername() + ".json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(buyer, Buyer.class));
        fileWriter.close();


//        String json = "{\"user name\":\"name\":\"family\":\"phone\":\"email\":\"password}";
////        gson.fromJson(String.valueOf(person), Person.class);
//        File fileName = new File(path + "Users/" + person.getUsername() + ".txt");
//        if (!fileName.exists()) {
//            try {
//                fileName.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        try {
//            FileWriter fileWriter = new FileWriter(fileName);
//            fileWriter.write(gson.toJson(person).substring(0, gson.toJson(person).indexOf("saveData") - 2) + "}");
//            fileWriter.close();
//
////            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
////            PrintWriter printWriter = new PrintWriter(bufferedWriter);
////            printWriter.flush();
////            printWriter.close();
////            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void addSupporter(Supporter supporter) throws IOException {
        String path = "src/main/Sources/Users/supporters/" + supporter.getUsername() + ".json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(supporter, Supporter.class));
        fileWriter.close();
    }

    public void addProduct(Product product) throws IOException {
        String path = "src/main/Sources/products/" + product.getProductID() + ".json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(product, Product.class));
        fileWriter.close();
    }

    public void addDiscount(Discount discount) throws IOException {
        String path = "src/main/Sources/discounts/" + discount.getCode() + ".json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(discount, Discount.class));
        fileWriter.close();
    }



}
