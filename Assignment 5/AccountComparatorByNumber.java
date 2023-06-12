import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		if(account1.getAccountNumber() > account2.getAccountNumber()) {
			return 1;
		}
		if(account1.getAccountNumber() < account2.getAccountNumber()) {
			return -1;
		}
		return 0;
	}

}
