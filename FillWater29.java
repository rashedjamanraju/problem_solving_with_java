// Problem: Fill Water Bottles
import java.util.Scanner;
public class FillWater29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int B1 = sc.nextInt(), B2 = sc.nextInt(), B3 = sc.nextInt();
        int empty = (B1 == 0 ? 1 : 0) + (B2 == 0 ? 1 : 0) + (B3 == 0 ? 1 : 0);
        if (empty >= 2) System.out.println("Water filling time");
        else System.out.println("Not now");
    }
}