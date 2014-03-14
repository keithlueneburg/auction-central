package test;

import static org.junit.Assert.*;

import bidding.Address;
import bidding.CreditCard;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
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
  private static final String LINE_DELIM = "\n";

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

    card_1 = new CreditCard(1234123412341234L, new GregorianCalendar(2015, 1, 15),
        123, "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501), 
        "Chase");

    card_1_duplicate = new CreditCard(1234123412341234L, new GregorianCalendar(2015, 1, 15), 
        123, "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501), 
        "Chase");
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address, 
   * java.lang.String)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreditCardInvalidCardNumber() {
    final CreditCard bad_param_card = new CreditCard(1234L,
        new GregorianCalendar(2015, 1, 15), 123, "John Smith", new Address(
            "123 Main Street", 0, "Tacoma", "WA", 98501), 
            "Chase");
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address
   * , java.lang.String)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreditCardExpirationDateNull() {
    final CreditCard bad_param_card = new CreditCard(1234123412341234L, null,
        123, "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA",
            98501), "Chase");
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address,
   *  java.lang.String)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreditCardExpiredCard() {
    final CreditCard bad_param_card = new CreditCard(1234123412341234L,
        new GregorianCalendar(2012, 1, 15), 123, "John Smith", new Address(
            "123 Main Street", 0, "Tacoma", "WA", 98501), "Chase");
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address,
   *  java.lang.String)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreditCardInvalidCSC() {
    final CreditCard bad_param_card = new CreditCard(1234123412341234L,
        new GregorianCalendar(2015, 1, 15), 5, "John Smith", new Address(
            "123 Main Street", 0, "Tacoma", "WA", 98501), "Chase");
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address,
   *  java.lang.String)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreditCardNoCardHolderName() {
    final CreditCard bad_param_card = new CreditCard(1234123412341234L,
        new GregorianCalendar(2015, 1, 15), 123, "", new Address(
            "123 Main Street", 0, "Tacoma", "WA", 98501), "Chase");
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address,
   *  java.lang.String)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreditCardNullAddress() {
    final CreditCard bad_param_card = new CreditCard(1234123412341234L,
        new GregorianCalendar(2015, 1, 15), 123, "John Smith", null, 
            "Chase");
    assertFalse("Passing incompatible object to equals should return false",
        bad_param_card.getAddress() == null);
  }

  /**
   * Test method for
   * {@link bidding.CreditCard#CreditCard(long, int, int, java.lang.String, bidding.Address, 
   * java.lang.String)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreditCardNullBank() {
    final CreditCard bad_param_card = new CreditCard(1234123412341234L,
        new GregorianCalendar(2015, 1, 15), 123, "John Smith", new Address(
            "123 Main Street", 0, "Tacoma", "WA", 98501), null);
    assertFalse("Passing incompatible object to equals should return false",
        bad_param_card == null);
  }

  /**
   * Test method for {@link bidding.CreditCard#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    // Object should equal itself
    assertTrue("Fail equals itself", card_1.equals(card_1)
        && card_1.hashCode() == card_1.hashCode());

    // Object should equal an identical object with same field values
    assertTrue("Fail equals identical", card_1.equals(card_1_duplicate)
        && card_1.hashCode() == card_1_duplicate.hashCode());

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
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501), 
        "Chase")));

    // Different expiration date:
    assertFalse("Fail different expiration date", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2238, 1, 15), 123,
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501), "Chase")));

    // Different CSC
    assertFalse("Fail different CSC", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 456,
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501), "Chase")));

    // Different name
    assertFalse("Fail different name", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 123,
        "Dave Jones", new Address("123 Main Street", 0, "Tacoma", "WA", 98501), "Chase")));

    // Different Address
    assertFalse("Fail different address", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 123,
        "John Smith", new Address("255 Broadway", 0, "Tacoma", "WA", 98501), "Chase")));

    // Different Bank
    assertFalse("Fail different bank", card_1.equals(new CreditCard(
        1234123412341234L, new GregorianCalendar(2015, 1, 15), 123,
        "John Smith", new Address("123 Main Street", 0, "Tacoma", "WA", 98501), 
        "Bank of America")));
  }

  /**
   * Test method for {@link bidding.CreditCard#toString()}.
   */
  @Test
  public void testToString() {
    final String test_credit_card_string = "Cardholder: John Smith"
        + LINE_DELIM + "Card Number: 1234123412341234" + LINE_DELIM
        + "Expiration Date: 2125" + LINE_DELIM + "CSC: 123"
        + LINE_DELIM + "Address:" + LINE_DELIM + "123 Main Street"
        + LINE_DELIM + "Tacoma WA 98501" + LINE_DELIM + LINE_DELIM
        + "Bank: Chase";

    assertTrue("toString not equal",
        card_1.toString().equals(test_credit_card_string));
  }

}
