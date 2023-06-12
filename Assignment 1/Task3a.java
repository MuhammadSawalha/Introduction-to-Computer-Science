import java.util.Scanner;

public class Task3a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;
        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        // base is the strong of the pow.
        int base = 2;
        // ans_of_pow is the current result of pow of 2.
        int ans_of_pow = 1;
        
        if (n == 0) {
        	ans = 1;
        }
        // a loop to multiple ans_of_pow by base.
        for (int i = 1 ; i <= n ; i = i + 1) {
        	ans_of_pow = ans_of_pow * base;
        	ans = ans_of_pow;
        }
        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans);
		scanner.close();
    }
}