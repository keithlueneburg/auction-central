package user;

import auction.Bid;

import bidding.Address;
import bidding.CreditCard;

import java.util.ArrayList;
import java.util.List;

/**
 * User type for a user who may bid on items through the "online" sealed bidding system.
 * 
 * @author Kaiyuan Shi
 * 
 * @version 3/2/2014
 *
 */
public class Bidder extends AbstractUser {
  private boolean my_is_register = false;
  private CreditCard my_card;
  private Address my_address;
  private List<Bid> my_bids;

  public Bidder(final String a_user_name, final String a_password, final String a_first_name,
      final String a_last_name) {
    super(a_user_name, a_password, a_first_name, a_last_name);
    my_bids = new ArrayList<Bid>();
  }
  
  public CreditCard getCard() {
    return my_card;
  }

  public void setCard(final CreditCard a_card) {
    my_card = a_card;
  }

  public Address getAddress() {
    return my_address;
  }

  public void setAddress(final Address an_address) {
    my_address = an_address;
  }

  public List<Bid> getBids() {
    return my_bids;
  }

  public void addBid(final Bid a_bid) {
    my_bids.add(a_bid);
  }
  
  public boolean isRegisiter() {
    return my_is_register;
  }
  
  public boolean regisiter(final CreditCard a_card, final Address an_address) {
    my_card = a_card;
    my_address = an_address;
    my_is_register = true;
    return my_is_register;
  }
}
