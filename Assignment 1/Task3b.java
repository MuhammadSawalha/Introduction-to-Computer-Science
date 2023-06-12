import java.util.Scanner;

public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;
        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int base = 2;
        int current_mod = 1;
        ans = 1;
        if (n == 0) {
        	// 2^0 = 1.
        	ans = 1 % k;
        }
        if (n == 1) {
        	// 2^1 = base = 2.
        	ans = base % k;
        }
        for (int i = 2 ; i <= n ; i=i+1) {
        	if (i == 2) {
        		current_mod = (base * base) % k;
        	}else {
        		current_mod = (current_mod * base) % k;
        	}
        	ans = current_mod;
        }
        
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}