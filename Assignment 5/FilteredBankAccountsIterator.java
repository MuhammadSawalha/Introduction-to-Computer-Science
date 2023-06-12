import java.util.Iterator;
import java.util.NoSuchElementException;


public class FilteredBankAccountsIterator implements Iterator<BankAccount> {

	private BinaryTreeInOrderIterator<BankAccount> iterator;
	private Filter<BankAccount> filter;
    private BankAccount current;
    
    public FilteredBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, Filter<BankAccount> filter) {
        // task 5c
        throw new UnsupportedOperationException("Delete this line and implement this function");
    }

    //Complete the following method
    @Override
    public boolean hasNext() {
        // task 5c
        throw new UnsupportedOperationException("Delete this line and implement this function");
    }

    //Complete the following method
    @Override
    public BankAccount next() {
        // task 5c
        throw new UnsupportedOperationException("Delete this line and implement this function");
    }
}
