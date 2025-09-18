// Problem: Eligible Voters
import java.util.Scanner;
public class EligibleVoters35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), X = sc.nextInt(), count = 0;
        for (int i = 0; i < N; i++) {
            int age = sc.nextInt();
            if (age >= X) count++;
        }
        System.out.println(count);
    }
}