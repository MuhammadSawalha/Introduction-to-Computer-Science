import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;
        //---------------write your code BELOW this line only!--------------
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        // a is the Minimum in the range.
        // b is the Maximum in the range.
        // range = Maximum - Minimum + 1.
        int range = b - a + 1;
        int random_number = (int)(Math.random() * range) + a;
        ans = random_number;
        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans);
		scanner.close();
    }
}