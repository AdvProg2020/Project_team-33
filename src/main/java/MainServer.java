import Model.Users.Person;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) throws IOException {
        new ServerImpl().run();
    }

    static class ServerImpl {

        public void run() throws IOException {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                ServerSocket serverSocket = new ServerSocket(127001);
                Socket socket = serverSocket.accept();
                DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String line = inputStream.readUTF();
                functionality(line);

            }
        }

        public void functionality(String method){

        }

    }

}
