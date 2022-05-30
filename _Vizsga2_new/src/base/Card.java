package base;

import java.time.LocalDate;

public class Card {

	public enum Bank {
		OTP, ERSTE, KANDH, CIB

	}

	private String owner;
	private LocalDate validto;
	private Bank issuebank;
	private int balance;

	public Card(String owner, LocalDate validto, Bank issuebank, int balance) {
		super();
		this.owner = owner;
		this.validto = validto;
		this.issuebank = issuebank;
		this.balance = balance;
	}

	public void setBalance(int amount) {
		if (amount >= 0) {
			balance = amount;
		}
	}

	public boolean withdraw(int amount) {
		if (balance > amount || validto.isBefore(LocalDate.now())) {
			balance = balance - amount;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Card [owner=" + owner + ", validto=" + validto + ", issuebank=" + issuebank + ", balance=" + balance
				+ "]";
	}

	public String getOwner() {
		return owner;
	}

	public LocalDate getValidto() {
		return validto;
	}

	public Bank getIssuebank() {
		return issuebank;
	}

	public int getBalance() {
		return balance;
	}

}
