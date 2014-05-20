/*
 * Charles Bloomfield
 * 
 * Bill contains a list group of Payments and defines some of it's own properties.
 */

package bill_payment;

import java.util.*;
import java.io.PrintWriter;

public class Bill {
	private String month;
	private ArrayList<Payment> allPayments = new ArrayList<Payment>();
	private ArrayList<Buyer> allBuyers = new ArrayList<Buyer>();
	private double totalAmount;
	private static int numberOfPersons = 0; //number of people who took part in the bill
	private char c; //will hold y/n. To be used in allPayments to decide whether or not to add more payments.

	public Bill(){
		setMonth();
		setAllPayments();
		setAllBuyers();
		sortAllBuyers(); //automatically sort the list of buyers
		setTotalAmount();
	}
	
	public void print(){
		for(Buyer b : getAllBuyers()){
			System.out.println(b.getName() + " paid " + b.getIndividualTotal() + " in " + this.getMonth());
		}
		System.out.printf("The average amount paid per person in %s was %.2f", this.getMonth(), this.getAveragePerPerson());
	}
	
	public void writeToFile(){
		try{
			PrintWriter wr = new PrintWriter(this.getMonth(), "UTF-8");
			for(Payment p: getAllPayments()){
				wr.printf("%s,%f,%s\n", p.getNameOfBuyer(), p.getAmount(), p.getPurchaseLocation()); //print in CSV format to a file named by the name of the Bill
			}
			
			wr.println(); //separate puchases from bill summary by a space for ease of reading this file later on.
			for(Buyer b: getAllBuyers()){
				wr.println(b.getName() + " paid " + b.getIndividualTotal() + " in " + this.getMonth());
			}
			wr.close();
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	/* 
	 * After the Bill has been created, we will need to figure out how much each person owes each other.
	 * My strategy for figuring this out is to have the person who paid the least pay the difference between what
	 * they paid and the average to the person who paid the most, adjust each person's balance, 
	 * and continue until everything is even. 
	 */
	public void getMoneyOwed(){
		double avg = getAveragePerPerson(); //store the average paid locally in avg
		int l = 0; //left index in buyer array
		int r = allBuyers.size()-1; //right index in buyer array
		int mid = (l+r)/2;
		
		while(l <= r){
			while(l == avg && l < r) l++; //move left pointer along if the person on the left paid exactly the average
			while(r == avg && l < r) r--; //move right pointer along if the person on the right paid exactly the average
			double amtShort = avg - allBuyers.get(l).getIndividualTotal(); //amtShort is the difference between what buyer l paid and the average paid by the group
			double amtOver = allBuyers.get(r).getIndividualTotal() - avg; //amtOver is the extra money paid by buyer r
		}	
	}
	
	//private method sort sorts the list of buyers by the amount each paid.
	private void sortAllBuyers(){
		Collections.sort(allBuyers, new BuyerComparator());
	}

	private void setMonth(){
		System.out.println("Enter the name of the month, or the period of time that this bill covers.\r");
		Scanner sc = new Scanner(System.in);
		this.month = sc.next();
	}
	
	private void setMonth(String month) {
		this.month = month;
	}
	
	public String getMonth() {
		return this.month;
	}
	
	private void setAllPayments(){
		c = 'y'; // initially set to yes.
		while(c == 'y' || c == 'Y'){
			Payment p = new Payment();
			allPayments.add(p);
			
			System.out.println("Continue? y/n");
			Scanner sc = new Scanner(System.in);
			c = sc.next().charAt(0);
		}
	}
	
	private void setAllPayments(ArrayList<Payment> allPayments) {
		this.allPayments = allPayments;
	}

	public ArrayList<Payment> getAllPayments() {
		return this.allPayments;
	}

	private void setAllBuyers() {
		for(int i = 0; i < this.allPayments.size(); i++){
			if(i == 0){
				String newName = this.allPayments.get(i).getNameOfBuyer(); //get name of buyer for the ith (in this case i=0) Payment
				double newAmount = this.allPayments.get(i).getAmount(); //get amount of ith Payment
				Buyer b = new Buyer(newName, newAmount); //create a new buyer, using the newName and newAmount variables
				this.allBuyers.add(b);
				numberOfPersons++; //increment numberOfPersons
			}
			
			else{ 
				boolean buyerAlreadyExists = false; //will be set to true if the name of the buyer for the ith purchase already exists in the allBuyers arraylist
				String newName = this.allPayments.get(i).getNameOfBuyer(); //get name of buyer for the ith purchase
				double newAmount = this.allPayments.get(i).getAmount(); //get amount of ith purchase
				
				/*
				 * Check if the nameOfBuyer of payment i already exists in allBuyers, add the amount of this payment to that buyers payments.
				 * Else, create a new buyer and add him to allBuyers.
				 */
				for(int j = 0; j<this.allBuyers.size(); j++){
					if(this.allBuyers.get(j).getName().equals(newName)){
						buyerAlreadyExists = true;
						this.allBuyers.get(j).addPayment(newAmount);
						break;
					}
				}
				
				if(!buyerAlreadyExists){ //if we loop through allBuyers without finding a matching name, then construct a new buyer and add him to allBuyers
					Buyer b = new Buyer(newName, newAmount);
					this.allBuyers.add(b);
					numberOfPersons++;
				}
			}
		}
	}
	
	public ArrayList<Buyer> getAllBuyers() {
		return this.allBuyers;
	}
	
	private void setTotalAmount() {
		double total = 0;
		for(Payment p : allPayments){
			total += p.getAmount();
		}
		this.totalAmount = total;
	}
	
	public double getTotalAmount() {
		return this.totalAmount;
	}
	
	public double getAveragePerPerson(){
		double avg = getTotalAmount()/(double)numberOfPersons;
		return avg;
	}
}
