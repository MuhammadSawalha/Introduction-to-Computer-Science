
class Change{
	//a function to return if n can be changed by the coins in the coins array
    public static boolean change(int [] coins, int n){
        boolean ans = false;
        //Task 2.1
        ans = change(coins,n,0);
        return ans;
    }
    // a helper function that has one more parameter i = index to go over the coin array
    public static boolean change(int [] coins, int n, int i) {
    	boolean ans = false;
    	if(n == 0) {
    		ans = true;
    	}else if(n > 0 & i < coins.length) {
    		// here I called change two times, the first is to decrease a coin from n without changing i so it can decrease the same coin 
    		// in the next time, the second is to take another coin without decreasing the previous one from n.
    		ans = (change(coins, n-coins[i], i) || change(coins, n, i+1));
    	}
    	return ans;
    }
    //a function to return if n can be changed by the coins in the coins array, with maximum numOfCoinsToUse coin
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){
        boolean ans = false;
        //Task 2.2
        ans = changeLimited(coins,n,numOfCoinsToUse,0);
        return ans;
    }
    // a helper function that has one more parameter i = index to go over the coin array
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse,int i) {
    	boolean ans = false;
    	if(n == 0 & numOfCoinsToUse >= 0) {
        	ans = true;
        }else if(n > 0 & i < coins.length & numOfCoinsToUse > 0){
        	// here I called changeLimited two times, the first is to decrease a coin from n without changing i so it can decrease the same coin 
    		// in the next time but we decrease numOfcoinsToUse with one, the second is to take another coin without decreasing the previous
        	//one from n.
        	ans = (changeLimited(coins,n-coins[i],numOfCoinsToUse-1,i) || changeLimited(coins,n,numOfCoinsToUse,i+1));
        }
    	return ans;
    }
    //a function to print one solution for if n can be changed by the coins in the coins array, with maximum numOfCoinsToUse coin
    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.3
    	System.out.println(printChangeLimited(coins,n,numOfCoinsToUse,0,""));
    }
    // a helper function that has two more parameters i = index to go over the coin array and toPrint a helper String
    public static String printChangeLimited(int[] coins, int n, int numOfCoinsToUse,int i,String toPrint) {
    	
    	if(n == 0 & numOfCoinsToUse >= 0) {
    		toPrint = toPrint.substring(0, toPrint.length()-1);
    		return toPrint;	
    	}else if(n > 0 & i < coins.length & numOfCoinsToUse > 0) {
    		String returnedToPrint;
    		// here I called printChangeLimited without changing n and numOfCoinsToUse but increasing i by 1 to go to the next kind of coins 
    		returnedToPrint = printChangeLimited(coins, n, numOfCoinsToUse, i+1 ,toPrint);
    		if(returnedToPrint != "") {
    			return returnedToPrint;
    		}
    		// here I called printChangeLimited with decreasing n with coin in index i so also decreasing numOfCoinsToUse with 1 but i still 
    		// the same to see if we can take another coin from the same kind
    		returnedToPrint = printChangeLimited(coins, n-coins[i], numOfCoinsToUse-1, i, toPrint+coins[i]+",");
    		if(returnedToPrint != "") {
    			return returnedToPrint;
    		}
    	}
    	return "";
    }
    //a function to find how much solutions for if n can be changed by the coins in the coins array, with maximum numOfCoinsToUse coin
    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        int ans = 0;
        //Task 2.4
        ans = countChangeLimited(coins,n,numOfCoinsToUse,0);
        return ans;
    }
    // a helper function that has one more parameter i = index to go over the coin array
    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse,int i) {
    	int counter = 0;
    	if(n == 0 & numOfCoinsToUse >= 0) {
        	counter = 1;
    	}else if(n > 0 & i < coins.length & numOfCoinsToUse > 0 ) {
    		// here I called countChangeLimited two times, the first is to decrease a coin from n without changing i so it can decrease 
    		// the same coin in the next time, the second is to take another coin without decreasing the previous one from n.
    		counter = counter + countChangeLimited(coins,n,numOfCoinsToUse,i + 1);
        	counter = counter + countChangeLimited(coins,n -coins[i],numOfCoinsToUse - 1,i);
    	}
    	return counter;
    }
    //a function to print all the solutions for if n can be changed by the coins in the coins array, with maximum numOfCoinsToUse coin
    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.5
    	printAllChangeLimited(coins,n,numOfCoinsToUse,0,"");
    }
    // a helper function that has two more parameters i = index to go over the coin array and toPrint a helper String
    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse, int i, String toPrint){
        //Task 2.5
    	if(n== 0 & numOfCoinsToUse >= 0) {
    		toPrint = toPrint.substring(0, toPrint.length()-1);
    		System.out.println(toPrint);
    	}else if(n > 0 & i < coins.length & numOfCoinsToUse > 0) {
    		// here I called printAllChangeLimited without changing n and numOfCoinsToUse but increasing i by 1 to go to the next kind of coins
    		printAllChangeLimited(coins,n,numOfCoinsToUse,i+1,toPrint);
    		// here I called printChangeLimited with decreasing n with coin in index i so also decreasing numOfCoinsToUse with 1 but i still 
    		// the same to see if we can take another coin from the same kind
    		printAllChangeLimited(coins,n-coins[i],numOfCoinsToUse-1,i,toPrint+coins[i]+",");
    	}
    }

}
