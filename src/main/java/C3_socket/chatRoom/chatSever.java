package C3_socket.chatRoom;

import org.w3c.dom.ls.LSOutput;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import  java.util.List;
public class chatSever {
    private static final  int port =5000;
    private List<ClinetHandler> clinets = new ArrayList<>();

    private void startSever(){
        try{
            //wedsocket
            ServerSocket severSocket = new ServerSocket(port);
            System.out.println("Sever started. Listening on port: "+port);

            //clinets connect to sever
            while (true){
                Socket clientSocket = severSocket.accept();
                System.out.println("New client connected :"+clientSocket.getInetAddress());//lấy địa chỉ ip

                ClinetHandler clinetHandler = new ClinetHandler(clientSocket, System.currentTimeMillis()+"");
            }
        }catch (Exception e){

        }
    }
}
