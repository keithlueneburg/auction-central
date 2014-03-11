/**
 * 
 */
package test;

import static org.junit.Assert.*;

import auction.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import user.AbstractUser;
import user.AuctionCentralStaff;
import user.Guest;
import user.NonProfitUser;
import user.User;



/**
 * This class is a JUnit test of makeBid method in System.
 * @author Kaiyan Shi
 * @version Win. 14
 */
public class TestSystemTest {

  /** The system to test. */
  private TestSystem my_system = new TestSystem();
  
  /** The item to be tested. */
  private Item my_cellphone = new Item(1, "cell phone", 100, 1000.0,
      "Donor", "10*5*5 in.", "in the box",
      "good", "comments");
  
  /** A new user to test. */
  private AbstractUser my_testperson = new AuctionCentralStaff("B", "C", "O", "P");

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * This method test whether the Bid can be made successfully if the user is a bidder.
   * Test method for {@link TestSystem.testSystem#makeBid(checkIn4.Item, double)}.
   */
  @Test
  public void testBidderMakeBid() {
    assertEquals("Bidder failed make a bid!", true, my_system.makeBid(my_cellphone, 100.23));
  }

  /**
   * This method test whether the Bid would be rejected if the user is an administer.
   * Test method for {@link TestSystem.testSystem#makeBid(checkIn4.Item, double)}.
   */
  @Test
  public void testAdminMakeBid() {
    my_testperson = new Guest("admin", "12345", "Admin", "User");
    my_system.setCurrentUser(my_testperson);
    assertEquals("Administrator shouldn't make a bid successfully!", false,
        my_system.makeBid(my_cellphone, 100.23));
  }

  /**
   * This method test whether the Bid would be rejected if the user is a non-profit user.
   * Test method for {@link TestSystem.testSystem#makeBid(checkIn4.Item, double)}.
   */
  @Test
  public void testNonProMakeBid() {
    my_testperson = new NonProfitUser("nonProfit", "54321", "non-pro", "User", "my org.");
    my_system.setCurrentUser(my_testperson);
    assertEquals("NonProfitUser shouldn't banke a bid successfully!", false,
        my_system.makeBid(my_cellphone, 100.23));
  }

  /**
   * This method test whether the Bid would be rejected if the user is a auction central staff.
   * Test method for {@link TestSystem.testSystem#makeBid(checkIn4.Item, double)}.
   */
  @Test
  public void testACStaffMakeBid() {
    my_testperson = new AuctionCentralStaff("Auction", "11111", "Auction Staff", "User");
    my_system.setCurrentUser(my_testperson);
    assertEquals("AuctionCentralStaff shouldn't banke a bid successfully!", false,
        my_system.makeBid(my_cellphone, 100.23));
  }

}
