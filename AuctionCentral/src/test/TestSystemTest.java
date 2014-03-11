/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import user.AbstractUser;
import user.Guest;
import user.AuctionCentralStaff;
import user.NonProfitUser;

import auction.Item;

/**
 * This class is a JUnit test of makeBid method in System.
 * @author Kaiyan Shi
 * @version Win. 14
 */
public class TestSystemTest {
	
	private TestSystem mySystem = new TestSystem();
	private Item cellPhone = new Item(1, "cell phone", 100, 1000.0,
			"Donor", "10*5*5 in.", "in the box",
			"good", "comments", "pic");
	private AbstractUser testPerson;

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
		assertEquals("Bidder failed make a bid!", true, mySystem.makeBid(cellPhone, 100.23));
	}

	/**
	 * This method test whether the Bid would be rejected if the user is an administer.
	 * Test method for {@link TestSystem.testSystem#makeBid(checkIn4.Item, double)}.
	 */
	@Test
	public void testAdminMakeBid() {
		testPerson = new Guest("admin", "12345", "Admin", "User");
		mySystem.setCurrentUser(testPerson);
		assertEquals("Administrator shouldn't make a bid successfully!", false, mySystem.makeBid(cellPhone, 100.23));
	}
	
	/**
	 * This method test whether the Bid would be rejected if the user is a non-profit user.
	 * Test method for {@link TestSystem.testSystem#makeBid(checkIn4.Item, double)}.
	 */
	@Test
	public void testNonProMakeBid() {
		testPerson = new NonProfitUser("nonProfit", "54321", "non-pro", "User", "my org.");
		mySystem.setCurrentUser(testPerson);
		assertEquals("NonProfitUser shouldn't banke a bid successfully!", false, mySystem.makeBid(cellPhone, 100.23));
	}
	
	/**
	 * This method test whether the Bid would be rejected if the user is a auction central staff.
	 * Test method for {@link TestSystem.testSystem#makeBid(checkIn4.Item, double)}.
	 */
	@Test
	public void testACStaffMakeBid() {
		testPerson = new AuctionCentralStaff("Auction", "11111", "Auction Staff", "User");
		mySystem.setCurrentUser(testPerson);
		assertEquals("AuctionCentralStaff shouldn't banke a bid successfully!", false, mySystem.makeBid(cellPhone, 100.23));
	}

}
