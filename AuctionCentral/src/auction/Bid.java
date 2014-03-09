package auction;

import bidding.CreditCard;

import java.util.Calendar;
import java.util.Date;

import user.Bidder;

public class Bid {
  /**
   * Line separator.
   */
  private static final String LINE_SEPARATOR = "\n";
  
  private String my_item_name;
  private double my_price;
  private String my_bidder_name;
  private Calendar my_bid_time;
  private CreditCard my_payment;

  public Bid(final String an_item_name, final double a_price, final String a_bidder_name,
      final Calendar a_bid_time, final CreditCard a_payment) {
    my_item_name = an_item_name;
    my_bidder_name = a_bidder_name;
    my_price = Double.valueOf(String.format("%.2f", a_price));
    my_bid_time = a_bid_time;
    my_payment = a_payment;
  }

  public String getItemName() {
    return my_item_name;
  }
  
  public double getPrice() {
    return my_price;
  }
  
  public String getBidderName() {
    return my_bidder_name;
  }
  
  public Calendar getBidTime() {
    return my_bid_time;
  }
  
  public CreditCard getPayment() {
    return my_payment;
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
