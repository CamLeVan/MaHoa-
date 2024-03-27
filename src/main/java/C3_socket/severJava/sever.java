package C3_socket.severJava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class sever {
    public static void main(String[] args) {
        try{
            //tạo sever socket và lắng nghe port truyền vào
            int port=999;
            ServerSocket seversocket = new ServerSocket(port);

            //chấp nhận kết nối từ nhiều cổng Client
            while (true) {
                Socket clineSocket = seversocket.accept();
                myProcess mp = new myProcess(clineSocket);
                mp.start();
            }
            //Bắt đầu trao đổi thông tin

//            //Ngắt kết nối
//            clineSocket.close();
//            seversocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
