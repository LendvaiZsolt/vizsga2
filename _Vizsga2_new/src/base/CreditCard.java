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
	public boolean karhaszdij(int dij) throws SajatException {
		boolean ok = false;
		int levonas = dij + getKartyadij();
		
		switch (this.getIssuebank()) {
		case OTP:
			if(this.getBalance()>levonas+10)
			setBalance(this.getBalance()-(levonas+10));
			ok = true;
			break;
		case ERSTE:
			if(this.getBalance()>levonas+20)
			setBalance(this.getBalance()-(levonas+20));
			ok = true;
			break;
		case KANDH:
			if(this.getBalance()>levonas+30)
			setBalance(this.getBalance()-(levonas+30));
			ok = true;
			break;
		case CIB:
			if(this.getBalance()>levonas+40)
			setBalance(this.getBalance()-(levonas+40));
			ok = true;
			break;
		default:
			if(this.getBalance()>levonas)
			setBalance(this.getBalance()-(levonas));
			ok = true;
			break;
		}
		return ok;
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
