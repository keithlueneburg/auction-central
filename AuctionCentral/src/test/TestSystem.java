package test;

import auction.Bid;
import auction.Item;
import bidding.Address;
import bidding.CreditCard;

import java.util.Calendar;
import java.util.GregorianCalendar;

import user.AbstractUser;
import user.Bidder;



/**
 * This is a test Auction system.
 * @author Kaiyuan Shi
 * @version Win. 14
 */
public class TestSystem {
  
  /** An abstract user. */
  private AbstractUser myCurrentUser;
  
  /** Creates a test system. */
  public TestSystem() {
    //this constructor just manually set a current user
    //in the real system, it won't be happen.

    final Bidder bidder = new Bidder("JohnW.", "12345", "John", "White");

    myCurrentUser = bidder;
  }

  /**
   * Gets the current user.
   * @return - the current user.
   */
  public AbstractUser getCurrentUser() {
    return myCurrentUser;
  }

  /**
   * Sets the current user.
   * @param aUser - the user.
   */
  public void setCurrentUser(final AbstractUser aUser) {
    myCurrentUser = aUser;
  }

  /**
   * This method let the current user which must be a bidder bid an item.
   * @param an_item the item would be bided
   * @param a_price the price the bidder wants to bid the item
   * @return true if the bid has be submitted successfully, else false
   */
  public boolean makeBid(final Item an_item, final double a_price) {
    if (myCurrentUser instanceof Bidder) {
      final Calendar calendar = new GregorianCalendar();
      final Bidder bidder = (Bidder) myCurrentUser;
      final Bid bid = new Bid("item", a_price, "james", calendar, bidder.getCard());
      bidder.addBid(bid);
      an_item.addBid(bid);
      System.out.println(bid);
      return true;
    }
    System.out.println("bid rejected!\nCurrent user is a " + myCurrentUser.getClass());
    return false;
  }

}
