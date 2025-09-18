// Problem: Find the Odd One
import java.util.Scanner;
public class FindOddOne23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] digits={0,0,0,0,0,0,0,0,0,0};
        int x,y,z;
        x=sc.nextInt();
        y=sc.nextInt();
        z=sc.nextInt();
        digits[x]++;
        digits[y]++;
        digits[z]++;
        for(int i=0;i<=9;i++){
            if (digits[i]==1) {
                System.out.println(i);
                break;
            }
        }
    }
}