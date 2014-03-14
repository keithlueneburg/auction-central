package auction;

import bidding.CreditCard;

import java.util.Calendar;

/**
 * A bid used by bidders to put on an item.
 * 
 * @author Team Froyo
 * @version 1.o
 */
public class Bid implements Comparable<Bid> {
  
  /** Used for compare. */
  private static final double SMALL = 0.01;
  
  /**The name of the item bid upon.*/
  private String my_item_name;
  
  /**The price of the bid.*/
  private double my_price;
  
  /**The name of the bidder.*/
  private String my_bidder_name;
  
  /**The time of the bid.*/
  private Calendar my_bid_time;
  
  /**The credit card payment of the bid.*/
  private CreditCard my_payment;

  /**
   * Initializes the fields of the bid object.
   * 
   * @param an_item_name the name of the item bid upon
   * @param a_price the price of the bid
   * @param a_bidder_name the name of the bidder
   * @param a_bid_time the time the bid was placed
   * @param a_payment the payment for the bid
   */
  public Bid(final String an_item_name, final double a_price, final String a_bidder_name,
      final Calendar a_bid_time, final CreditCard a_payment) {
    my_item_name = an_item_name;
    my_bidder_name = a_bidder_name;
    my_price = Double.valueOf(String.format("%.2f", a_price));
    my_bid_time = a_bid_time;
    my_payment = a_payment;
  }

  /**
   * Returns the item bid upon.
   * @return The item bid upon
   */
  public String getItemName() {
    return my_item_name;
  }
  
  /**
   * Returns the price of the bid.
   * @return my_price the price of the bid
   */
  public double getPrice() {
    return my_price;
  }
  
  /**
   * Returns the bidder that placed the bid.
   * @return the bidder that placed the bid
   */
  public String getBidderName() {
    return my_bidder_name;
  }
  
  /**
   * Returns the time of the bid.
   * @return the time of the bid
   */
  public Calendar getBidTime() {
    return my_bid_time;
  }
  
  /**
   * Returns the credit card used as payment.
   * @return the credit card used as payment.
   */
  public CreditCard getPayment() {
    return my_payment;
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(final Bid an_another_bid) {
    if ((my_price - an_another_bid.getPrice()) < SMALL) {
      return my_bid_time.compareTo(an_another_bid.getBidTime());
    } else if (my_price > an_another_bid.getPrice()) {
      return -1;
    } else {
      return 1;
    }
  }

  //@Override
  /*public String toString() {
    String ret = "a bid has been made by: ";
    ret += my_bidder.getFirstName() + " ";
    ret += my_bidder.getLastName() + LINE_SEPARATOR;
    ret += "Bidded item: " + my_item.toString() + LINE_SEPARATOR;
    ret += "Bid time: " + my_bid_time.toString() + LINE_SEPARATOR;
    ret += "Payment information:\n" + my_payment;
    return ret;
  }*/
  
}
