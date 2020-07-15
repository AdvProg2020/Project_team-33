import Controller.RegisterAndLogin.PersonController;
import Controller.RegisterAndLogin.RegisterProcess;
import Model.Users.Person;
import Model.Users.Seller;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import View.Menu;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) throws IOException {
        new ServerImpl().run();
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private ObjectOutputStream objectOutputStream;
        private ServerImpl server;
        private Person person;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream,
                             ObjectOutputStream objectOutputStream, ServerImpl server) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.objectOutputStream = objectOutputStream;
            this.server = server;
        }

        public void handleClient() {
            try {
                String input = "";
                while (true) {
                    input = dataInputStream.readUTF();
                    if (input.startsWith("createAccount")) {
                        String[] splitInput = input.split(",");
                        person = server.createAccount(splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5], splitInput[6], splitInput[7], dataOutputStream, objectOutputStream);
                    } else if (input.startsWith("chooseRole,buyer")) {
                        String[] splitInput = input.split(",");
                        person = server.chooseBuyerRole(person, dataOutputStream);
                    } else if (input.startsWith("chooseRole,seller")) {
                        String[] splitInput = input.split(",");
                        person = server.chooseSellerRole(person, splitInput[2], dataOutputStream);
                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else {
                        dataOutputStream.writeUTF("done");
                        dataOutputStream.flush();
                        clientSocket.close();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            handleClient();
        }
    }

    static class ServerImpl {
        public ServerSocket serverSocket;

        private void run() throws IOException {
            serverSocket = new ServerSocket(8888);
            Socket clientSocket;
            while (true) {
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream, objectOutputStream, this).start();
            }

        }

        private Person createAccount(String username, String name, String family, String email, String password, String reenterPassword,
                                     String phone, DataOutputStream dataOutputStream, ObjectOutputStream objectOutputStream) throws IOException {

            if (PersonController.existUsername(username)) {
                dataOutputStream.writeUTF("fail");
                dataOutputStream.flush();
            }

            StringBuilder answer = new StringBuilder();
            boolean create = true;

            if (!PersonController.usernameTypeErr(username)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (PersonController.existUsername(username)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (username.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!PersonController.emailTypeErr(email)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (email.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (PersonController.checkLengthOfPassWord(password)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!reenterPassword.equals(password)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (password.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (reenterPassword.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (!PersonController.phoneTypeErr(phone)) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (phone.isEmpty()) {
                answer.append("1-");
                create = false;
            } else {
                answer.append("0-");
            }

            if (create) {
                answer.append("pass-");
                if (!PersonController.isManagerAccountCreate) {
                    answer.append("1");
                    PersonController.mainManager = RegisterProcess.createAccountForMainManager(username, name, family,
                            phone, email, password);
                    LoginMenu.currentPerson = PersonController.mainManager;
                    PersonController.isManagerAccountCreate = true;
                    Menu.currentMenu = Menu.previousMenu;
                    dataOutputStream.writeUTF(answer.toString());
                    dataOutputStream.flush();
                    return PersonController.mainManager;
                } else {
                    answer.append("2");
                    Person registeringPerson = new Person(username, name, family,
                            phone, email, password);
                    objectOutputStream.writeObject(registeringPerson);
                    dataOutputStream.writeUTF(answer.toString());
                    dataOutputStream.flush();
                    return registeringPerson;
                }
            } else {
                answer.append("fail");
                dataOutputStream.writeUTF(answer.toString());
                dataOutputStream.flush();
                return null;
            }

        }

        public Person chooseBuyerRole(Person person, DataOutputStream dataOutputStream) throws IOException {
            Person.deleteUser(person);
            LoginMenu.currentPerson = RegisterProcess.createAccountForBuyer(person.getUsername(), person.getName(), person.getFamily(),
                    person.getPhone(), person.getEmail(), person.getPassword());
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
            return LoginMenu.currentPerson;
        }

        public Person chooseSellerRole(Person person, String company, DataOutputStream dataOutputStream) throws IOException {
            Seller seller = new Seller(person.getUsername(), person.getName(), person.getFamily(),
                    person.getPhone(), person.getEmail(), person.getPassword(), company);
            Person.deleteUser(person);
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();
            return seller;
        }


        private void updateDatabase() {
        }

    }

}
