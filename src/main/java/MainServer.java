import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(127001);
        Socket socket = serverSocket.accept();
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        String line = inputStream.readUTF();

    }

}
