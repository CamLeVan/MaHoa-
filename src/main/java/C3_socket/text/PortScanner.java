package C3_socket.text;

import java.net.Socket;

public class PortScanner {
    public static void main(String[] args) {
    //checkPort("localhost");
        checkPort("www.vnexpress.net");
    }
    public static void checkPort(String urlString){
        int startPort=1;
        int endPort=100000;

        System.out.println("Dang Quet Port cua may "+urlString);

        for (int i = startPort; i <=endPort ; i++) {
            try{
                Socket socket = new Socket(urlString,i);
                System.out.println("Cong "+i+" Dang mo");
                socket.close();
            }catch (Exception exception){

            }
        }
        System.out.println("Quet cong hoan tat");
    }
}
