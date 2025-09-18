// Problem: IIUC-TV Subscription Cost
import java.util.Scanner;
public class IIUCTV25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), X = sc.nextInt();
        int subs = (N + 5) / 6;
        if(N%6==0){
            System.out.println((N/6)*X);
        }
        else{
            System.out.println(((N/6)+1)*X);
        }
    }
}