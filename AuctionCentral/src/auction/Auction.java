package auction;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import user.NonProfitUser;
/**
 * Represents an Auction in the AuctionCentral system.
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
   * The next item to be added to the auction.
   */
  private int my_next_item;
  /**
   * The auction number.
   */
  private int my_number;
  /**
   * The auction name.
   */
  private String my_auction_name;
  /**
   * The contact person name.
   */
  private String my_contact_person;
  /**
   * The contact phone number.
   */
  private String my_contact_phone;
  /**
   * The intake person name.
   */
  private String my_intake_person;
  /**
   * The auction date.
   */
  private Calendar my_auction_date;
  /**
   * The duration of the auction.
   */
  private int my_auction_duration;
  /**
   * The auction comments.
   */
  private String my_comments;
  /**
   * The list of the auction's items.
   */
  private List<Item> my_auction_items;
  /**
   * Create a new Auction.
   * 
   * @param the_auction_name Auction name.
   * @param the_contact_person Contact person name.
   * @param the_contact_phone Contact phone number.
   * @param the_intake_person Intake person name.
   * @param the_auction_date Auction date.
   * @param the_auction_duration Duration of auction.
   * @param the_comments Auction comments.
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
   * 
   *  @param the_user The user logged in to the system.
   */
  public Auction(final NonProfitUser the_user) {
    this(the_user.getOrganization(), the_user.getFirstName() + ' ' + the_user.getLastName(),
        "", "",
        new GregorianCalendar(), 0, "");
  }
  
  // SETTERS
  /**
   * Set the auction name.
   * 
   * @param the_auction_name The name of the auction.
   */
  public void setAuctionName(final String the_auction_name) {
    my_auction_name = the_auction_name;
  }
  
  /**
   * Set the contact person name.
   * 
   * @param the_contact_person Contact person name.
   */
  public void setContactPerson(final String the_contact_person) {
    my_contact_person = the_contact_person;
  }
  
  /**
   * Set the contact phone number.
   * 
   * @param the_contact_phone The contact phone number.
   */
  public void setContactPhone(final String the_contact_phone) {
    my_contact_phone = the_contact_phone;
  }
  
  /**
   * Set the intake person name.
   * 
   * @param the_intake_person The intake person name.
   */
  public void setIntakePerson(final String the_intake_person) {
    my_intake_person = the_intake_person;
  }
  
  /**
   * Set the auction date.
   * 
   * <dt><b>Preconditions:</b> A valid date is passed in.<dd>
   * <dt><b>Postconditions:</b> The date is set.<dd>
   * 
   * @param the_auction_date The auction date.
   * 
   */
  public void setAuctionDate(final Calendar the_auction_date) {
    my_auction_date = the_auction_date;
    
    final String formatted_date = "" + (the_auction_date.get(Calendar.MONTH) + 1) 
        + '/' + the_auction_date.get(Calendar.DAY_OF_MONTH) 
        + '/' + the_auction_date.get(Calendar.YEAR);
    
    setAuctionName(my_auction_name + " - " + formatted_date);
  }
  
  /**
   * Set the auction duration.
   * 
   * @param the_auction_duration The auction duration.
   */
  public void setAuctionDuration(final int the_auction_duration) {
    my_auction_duration = the_auction_duration;
  }
  /**
   * Set the auction comments.
   * 
   * @param the_comments The auction comments.
   */
  public void setComments(final String the_comments) {
    my_comments = the_comments;
  }

  // GETTERs
  /**
   * Get the auction name.
   * 
   * @return the name.
   */
  public String getAuctionName() {
    return my_auction_name;
  }
  
  
  /**
   * Returns the auction number.
   * Added to show which auction we are using.
   * 
   * @return AUCTION_NUMBER - the number of this auction.
   * @author Casey Morrison
   */
  public String getAuctionNumber() {
    final StringBuilder sb = new StringBuilder();
    sb.append("");
    sb.append(my_number);
    final String str = sb.toString();
    return str;
  }
  
  /**
   * Get the contact person name.
   * 
   * @return Contact person name.
   */
  public String getContactPerson() {
    return my_contact_person;
  }
  
  /**
   * Get the contact phone number.
   * 
   * @return the phone number.
   */
  public String getContactPhone() {
    return my_contact_phone;
  }
  
  /**
   * Get intake person name.
   * 
   * @return the intake person.
   */
  public String getIntakePerson() {
    return my_intake_person;
  }
  
  /**
   * Get the auction date.
   * 
   * @return the date.
   */
  public Calendar getAuctionDate() {
    return my_auction_date;
  }
  
  /**
   * Get auction duration.
   * 
   * @return the duration.
   */
  public int getAuctionDuration() {
    return my_auction_duration;
  }
  
  /**
   * Get auction comments.
   * 
   * @return the comments.
   */
  public String getComments() {
    return my_comments;
  }
  
  /**
   * Get the item count.
   * 
   * @return the item count.
   */
  public int getItemCount() {
    return my_auction_items.size();
  }
  
  /**
   * Get the list of item belonging to the auction.
   * 
   * @return the list of items.
   */
  public List<Item> getItems() {
    return my_auction_items;
  }
  
  /**
   * Set the items to the given list of items.
   * 
   * @param an_item_list The list to set the auction's items to.
   */
  public void setItems(final List<Item> an_item_list) {
    my_auction_items = an_item_list;
  }

  // METHODS
  /**
   * This method adds the given item to the auction and increments the number of items. 
   *   
   * @param the_item The Item object that is wanting to be added to the auction.
   * @return A boolean to whether the item was added or not.
   * 
   */
  public boolean addItem(final Item the_item) {
    final boolean success = my_auction_items.add(the_item);

    if (success) {
      the_item.setItemNumber(my_next_item);
      System.out.println("my_next_item: " + my_next_item);
      my_next_item++;
    }

    return success;
  }

  /**
   * This method deletes the given item from the auction and decrements the number of 
   * items.
   * 
   * <dt><b>Preconditions:</b> The item exists in the auction. <dd>
   * <dt><b>Postconditions:</b> The item is removed. <dd>
   * 
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
