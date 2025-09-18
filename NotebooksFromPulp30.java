// Problem: Notebooks from Pulp
import java.util.Scanner;
public class NotebooksFromPulp30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int pages = N * 1000;
        System.out.println(pages / 100);
    }
}