/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import auction.Auction;
import user.NonProfitUser;

/**
 * @author Administrator
 *
 */
public class TestSystemTest {
  
  TestSystem my_test_system;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    my_test_system = new TestSystem();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * This method test if the user add an auction on a good time.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionSuccess() {
    final Calendar test_date = Calendar.getInstance();
    test_date.set(2014, 5, 1, 9, 0);
    
    final Auction test_auction = new Auction("test auction name", "test contact person",
        "test phone number", "test intake person", test_date, 2, "test comments");
    
    assertEquals("normal add failed!", null, my_test_system.addAuction(test_auction));
  }
  
  /**
   * This method test user adding a Auction before the current time.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionbefore() {
    final Calendar test_date = Calendar.getInstance();
    test_date.set(2014, 2, 4, 9, 0);
    
    final Auction test_auction = new Auction("test auction name", "test contact person",
        "test phone number", "test intake person", test_date, 2, "test comments");
    assertEquals("add before fail!","you can only schedule Auction in the future!",
        my_test_system.addAuction(test_auction));
  }
  
  /**
   * This method test user adding a null Auction.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionnull() {
    final Auction test_auction = null;
    assertEquals("add null fail!","input Auction is null!",
        my_test_system.addAuction(test_auction));
  }
  
  /**
   * This method test the user wants to add more than 25 auction.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionMoreThan25() {
    final Calendar current1 = Calendar.getInstance();
    current1.set(2014, 4, 21, 9, 0);
    final Calendar current2 = Calendar.getInstance();
    current2.set(2014, 4, 22, 9, 0);
    final Auction test_auction1 = new Auction("test auction name1", "test contact person1",
        "test phone number1", "test intake person1", current1, 2, "test comments1");
    final Auction test_auction2 = new Auction("test auction name2", "test contact person2",
        "test phone number2", "test intake person2", current2, 2, "test comments2");
    my_test_system.setUser(new NonProfitUser("new username2", "new password2", "new first name2",
        "new last name2", "new non-profit2"));
    my_test_system.addAuction(test_auction1);
    my_test_system.setUser(new NonProfitUser("new username3", "new password3", "new first name3",
        "new last name3", "new non-profit3"));
    assertEquals("add 25 fail!","No more than 25 auctions may be schedule into the future!",
        my_test_system.addAuction(test_auction2));
  }
  
  /**
   * This method test if the user wants to add a Auction more than 3 month later.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionmoreThan3Month() {
    final Calendar test_date = Calendar.getInstance();
    test_date.set(2014, 10, 6, 9, 0);
    
    final Auction test_auction = new Auction("test auction name", "test contact person",
        "test phone number", "test intake person", test_date, 2, "test comments");
    
    assertEquals("3 month failed!", "An auction may not be scheduled more"
          + " than 3 months from the current date!", my_test_system.addAuction(test_auction));
  }
  
  /**
   * This method test if the user wants to add 6th auction in 7 days.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuction7Days() {
    final Calendar test_date = Calendar.getInstance();
    test_date.set(2014, 4, 2, 9, 0);
    final Auction test_auction = new Auction("test auction name", "test contact person",
        "test phone number", "test intake person", test_date, 2, "test comments");
    assertEquals("add before fail!", "No more than 5 auctions may be scheduled"
        + " for any 7 day period!", my_test_system.addAuction(test_auction));
  }
  
  /**
   * this method test if the user wants to add a auction in same day,
   * but the gap is more than 2 hours.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionOnedaySuccess() {
    final Calendar test_date = Calendar.getInstance();
    test_date.set(2014, 3, 1, 13, 0);
    final Auction test_auction = new Auction("test auction name", "test contact person",
        "test phone number", "test intake person", test_date, 2, "test comments");
    assertEquals("add before fail!", null, my_test_system.addAuction(test_auction));
  }
  
  /**
   * this method test if the user wants to add a auction in same day,
   * but the gap is less than 2 hours.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionOnedayFail() {
    final Calendar test_date = Calendar.getInstance();
    test_date.set(2014, 3, 1, 9, 0);
    final Auction test_auction = new Auction("test auction name", "test contact person",
        "test phone number", "test intake person", test_date, 2, "test comments");
    assertEquals("add before fail!", "No more than 2 auctions can be scheduled"
          + " on the same day, and the start time of the second"
          + " can be no earlier than 2 hours after the end time"
          + " of the first!", my_test_system.addAuction(test_auction));
  }
  
  /**
   * this method test if the same user wants to add a auction in same year.
   * Test method for {@link system.AuctionCentralSystem#addAuction(auction.Auction)}.
   */
  @Test
  public void testAddAuctionOneYear() {
    final Calendar test_date = Calendar.getInstance();
    test_date.set(2014, 5, 1, 9, 0);
    my_test_system.setUser(14);
    final Auction test_auction = new Auction("test auction name", "test contact person",
        "test phone number", "test intake person", test_date, 2, "test comments");
    assertEquals("normal add failed!", "No more than one auction per year"
            + " per Non-profit organization can be scheduled",
            my_test_system.addAuction(test_auction));
  }


}
