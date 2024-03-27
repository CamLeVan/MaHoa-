package C3_socket.severJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class myProcess extends Thread {
    private Socket socket;

    public myProcess(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); // Sử dụng autoFlush để tự động xả lập dữ liệu
            String message;

            while ((message = reader.readLine()) != null) {
                // Hiển thị tin nhắn từ client
                System.out.println("Client: " + message);

                // Nhận tin nhắn từ server
                System.out.println("Server: ");
                BufferedReader serverReader = new BufferedReader(new InputStreamReader(System.in));
                String serverMessage = serverReader.readLine();

                // Gửi tin nhắn từ server tới client
                writer.println(serverMessage);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                socket.close(); // Đóng socket khi kết thúc giao tiếp
            } catch (IOException e) {
                System.err.println("Error while closing socket: " + e.getMessage());
            }
        }
    }
}
