// Problem: Simple Calculator
import java.util.Scanner;
public class Calculator05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble(), b = sc.nextDouble();
        String op = sc.next();
        if (op.equals("+")) System.out.println(a + b);
        else if (op.equals("-")) System.out.println(a - b);
        else if (op.equals("*")) System.out.println(a * b);
        else if (op.equals("/")) System.out.println(a / b);
        else System.out.println("Invalid Operation");
    }
}