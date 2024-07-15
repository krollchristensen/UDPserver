import java.io.IOException;
import java.net.*; // Importerer netværksklasser

public class UDPClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Definerer serverens hostname
        int port = 5000; // Definerer serverens portnummer

        // Opretter en DatagramSocket til at sende og modtage data
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = "Hello Server".getBytes(); // Konverterer beskeden til bytes
            InetAddress address = InetAddress.getByName(hostname); // Henter serverens adresse
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet); // Sender datagram-pakken til serveren

            byte[] responseBuffer = new byte[512]; // Buffer til at modtage respons
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket); // Modtager responsen fra serveren

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server response: " + response);
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
