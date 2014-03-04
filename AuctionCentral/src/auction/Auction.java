package auction;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class Auction {

  // Used only when adding/removing items
  private int my_next_item;

  private String my_auction_name;
  private String my_contact_person;
  private String my_contact_phone;
  private String my_intake_person;
  private Calendar my_auction_date;
  private int my_auction_duration;
  private String my_comments;

  private List<Item> my_auction_items;

  public Auction(final String the_auction_name,
      final String the_contact_person, final String the_contact_phone,
      final String the_intake_person, final Calendar the_auction_date,
      final int the_auction_duration, final String the_comments) {
    my_next_item = 1;

    my_auction_name = the_auction_name;
    my_contact_person = the_contact_person;
    my_contact_phone = the_contact_phone;
    my_intake_person = the_intake_person;
    my_auction_date = the_auction_date;
    my_auction_duration = the_auction_duration;
    my_comments = the_comments;

    my_auction_items = new LinkedList<Item>();

  }

  public Auction() {
    this("", "", "", "", new GregorianCalendar(), 0, "");
  }

  // SETTERS
  public void setAuctionName(final String the_auction_name) {
    my_auction_name = the_auction_name;
  }

  public void setContactPerson(final String the_contact_person) {
    my_contact_person = the_contact_person;
  }

  public void setContactPhone(final String the_contact_phone) {
    my_contact_phone = the_contact_phone;
  }

  public void setIntakePerson(final String the_intake_person) {
    my_intake_person = the_intake_person;
  }

  public void setAuctionDate(final Calendar the_auction_date) {
    my_auction_date = the_auction_date;
  }

  public void setAuctionDuration(final int the_auction_duration) {
    my_auction_duration = the_auction_duration;
  }

  public void setComments(final String the_comments) {
    my_comments = the_comments;
  }

  // GETTERs
  public String getAuctionName() {
    return my_auction_name;
  }

  public String getContactPerson() {
    return my_contact_person;
  }

  public String getContactPhone() {
    return my_contact_phone;
  }

  public String getIntakePerson() {
    return my_intake_person;
  }

  public Calendar getAuctionDate() {
    return my_auction_date;
  }

  public int getAuctionDuration() {
    return my_auction_duration;
  }

  public String getComments() {
    return my_comments;
  }
  
  public int getItemCount() {
    return my_auction_items.size();
  }

  // METHODS
  boolean addItem(final Item the_item) {
    final boolean success = my_auction_items.add(the_item);

    if (success) {
      the_item.setItemNumber(my_next_item);
      my_next_item++;
    }

    return success;
  }

  boolean deleteItem(final Item the_item) {
    final boolean success = my_auction_items.remove(the_item);

    if (success) {
      my_next_item--;
    }

    return success;
  }
}
