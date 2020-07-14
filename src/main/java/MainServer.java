import Model.Users.Person;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) throws IOException {
        new ServerImpl().run();
    }

    static class ServerImpl {
        public ServerSocket serverSocket;
        public Socket clientSocket;
        public DataOutputStream dataOutputStream;
        public DataInputStream dataInputStream;

        private void run() throws IOException {
            Scanner scanner = new Scanner(System.in);
            serverSocket = new ServerSocket(8888);
            waitForClient();
            handleClient();

        }

        private void waitForClient() throws IOException {
            clientSocket = serverSocket.accept();
            dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        }

        public void handleClient() {
            try {
                String input = "";
                while (true) {
                    input = dataInputStream.readUTF();
                    if (input.startsWith("SignIn")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else if (input.startsWith("")) {

                    } else {
                        System.exit(0);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        private void updateDatabase() {
        }

    }

}
