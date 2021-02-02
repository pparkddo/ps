import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        in.close();
        System.out.println(Math.PI * Math.pow(r, 2));
        System.out.println(2*Math.pow(r, 2));
    }
}