// Problem: Strict Average
import java.util.Scanner;
public class StrictAverage24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double A = sc.nextDouble(), B = sc.nextDouble(), C = sc.nextDouble();
        if ((A + B) / 2 > C) System.out.println("Yes");
        else System.out.println("No");
    }
}