package bidding;

import java.util.Date;

import auction.Item;

/**
 * Class: Bill
 * 
 * Represents a bill that a the Auction Central Staff
 * will use to pay for a charge a bidder for the item they win
 * in the auction.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * University of Washington, Tacoma
 * Winter 2014
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Casey Morrison
 * @version 1.0 Winter 2014
 */
public final class Bill {

	/** The item that was purchased by the bidder. */
	private Item myItem;
	
	/** The price of the item. */
	private double myPrice;
	
	/** The date of the transaction. */
	private Date myDate;
	
	/** The payment method, credit card. */
	private CreditCard myPayment;
	
	/**
	 * Create a new Bill object.
	 * <p>
	 * <dt><b> Precondition: Valid parameters are passed to constructor. </b><dd>
	 * <dt><b> Postcondition: The Bill has been initialized properly. </b><dd>
	 * 
	 * @param anItem The item.
	 * @param theDate The date of transaction.
	 * @param aPrice The price of the item.
	 * @param aPayment The payment method.
	 * 
	 * @return The Bill object.
	 */
	public Bill(Item anItem, double aPrice, CreditCard aPayment) {
		myItem = anItem;
		myPrice = aPrice;
		myDate = new Date();
		myPayment = aPayment;
	}

	
	// might not need getters and setters....
	/**
	 * Get the item object.
	 * @return the myItem.
	 */
	public Item getMyItem() {
		return myItem;
	}

	/**
	 * Set the item.
	 * @param anItem the Item to set.
	 */
	public void setMyItem(Item anItem) {
		myItem = anItem;
	}

	/**
	 * Gets the price of the item.
	 * @return the myPrice.
	 */
	public double getMyPrice() {
		return myPrice;
	}

	/**
	 * Sets the price of the item.
	 * @param aPrice the Price to set.
	 */
	public void setMyPrice(double aPrice) {
		myPrice = aPrice;
	}

	/**
	 * Get the date.
	 * @return the myDate.
	 */
	public Date getMyDate() {
		return myDate;
	}

	/**
	 * Set the date.
	 * @param aDate the Date to set.
	 */
	public void setMyDate(Date aDate) {
		myDate = aDate;
	}

	/**
	 * Get the payment.
	 * @return the myPayment.
	 */
	public CreditCard getMyPayment() {
		return myPayment;
	}

	/**
	 * Set the payment.
	 * @param aPayment the Payment to set.
	 */
	public void setMyPayment(CreditCard aPayment) {
		myPayment = aPayment;
	}
	
	/**
	 * Get a printout of the Bill.
	 * <p>
	 * <dt><b> Precondition: Bill has been initialized. </b><dd>
	 * <dt><b> Postcondition: The Bill has not been changed. </b><dd>
	 */
	public void printBill() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bill: \n----------------------\n");
		sb.append("Item: ");
		sb.append(myItem);
		sb.append("\n");
		sb.append("Date: ");
		sb.append(myDate);
		sb.append("\n");
		sb.append("Price: ");
		sb.append(myPrice);
		sb.append("\n");
		sb.append("Payment Information: ");
		sb.append(myPayment);
		sb.append("\n----------------------");

		System.out.println(sb);
	}
}
