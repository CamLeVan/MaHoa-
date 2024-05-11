package iostream;

import java.io.File;
import java.util.Scanner;

public class deleteFile {
    public static void checkFile(String st){
        File f = new File(st);
        if (f.exists()) {
            f.delete();
            System.out.println("đã xóa file có đường dẫn là :"+f);
        } else {
            System.out.println("File not found!");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập đường dẫn file : ");
        String link = sc.nextLine();
        checkFile(link);
    }
}
