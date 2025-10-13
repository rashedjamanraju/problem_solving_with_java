// Problem: Project Euler #34 - Digit Factorials
import java.util.Scanner;
public class DigitFactorials36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        // Precompute factorials for digits 0-9
        long[] factorial = new long[10];
        factorial[0] = 1;
        for (int i = 1; i < 10; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        
        long sum = 0;
        // Start from 10 because single-digit numbers are not considered sums
        for (int x = 10; x < N; x++) {
            long digitFactorialSum = 0;
            int temp = x;
            
            // Calculate sum of factorials of digits
            while (temp > 0) {
                int digit = temp % 10;
                digitFactorialSum += factorial[digit];
                temp /= 10;
            }
            
            // Check if x divides the sum of factorials of its digits
            if (digitFactorialSum % x == 0) {
                sum += x;
            }
        }
        
        System.out.println(sum);
    }
}
