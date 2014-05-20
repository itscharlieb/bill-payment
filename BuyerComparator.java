package bill_payment;

import java.util.Comparator;

public class BuyerComparator implements Comparator<Buyer> {

	@Override
	public int compare(Buyer b1, Buyer b2) {
		return Double.compare(b1.getIndividualTotal(), b2.getIndividualTotal());
	}
}
