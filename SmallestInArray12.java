// Problem: Smallest Number in Array
import java.util.Scanner;
public class SmallestInArray12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), min = Integer.MAX_VALUE;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for (int x : arr) if (x < min) min = x;
        System.out.println(min);
    }
}