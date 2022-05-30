package base;

import java.time.LocalDate;

public class Card implements Chargeable {

	public enum Bank {
		OTP, ERSTE, KANDH, CIB
	}

	private String owner;
	private LocalDate validto;
	private Bank issuebank;
	private int balance;
	private int kartyadij;

	public Card(String owner, LocalDate validto, Bank issuebank, int balance) {
		super();
		this.owner = owner;
		this.validto = validto;
		this.issuebank = issuebank;
		this.balance = balance;
		switch (this.issuebank) {
		case OTP:
			this.kartyadij = 1;
			break;
		case ERSTE:
			this.kartyadij = 2;
			break;
		case KANDH:
			this.kartyadij = 3;
			break;
		case CIB:
			this.kartyadij = 4;;
			break;
		default:
			this.kartyadij = 0;
			break;
		}
	}

	@Override
	public boolean karhaszdij(int dij) throws SajatException {
		boolean ok = false;
		int levonas = dij + kartyadij;

		if (balance >= levonas) {
			balance = balance - levonas;
			ok = true;
		} else {
			ok = false;
		}
		return ok;
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

	public int getKartyadij() {
		return kartyadij;
	}

	public void setKartyadij(int kartyadij) {
		this.kartyadij = kartyadij;
	}
}
