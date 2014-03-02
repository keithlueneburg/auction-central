package test;

import static org.junit.Assert.*;

import bidding.Address;
import bidding.Bank;
import bidding.CreditCard;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;




/**
 * Class: CreditCardTest
 * 
 * Contains tests for class: CreditCard
 * 
 * TCSS 360 - Software Development and Quality Assurance 
 * University of Washington, Tacoma 
 * Winter 2014 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 * @version 2/26/2014
 * 
 */
public class CreditCardTest {

  /**
   * Test credit card.
   */
  private static CreditCard card_1;

  /**
   * Test credit card, identical to 'card_1'.
   */
  private static CreditCard card_1_duplicate;

  /**
   * Setup up prior to each test.
   */
  @Before
  public void setup() {

    card_1 = new CreditCard(1234123412341234L, new Date(2015, 1, 15), 123,
        "John Smith", new Address(), new Bank());

    card_1_duplicate = new CreditCard(1234123412341234L, new Date(2015, 1, 15),
        123, "John Smith", new Address(), new Bank());
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, 
   *    bidding.Address, java.lang.String)}
   * .
   */
  @Test
  public void testCreditCard() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link bidding.CreditCard#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    // Object should equal itself
    assertTrue(card_1.equals(card_1));

    // Object should equal an identical object with same field values
    // TODO: This currently fails due to Address and Bank not being
    // implemented yet.

    assertTrue(card_1.equals(card_1_duplicate));

    // Should not equal a null object
    final CreditCard null_card = null;
    assertFalse(card_1.equals(null_card));

    // Should not equal a different type of object
    assertFalse(card_1.equals(new StringBuilder()));

    // Should not equal a card with different fields:

    // Different card number:
    assertFalse(card_1.equals(new CreditCard(4567123412341234L, new Date(2015,
        1, 15), 123, "John Smith", new Address(), new Bank())));

    // Different expiration date:
    assertFalse(card_1.equals(new CreditCard(1234123412341234L, new Date(2238,
        1, 15), 123, "John Smith", new Address(), new Bank())));

    // Different CSC
    assertFalse(card_1.equals(new CreditCard(1234123412341234L, new Date(2015,
        1, 15), 456, "John Smith", new Address(), new Bank())));

    // Different CSC
    assertFalse(card_1.equals(new CreditCard(1234123412341234L, new Date(2015,
        1, 15), 123, "Dave Jones", new Address(), new Bank())));

    // Different Address

    // TODO: Test once Address implemented

    // Different Bank

    // TODO: Test once Bank implemented
  }

  /**
   * Test method for {@link bidding.CreditCard#toString()}.
   */
  @Test
  public void testToString() {
    fail("Not yet implemented");
  }

}
