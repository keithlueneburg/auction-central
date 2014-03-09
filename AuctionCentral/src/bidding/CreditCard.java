package bidding;

import java.util.Calendar;

/**
 * Class: CreditCard
 * 
 * Represents a credit card that a bidder will use to pay for a item that they
 * win the bidding for.
 * 
 * TCSS 360 - Software Development and Quality Assurance 
 * 
 * University of Washington, Tacoma 
 * 
 * Winter 2014 
 * 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 * @version 3/2/2014
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
   * Character for use in String representation of dates (i.e. 3/2/2014)
   */
  private static final int DATE_SEPARATOR_CHARACTER = '/';

  /**
   * Line separator.
   */
  private static final String LINE_SEPARATOR = "\n";

  /**
   * The 16 digit card number.
   */
  private long my_card_num;

  /**
   * Card expiration date.
   */
  private Calendar my_expiration_date;

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
  private String my_bank;

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
  public CreditCard(final long a_card_num, final Calendar an_exp_date,
      final int a_csc, final String a_card_holder, final Address an_address,
      final String a_bank) throws IllegalArgumentException {

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
  public long getCardNum() {
    return my_card_num;
  }

  /**
   * @return The expiration date.
   */
  public Calendar getExpDate() {

    // TODO : return a defensive copy here
    return my_expiration_date;
  }

  /**
   * @return The CSC code.
   */
  public int getCSC() {
    return my_csc;
  }

  /**
   * @return The card holder name.
   */
  public String getCardHolder() {
    return my_card_holder;
  }

  /**
   * @return The address.
   */
  public Address getAddress() {

    // TODO : return a defensive copy once Address is complete.
    return my_address;
  }

  /**
   * @return The bank.
   */
  public String getBank() {
    return my_bank;
  }

  /**
   * @param a_card_num
   *          The card number to set.
   * 
   * @throws IllegalArgumentException
   *           Throws exception in the case of wrong card number length.
   */
  private void setCardNum(final long a_card_num) throws IllegalArgumentException {
    if ((new Long(a_card_num)).toString().length() != CARD_NUMBER_LENGTH) {
      throw new IllegalArgumentException("Card number must be 16 digits.");
    } else {
      my_card_num = a_card_num;
    }
  }

  /**
   * @param an_expiration_date
   *          The expiration date to set.
   * 
   * @throws IllegalArgumentException
   *           Throws exception in the case of invalid parameters.
   */
  private void setExpDate(final Calendar an_expiration_date) throws IllegalArgumentException {
    if (an_expiration_date == null) {
      throw new IllegalArgumentException("Expiration date cannot be null.");
    } else if (an_expiration_date.compareTo(Calendar.getInstance()) <= 0) {
      throw new IllegalArgumentException("Expiration date must be in the future.");
    }
    // TODO : make defensive copy
    my_expiration_date = an_expiration_date;
  }

  /**
   * @param a_csc
   *          The CSC to set.
   * 
   * @throws IllegalArgumentException
   *           Throws exception in the case of invalid parameters.
   */
  private void setCSC(final int a_csc) throws IllegalArgumentException {
    if ((new Integer(a_csc)).toString().length() != CSC_LENGTH) {
      throw new IllegalArgumentException("Invalid CSC length");
    } else {
      my_csc = a_csc;
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
   * 
   * @throws IllegalArgumentException
   *           Throws exception in the case of invalid parameters.
   */
  private void setCardHolder(final String a_card_holder) throws IllegalArgumentException {
    if (a_card_holder.length() == 0) {
      throw new IllegalArgumentException("Name cannot be blank");
    } else {
      my_card_holder = a_card_holder;
    }
  }

  /**
   * @param an_address
   *          The address to set.
   * 
   * @throws IllegalArgumentException
   *           Throws exception in the case of invalid parameters.
   */
  private void setAddress(final Address an_address) throws IllegalArgumentException {
    if (an_address == null) {
      throw new IllegalArgumentException("Address cannot be null.");
    } else {
      // TODO : make defensive copy
      my_address = an_address;
    }
  }

  /**
   * @param a_bank
   *          The bank name to set.
   * 
   * @throws IllegalArgumentException
   *           Throws exception in the case of invalid parameters.
   */
  private void setBank(final String a_bank) throws IllegalArgumentException {
    if (a_bank == null) {
      throw new IllegalArgumentException("Bank cannot be null.");
    } else {
      // TODO : make defensive copy
      my_bank = a_bank;
    }
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
   * @return true if the other object is also a CreditCard, and it represents
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
      } else if (!my_address.toString().equals(other.getAddress().toString())) {
        is_equal = false;
      } else if (!my_bank.equals(other.getBank())) {
        is_equal = false;
      }
    }
    return is_equal;
  }

  /**
   * Hash code for CreditCard.
   * <p>
   * <dt><b> Precondition: CreditCard has been initialized. </b>
   * <dd>
   * <dt><b> Postcondition: The CreditCard has not been changed. </b>
   * <dd>
   * 
   * @return the item's hashcode
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
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
    sb.append(LINE_SEPARATOR);
    sb.append("Card Number: ");
    sb.append(my_card_num);
    sb.append(LINE_SEPARATOR);
    sb.append("Expiration Date: ");
    sb.append(my_expiration_date.get(Calendar.MONTH) + DATE_SEPARATOR_CHARACTER
        + my_expiration_date.get(Calendar.DAY_OF_MONTH)
        + DATE_SEPARATOR_CHARACTER + my_expiration_date.get(Calendar.YEAR));
    sb.append(LINE_SEPARATOR);
    sb.append("CSC: ");
    sb.append(my_csc);
    sb.append(LINE_SEPARATOR);
    
    sb.append(my_address);

    sb.append(LINE_SEPARATOR);
    sb.append("Bank: ");
    
    sb.append(my_bank);
    

    return sb.toString();
  }
}
