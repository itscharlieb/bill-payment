/*
 * Charles Bloomfield
 * 
 * This class holds the payments made by individual buyers, and the total amount they paid
 */

package bill_payment;

import java.util.ArrayList;

public class Buyer implements Comparable<Buyer>{
	String name;
	private double individualTotal = 0;
	ArrayList<Double> payments = new ArrayList<Double>();
	
	/*
	 * A new buyer will be constructed during execution of bill class for each person that makes a purhcase.
	 * For example, if the bill ArrayList<Payment> contains payments made by Michael, Nick, and Charlie, we will
	 * loop through the arraylist to assess how much each person spent. If the first payment is made by Michael,
	 * we construct a new buyer with name "Michael", and add the amount of that payment to the payments ArrayList<Double>
	 * in the Michael object. If the second payment is also made by Michael, then we add that payment to Michael's
	 * payments ArrayList. Then, for every payment that isn't made by Michael, we construct a new buyer and add to that
	 * buyer's payments in the same way we did for Michael. This allows us to generalize the code to account for an 
	 * arbitrary number of buyer's.
	 */
	public Buyer(String name, double payment){
		setName(name);
		setPaymentAmounts(payment);
	}
	
	@Override
	public int compareTo(Buyer b){
		if(getIndividualTotal() > b.getIndividualTotal())
			return 1;
		if(getIndividualTotal() == b.getIndividualTotal())
			return 0;
		return -1;
	}
	
	public ArrayList<Double> getPaymentAmounts() {
		return this.payments;
	}
	
	public void setPayments(double payment){
		this.payments.add(payment);
	}

	//this method also adds the payment amount to individualTotal so we don't have to calculate it later.
	public void setPaymentAmounts(double payment) {
		this.payments.add(payment);
		this.individualTotal += payment;
	}
	
	/* 
	 * This method also adds the payment amount to individualTotal so we don't have to calculate it later.
	 * It should also be noted that addPayment doesn't add a "Payment" object. It simply adds the amount of a payment.
	 */
	public void addPayment(double payment){
		this.payments.add(payment);
		this.individualTotal += payment;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getIndividualTotal() {
		return individualTotal;
	}
}
