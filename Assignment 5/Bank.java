public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}
	
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}
	
	// END OF Given code -----------------------------------
	
	// Complete the methods from here on

	public boolean add(BankAccount newAccount) {
		// task 6a
		throw new UnsupportedOperationException("Delete this line and implement this function");
	}

	public boolean delete(String name){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:

		// task 6b
		throw new UnsupportedOperationException("Delete this line and implement this function");
	}
	
	public boolean delete(int accountNumber){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:

		// task 6c
		throw new UnsupportedOperationException("Delete this line and implement this function");
	}

	public boolean depositMoney(int amount, int accountNumber){
		// task 6d
		throw new UnsupportedOperationException("Delete this line and implement this function");
	}

	public boolean withdrawMoney(int amount, int accountNumber){
		// task 6e
		throw new UnsupportedOperationException("Delete this line and implement this function");
	}	
	
	public boolean transferMoney(int amount, int accountNumber1, int accountNumber2) {
		// task 6f
		throw new UnsupportedOperationException("Delete this line and implement this function");
	}	
	public boolean transferMoney(int amount, int accountNumber, String name) {
		// task 6g
		throw new UnsupportedOperationException("Delete this line and implement this function");
	}	
}
