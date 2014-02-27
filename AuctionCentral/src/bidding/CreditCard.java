package bidding;

import java.util.Date;

/**
 * Class: CreditCard
 * 
 * Represents a credit card that a bidder will use to pay for a item that they
 * win the bidding for.
 * 
 * TCSS 360 - Software Development and Quality Assurance University of
 * Washington, Tacoma Winter 2014 Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 * @version 2/26/2014
 * 
 */
public final class CreditCard {
  /**
   * Acceptable length of credit card number.
   */
  private static final int CARD_NUMBER_LENGTH = 16;

  /**
   * Acceptable length of CSC code.
   */
  private static final int CSC_LENGTH = 3;

  /**
   * The 16 digit card number.
   */
  private long my_card_num;

  /**
   * Card expiration date.
   */
  private Date my_expiration_date;

  /**
   * The 3 digit card security code.
   */
  private int my_csc;

  /**
   * The name of the card holder.
   */
  private String my_card_holder;

  /**
   * The card holder's address.
   */
  private Address my_address;

  /**
   * The bank this card belongs to.
   */
  private Bank my_bank;

  /**
   * Create a new CreditCard object.
   * <p>
   * <dt><b> Precondition: Valid parameters are passed to constructor. </b>
   * <dd>
   * <dt><b> Postcondition: The CreditCard is initialized properly. </b>
   * <dd>
   * 
   * @param a_card_num
   *          The card number.
   * @param an_exp_date
   *          The expiration date.
   * @param a_csc
   *          The CSC code.
   * @param a_card_holder
   *          The card holder.
   * @param an_address
   *          The card holder's address.
   * @param a_bank
   *          The bank the card belongs to.
   * 
   * @throws IllegalArgumentException
   *           Throws exception in the case of invalid parameters.
   */
  public CreditCard(final long a_card_num, final Date an_exp_date,
      final int a_csc, final String a_card_holder, final Address an_address,
      final Bank a_bank) {

    // Validation is handled by individual setters
    setCardNum(a_card_num);
    setExpDate(an_exp_date);
    setCSC(a_csc);
    setCardHolder(a_card_holder);
    setAddress(an_address);
    setBank(a_bank);
  }

  /**
   * @return The card number.
   */
  private long getCardNum() {
    return my_card_num;
  }

  /**
   * @return The expiration date.
   */
  private Date getExpDate() {

    // TODO : return a defensive copy here
    return my_expiration_date;
  }

  /**
   * @return The CSC code.
   */
  private int getCSC() {
    return my_csc;
  }

  /**
   * @return The card holder name.
   */
  private String getCardHolder() {
    return my_card_holder;
  }

  /**
   * @return The address.
   */
  private Address getAddress() {

    // TODO : return a defensive copy once Address is complete.
    return my_address;
  }

  /**
   * @return The bank.
   */
  private Bank getBank() {
    return my_bank;
  }

  /**
   * @param a_card_num
   *          The card number to set.
   */
  private void setCardNum(final long a_card_num) {
    if ((new Long(a_card_num)).toString().length() != CARD_NUMBER_LENGTH) {
      throw new IllegalArgumentException("Invalid card number length");
    } else {
      this.my_card_num = a_card_num;
    }
  }

  /**
   * @param an_expiration_date
   *          The expiration date to set.
   */
  private void setExpDate(final Date an_expiration_date) {

    // TODO : make defensive copy
    this.my_expiration_date = an_expiration_date;
  }

  /**
   * @param a_csc
   *          The CSC to set.
   */
  private void setCSC(final int a_csc) {
    if ((new Integer(a_csc)).toString().length() != CSC_LENGTH) {
      throw new IllegalArgumentException("Invalid CSC length");
    } else {
      this.my_csc = a_csc;
    }
  }

  /**
   * <p>
   * <dt><b> Precondition: Card holder name is not blank. </b>
   * <dd>
   * <dt><b> Postcondition: Name is set. </b>
   * <dd>
   * 
   * @param a_card_holder
   *          The card holder name to set.
   */
  private void setCardHolder(final String a_card_holder) {
    if (a_card_holder.length() == 0) {
      throw new IllegalArgumentException("Name cannot be blank");
    } else {
      this.my_card_holder = a_card_holder;
    }
  }

  /**
   * @param an_address
   *          The address to set.
   */
  private void setAddress(final Address an_address) {

    // TODO : make defensive copy
    this.my_address = an_address;
  }

  /**
   * @param a_bank
   *          The bank name to set.
   */
  private void setBank(final Bank a_bank) {

    // TODO : make defensive copy
    this.my_bank = a_bank;
  }

  /**
   * Check to see if this CreditCard matches another CreditCard.
   * <p>
   * <dt><b> Precondition: CreditCard has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The CreditCard has not been changed. </b>
   * <dd>
   * 
   * @param an_object
   *          The object to compare this CreditCard with.
   * 
   * @return True if the other object is also a CreditCard, and it represents
   *         the same card as the card we are checking.
   */
  @Override
  public boolean equals(final Object an_object) {
    boolean is_equal = true;

    // Check for null object
    if (an_object == null) {
      is_equal = false;

      // Check for correct object type
    } else if (!(an_object instanceof CreditCard)) {
      is_equal = false;
    } else {

      // Cast other object to a CreditCard
      final CreditCard other = (CreditCard) an_object;

      // Compare field by field for equality
      if (!my_card_holder.equals(other.getCardHolder())) {
        is_equal = false;
      } else if (my_card_num != other.getCardNum()) {
        is_equal = false;
      } else if (!my_expiration_date.equals(other.getExpDate())) {
        is_equal = false;
      } else if (my_csc != other.getCSC()) {
        is_equal = false;
      } else if (!my_address.equals(other.getAddress().toString())) {
        is_equal = false;
      } else if (!my_bank.equals(other.getBank())) {
        is_equal = false;
      }
    }

    return is_equal;
  }

  /**
   * Get a String representation of the CreditCard.
   * <p>
   * <dt><b> Precondition: CreditCard has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The CreditCard has not been changed. </b>
   * <dd>
   * 
   * @return A string representation of the card.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Cardholder: ");
    sb.append(my_card_holder);
    sb.append("\n");
    sb.append("Card Number: ");
    sb.append(my_card_num);
    sb.append("\n");
    sb.append("Expiration Date: ");
    sb.append(my_expiration_date.getMonth() + "/" + my_expiration_date.getDay() + "/" 
        + my_expiration_date.getYear());
    sb.append("\n");
    sb.append("CSC: ");
    sb.append(my_csc);
    sb.append("\n");
    sb.append("Address: ");

    // TODO : once Address toString() is implemented, use that here!
    sb.append("PLACEHOLDER ADDRESS");

    sb.append("\n");
    sb.append("Bank: ");
    // TODO : once Address toString() is implemented, use that here!
    sb.append("PLACEHOLDER BANK");
    sb.append("\n");

    return sb.toString();
  }
}
