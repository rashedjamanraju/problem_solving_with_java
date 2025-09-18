// Problem: Search in Array
import java.util.Scanner;
public class SearchInArray15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int key = sc.nextInt();
        boolean found = false;
        for (int x : arr) if (x == key) found = true;
        System.out.println(found ? "Found" : "Not Found");
    }
}