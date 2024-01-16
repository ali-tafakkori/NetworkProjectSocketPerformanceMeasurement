import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                InputStream inFromClient = socket.getInputStream();
                DataInputStream in = new DataInputStream(inFromClient);

                OutputStream outToClient = socket.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToClient);

                List<Byte> bytes = new ArrayList<>();

                do {
                    bytes.add((byte) in.read());
                } while (in.available() > 0);

                byte[] bytes1 = new byte[bytes.size()];

                for (int i = 0; i < bytes1.length; i++) {
                    bytes1[i] = bytes.get(i);
                }

                out.write(bytes1);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
