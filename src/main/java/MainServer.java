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
        private ServerSocket serverSocket;
        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;

        private void run() throws IOException {
            Scanner scanner = new Scanner(System.in);
            serverSocket = new ServerSocket(8888);
            waitForClient();

            while (true) {
//                String line = inputStream.readUTF();
                functionality(line);

            }
        }

        private void waitForClient() throws IOException {
            clientSocket = serverSocket.accept();
            dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        }

        private void functionality(String method){

        }

        private void updateDatabase(){
        }

    }

}
