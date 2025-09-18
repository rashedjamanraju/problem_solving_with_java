// Problem: Monopoly
import java.util.Scanner;
public class Monopoly33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt(), Q = sc.nextInt(), R = sc.nextInt(), S = sc.nextInt();
        if (P > Q + R + S || Q > P + R + S || R > P + Q + S || S > P + Q + R)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}