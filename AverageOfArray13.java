// Problem: Average of Array
import java.util.Scanner;
public class AverageOfArray13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), sum = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for (int x : arr) sum += x;
        System.out.println((double)sum / n);
    }
}