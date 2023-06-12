import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;
        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int base = 2;
        int s = 1;
        int d = 1;
        int ans_of_pow = base;
        boolean isOdd = false;
        int even = n - 1;
        while (ans_of_pow <= even & !isOdd ) {
        	if (even % ans_of_pow == 0) {
        		d = (n-1) / ans_of_pow;
        	}
        	// to check if d is odd.
        	if(d % 2 == 1) {
        		ans1 = s;
        		ans2 = d;
        		isOdd = true;
        	}
        	ans_of_pow = ans_of_pow * base;
        	s++;
        }
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
		scanner.close();
    }
}