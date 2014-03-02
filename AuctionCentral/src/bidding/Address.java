package bidding;

import java.util.List;

/**
 * Class: Address
 * 
 * Represents an address used for billing and to keep bidder data.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * University of Washington, Tacoma
 * Winter 2014
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Casey Morrison
 * @version 1.0 Winter 2014
 */
public final class Address {

	/** The Street number. */
	private String myStreet;
	
	/** The Apartment number. */
	private int myApt = 0;
	
	/** The City name. */
	private String myCity;
	
	/** The State name. */
	private String myState;
	
	/** The Zipcode. */
	private int myZip;
	
	/** The list of states. */
	private List<String> myStateList;

	/**
	 * @param myStreet
	 * @param myApt
	 * @param myCity
	 * @param myState
	 * @param myZip
	 * @param myStateList
	 */
	public Address(String aStreet, int anApt, String aCity, String aState,
			int aZip, List<String> aStateList) {
		myStreet = aStreet;
		myApt = anApt;
		myCity = aCity;
		myState = aState;
		myZip = aZip;
		myStateList = aStateList;
	}
	
	/**
	 * @return the Street number.
	 */
	public String getMyStreet() {
		return myStreet;
	}

	/**
	 * @param aStreet the myStreet to set.
	 */
	public void setMyStreet(String aStreet) {
		this.myStreet = aStreet;
	}

	/**
	 * @return the apartment.
	 */
	public int getMyApt() {
		return myApt;
	}

	/**
	 * @param anApt the apartment to set.
	 */
	public void setMyApt(int anApt) {
		this.myApt = anApt;
	}

	/**
	 * @return the City.
	 */
	public String getMyCity() {
		return myCity;
	}

	/**
	 * @param aCity the myCity to set.
	 */
	public void setMyCity(String aCity) {
		this.myCity = aCity;
	}

	/**
	 * @return the State.
	 */
	public String getMyState() {
		return myState;
	}

	/**
	 * @param aState the State to set.
	 */
	public void setMyState(String aState) {
		this.myState = aState;
	}

	/**
	 * @return the zipcode.
	 */
	public int getMyZip() {
		return myZip;
	}

	/**
	 * @param aZip the myZip to set.
	 */
	public void setMyZip(int aZip) {
		this.myZip = aZip;
	}

	/**
	 * @return the myStateList.
	 */
	public List<String> getMyStateList() {
		return myStateList;
	}

	/**
	 * @param myStateList the myStateList to set
	 */
	public void setMyStateList(List<String> myStateList) {
		this.myStateList = myStateList;
	}

	/**
	 * Check to see if this Address matches another Address.
	 * <p>
	 * <dt><b> Precondition: Address has been initialized. </b><dd>
	 * <dt><b> Postcondition: The Address has not been changed. </b><dd>
	 * 
	 * @param anObject The object to compare this Address with.
	 * @return True if the other object is also an Address, and it represents
	 * the same address as the address we are checking.
	 */
	@Override
	public boolean equals(Object anObject) {
		boolean isEqual = true;
		
		// Check for correct object type, and set flag to false if incorrect
		if (!(anObject instanceof Address)) {
			isEqual = false;
		} else {
			// If object is an Address, cast it to an Address
			Address other = (Address)anObject;
		
			// Compare field by field for equality and set flag to false if
			// a field doesn't match
			if (!myStreet.equals(other.getMyStreet())) {
				isEqual = false;
			} else if (myApt != other.getMyApt()) {
				isEqual = false;
			} else if (myCity != other.getMyCity()) {
				isEqual = false;
			} else if (myState != other.getMyState()) {
				isEqual = false;
			} else if (myZip != (other.getMyZip())) {
				isEqual = false;
			}
		}
		return isEqual;
	}
	
	/**
	 * Get a String representation of the Address.
	 * <p>
	 * <dt><b> Precondition: Address has been initialized. </b><dd>
	 * <dt><b> Postcondition: The Address has not been changed. </b><dd>
	 * 
	 * @return A string representation of the Address.
	 * @author Keith Lueneburg (Minor changes)
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Address:\n");
		sb.append(myStreet);
		if (myApt > 0)
			sb.append(" " + myApt);	
		sb.append("\n" + myCity + " " + myState + " ");
		sb.append(myZip);
		sb.append("\n");

		return sb.toString();
	}
}
