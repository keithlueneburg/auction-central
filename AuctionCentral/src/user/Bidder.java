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
  
  /** If it is getting registered. */
  private boolean my_is_register;
  
  /** The credit card attached to the bidder. */
  private CreditCard my_card;
  
  /** The address attached to the bidder. */
  private Address my_address;
  
  /** The list of bids attached to the bidder. */
  private List<Bid> my_bids;

  /**
   * Creates a new bidder with the parameters.
   * 
   * @param a_user_name - the name of the bidder.
   * @param a_password - the password to log in.
   * @param a_first_name - the bidders first name.
   * @param a_last_name - the bidders last name.
   */
  public Bidder(final String a_user_name, final String a_password, final String a_first_name,
      final String a_last_name) {
    super(a_user_name, a_password, a_first_name, a_last_name);
    my_bids = new ArrayList<Bid>();
  }
  
  /**
   * Gets the credit card.
   * @return my_card - the credit card attached to bidder.
   */
  public CreditCard getCard() {
    return my_card;
  }

  /**
   * Sets the credit card.
   * @param a_card - the card attached to the user.
   */
  public void setCard(final CreditCard a_card) {
    my_card = a_card;
  }

  /**
   * Returns the address of the bidder for billing.
   * @return my_address - the bidders address.
   */
  public Address getAddress() {
    return my_address;
  }

  /**
   * Set the address of this bidder.
   * @param an_address - the address.
   */
  public void setAddress(final Address an_address) {
    my_address = an_address;
  }

  /**
   * Gets the bids of this bidder.
   * @return my_bids - a list of bids.
   */
  public List<Bid> getBids() {
    return my_bids;
  }

  /**
   * Add a bid to the action.
   * @param a_bid - the bid to add.
   */
  public void addBid(final Bid a_bid) {
    my_bids.add(a_bid);
  }
  
  /**
   * Sets the register to true.
   * @return - true if successful.
   */
  public boolean isRegistered() {
    return my_is_register;
  }
  
  /**
   * Register of the user.
   * 
   * @param a_card - the credit card.
   * 
   * @param an_address - the address.
   * 
   * @return - a boolean if it is a register.
   */
  public boolean register(final CreditCard a_card, final Address an_address) {
    my_card = a_card;
    my_address = an_address;
    my_is_register = true;
    return my_is_register;
  }
  
  /**
   * Set's the user's credit card as expired.
   */
  public void cardExpired() {
    my_is_register = false;
    my_card = null;
    my_address = null;
  }
}
