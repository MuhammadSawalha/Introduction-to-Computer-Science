/*
I, <Muhammad Sawalha> (<207525510>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE  = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not chainge this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not chainge this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    // a constructor for BinaryNumber
    public BinaryNumber(char c) {
    	// to check if c is between 0 and 9
    	if(c - '0' < 0 | c - '0' > 9) {
    		throw new IllegalArgumentException("Illegal input.");
    	}
    	bits = new BitList();
    	int num = c - '0';
    	// to convert from decimal to binary
    	while(num != 0) {
    		if(num % 2 == 1) {
    			bits.addLast(Bit.ONE);
    		}else {
    			bits.addLast(Bit.ZERO);
    		}
    		num = num / 2;
    	}
    	bits.addLast(Bit.ZERO);
    }

  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    // a function to convert the binary number to string
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        
        String output = "";
    	Iterator<Bit> itr = this.bits.iterator();
    	while(itr.hasNext()) {
    		String temp = itr.next().toString();
    		temp = temp + output;
    		output = temp;
    	}
    	return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    // a function to check if 2 BinaryNumbers are equal
    public boolean equals(Object other) {
    	if(other == null) {
    		return false;
    	}
    	// to check if other is BinaryNumber
    	if (! (other instanceof BinaryNumber)) {
    		return false;
    	}
    	// to check if the BinaryNumbers have the same length
    	if(this.length() != ((BinaryNumber)other).length()) {
    		return false;
    	}
    	// to check if the string for 2 BinaryNumbers are equal
    	if(!this.toString().equals(((BinaryNumber)other).toString()) ) {
    		return false;
    	}
    	return true;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    // a function to add Binary number to other
    public BinaryNumber add(BinaryNumber addMe) {
    	if(!addMe.isLegal()) {
    		throw new IllegalArgumentException("Illegal number.");
    	}
    	boolean addCarry = true;
    	if(!this.bits.getLast().equals(addMe.bits.getLast())) {
    		addCarry = false;
    	}
    	BinaryNumber copyThis = new BinaryNumber(this);
    	BinaryNumber copyAddMe = new BinaryNumber(addMe);
    	// padding for the both to make them in the same length
    	if(this.length() >= copyAddMe.length()) {
    		copyThis.bits.padding(this.length() + 1);
    		copyAddMe.bits.padding(copyThis.length());
    	}
    	if(this.length() < copyAddMe.length()) {
    		copyAddMe.bits.padding(copyAddMe.length() + 1);
    		copyThis.bits.padding(copyAddMe.length());
    	}
    	BinaryNumber sumList = new BinaryNumber(0);
    	sumList.bits.clear();
    	Iterator<Bit> itr1 = copyThis.bits.iterator();
    	Iterator<Bit> itr2 = copyAddMe.bits.iterator();
    	Bit carry = new Bit(0);
    	// to use the functions fullAdderSum and fullAdderCarry for every 2 digits for each number
    	while(itr1.hasNext()) {
    		Bit digit1 = itr1.next();
    		Bit digit2 = itr2.next();
    		sumList.bits.addLast(Bit.fullAdderSum(digit1,digit2,carry));
    		carry = Bit.fullAdderCarry(digit1, digit2, carry);
    	}
    	if(!addCarry) {
    		sumList.bits.removeLast();
    	}
    	sumList.bits.reduce();
    	return sumList;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    // a function to calculate the complement of the binary number then to add 1
    public BinaryNumber negate() {
    	if(this.compareTo(new BinaryNumber(0)) == 0) {
    		return new BinaryNumber(0);
    	}
    	BinaryNumber negateList = new BinaryNumber(1);
    	BinaryNumber helperList = new BinaryNumber(0);
    	helperList.bits.clear();
    	BinaryNumber copyThis = new BinaryNumber(this);
    	Iterator<Bit> itr = copyThis.bits.iterator();
    	while(itr.hasNext()) {
    		helperList.bits.addLast(itr.next().negate());
    	}
    	negateList = helperList.add(negateList);
    	return negateList;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    // a function to substract a Binary number from other by using negate and add functions
    public BinaryNumber subtract(BinaryNumber subtractMe) {
    	if(!subtractMe.isLegal()) {
    		throw new IllegalArgumentException("Illegal number.");
    	}
    	if(subtractMe.bits.getLast().toInt() == 0 & subtractMe.bits.size() == 1) {
    		return new BinaryNumber(this);
    	}
    	BinaryNumber subtractList = this.add(subtractMe.negate());
    	return subtractList;
    	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    // a function to check if the binary number is positive or negative or 0
    public int signum() {
    	if(this.bits.size() == 0) {
    		throw new IllegalArgumentException("Empty list.");
    	}
    	if(this.bits.getLast().toInt() == 0 & this.bits.size() == 1) {
    		return 0;
    	}else if(this.bits.getLast().toInt() == 0) {
    		return 1;
    	}
    	return -1;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    // a function to compare 2 binary numbers by using substract function
    public int compareTo(BinaryNumber other) {
    	if(!other.isLegal()) {
    		throw new IllegalArgumentException("Illegal number.");
    	}
    	int ansCompareTo = 0;
    	if(this.subtract(other).signum() == 0) {
    		return ansCompareTo;
    	}
    	if(this.subtract(other).signum() == 1) {
    		ansCompareTo = 1;
    	}
    	if(this.subtract(other).signum() == -1) {
    		ansCompareTo = -1;
    	}
    	return ansCompareTo;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    // a function to check if we can convert the binary number to integer 
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        BinaryNumber sumList = this;
        boolean minus = false;
        if(this.bits.getLast().toInt() == 1) {
        	sumList = this.negate();
        	minus = true;
        }
        Iterator<Bit> itr = sumList.bits.iterator();
        int pow = 1;
        int sum = 0;
    	while(itr.hasNext()) {
    		int toAdd = itr.next().toInt() * pow;
    		// to check if the number is not minus and its not over max of integer
    		if(!minus & Integer.MAX_VALUE - sum < toAdd) {
    			throw new RuntimeException("Out of range.");
    		}
    		// to check if the number is minus and its not over max of integer +1 because its min of integer
    		if(minus & sum > 0 && Integer.MAX_VALUE + 1 - sum < toAdd) {
    			throw new RuntimeException("Out of range.");
    		}
    		sum = sum + toAdd;
    		pow = pow * 2;
    	}
    	if(minus) {
    		return -sum;
    	}
    	return sum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // a function to multiply 2 binary numbers
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
    	if(!multiplyMe.isLegal()) {
    		throw new IllegalArgumentException("Illegal number.");
    	}
    	BinaryNumber result = multiplyPositive(multiplyMe);
    	if(!this.bits.getLast().equals(multiplyMe.bits.getLast())) {
    		return result.negate();
    	}
    	return result;
    }
    
    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
    	BinaryNumber positiveThis = new BinaryNumber(this);
    	BinaryNumber positiveMultiplyMe = new BinaryNumber(multiplyMe);
    	if(positiveThis.bits.getLast().toInt() == 1) {
    		positiveThis = positiveThis.negate();
    	}
    	if(positiveMultiplyMe.bits.getLast().toInt() == 1) {
    		positiveMultiplyMe = positiveMultiplyMe.negate();
    	}
    	BinaryNumber toMultiply;
    	BinaryNumber toDivide;
    	// to check who is bigger
    	if(positiveThis.compareTo(positiveMultiplyMe) >= 0) {
    		toMultiply = positiveThis;
    		toDivide = positiveMultiplyMe;
    	}else {
    		toMultiply = positiveMultiplyMe;
    		toDivide = positiveThis;
    	}
    	if(toDivide.compareTo(new BinaryNumber(0)) == 0) {
    		return toDivide;
    	}
    	
    	if(toDivide.compareTo(new BinaryNumber(1)) == 0) {
    		return toMultiply;
    	}
    	BinaryNumber result = multiplyPositiveRecursion(toMultiply, toDivide );
    	return result;
    }
    // a helper recursion function
    private BinaryNumber multiplyPositiveRecursion(BinaryNumber toMultiply, BinaryNumber toDivide ) {
    	BinaryNumber result = toMultiply;
    	
    	if(toDivide.compareTo(new BinaryNumber('2')) >= 0 & toDivide.bits.getFirst().toInt() == 0){
    		result = multiplyPositiveRecursion(toMultiply.multiplyBy2(), toDivide.divideBy2() );
    		return result;
    	}
    	if(toDivide.compareTo(new BinaryNumber('2')) >= 0 & toDivide.bits.getFirst().toInt() == 1){
    		BinaryNumber toAdd = toMultiply;
    		result = multiplyPositiveRecursion(toMultiply.multiplyBy2(), toDivide.divideBy2() );
    		result = result.add(toAdd);
    		return result;
    	}
    	return result;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // a function to divide a binary numbers
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	//
    	if(!divisor.isLegal()) {
    		throw new IllegalArgumentException("Illegal number.");
    	}
    	if(this.compareTo(divisor) == 0) {
    		return ONE;
    	}
    	BinaryNumber copyDivisor = new BinaryNumber(divisor);
    	if(copyDivisor.bits.getLast().toInt() == 1) {
    		copyDivisor = copyDivisor.negate();
    	}
    	BinaryNumber result = dividePositive(copyDivisor);
    	
    	if(!this.bits.getLast().equals(divisor.bits.getLast())) {
    		return result.negate();
    	}
    	return result;
    }

    private BinaryNumber dividePositive(BinaryNumber divisor) {
    	BinaryNumber copyThis = new BinaryNumber(this);
    	if(copyThis.bits.getLast().toInt() == 1) {
    		copyThis = copyThis.negate();
    	}
    	if(copyThis.compareTo(divisor) < 0) {
    		return ZERO;
    	}
    	BinaryNumber copyToDivide = new BinaryNumber(copyThis);
    	BinaryNumber result = dividePositiveRecursion(copyToDivide, copyThis, divisor, ONE);
    	return result;
    	
    }
    private BinaryNumber dividePositiveRecursion(BinaryNumber toDivide, BinaryNumber currDivide, BinaryNumber divisor, BinaryNumber numOfBranshes) {
    	BinaryNumber result = new BinaryNumber(numOfBranshes);
    	BinaryNumber sumRest = new BinaryNumber(0);
    	
    	if(currDivide.compareTo(divisor.multiplyBy2()) < 0 | currDivide.compareTo(divisor) == 0) {
    		sumRest = currDivide.subtract(divisor);
    		sumRest = sumRest.multiply(numOfBranshes);
    		sumRest = sumRest.add(toDivide.subtract(currDivide.multiply(numOfBranshes)));
    		if(divisor.compareTo(sumRest) > 0) {
    			return result;
    		}else {
    			BinaryNumber restCurrDivide = new BinaryNumber(sumRest);
    			BinaryNumber rest = new BinaryNumber(dividePositiveRecursion(sumRest, restCurrDivide, divisor, ONE));
    			result = result.add(rest);
    			return result; 
    		}
    	}
    	if(currDivide.compareTo(divisor.multiplyBy2()) >= 0) {
    		result = dividePositiveRecursion(toDivide, currDivide.divideBy2(), divisor, numOfBranshes.multiplyBy2() );
    		return result;
    	}
    	
    	return result;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    // a function to convert decimal number to binary
    public BinaryNumber(String s) {
    	boolean positiveNumber = true;
    	if(s.substring(0, 1).equals("-")) {
    		positiveNumber = false;
    	}
    	BinaryNumber pow = new BinaryNumber(1);
    	String copyS = new String(s);
    	this.bits = new BitList();
    	BinaryNumber newList = new BinaryNumber(0);
    	BinaryNumber tempList = new BinaryNumber(0);
    	tempList.bits.clear();
    	// to convert every 9 digits in string to binary number
    	while(copyS.length() - 1 > 9) { 		
    		int partOfS = Integer.parseInt(copyS.substring(copyS.length()-9));
    		while(partOfS != 0) {
        		if(partOfS % 2 == 1) {
        			tempList.bits.addLast(Bit.ONE);
        		}else {
        			tempList.bits.addLast(Bit.ZERO);
        		}
        		partOfS = partOfS / 2;
        	}
    		tempList.bits.addLast(Bit.ZERO);
    		tempList = tempList.multiply(pow);
    		newList = newList.add(tempList);
    		this.bits = newList.bits;
    		tempList.bits.clear();
    		copyS = copyS.substring(0,copyS.length()-9);
    		int i = 9;
    		while(i > 0) {
    			pow = pow.multiply(new BinaryNumber('9').add(ONE));
    			i = i - 1;
    		}
    	}
    	if(copyS.length() == 0 || copyS.substring(0).equals("-")) {
    		if(!positiveNumber) {
    			newList = newList.negate();
    		}
    		this.bits = newList.bits;
    		return;
    	}
    	// to convert the last some digits that their number is less than 9 to binary
    	int lastPartOfS = Integer.parseInt(copyS.substring(0));
    	lastPartOfS = Math.abs(lastPartOfS);
    	while(lastPartOfS != 0) {
    		if(lastPartOfS % 2 == 1) {
    			tempList.bits.addLast(Bit.ONE);
    		}else {
    			tempList.bits.addLast(Bit.ZERO);
    		}
    		lastPartOfS = lastPartOfS / 2;
    	}
    	tempList.bits.addLast(Bit.ZERO);
    	tempList = tempList.multiply(pow);
    	newList = newList.add(tempList);
    	if(!positiveNumber) {
			newList = newList.negate();
		}
		this.bits = newList.bits;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    // to convert binary to decimal
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        BinaryNumber copyThis = new BinaryNumber(this);
        boolean positiveNumber = true;
        if(this.bits.getLast().toInt() == 1) {
        	copyThis = copyThis.negate();
        	positiveNumber = false;
        }
        String output = "";
        while(copyThis.compareTo(ZERO) > 0) {
        	// every time I divided by 10 then to multiply the result by 10 so when I substract the result I get the first decimal 
        	// digit from the right
        	BinaryNumber result = copyThis.divide(new BinaryNumber('9').add(new BinaryNumber(1)));
        	result = result.multiply(new BinaryNumber('9').add(new BinaryNumber(1)));
            result = copyThis.subtract(result);
            int pow = 1;
            int number = 0;
            Iterator<Bit> itr = result.bits.iterator();
            while(itr.hasNext()) {
            	number = number + itr.next().toInt() * pow;
            	pow = pow * 2;
            }
            output = String.valueOf(number) + output;
            // to divide by 10
            copyThis = copyThis.divide(new BinaryNumber('9').add(new BinaryNumber(1)));
        }
        if(!positiveNumber) {
        	output = "-" + output;
        }
        return output;
    }


    // Returns this * 2
    public BinaryNumber multiplyBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divideBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
}
