package test;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
public class TestSystem {
  private AbstractUser myCurrentUser;
  public TestSystem() {
    //this constructor just manually set a current user
    //in the real system, it won't be happen.
    final String aStreet = "100 2nd Street";
    final int anApt = 101;
    final String aCity = "Tacoma";
    final String aState = "Washington";
    final int aZip = 98402;

    final Address address = new Address(aStreet, 0, aCity, aState, aZip);

    final Calendar my_calendar = new GregorianCalendar();

    final Long aCardNum = new Long("1234567890123456");
    String aCardHolder = "John White";
    final int anExpDate = 816;
    String aBank = "US Bank";
    final int aCSC = 123;


    Address anAddress = new Address(aStreet, anApt, aCity, aState, aZip);
    final CreditCard aCard = new CreditCard(aCardNum, my_calendar, anExpDate, "holder",
        address, "bank");
    final Bidder aBidder = new Bidder("JohnW.", "12345", "John", "White");
    final Item anItem = new Item(1, "cell phone", 100, 1000.0,
        "Donor", "10*5*5 in.", "in the box",
        "good", "comments");
    myCurrentUser = aBidder;
  }

  public AbstractUser getCurrentUser() {
    return myCurrentUser;
  }

  public void setCurrentUser(AbstractUser aUser) {
    myCurrentUser = aUser;
  }

  /**
   * This method let the current user which must be a bidder bid an item.
   * @param anItem the item would be bided
   * @param aPrice the price the bidder wants to bid the item
   * @return true if the bid has be submitted successfully, else false
   */
  public boolean makeBid(final Item anItem, final double aPrice)	{
    if (myCurrentUser instanceof Bidder) {
      final Calendar my_calendar = new GregorianCalendar();
      final Bidder aBidder = (Bidder) myCurrentUser;
      final Bid aBid = new Bid("item", aPrice, "james", my_calendar, aBidder.getCard());
      aBidder.addBid(aBid);
      anItem.addBid(aBid);
      System.out.println(aBid);
      return true;
    }
    System.out.println("bid rejected!\nCurrent user is a " + myCurrentUser.getClass());
    return false;
  }

}
