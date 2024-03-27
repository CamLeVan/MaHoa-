package C3_socket.myClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối tới server thông qua port
            int port = 999;
            Socket socket = new Socket("localhost", port);
            System.out.println("Đã kết nối thành công đến server");

            // Tạo luồng đọc và ghi dữ liệu
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Scanner để đọc dữ liệu từ bàn phím
            Scanner scanner = new Scanner(System.in);

            String message;
            while (true) {
                // Gửi tin nhắn tới server
                System.out.print("Client: ");
                message = scanner.nextLine();
                writer.println(message);

                // Đọc tin nhắn từ server
                String response = reader.readLine();
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            System.out.println("Không thể kết nối đến server: " + e.getMessage());
        }
    }
}
