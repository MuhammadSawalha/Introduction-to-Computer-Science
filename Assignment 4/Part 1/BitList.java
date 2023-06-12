/*
I, <Muhammad Sawalha> (<207525510>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================
    // a function to add Bit to the last of list
    public void addLast(Bit element) {
    	if(element == null) {
        	throw new IllegalArgumentException("Null is not allowed.");
        }
        if(element.equals(Bit.ONE)) {
        	numberOfOnes = numberOfOnes + 1;
        }
        super.addLast(element);
    }
 // a function to add Bit to the first of list
    public void addFirst(Bit element) {
        if(element == null) {
        	throw new IllegalArgumentException("Null is not allowed.");
        }
        if(element.equals(Bit.ONE)) {
        	numberOfOnes = numberOfOnes + 1;
        }
        super.addFirst(element);
    }
    // a function to remove the latest bit in list
    public Bit removeLast() {
    	if(super.size() == 0) {
    		throw new IllegalArgumentException("The Bit list is empty.");
    	}
    	Bit removedElement = super.removeLast();
        if(removedElement.equals(Bit.ONE)) {
        	numberOfOnes = numberOfOnes - 1;
        }
        return removedElement;
    }
    // a function to remove the first bit in list
    public Bit removeFirst() {
    	if(super.size() == 0) {
    		throw new IllegalArgumentException("The Bit list is empty.");
    	}
    	Bit removedElement = super.removeFirst();
        if(removedElement.equals(Bit.ONE)) {
        	numberOfOnes = numberOfOnes - 1;
        }    
        return removedElement;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    // a function to convert BitList to String
    public String toString() {
    	String output = "<";
    	Iterator<Bit> itr = super.iterator();
    	String binaryToString = "";
    	while(itr.hasNext()) {
    		String temp = itr.next().toString();
    		temp = temp + binaryToString;
    		binaryToString = temp;
    	}
    	output = output + binaryToString + ">";
    	return output;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    // a function to copy other to this BitList
    public BitList(BitList other) {
        if(other == null) {
        	throw new IllegalArgumentException("Null is not allowed.");
        }
        Iterator<Bit> itr = other.iterator();
        while(itr.hasNext()) {
        	Bit newBit = new Bit(itr.next().toInt());
        	this.addLast(newBit);
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    // a function to check if the number in BitList is legal
    public boolean isNumber() {
        boolean isNum = true;
        if(this.size() == 0) {
        	isNum = false;
        }
        if(isNum && this.getLast().toInt() == 1 & this.getNumberOfOnes() == 1) {
        	isNum = false;
        }
        return isNum;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    // to check if the number in BitList is reduced
    public boolean isReduced() {
    	boolean ifReduced = true;
    	if(!this.isNumber()) {
    		ifReduced = false;
    	}
    	if(super.size() == 2 && super.getFirst().toInt()==0 & super.getLast().toInt()==0) {
    		ifReduced = false;
    	}
    	if(ifReduced & super.size() > 2) {
    		// to take the latest 2 bits
    		Bit lastBit = super.getLast();
    		Bit prevLastBit = lastBit;
    		Iterator<Bit> itr = super.iterator();	
        	while(itr.hasNext()) {
        		Bit current = itr.next();
        		if(itr.hasNext()) {
        			prevLastBit = current;
        		}
        	}
        	if(lastBit.equals(prevLastBit) & lastBit.toInt() == 0) {
        		ifReduced = false;
        	}
        	if(lastBit.equals(prevLastBit) & lastBit.toInt() == 1 & numberOfOnes > 2) {
        		ifReduced = false;
        		
        	}
    	}
    	return ifReduced;
    }
    // a function to reduce the number in the BitList
    public void reduce() {
    	while(this.isNumber() && !this.isReduced())
        {
            this.removeLast();
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    // a function to calculate the complement
    public BitList complement() {
    	BitList complementList = new BitList();
    	Iterator<Bit> itr = super.iterator();
    	while(itr.hasNext())
    	{
    		complementList.addLast(itr.next().negate());
    	}
    	return complementList;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    // a function to divide the number by 2 by shifting right
    public Bit shiftRight() {
    	return this.removeFirst();
    }
    // a function to multiply by 2 by shifting left
    public void shiftLeft() {
    	this.addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    // a function for padding the number in the BitList by newLenght times
    public void padding(int newLength) {
    	if(size() == 0)
    	{
    		throw new IllegalArgumentException("cannot pad an empty Bit List.");
    	}
    	while(newLength > size())
    	{
    		this.addLast(this.getLast());
    	}
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
