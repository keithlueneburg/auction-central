package auction;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import user.NonProfitUser;
/**
 * 
 * @author Kevin Alexander
 * @version 2/28/2014
 *
 */
public class Auction {
  
  /** The auction number, increases per instantiation of an auction class. */
  private static int AUCTION_NUMBER;

  // Used only when adding/removing items
  /**
   * 
   */
  private int my_next_item;
  /**
   * 
   */
  private int my_number;
  /**
   * 
   */
  private String my_auction_name;
  /**
   * 
   */
  private String my_contact_person;
  /**
   * 
   */
  private String my_contact_phone;
  /**
   * 
   */
  private String my_intake_person;
  /**
   * 
   */
  private Calendar my_auction_date;
  /**
   * 
   */
  private int my_auction_duration;
  /**
   * 
   */
  private String my_comments;
  /**
   * 
   */
  private List<Item> my_auction_items;
  /**
   * 
   * @param the_auction_name
   * @param the_contact_person
   * @param the_contact_phone
   * @param the_intake_person
   * @param the_auction_date
   * @param the_auction_duration
   * @param the_comments
   */
  public Auction(final String the_auction_name,
      final String the_contact_person, final String the_contact_phone,
      final String the_intake_person, final Calendar the_auction_date,
      final int the_auction_duration, final String the_comments) {
    my_next_item = 1;
    
    AUCTION_NUMBER++;
    my_number = AUCTION_NUMBER;

    my_auction_name = the_auction_name;
    my_contact_person = the_contact_person;
    my_contact_phone = the_contact_phone;
    my_intake_person = the_intake_person;
    my_auction_date = the_auction_date;
    my_auction_duration = the_auction_duration;
    my_comments = the_comments;

    my_auction_items = new LinkedList<Item>();

  }
  /**
   * 
   */
  public Auction() {
    this("", "", "", "", new GregorianCalendar(), 0, "");
  }

  /**
   * Creates an auction assigned to the given user and their organization. 
   */
  public Auction(final NonProfitUser the_user) {
    this(the_user.getOrganization(), the_user.getFirstName() + ' ' + the_user.getLastName(), "", "",
        new GregorianCalendar(), 0, "");
  }
  
  // SETTERS
  /**
   * 
   * @param the_auction_name
   */
  public void setAuctionName(final String the_auction_name) {
    my_auction_name = the_auction_name;
  }
  /**
   * 
   * @param the_contact_person
   */
  public void setContactPerson(final String the_contact_person) {
    my_contact_person = the_contact_person;
  }
  /**
   * 
   * @param the_contact_phone
   */
  public void setContactPhone(final String the_contact_phone) {
    my_contact_phone = the_contact_phone;
  }
  /**
   * 
   * @param the_intake_person
   */
  public void setIntakePerson(final String the_intake_person) {
    my_intake_person = the_intake_person;
  }
  /**
   * 
   * @param the_auction_date
   */
  public void setAuctionDate(final Calendar the_auction_date) {
    my_auction_date = the_auction_date;
    
    final String formatted_date = "" + (the_auction_date.get(Calendar.MONTH) + 1) 
        + '/' + the_auction_date.get(Calendar.DAY_OF_MONTH) 
        + '/' + the_auction_date.get(Calendar.YEAR);
    
    setAuctionName(my_auction_name + " - " + formatted_date);
  }
  /**
   * 
   * @param the_auction_duration
   */
  public void setAuctionDuration(final int the_auction_duration) {
    my_auction_duration = the_auction_duration;
  }
  /**
   * 
   * @param the_comments
   */
  public void setComments(final String the_comments) {
    my_comments = the_comments;
  }

  // GETTERs
  /**
   * 
   * @return
   */
  public String getAuctionName() {
    return my_auction_name;
  }
  
  
  /**
   * Returns the auction number.
   * Added to show which auction we are using.
   * @return AUCTION_NUMBER - the number of this auction.
   * @author Casey Morrison
   */
  public String getAuctionNumber() {
    StringBuilder sb = new StringBuilder();
    sb.append("");
    sb.append(my_number);
    String strI = sb.toString();
    return strI;
  }
  /**
   * 
   * @return
   */
  public String getContactPerson() {
    return my_contact_person;
  }
  /**
   * 
   * @return
   */
  public String getContactPhone() {
    return my_contact_phone;
  }
  /**
   * 
   * @return
   */
  public String getIntakePerson() {
    return my_intake_person;
  }
  /**
   * 
   * @return
   */
  public Calendar getAuctionDate() {
    return my_auction_date;
  }
  /**
   * 
   * @return
   */
  public int getAuctionDuration() {
    return my_auction_duration;
  }
  /**
   * 
   * @return
   */
  public String getComments() {
    return my_comments;
  }
  /**
   * 
   * @return
   */
  public int getItemCount() {
    return my_auction_items.size();
  }
  
  public List<Item> getItems() {
    return my_auction_items;
  }

  // METHODS
  /**
   * This method adds the given item to the auction and increments the number of items. 
   * @param the_item The Item object that is wanting to be added to the auction.
   * @return A boolean to whether the item was added or not.
   */
  public boolean addItem(final Item the_item) {
    final boolean success = my_auction_items.add(the_item);

    if (success) {
      the_item.setItemNumber(my_next_item);
      my_next_item++;
    }

    return success;
  }

  /**
   * This method deletes the given item from the auction and decrements the number of 
   * items.
   * @param the_item The item object that is wanting to be deleted from the auction.
   * @return A boolean to whether the item was added or not.
   */
  public boolean deleteItem(final Item the_item) {
    final boolean success = my_auction_items.remove(the_item);

    if (success) {
      my_next_item--;
    }

    return success;
  }

}
