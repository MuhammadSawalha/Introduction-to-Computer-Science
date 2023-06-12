import java.util.Scanner;

public class Task4c {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;
        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int min = 2;
        int max = n - 1;
        int range = max - min + 1;
        int random_number = (int)(Math.random() * range) + min;
        ans = random_number;
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}