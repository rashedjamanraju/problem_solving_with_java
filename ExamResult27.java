// Problem: Exam Result
import java.util.Scanner;
public class ExamResult27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt(), Y = sc.nextInt(), Z = sc.nextInt();
        int total = X * Y;
        if (Z > total / 2) System.out.println("Yes");
        else System.out.println("No");
    }
}