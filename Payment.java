/*
 * Charles Bloomfield
 * April 2014
 * Payment class describes attributes of a single payment.
 * There are two constructors. One is passed no arguments and scans data from setter methods. The second is passed data and sets the corresponding fields to the data passed in.
 */

package bill_payment;

import java.util.*;

public class Payment {
	private double amount;
	private String nameOfBuyer;
	private String purchaseLocation;

	public Payment(){
		setAmount();
		setNameOfBuyer();
		setPurchaseLocation();
	}

	public Payment(double d, String name, String location) {
		setAmount(d);
		setNameOfBuyer(name);
		setPurchaseLocation(location);
	}

	public void setAmount() {
		System.out.println("Enter the amount of the payment.\r");
		Scanner sc = new Scanner(System.in);
		this.amount = sc.nextDouble();
	}

	public void setAmount(double d) {
		this.amount = d;
	}

	public double getAmount() {
		return amount;
	}

	public void setNameOfBuyer() {
		System.out.println("Enter the name of the person who bought this item.\r");
		Scanner sc = new Scanner(System.in);
		this.nameOfBuyer = sc.next();
	}

	public void setNameOfBuyer(String s) {
		this.nameOfBuyer = s;
	}

	public String getNameOfBuyer() {
		return nameOfBuyer;
	}

	public void setPurchaseLocation() {
		System.out.println("Enter the location of the payment.\r");
		Scanner sc = new Scanner(System.in);
		this.purchaseLocation = sc.next();
	}
	
	public void setPurchaseLocation(String s) {
		this.purchaseLocation = s;
	}

	public String getPurchaseLocation() {
		return purchaseLocation;
	}
}
