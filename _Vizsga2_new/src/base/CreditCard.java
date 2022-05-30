package base;

import java.time.LocalDate;

public class CreditCard extends Card {

	private int creditlimit;

	public CreditCard(String owner, LocalDate validto, Bank issuebank, int balance) {
		super(owner, validto, issuebank, balance);
		this.creditlimit = 100000;
		// TODO Auto-generated constructor stub
	}

	public CreditCard(String owner, LocalDate validto, Bank issuebank, int balance, int creditlimit) {
		super(owner, validto, issuebank, balance);
		this.creditlimit = creditlimit;
	}

	@Override
	public boolean withdraw(int amount) {
		if (getBalance() + creditlimit > amount || getValidto().isBefore(LocalDate.now())) {
			if (getBalance() >= amount) {
				setBalance(getBalance() - amount);
				return true;
			} else if (getBalance() < amount) {
				creditlimit = creditlimit - (amount - getBalance());
				setBalance(0);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CreditCard [" + ", getOwner()=" + getOwner() + ", getValidto()=" + getValidto() + ", getIssuebank()="
				+ getIssuebank() + ", getBalance()=" + getBalance() + "creditlimit=" + creditlimit + "]";
	}

	public int getCreditlimit() {
		return creditlimit;
	}

	public void setCreditlimit(int creditlimit) {
		this.creditlimit = creditlimit;
	}

}
