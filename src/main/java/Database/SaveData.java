package Database;

import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Person;
import com.google.gson.Gson;

import java.io.*;

public class SaveData {
    private static String path = "src/main/Sources/";
    private Gson gson;
    File file;

    public SaveData() {
        gson = new Gson();
        file = new File("src/main/Sources/Users/");
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

    public void addManager(Person person) {
//        String json = "{\"user name\":\"name\":\"family\":\"phone\":\"email\":\"password}";
//        gson.fromJson(String.valueOf(person), Person.class);
        File fileName = new File(path + "Users/" + person.getUsername() + ".txt");
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
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

    public void addSeller(Person person) {
//        String json = "{\"user name\":\"name\":\"family\":\"phone\":\"email\":\"password}";
//        gson.fromJson(String.valueOf(person), Person.class);
        File fileName = new File(path + "Users/" + person.getUsername() + ".txt");
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
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

    public void addBuyer(Person person) {
//        String json = "{\"user name\":\"name\":\"family\":\"phone\":\"email\":\"password}";
//        gson.fromJson(String.valueOf(person), Person.class);
        File fileName = new File(path + "Users/" + person.getUsername() + ".txt");
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
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
}
