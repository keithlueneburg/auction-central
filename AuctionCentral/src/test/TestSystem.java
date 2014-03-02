package test;

import java.math.BigInteger;

import user.AbstractUser;
import user.Bidder;

import auction.Bid;
import auction.Item;
import bidding.Address;
import bidding.CreditCard;

/**
 * This is a test Auction system.
 * @author Kaiyuan Shi
 * @version Win. 14
 */
public class TestSystem
{
	private AbstractUser myCurrentUser;
	public TestSystem()
	{
		//this constructor just manually set a current user
		//in the real system, it won't be happen.
		String aStreet = "100 2nd Street";
		int anApt = 101;
		String aCity = "Tacoma";
		String aState = "Washington";
		int aZip = 98402;
		
		BigInteger aCardNum = new BigInteger("1234567890123456");
		String aCardHolder = "John White";
		int anExpDate = 816;
		String aBank = "US Bank";
		int aCSC = 123;
		
		
		Address anAddress = new Address(aStreet, anApt, aCity, aState, aZip);
		CreditCard aCard = new CreditCard(aCardNum, aCardHolder, anExpDate, anAddress, aBank, aCSC);
		Bidder aBidder = new Bidder("JohnW.", "12345", "John", "White", aCard, anAddress);
		Item anItem = new Item(1, "cell phone", 100, 1000.0,
				"Donor", "10*5*5 in.", "in the box",
				"good", "comments", "pic");
		myCurrentUser = aBidder;
	}
	
	public AbstractUser getCurrentUser()
	{
		return myCurrentUser;
	}
	
	public void setCurrentUser(AbstractUser aUser)
	{
		myCurrentUser = aUser;
	}
	
	/**
	 * This method let the current user which must be a bidder bid an item.
	 * @param anItem the item would be bided
	 * @param aPrice the price the bidder wants to bid the item
	 * @return true if the bid has be submitted successfully, else false
	 */
	public boolean makeBid(Item anItem, double aPrice)
	{
		if (myCurrentUser instanceof Bidder)
		{
			Bidder aBidder = (Bidder) myCurrentUser;
			Bid aBid = new Bid(anItem, aPrice, aBidder, aBidder.getCard());
			aBidder.addBid(aBid);
			anItem.addBid(aBid);
			System.out.println(aBid);
			return true;
		}
		System.out.println("bid rejected!\nCurrent user is a " + myCurrentUser.getClass());
		return false;
	}

}
