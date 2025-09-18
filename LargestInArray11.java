// Problem: Largest Number in Array
import java.util.Scanner;
public class LargestInArray11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), max = Integer.MIN_VALUE;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for (int x : arr) if (x > max) max = x;
        System.out.println(max);
    }
}