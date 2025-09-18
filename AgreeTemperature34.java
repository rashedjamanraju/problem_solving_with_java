// Problem: Agree on Temperature
import java.util.Scanner;
public class AgreeTemperature34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt(), B = sc.nextInt(), C = sc.nextInt();
        if (Math.max(A, C) <= B) System.out.println("Yes");
        else System.out.println("No");
    }
}