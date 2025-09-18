// Problem: Sum of Numbers from 1 to N
import java.util.Scanner;
public class SumToN09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = n * (n + 1) / 2;
        System.out.println(sum);
    }
}