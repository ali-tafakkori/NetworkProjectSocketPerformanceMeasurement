import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("Enter Message size: ");
            int messageSize = scanner.nextInt();

            try {
                long totalRTT = 0;
                for (int i = 0; i < 1000; i++) {

                    long start = System.currentTimeMillis();
                    Socket clientSocket = new Socket("localhost", 7000);

                    OutputStream out = clientSocket.getOutputStream();
                    InputStream in = clientSocket.getInputStream();

                    out.write(new byte[messageSize]);
                    out.flush();

                    in.read(new byte[messageSize]);

                    long end = System.currentTimeMillis();
                    long rtt = end - start;

                    totalRTT += rtt;

                    clientSocket.close();
                }
                long avgRTT = totalRTT / 1000;
                double throughput = (double) messageSize / avgRTT;
                System.out.println("Message Size: " + messageSize + " bytes, Average RTT: " + avgRTT + " ms, Throughput: " + throughput + " bytes/ms");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
