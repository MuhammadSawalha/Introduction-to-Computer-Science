
public class Bit {

    private boolean value;
    // a constructor to Bit
    public Bit(boolean value){
        //Task 4.1
    	this.value = value;
    }
    // to convert value to int
    public int toInt(){ 
        int ans = 0;
        //Task 4.2
        if(value) {
        	ans = 1;
        }
        return ans;

    }
    // to convert value to string
    public String toString(){
        String ans = "";
        //Task 4.3
        if(value) {
        	ans = "1";
        }else {
        	ans = "0";
        }
        return ans;
    }
}

