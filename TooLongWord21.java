// Problem: Too Long Word
import java.util.Scanner;
public class TooLongWord21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        if (word.length() > 10) {
            System.out.println(word.charAt(0) + "" + (word.length() - 2) + word.charAt(word.length() - 1));
        } else {
            System.out.println(word);
        }
    }
}