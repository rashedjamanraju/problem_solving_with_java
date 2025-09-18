// Problem: Count Vowels and Consonants
import java.util.Scanner;
public class CountVowelConsonant18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int v = 0, c = 0;
        s = s.toLowerCase();
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                if ("aeiou".indexOf(ch) != -1) v++;
                else c++;
            }
        }
        System.out.println("Vowels: " + v + ", Consonants: " + c);
    }
}