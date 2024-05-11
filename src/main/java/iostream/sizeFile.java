package iostream;

import java.io.File;
import java.util.Scanner;

public class sizeFile {
    public static void checkFile(String st){
        File f = new File(st);
        if (f.exists()) {
            System.out.println("Size file :"+f.length());
        } else {
            System.out.println("File not found!");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập đường dẫn file :");
        String link=sc.nextLine();
        checkFile(link);
    }
}
