import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true;
        int ans2 = 0;
        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int b = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();
        int current_mod = 1;
        int base = 2;
        boolean condition = true;
   
        // exp = 2^i.
        int exp = 1;
        current_mod = 0;

        for (int i = 0 ; i < s & condition ; i=i+1) {
        	// to calculate 2^i
        	for (int j = 0 ; j < i ; j=j+1) {
        		exp = exp * base;
        	}
        	// to check if b^d % n == n-1 when d*exp = 1.
        	if(d * exp == 1 & (b % n) == (n-1)) {
        		condition = false;
        	}
        	// to check if b^d % n == 1 when d*exp = 1.
        	if(d * exp == 1 & (b % n) == 1){
        		condition = false;
        	}
        	for (int m = 2 ; m <= (exp*d) ; m=m+1) {
        		if (m == 2) {
        			current_mod = (b * b) % n;
        			
        		}else {
        			current_mod = (current_mod * b) % n;
        		}
        	}
        	// to check if b^d % n == 1.
        	if(i == 0 & current_mod == 1) {
        		condition = false;
        	}
        	// to check if b^(exp*d) % n == n-1
        	if (current_mod == (n - 1)) {
            	condition = false;
            }
        	exp = 1;
        	current_mod = 0;
        }
        
        if(condition == true) {
        	ans1 = false;
        	ans2 = b;
        }else {
        	ans2 = -1;
        }
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
		scanner.close();
    }
}