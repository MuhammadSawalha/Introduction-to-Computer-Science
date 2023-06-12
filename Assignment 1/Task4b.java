import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;
        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int primes_counter = 0;
        for (int i = 2 ; i <= n ; i=i+1) {
        	boolean isPrime = true;
        	// to check if i is prime, so we can add 1 to primes_counter.
        	for (int j = 2 ; j < i ; j=j+1) {
        		if (i % j == 0) {
        			isPrime = false;
        		}
        	}
        	if (isPrime == true) primes_counter = primes_counter+1;
        }
        ans = primes_counter;
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);    
		scanner.close();    
    }
}