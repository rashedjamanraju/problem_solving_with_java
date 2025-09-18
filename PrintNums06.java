// Problem: Print Numbers from 1 to N
import java.util.Scanner;
public class PrintNums06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) System.out.print(i + " ");
    }
}