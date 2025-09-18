// Problem: Make Average
import java.util.Scanner;
public class MakeAvg20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt(), C = sc.nextInt();
        int sum = A + C;
        if (sum % 2 == 0) System.out.println(sum / 2);
        else System.out.println("No integer B exists");
    }
}