package bidding;

import auction.Item;

/**
 * Class: Bank
 * 
 * Represents a Bank that will process the bills and payments.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * University of Washington, Tacoma
 * Winter 2014
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Casey Morrison
 * @version 1.0 Winter 2014
 */
public final class Bank {

	/** Used as the bank name */
	private String myName;
	
	/**
	 * Create a new Bank object.
	 * <p>
	 * <dt><b> Precondition: Valid parameters are passed to constructor. </b><dd>
	 * <dt><b> Postcondition: The Bank has been initialized properly. </b><dd>
	 * 
	 * @param aName The name of the bank.
	 * 
	 * @return The Bank object.
	 */
	public Bank(String aName) {
		myName = aName;
	}
	
	/**
	 * Pays a bill for a bidder.
	 * <p>
	 * <dt><b> Precondition: Valid parameters are passed to payment. </b><dd>
	 * <dt><b> Postcondition: The Bill has been initialized properly. </b><dd>
	 * 
	 * @param anItem The item.
	 * @param aPrice The price of the bill.
	 * @param aCard The card used to pay.
	 * @return bill, a Bill used to charge a bidder.
	 */
	public static Bill makePayment(Item anItem, double aPrice, CreditCard aCard) {
		Bill bill;
		return bill = new Bill(anItem, aPrice, aCard);
	}
}
