package auction;

import bidding.CreditCard;

import java.util.Date;

import user.Bidder;

public class Bid {
  /**
   * Line separator.
   */
  private static final String LINE_SEPARATOR = "\n";
  
  private Item my_item;
  private double my_price;
  private Bidder my_bidder;
  private Date my_bid_time;
  private CreditCard my_payment;

  public Bid(final Item an_item, final double a_price, final Bidder a_bidder,
      final CreditCard a_payment) {
    my_item = an_item;
    my_bidder = a_bidder;
    my_price = Double.valueOf(String.format("%.2f", a_price));
    my_bid_time = new Date();
    my_payment = a_payment;
  }

  @Override
  public String toString() {
    String ret = "a bid has been made by: ";
    ret += my_bidder.getFirstName() + " ";
    ret += my_bidder.getLastName() + LINE_SEPARATOR;
    ret += "Bidded item: " + my_item.toString() + LINE_SEPARATOR;
    ret += "Bid time: " + my_bid_time.toString() + LINE_SEPARATOR;
    ret += "Payment information:\n" + my_payment;
    return ret;
  }
}
