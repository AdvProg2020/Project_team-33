import Model.Users.Person;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) throws IOException {
        new ServerImpl().run();
    }

    static class ClientHandler extends Thread{
        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private ServerImpl server;
        private Person person;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream, ServerImpl server){
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.server = server;
        }

        public void handleClient() {
            try {
                String input = "";
                while (true) {
                    input = dataInputStream.readUTF();
                    if (input.startsWith("createAccount")) {
                        String[] splitInput = input.split(",");
                        server.createAccount(splitInput[1], splitInput[2],dataOutputStream);
                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

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
        public void run(){
            handleClient();
        }
    }

    static class ServerImpl {
        public ServerSocket serverSocket;

        private void run() throws IOException {
            Scanner scanner = new Scanner(System.in);
            serverSocket = new ServerSocket(8888);
            Socket clientSocket;
            while (true){
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream, this).start();
            }

        }

        private Person createAccount(String username, String password, DataOutputStream dataOutputStream) throws IOException {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    if (!user.getPassword().equals(password)) {
                        dataOutputStream.writeUTF("Failure");
                    } else {
                        dataOutputStream.writeUTF("Success");
//                        currentUser = user;
                        System.out.println("Logged in user with username : " + username + " and password : " + password);
                    }
                    dataOutputStream.flush();
                    return user;
                }
            }
            User newUser = new User(username, password);
//            currentUser = newUser;
            users.add(newUser);
            usersInfo.put(newUser, new UserInfo());
            dataOutputStream.writeUTF("Success");
            dataOutputStream.flush();
            System.out.println("Created and Logged in user with username : " + newUser.getUsername() + " and password : " + newUser.getPassword());
            return newUser;
        }

        private void updateDatabase() {
        }

    }

}
