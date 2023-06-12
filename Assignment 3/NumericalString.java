public class NumericalString {
	// a helper function to convert char to int.
	public static int toInt(char c) {
		return "0123456789".indexOf(c);
		}
	// a function to check if s and b are legals
    public static boolean legalNumericString(String s, int b) {
        boolean ans = false;
        //Task 3.1
        ans = true;
        if(s == null | b < 2 | b > 10 ) {
        	throw new IllegalArgumentException("Illegal Input");
        }
        if(s.length() == 0) {
        	ans = false;
        }
        if(s.length() > 1 & ans && s.charAt((s.length()-1)) == '0') {
        	ans = false;
        }
        
        for(int i = 0 ; i < s.length() & ans ; i = i + 1) {
        	if(toInt(s.charAt(i)) < 0 | toInt(s.charAt(i)) >= b) {
        		ans = false;
        	}
        }
        return ans;
    }
    // a function to add 1 to the decimal in s
    public static String decimalIncrement(String s) {
        String ans = "";
        //Task 3.2
        if(!legalNumericString(s, 10)) {
        	throw new IllegalArgumentException("Illegal Input");
        }
        return decimalIncrement(s ,ans, 0, 1);
    }
    // a helper recursion function with two more parameters, i = index to go over s and carry that we want to add to the decimal number
    public static String decimalIncrement(String s , String ans, int i, int carry) {
    	String returnedAns;
    	if(i == s.length()) {
    		if(carry == 1) {
    			ans = ans + '1';
    		}
    		return ans;
    	}
    	int valueDigit = s.charAt(i);
    	valueDigit = valueDigit - '0';
    	valueDigit = valueDigit + carry;
    	if(valueDigit == 10) {
    		ans = ans + '0';
    		returnedAns = decimalIncrement(s ,ans,i+1,carry);
    		return returnedAns;
    	}else {
    		valueDigit = valueDigit + '0';
    		ans = ans + (char)valueDigit;
    		carry = 0;
    		returnedAns = decimalIncrement(s ,ans,i+1,carry);
    		return returnedAns;
    	} 	
    }
     // a function to multiply to the decimal number in s with 2
    public static String decimalDouble(String s) {
        String ans = "";
        //Task 3.3
        if(!legalNumericString(s, 10)) {
        	throw new IllegalArgumentException("Illegal Input");
        }
        
        return decimalDouble(s , ans, 0, 0);
    }
    // a helper recursion function with 3 more parameters, i = index to go over s and carry that we want to add to the decimal number in 
    // the next index , ans is a helper String
    public static String decimalDouble(String s , String ans, int i, int carry) {
    	if(i == s.length()) {
    		if(carry == 1) {
    			ans = ans + '1';
    		}
    		return ans;
    	}
    	String returnedAns;
    	int valueDigit = s.charAt(i);
    	valueDigit = valueDigit - '0';
    	// to multiply one digit with 2 and to add carry if there is a need to.
    	valueDigit = valueDigit * 2 + carry;
    	if(valueDigit >= 10) {
    		// to take the lift digit 
    		valueDigit = valueDigit % 10;
    		valueDigit = valueDigit + '0';
    		ans = ans + (char)valueDigit;
    		carry = 1;
    		returnedAns = decimalDouble(s ,ans,i+1,carry);
    		return returnedAns;
    	}else {
    		valueDigit = valueDigit + '0';
    		ans = ans + (char)valueDigit;
    		carry = 0;
    		returnedAns = decimalDouble(s ,ans,i+1,carry);
    		return returnedAns;
    	} 	
    }
    // a function to convert a binary number to decimal
    public static String binary2Decimal(String s) {
        String ans = "";
        //Task 3.4
        if(!legalNumericString(s, 2)) {
        	throw new IllegalArgumentException("Illegal Input");
        }
        ans = new String(s);
        return binary2Decimal(s ,ans);
    }
    // a helper recursion function with one more parameter, ans is a helper String
    // I used 3.2 and 3.3 here 
    public static String binary2Decimal(String s , String ans) {
    	String returnedAns;
    	if(ans.length() == 1) {
        	return ans;
        }
        if(ans.charAt(0) == '1') { 
        	// if s="111..." so its 1 + 2 + 2^2... its the same when 1 + 2(1 + 2(1 + 2(1 + 2(.......
        	returnedAns = decimalIncrement(decimalDouble(binary2Decimal(s ,ans.substring(1))));
        	return returnedAns;
        }
        if(ans.charAt(0) == '0') {
        	//same to the above but without increment.
        	returnedAns = decimalDouble(binary2Decimal(s ,ans.substring(1)));
        	return returnedAns;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Good Luck! :)");
    }
}
