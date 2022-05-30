package running;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import base.Card;
import base.Card.Bank;
import base.CreditCard;

public class CardTest {

	public static void main(String[] args) {
		/*
		 * System.out.println("Adj meg egy számot!"); int size = intin();
		 * 
		 * Card[] bc = new Card[size];
		 * 
		 * for (int i = 0; i < bc.length; i++) { bc[i] = createCard(); }
		 * 
		 * for (Card card : bc) { System.out.println(card); }
		 */

		ArrayList<Card> list = createList();
		for (Card card : list) {
			System.out.println(card);
		}

		System.out.println("Az OTP kártyák száma: " + countCard(list, "OTP"));
		
		list.sort(Comparator.comparing(Card::getIssuebank).reversed());
		for (Card card : list) {
			System.out.println(card);
		}
	}

	private static int countCard(ArrayList<Card> list, String bankstr) {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIssuebank() == Bank.valueOf(bankstr.toUpperCase())) {
				count++;
			}
		}
		return count;
	}

	private static ArrayList<Card> createList() {

		ArrayList<Card> cardlist = new ArrayList<Card>();

		Card c = null;

		String line;
		try {
			FileReader filein = new FileReader("resource/data.txt");
			BufferedReader bufin = new BufferedReader(filein);
			while ((line = bufin.readLine()) != null) {

				String[] row = line.split(",");

				String name = row[0];

				String[] strdate = row[1].split("-");
				int year = Integer.parseInt(strdate[0]);
				int month = Integer.parseInt(strdate[1]);
				int day = Integer.parseInt(strdate[2]);
				LocalDate date = LocalDate.of(year, month, day);

				Bank bank = Bank.valueOf(row[2]);

				int balance = Integer.parseInt(row[3]);

				if (row.length == 4) {
					c = new Card(name, date, bank, balance);
				} else {
					int credit = Integer.parseInt(row[4]);
					c = new CreditCard(name, date, bank, balance, credit);
				}
				// System.out.println(c);
				cardlist.add(c);
			}

		} catch (Exception e) { // TODO: handle exception }

			return cardlist;
		}
		return cardlist;
	}

	private static Card createCard() {

		Card c = new Card(null, null, null, 0);
		InputStreamReader str = new InputStreamReader(System.in);
		BufferedReader buf = new BufferedReader(str);

		try {
			System.out.println("Add meg a kártyatulajdonos nevét: ");
			String name = buf.readLine();

			System.out.println("Add meg a kártya lejárati dátumát: ");
			String[] strdate = buf.readLine().split("-");
			int year = Integer.parseInt(strdate[0]);
			int month = Integer.parseInt(strdate[1]);
			int day = Integer.parseInt(strdate[2]);
			// LocalDate date = LocalDate.of(year, month, day);

			System.out.println("Add meg a bank nevét: ");
			String strbank = buf.readLine().toUpperCase();
			Bank bank = Bank.valueOf(strbank);

			System.out.println("Add meg a nyitó egyenleget: ");
			int balance = Integer.parseInt(buf.readLine());

			System.out.println("Add meg a hitelkeretet: ");
			int limit = Integer.parseInt(buf.readLine());

			if (limit <= 0) {
				c = new Card(name, LocalDate.of(year, month, day), bank, balance);

			} else {
				c = new CreditCard(name, LocalDate.of(year, month, day), bank, balance, limit);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;

	}

	public static int intin() {
		int i = 0;
		Scanner input = new Scanner(System.in);
		do {
			try {
				do {
					System.out.println("Adj meg egy számot 1 és 10 között!");
					i = input.nextInt();
				} while (i < 1 || i > 10);

			} catch (Exception e) {
				System.out.println("Ez nem szám.");
				input.nextLine();
			}
		} while (i < 1 || i > 10);
		return i;
	}

}
