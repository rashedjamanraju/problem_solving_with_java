// Problem: Candy Packets
import java.util.Scanner;
public class CandyPackets31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), X = sc.nextInt();
        int need = N - X;
        int packets = need > 0 ? (need + 3) / 4 : 0;
        System.out.println(packets);
    }
}