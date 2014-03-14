/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bidding.Address;
import bidding.CreditCard;
import user.Bidder;

/**
 * @author Kaiyuan Shi
 * @version Win. 2014
 * This test class test the Bidder class
 */
public class BidderTest {

  Bidder my_test_bidder;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    my_test_bidder = new Bidder("username", "password", "firstname", "lastname");
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * This method test whether the bidder was registered correctly.
   * Test method for {@link user.Bidder#register(bidding.CreditCard, bidding.Address)}.
   */
  @Test
  public void testRegister() {
    long card_num = Long.valueOf("1234567890123456").longValue();
    Calendar exp_date = Calendar.getInstance();
    exp_date.set(2015, 12, 31);
    Address test_address = new Address("street", 0, "city", "state", 98401);
    CreditCard test_card= new CreditCard(card_num, exp_date, 123,
        "card holder", test_address, "test bank");
    my_test_bidder.register(test_card, test_address);
    assertTrue(my_test_bidder.isRegistered());
    assertEquals(test_address, my_test_bidder.getAddress());
    assertEquals(test_card, my_test_bidder.getCard());
  }

  /**
   * This method test whether the bidder's card was expired correctly
   * Test method for {@link user.Bidder#cardExpired()}.
   */
  @Test
  public void testCardExpired() {
    long card_num = Long.valueOf("1234567890123456").longValue();
    Calendar exp_date = Calendar.getInstance();
    exp_date.set(2015, 12, 31);
    Address test_address = new Address("street", 0, "city", "state", 98401);
    CreditCard test_card= new CreditCard(card_num, exp_date, 123,
        "card holder", test_address, "test bank");
    my_test_bidder.register(test_card, test_address);
    my_test_bidder.cardExpired();
    assertFalse(my_test_bidder.isRegistered());
    assertNull(my_test_bidder.getAddress());
    assertNull(my_test_bidder.getCard());
  }

}
