package auction;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.LinkedList;


public class Auction {
	
	// Used only when adding/removing items
	private int myNextItem;
	
	private String myAuctionName;
	private String myContactPerson;
	private String myContactPhone;
	private String myIntakePerson;
	private Calendar myAuctionDate;
	private int myAuctionDuration;
	private String myComments;
	
	private List<Item> myAuctionItems;
	
	public Auction(String theAuctionName, String theContactPerson, String theContactPhone, String theIntakePerson,
			Calendar theAuctionDate, int theAuctionDuration, String theComments) {
		myNextItem = 1;
		
		myAuctionName = theAuctionName;
		myContactPerson = theContactPerson;
		myContactPhone = theContactPhone;
		myIntakePerson = theIntakePerson;
		myAuctionDate = theAuctionDate;
		myAuctionDuration = theAuctionDuration;
		myComments = theComments;
		
		myAuctionItems = new LinkedList<Item>();
		
	}
	
	public Auction() {
		this("", "", "", "", new GregorianCalendar(), 0, "");
	}
	
	// SETTERS
	public void setAuctionName(String theAuctionName) {
		myAuctionName = theAuctionName;
	}
	
	public void setContactPerson(String theContactPerson) {
		myContactPerson = theContactPerson;
	}
	
	public void setContactPhone(String theContactPhone) {
		myContactPhone = theContactPhone;
	}
	
	public void setIntakePerson(String theIntakePerson) {
		myIntakePerson = theIntakePerson;
	}
	
	public void setAuctionDate(Calendar theAuctionDate) {
		myAuctionDate = theAuctionDate;
	}
	
	public void setAuctionDuration(int theAuctionDuration) {
		myAuctionDuration = theAuctionDuration;
	}
	
	public void setComments(String theComments) {
		myComments = theComments;
	}
	
	// GETTERs
	public String getAuctionName() {
		return myAuctionName;
	}
	
	public String getContactPerson() {
		return myContactPerson;
	}
	
	public String getContactPhone() {
		return myContactPhone;
	}
	
	public String getIntakePerson() {
		return myIntakePerson;
	}
	
	public Calendar getAuctionDate() {
		return myAuctionDate;
	}
	
	public int getAuctionDuration() {
		return myAuctionDuration;
	}
	
	public String getComments() {
		return myComments;
	}
	
	// METHODS 
	boolean addItem(Item theItem) {
		boolean success = myAuctionItems.add(theItem);
		
		if (success) {
			theItem.setItemNumber(myNextItem);
			myNextItem++;
		}
		
		return success;
	}
	
	boolean deleteItem(Item theItem) {
		boolean success = myAuctionItems.remove(theItem);
		
		if (success) {
			myNextItem--;
		}
		
		return success;
	}
}
