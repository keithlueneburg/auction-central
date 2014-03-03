package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import auction.Bid;
import auction.Item;

/**
 * This class tests the Item class. It only tests the constructors and methods
 * which are not setters or getters.
 * 
 * @author Kevin Alexander
 * @version 2/24/2014
 */
public class ItemTest {

  Item testItem;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    testItem = new Item();
    testItem.setItemName("Test Item");
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for
   * {@link backend.Item#Item(int, java.lang.String, int, double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
   * .
   */
  @Test
  public void testItemIntStringIntDoubleStringStringStringStringStringString() {
    assertTrue(new Item(0, "", 0, 0.0, "", "", "", "", "", "") != null);
  }

  /**
   * Test method for {@link backend.Item#Item()}.
   */
  @Test
  public void testItem() {
    assertTrue(new Item() != null);
  }

  /**
   * Test method for {@link backend.Item#addBid(backend.Bid)}.
   */
  @Test
  public void testAddBid() {
    testItem.addBid(new Bid("Bid1"));
    testItem.addBid(new Bid("Bid2"));
    testItem.addBid(new Bid("Bid3"));

    assertTrue(testItem.getBids() != null);
  }

  /**
   * Test method for {@link backend.Item#removeBid(backend.Bid)}.
   */
  @Test
  public void testRemoveBid() {
    Bid bid1 = new Bid("Bid1");
    Bid bid2 = new Bid("Bid2");
    Bid bid3 = new Bid("Bid3");

    testItem.addBid(bid1);
    testItem.addBid(bid2);
    testItem.addBid(bid3);

    testItem.removeBid(bid3);
    testItem.removeBid(bid1);
    testItem.removeBid(bid2);

    assertTrue(testItem.getBids() != null);
  }

  /**
   * Test method for
   * {@link backend.Item#unsealBid(java.lang.String, java.lang.String)}.
   */
  @Test
  public void testUnsealBid() {
    Bid bid1 = new Bid("Bid1");
    Bid bid2 = new Bid("Bid2");
    
    int correctOutcomes = 0;

    testItem.addBid(bid1);
    testItem.addBid(bid2);

    if (testItem.unsealBid("admin", "password") == bid1) {
      correctOutcomes++;
    }

    if (testItem.unsealBid("admin", "passwrd") == null) {
      correctOutcomes++;
    }

    assertEquals(2, correctOutcomes);
  }

}