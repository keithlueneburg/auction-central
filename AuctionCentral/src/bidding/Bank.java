package bidding;

import auction.Item;

/**
 * Class: Bank
 * 
 * Represents a Bank that will process the bills and payments.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * 
 * University of Washington, Tacoma
 * 
 * Winter 2014
 * 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Casey Morrison
 * 
 * @author Keith Lueneburg (Minor changes)
 * 
 * @version 1.0 Winter 2014
 */
public final class Bank {

  /** Used as the bank name. */
  private String my_name;

  /**
   * Create a new Bank object.
   * <p>
   * <dt><b> Precondition: Valid parameters are passed to constructor. </b>
   * <dd>
   * <dt><b> Postcondition: The Bank has been initialized properly. </b>
   * <dd>
   * 
   * @param a_name
   *          The name of the bank.
   * 
   */
  public Bank(final String a_name) {
    my_name = a_name;
  }

  /**
   * Get the bank's name.
   * 
   * @return the bank's name.
   * 
   * @author Keith Lueneburg
   */
  private String getName() {
    return my_name;
  }

  /**
   * Pays a bill for a bidder.
   * <p>
   * <dt><b> Precondition: Valid parameters are passed to payment. </b>
   * <dd>
   * <dt><b> Postcondition: The Bill has been initialized properly. </b>
   * <dd>
   * 
   * @param an_item
   *          The item.
   * @param a_price
   *          The price of the bill.
   * @param a_card
   *          The card used to pay.
   * @return bill, a Bill used to charge a bidder.
   */
  public Bill makePayment(final Item an_item, final double a_price,
      final CreditCard a_card) {
    final Bill bill = new Bill(an_item, a_price, a_card);
    return bill;
  }

  /**
   * Check for equality with another Bank.
   * <p>
   * <dt><b> Precondition: Bank has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The Bank has not been changed. </b>
   * <dd>
   * 
   * @param an_object
   *          The object to check equality with.
   * 
   * @return true if the other object is an identical bank
   * 
   * @author Keith Lueneburg
   */
  @Override
  public boolean equals(final Object an_object) {
    boolean is_equal = true;

    // Check for null object
    if (an_object == null) {
      is_equal = false;

      // Check for correct object type
    } else if (!(an_object instanceof Bank)) {
      is_equal = false;
    } else {

      // Cast other object to a Bank
      final Bank other = (Bank) an_object;

      // Compare field by field for equality
      if (!my_name.equals(other.getName())) {
        is_equal = false;
      }
    }
    return is_equal;
  }

  /**
   * Hash code for bank.
   * <p>
   * <dt><b> Precondition: Bank has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The Bank has not been changed. </b>
   * <dd>
   * 
   * @return the bank's hash code
   * 
   * @author Keith Lueneburg
   */
  @Override
  public int hashCode() {
    return my_name.hashCode();
  }

  /**
   * String representation of bank.
   * <p>
   * <dt><b> Precondition: Bank has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The Bank has not been changed. </b>
   * <dd>
   * 
   * @return string representing the bank.
   * 
   * @author Keith Lueneburg
   */
  public String toString() {
    return my_name;
  }
}
