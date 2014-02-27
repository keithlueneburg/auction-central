package auction;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This is the Item class. It holds all the information on a given item 
 * for an auction. The item is able to add and remove bids from myBids, 
 * and it can "unseal" a bid with a username and password.
 * 
 * @author Kevin Alexander
 * @version February 23, 2014
 *
 */
public class Item {
	
	////////////////////////////////////////////////////////////////////
	// FIELDS
	////////////////////////////////////////////////////////////////////	
	private int myItemNumber;
	private String myItemName;
	private int myQuantity;
	private double myStartingBid;
	private String myDonor;
	private String mySize;
	private String myStorage;
	private String myCondition;
	private String myComments;
	private String mPhotoLoc;
	
	private double mSellingPrice;
	
	private List<Bid> myBids;
	
	////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////
	/**
	 * This constructor takes in values for all fields except myBids. 
	 * @param theItemNumber The number of the item.
	 * @param theItemName The name of the item.
	 * @param theItemQuantity The quantity of the item.
	 * @param theStartingBid The starting bid of the item.
	 * @param theDonor The donor of the item.
	 * @param theSize The size of the item.
	 * @param theStorage The storage location of the item.
	 * @param theCondition The condition of the item.
	 * @param theComments The comments of the item.
	 * @param thePhotoLoc The file name for the photo of the item.
	 */
	public Item(int theItemNumber, String theItemName, int theItemQuantity, 
			double theStartingBid, String theDonor, String theSize, 
			String theStorage, String theCondition, String theComments, 
			String thePhotoLoc) {
		myItemNumber = theItemNumber;
		myItemName = theItemName;
		myQuantity = theItemQuantity;
		myStartingBid = theStartingBid;
		myDonor = theDonor;
		mySize = theSize;
		myStorage = theStorage;
		myCondition = theCondition;
		myComments = theComments;
		mPhotoLoc = thePhotoLoc;
		mSellingPrice = 0.0;
		
		myBids = new LinkedList<Bid>();
	}

	/**
	 * This constructor takes in no arguments and sets all the fields 
	 * to default values.
	 */
	public Item() {
		this(0, "", 0, 0.00, "", "", "", "", "", "");
	}
	
	////////////////////////////////////////////////////////////////////
	// SETTERS
	////////////////////////////////////////////////////////////////////
	/**
	 * Sets the item's number to theItemNumber.
	 * @param theItemNumber A String representing the new number.
	 */
	public void setItemNumber(int theItemNumber) {
		myItemNumber = theItemNumber;
	}
	
	/**
	 * Sets the item's name to theItemName.
	 * @param theItemName A String representing the new name.
	 */
	public void setItemName(String theItemName) {
		myItemName = theItemName;
	}
	
	/**
	 * Sets the item's quantity to theItemQuantity.
	 * @param theItemQuantity An integer representing the new quantity.
	 */
	public void setItemQuantity(int theItemQuantity) {
		myQuantity = theItemQuantity;
	}
	
	/**
	 * Sets the item's starting bid to theStartingBid.
	 * @param theStartingBid A double representing the new starting bid.
	 */
	public void setStartingBid(double theStartingBid) {
		myStartingBid = theStartingBid;
	}
	
	/**
	 * Sets the item's donor to theDonor.
	 * @param theDonor A String representing the new donor.
	 */
	public void setDonor(String theDonor) {
		myDonor = theDonor;
	}
	
	/**
	 * Sets the item's size to theSize.
	 * @param theSize A String representing the new size.
	 */
	public void setSize(String theSize) {
		mySize = theSize;
	}
	
	/**
	 * Sets the item's storage location to theStorage.
	 * @param theStorage A String representing the new storage location. 
	 */
	public void setStorage(String theStorage) {
		myStorage = theStorage;
	}
	
	/**
	 * Sets the item's condition to theCondition.
	 * @param theCondition A String representing the new condition.
	 */
	public void setCondition(String theCondition) {
		myCondition = theCondition;
	}
	
	/**
	 * Sets the item's comments to theComments.
	 * @param theComments A String representing the new comments.
	 */
	public void setComments(String theComments) {
		myComments = theComments;
	}
	
	/**
	 * Sets the item's file name to thePhotoLoc.
	 * @param thePhotoLoc A String representing the new photo file name.
	 */
	public void setPhotoLocation(String thePhotoLoc) {
		mPhotoLoc = thePhotoLoc;
	}
	
	/**
	 * Sets the item's selling price to theSellingPrice.
	 * @param theSellingPrice A double representing the new selling price.
	 */
	public void setSellingPrice(double theSellingPrice) {
		mSellingPrice = theSellingPrice;
	}
	
	////////////////////////////////////////////////////////////////////
	// GETTERS
	////////////////////////////////////////////////////////////////////
	/**
	 * Returns the item number.
	 * @return myItemNumber The item number.
	 */
	public int getItemNumber() {
		return myItemNumber;
	}
	/**
	 * Returns the name of the item.
	 * @return myItemName The item name.
	 */
	public String getItemName() {
		return myItemName;
	}
	
	/**
	 * Returns the quantity of the item.
	 * @return myQuantity The quantity.
	 */
	public int getItemQuantity() {
		return myQuantity;
	}
	
	/**
	 * Returns the starting bid of the item.
	 * @return myStartingBid The starting bid.
	 */
	public double getStartingBid() {
		return myStartingBid;
	}
	
	/**
	 * Returns the donor of the item.
	 * @return myDonor The donor.
	 */
	public String getDonor() {
		return myDonor;
	}
	
	/**
	 * Returns the size of the item.
	 * @return mySize The size.
	 */
	public String getSize() {
		return mySize;
	}
	
	/**
	 * Returns the storage location of the item.
	 * @return myStorage The storage location.
	 */
	public String getStorage() {
		return myStorage;
	}
	
	/**
	 * Returns the condition of the item.
	 * @return myCondition The condition.
	 */
	public String getCondition() {
		return myCondition;
	}
	
	/**
	 * Returns the comments of this item.
	 * @return myComments The comments.
	 */
	public String getComments() {
		return myComments;
	}
	
	/**
	 * Returns the file name of a photo of this item.
	 * @return A String of the photo's file name.
	 */
	public String getPhotoLocation() {
		return mPhotoLoc;
	}
	
	/**
	 * Returns the selling price of this item.
	 * @return mSellingPrice The selling price.
	 */
	public double getSellingPrice() {
		return mSellingPrice;
	}
	
	/**
	 * Returns the list of Bid objects on this item.
	 * @return myBids The list of Bids.
	 */
	public List<Bid> getBids() {
		return myBids;
	}
	
	////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////
	/**
	 * This method takes in a Bid item and adds it to the current list of 
	 * Bid objects.
	 * Precondition: None
	 * Postcondition: The Bid item has been added to the list of Bid objects.
	 * @param theBid The Bid object that is being added to the myBids list.
	 * @return A boolean indicating whether the Bid object was successfully 
	 * added to the
	 * list or not.
	 */
	public boolean addBid(Bid theBid) {
		return myBids.add(theBid);
	}
	
	/**
	 * This method takes in a Bid item and adds it to the current list of Bid 
	 * objects.
	 * Precondition: The list myBids has at least one Bid.
	 * Postcondition: The Bid item has been removed from the list of Bid objects.
	 * @param theBid The Bid object that is being removed from the myBids list.
	 * @return A boolean indicating whether the Bid object was successfully 
	 * deleted from the list or not.
	 */
	public boolean removeBid(Bid theBid) {
		return myBids.remove(theBid);
	}
	
	/**
	 * This method takes in two Strings, which represent the username and password 
	 * of one member of the AuctionCentral Staff, and verifies that they are correct. 
	 * It then returns the highest bid or returns null.
	 * Precondition: The item must have at least one bid in myBids and the file 
	 * admin.txt must be existent and contain the proper username and password.
	 * Postcondition: If the correct username and password were entered, the highest 
	 * bid is returned.
	 * @param theUsername The username that is being entered.
	 * @param thePassword The password that is being entered.
	 * @return Returns the highest bid.
	 */
	public Bid unsealBid(String theUsername, String thePassword) {
		Scanner input;
		Bid returnBid = null;
		
		input = getScanner("log//admin.txt");
		if (input != null && !myBids.isEmpty()) {
			if (input.hasNext()) {
				String username = input.next();
				if (input.hasNext()) {
					String password = input.next();
					if (theUsername.equals(username) && thePassword.equals(password)) {
						// LOGIN SUCCESS
						returnBid = myBids.get(0);
					}
				}
			}
			
			input.close();
		}
		
		return returnBid;
	}
	
	/**
	 * This is a private method that is used in the unsealBid() method. It takes in a 
	 * String containing the file location and attempts to assign a Scanner object to it. 
	 * The Scanner object is then returned.
	 * Precondition: The String should contain a valid file path for the username and 
	 * password.
	 * Postcondtion: The Scanner object should be assigned to the File at the String's 
	 * location. If it doesn't then the Scanner is null.
	 * @param fileName A String object containing the file path of the username and 
	 * password.
	 * @return A Scanner object if the file was found and null otherwise.
	 */
	private Scanner getScanner(String fileName) {
		Scanner temp = null;
		
		try {
			temp = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			
		}
		
		return temp;
	}
	
}