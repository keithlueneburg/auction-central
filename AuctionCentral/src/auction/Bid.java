package auction;

import java.util.Date;

import bidding.CreditCard;

import user.Bidder;

public class Bid
{
	private Item myItem;
	private double myPrice;
	private Bidder myBidder;
	private Date myBidTime;
	private CreditCard myPayment;
	
	public Bid(Item anItem, double aPrice, Bidder aBidder, CreditCard aPayment)
	{
		myItem = anItem;
		myBidder = aBidder;
		myPrice = Double.valueOf(String.format("%.2f", aPrice));
		myBidTime = new Date();
		myPayment = aPayment;
	}
	
	public String toString()
	{
		String ret = "a bid has been made by: ";
		ret += myBidder.getFirstName() + " ";
		ret += myBidder.getLastName() + "\n";
		ret += "Bidded item: " + myItem.toString() + "\n";
		ret += "Bid time: " + myBidTime.toString() + "\n";
		ret += "Payment information:\n" + myPayment;
		return ret;
	}
}
