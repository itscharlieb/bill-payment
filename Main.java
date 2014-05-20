/*
 * Charles Bloomfield
 * April 2014
 */

package bill_payment;

public class Main {
	public static void main(String[] args){
		Bill testBill = new Bill();
		testBill.print();
		testBill.getMoneyOwed();
		testBill.print();
	}
}
