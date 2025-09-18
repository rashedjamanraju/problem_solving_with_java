// Problem: Declamation Contest
import java.util.Scanner;
public class DeclamationContest32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt(), B = sc.nextInt(), C = sc.nextInt(), X = sc.nextInt();
        if (X == A || X == B || X == C) System.out.println("Yes");
        else System.out.println("No");
    }
}