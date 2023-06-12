
import java.math.BigInteger;
import java.util.Random;

class Assignment3BigInteger{
	//a function to return the sum from 0 to n-1
    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum =  new BigInteger("0");
      //Task 1.1
        //BigInteger BigIntegerOne = BigInteger.ONE;
        for(BigInteger i = new BigInteger("1") ; i.compareTo(n) < 0 ; i = i.add(new BigInteger("1"))) {
        	sum = sum.add(i);
        }
        return sum;
    }
    //a function to print n random integer numbers
    public static void printRandoms(int n){
        //Task 1.2
    	for(int i = 0 ; i < n ; i = i + 1) {
    		Random RandomNum = new Random();
        	int currInt = RandomNum.nextInt();
        	System.out.println(currInt);
    	}
    }
    //a function to checks if n is prime 
    public static   boolean isPrime(BigInteger n){
        boolean ans= true;
        //Task 1.3
        if(n.compareTo(new BigInteger("0")) == 0 | n.compareTo(new BigInteger("1")) == 0) {
        	ans = false;
        } 
        // this for starts from i=2 to i*i <= n
        for(BigInteger i = new BigInteger("2") ; n.compareTo(i.multiply(i)) >= 0 &ans ; i = i.add(new BigInteger("1"))) {
        	if(n.mod(i).compareTo(new BigInteger("0")) == 0)
        		ans = false;
        }
        return ans;
    }
    //a function to return a random prime BigInteger
    public static BigInteger randomPrime(int n){
        BigInteger randBig = new BigInteger("0");
        //Task 1.4
        Random rnd = new Random();
        randBig = new BigInteger(n, rnd);
        while(!isPrime(randBig)) {
        	rnd = new Random();
        	randBig = new BigInteger(n, rnd);
        }
        return randBig;
    }

}