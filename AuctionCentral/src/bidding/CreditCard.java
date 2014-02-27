package bidding;

import java.util.Date;

/**
 * Class: CreditCard
 * 
 * Represents a credit card that a bidder will use to pay for a
 * item that they win the bidding for.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * University of Washington, Tacoma
 * Winter 2014
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 *
 */
public final class CreditCard {
	/**
	 * The 16 digit card number.
	 */
	private long myCardNum;
	
	/**
	 * Card expiration date.
	 */
	private Date myExpDate;
	
	/**
	 * The 3 digit card security code.
	 */
	private int myCSC;
	
	/**
	 * The name of the card holder.
	 */
	private String myCardHolder;	
	
	/**
	 * The card holder's address.
	 */
	private Address myAddress;
	
	/**
	 * The bank this card belongs to.
	 */
	private Bank myBank;	
		
	/**
	 * Create a new CreditCard object.
	 * <p>
	 * <dt><b> Precondition: Valid parameters are passed to 
	 * constructor. </b><dd>
	 * <dt><b> Postcondition: The CreditCard is initialized 
	 * properly. </b><dd>
	 * 
	 * @param aCardNum The card number.
	 * @param anExpDate The expiration date.
	 * @param aCSC The CSC code.
	 * @param aCardHolder The card holder.
	 * @param anAddress The card holder's address.
	 * @param aBank The bank the card belongs to.
	 * 
	 * @return The CreditCard object.
	 * @throws IllegalArgumentException Throws exception in the case of
	 * invalid parameters.
	 */
	public CreditCard(long aCardNum, Date anExpDate, int aCSC,
			String aCardHolder, Address anAddress, Bank aBank){
		
		// Validation is handled by individual setters
		setCardNum(aCardNum);
		setExpDate(anExpDate);
		setCSC(aCSC);
		setCardHolder(aCardHolder);
		setAddress(anAddress);
		setBank(aBank);
	}
	
	/**
	 * @return The card number.
	 */
	private final long getCardNum() {
		return myCardNum;
	}

	/**
	 * @return The expiration date.
	 */
	private final Date getExpDate() {
		
		// TODO : return a defensive copy here
		return myExpDate;
	}

	/**
	 * @return The CSC code.
	 */
	private final int getCSC() {
		return myCSC;
	}

	/**
	 * @return The card holder name.
	 */
	private final String getCardHolder() {
		return myCardHolder;
	}

	/**
	 * @return The address.
	 */
	private final Address getAddress() {
		
		//TODO : return a defensive copy once Address is complete.
		return myAddress;
	}

	/**
	 * @return The bank.
	 */
	private final Bank getBank() {
		return myBank;
	}
	
	/**
	 * @param aCardNum The card number to set.
	 */
	private final void setCardNum(long aCardNum) {
		if((new Long(aCardNum)).toString().length() != 16) {
			throw new IllegalArgumentException("Invalid card number length");
		} else {
			this.myCardNum = aCardNum;
		}
	}

	/**
	 * @param anExpDate The expiration date to set.
	 */
	private final void setExpDate(Date anExpDate) {
		
		//TODO : make defensive copy
		this.myExpDate = anExpDate;
	}

	/**
	 * @param aCSC The CSC to set.
	 */
	private final void setCSC(int aCSC) {
		if((new Integer(aCSC)).toString().length() != 3) {
			throw new IllegalArgumentException("Invalid CSC length");
		} else {
			this.myCSC = aCSC;
		}
	}

	/**
	 * <p>
	 * <dt><b> Precondition: Card holder name is not blank. </b><dd>
	 * <dt><b> Postcondition: Name is set. </b><dd>
	 * 
	 * @param aCardHolder The card holder name to set.
	 */
	private final void setCardHolder(String aCardHolder) {
		if (aCardHolder.length() == 0) {
			throw new IllegalArgumentException("Name cannot be blank");
		} else {
			this.myCardHolder = aCardHolder;
		}
	}

	/**
	 * @param anAddress The address to set.
	 */
	private final void setAddress(Address anAddress) {
		
		//TODO : make defensive copy
		this.myAddress = anAddress;
	}

	/**
	 * @param aBank The bank name to set.
	 */
	private final void setBank(Bank aBank) {

		//TODO : make defensive copy
		this.myBank = aBank;
	}

	/**
	 * Check to see if this CreditCard matches another CreditCard.
	 * <p>
	 * <dt><b> Precondition: CreditCard has been initialized. </b><dd>
	 * <dt><b> Postcondition: The CreditCard has not been changed. </b><dd>
	 * 
	 * @param anObject The object to compare this CreditCard with.
	 * 
	 * @return True if the other object is also a CreditCard, and it represents
	 * the same card as the card we are checking.
	 */
	@Override
	public boolean equals(Object anObject) {
		boolean isEqual = true;
		
		// Check for null object
		if (anObject == null) {
			isEqual = false;
			
		// Check for correct object type
		} else if (!(anObject instanceof CreditCard)) {
			isEqual = false;
		} else {
			
			// Cast other object to a CreditCard
			CreditCard other = (CreditCard)anObject;
		
			// Compare field by field for equality
			if (!myCardHolder.equals(other.getCardHolder())) {
				isEqual = false;
			} else if (myCardNum != other.getCardNum()) {
				isEqual = false;
			} else if (!myExpDate.equals(other.getExpDate())) {
				isEqual = false;
			} else if (myCSC != other.getCSC()) {
				isEqual = false;
			} else if (!myAddress.equals(other.getAddress().toString())) {
				isEqual = false;
			} else if (!myBank.equals(other.getBank())) {
				isEqual = false;
			}
		}
		
		return isEqual;
	}
	
	/**
	 * Get a String representation of the CreditCard.
	 * <p>
	 * <dt><b> Precondition: CreditCard has been initialized. </b><dd>
	 * <dt><b> Postcondition: The CreditCard has not been changed. </b><dd>
	 * 
	 * @return A string representation of the card.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cardholder: ");
		sb.append(myCardHolder);
		sb.append("\n");
		sb.append("Card Number: ");
		sb.append(myCardNum);
		sb.append("\n");
		sb.append("Expiration Date: ");
		sb.append(myExpDate.getDay() + " ");
		sb.append("\n");
		sb.append("CSC: ");
		sb.append(myCSC);
		sb.append("\n");
		sb.append("Address: ");
		
		//TODO : once Address toString() is implemented, use that here!
		sb.append("PLACEHOLDER ADDRESS");
		
		sb.append("\n");
		sb.append("Bank: ");
		//TODO : once Address toString() is implemented, use that here!
		sb.append("PLACEHOLDER BANK");
		sb.append("\n");
		
		return sb.toString();
	}
}
