package bidding;

import auction.Item;

import java.util.Date;


/**
 * 
 * Represents a bill that a the Auction Central Staff will use to pay for a
 * charge a bidder for the item they win in the auction.
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
 * @version 1.0 Winter 2014
 */
public final class Bill {
  /**
   * Line separator.
   */
  private static final String LINE_SEPARATOR = "\n";
  
  /** The item that was purchased by the bidder. */
  private Item my_item;

  /** The price of the item. */
  private double my_price;

  /** The date of the transaction. */
  private Date my_date;

  /** The payment method, credit card. */
  private CreditCard my_payment;

  /**
   * Create a new Bill object.
   * <p>
   * <dt><b> Precondition:</b> Valid parameters are passed to constructor.
   * <dd>
   * <dt><b> Postcondition:</b> The Bill has been initialized properly.
   * <dd>
   * 
   * @param an_item
   *          The item.
   * @param a_price
   *          The price of the item.
   * @param a_payment
   *          The payment method.
   */
  public Bill(final Item an_item, final double a_price, final CreditCard a_payment) {
    my_item = an_item;
    my_price = a_price;
    my_date = new Date();
    my_payment = a_payment;
  }

  // might not need getters and setters....
  /**
   * Get the item object.
   * 
   * @return the myItem.
   */
  public Item getMyItem() {
    return my_item;
  }

  /**
   * Set the item.
   * 
   * @param an_item
   *          the Item to set.
   */
  public void setMyItem(final Item an_item) {
    my_item = an_item;
  }

  /**
   * Gets the price of the item.
   * 
   * @return the myPrice.
   */
  public double getMyPrice() {
    return my_price;
  }

  /**
   * Sets the price of the item.
   * 
   * @param a_price
   *          the Price to set.
   */
  public void setMyPrice(final double a_price) {
    my_price = a_price;
  }

  /**
   * Get the date.
   * 
   * @return the myDate.
   */
  public Date getMyDate() {
    return my_date;
  }

  /**
   * Set the date.
   * 
   * @param a_date
   *          the Date to set.
   */
  public void setMyDate(final Date a_date) {
    my_date = a_date;
  }

  /**
   * Get the payment.
   * 
   * @return the myPayment.
   */
  public CreditCard getMyPayment() {
    return my_payment;
  }

  /**
   * Set the payment.
   * 
   * @param a_payment
   *          the Payment to set.
   */
  public void setMyPayment(final CreditCard a_payment) {
    my_payment = a_payment;
  }

  /**
   * Get a printout of the Bill.
   * <p>
   * <dt><b> Precondition:</b> Bill has been initialized.
   * <dd>
   * <dt><b> Postcondition:</b> The Bill has not been changed.
   * <dd>
   */
  public void printBill() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Bill: \n----------------------\n");
    sb.append("Item: ");
    sb.append(my_item);
    sb.append(LINE_SEPARATOR);
    sb.append("Date: ");
    sb.append(my_date);
    sb.append(LINE_SEPARATOR);
    sb.append("Price: ");
    sb.append(my_price);
    sb.append(LINE_SEPARATOR);
    sb.append("Payment Information: ");
    sb.append(my_payment);
    sb.append("\n----------------------");

    System.out.println(sb);
  }
}
