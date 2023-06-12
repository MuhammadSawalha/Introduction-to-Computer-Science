public class BitVector {
    private Bit[] bits;
    // a constructor to BitVector
    public BitVector(String s) {
        //Task 4.4
    	// I used 3.1 to check if s is legal
    	if(!NumericalString.legalNumericString(s, 2)) {
    		throw new IllegalArgumentException("Illegal Input");
    	}
    	this.bits = new Bit[s.length()];
    	for(int i = 0 ; i < s.length() ; i = i + 1) {
    		int valueDigit = s.charAt(i) - '0';
    		if(valueDigit == 1) {
    			this.bits[i] = new Bit(true);
    		}else {
    			this.bits[i] = new Bit(false);
    		}
    	}
    }

    public String toString() {
        String ans = "";
        //Task 4.5
        String bitsAsString = "";
        // to convert bits to a string
        for(int i = 0 ; i < bits.length ; i = i + 1) {
        	bitsAsString = bitsAsString + bits[i].toString();
        }
        // here I used 3.4 to convert binary to decimal
        String temp = NumericalString.binary2Decimal(bitsAsString);
        // here I reverse temp to get most significant bit first
        for(int i=0;i<temp.length();i++) {
			ans =ans + temp.charAt(temp.length()-1-i);
		}
        return ans;
    }

}
