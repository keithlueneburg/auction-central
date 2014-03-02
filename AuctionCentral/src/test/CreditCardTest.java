package test;

import static org.junit.Assert.*;

import bidding.Address;
import bidding.Bank;
import bidding.CreditCard;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * Class: CreditCardTest
 * 
 * Contains tests for class: CreditCard
 * 
 * TCSS 360 - Software Development and Quality Assurance University of
 * Washington, Tacoma Winter 2014 Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 * @version 2/26/2014
 * 
 */
public class CreditCardTest {

  /**
   * Line separator.
   */
  private static final String LINE_SEPARATOR = "\n";

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

    card_1 = new CreditCard(1234123412341234L, new GregorianCalendar(2015, 1,
        15), 123, "John Smith", new Address("123 Main Street", 0, "Tacoma",
        "WA", 98501, null), new Bank("Chase"));

    card_1_duplicate = new CreditCard(1234123412341234L, new GregorianCalendar(
        2015, 1, 15), 123, "John Smith", new Address("123 Main Street", 0,
        "Tacoma", "WA", 98501, null), new Bank("Chase"));
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address, java.lang.String)}
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
    assertTrue("Fail equals itself", card_1.equals(card_1));

    // Object should equal an identical object with same field values
    // TODO: This currently fails due to Address and Bank not being
    // implemented yet.

    assertTrue("Fail equals identical", card_1.equals(card_1_duplicate));

    // Should not equal a null object
    final CreditCard null_card = null;
    assertFalse("Fail equals null", card_1.equals(null_card));

    // Should not equal a different type of object
    assertFalse("Fail equals different type",
        card_1.equals(new StringBuilder()));

    // Should not equal a card with different fields:

    // Different card number:
    assertFalse("Fail different card number", card_1.equals(new CreditCard(
        4567123412341234L, new GregorianCalendar(2015, 1, 15), 123,
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501,
            null), new Bank("Chase"))));

    // Different expiration date:
    assertFalse("Fail different expiration date", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2238, 1, 15), 123,
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501,
            null), new Bank("Chase"))));

    // Different CSC
    assertFalse("Fail different CSC", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 456,
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501,
            null), new Bank("Chase"))));

    // Different name
    assertFalse("Fail different name", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 123,
        "Dave Jones", new Address("123 Main Street", 0, "Tacoma", "WA", 98501,
            null), new Bank("Chase"))));

    // Different Address
    assertFalse("Fail different address", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 123,
        "John Smith", new Address("255 Broadway", 0, "Tacoma", "WA", 98501,
            null), new Bank("Chase"))));

    // Different Bank
    assertFalse("Fail different bank", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 123,
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501,
            null), new Bank("Bank of America"))));
  }

  /**
   * Test method for {@link bidding.CreditCard#toString()}.
   */
  @Test
  public void testToString() {
    final String test_credit_card_string = "Cardholder: John Smith"
        + LINE_SEPARATOR + "Card Number: 1234123412341234" + LINE_SEPARATOR
        + "Expiration Date: 2125" + LINE_SEPARATOR + "CSC: 123"
        + LINE_SEPARATOR + "Address:" + LINE_SEPARATOR + "123 Main Street"
        + LINE_SEPARATOR + "Tacoma WA 98501" + LINE_SEPARATOR + LINE_SEPARATOR
        + "Bank: Chase";
    
    System.out.println(card_1);
    System.out.println(test_credit_card_string);
    System.out.println(card_1.toString().equals(test_credit_card_string));
    assertTrue("toString not equal", card_1.toString().equals(test_credit_card_string));
  }

}
