import java.io.IOException;
import java.net.*; // Importerer netværksklasser

public class UDPServer {
    public static void main(String[] args) {
        // Opretter en DatagramSocket, der lytter på port 5000
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            byte[] buffer = new byte[512]; // Buffer til at modtage data

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                // Modtager en datagram-pakke
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message from client: " + received);

                // Sender en echo-respons tilbage til klienten
                String response = "Echo: " + received;
                byte[] responseBytes = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
