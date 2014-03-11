package test;

import auction.Auction;
import auction.Item;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuctionTest {
  
  Auction testAuction;
  Item testItem;

  @Before
  public void setUp() throws Exception {
    testAuction = new Auction();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testAuctionStringStringStringStringCalendarIntString() {
    Auction testAuction2 = new Auction("", "", "", "", new GregorianCalendar(), 0, "");
    assertTrue(testAuction2 != null);
  }

  @Test
  public void testAuction() {
    assertTrue(testAuction != null);
  }

  @Test
  public void testAddItem() {
    testAuction = new Auction();
    
    testItem = new Item();
    testAuction.addItem(testItem);
    assertEquals(1, testAuction.getItemCount());
  }

  @Test
  public void testDeleteItem() {
    Item testItem1 = new Item();
    Item testItem2 = new Item();
    testAuction.addItem(testItem1);
    testAuction.addItem(testItem2);
    assertEquals(2, testAuction.getItemCount());
    testAuction.deleteItem(testItem2);
    assertEquals(1, testAuction.getItemCount());
  }

}
