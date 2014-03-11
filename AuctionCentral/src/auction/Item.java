package auction;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JOptionPane;

import bidding.CreditCard;





/**
 * This is the Item class. It holds all the information on a given item for an
 * auction. The item is able to add and remove bids from myBids, and it can
 * "unseal" a bid with a username and password.
 * 
 * @author Kevin Alexander
 * 
 * @version February 23, 2014
 * 
 */
public class Item {

  private static final String NEW_LINE = "\n";

  // //////////////////////////////////////////////////////////////////
  // FIELDS
  // //////////////////////////////////////////////////////////////////
  /** The item's number.*/
  private int my_item_number;
  
  /**The item name.*/
  private String my_item_name;
  
  /**The quantity of the item.*/
  private int my_quantity;
  
  /**The starting bid for the item.*/
  private double my_starting_bid;
  
  /**The donor of the item.*/
  private String my_donor;
  
  /**The size of the item.*/
  private String my_size;
  
  /**The storage of the item.*/
  private String my_storage;
  
  /**The condition of the item.*/
  private String my_condition;
  
  /**The comments of the item.*/
  private String my_comments;

  /**The selling price.*/
  private double my_selling_price;

  /**The bids on the item.*/
  private Queue<Bid> my_bids;
  
  /**Win bid list. */
  private List<Bid> my_win_list;

  // //////////////////////////////////////////////////////////////////
  // CONSTRUCTORS
  // //////////////////////////////////////////////////////////////////
  /**
   * This constructor takes in values for all fields except myBids.
   * 
   * @param the_item_number
   *          The number of the item.
   * @param the_item_name
   *          The name of the item.
   * @param the_item_quantity
   *          The quantity of the item.
   * @param the_starting_bid
   *          The starting bid of the item.
   * @param the_donor
   *          The donor of the item.
   * @param the_size
   *          The size of the item.
   * @param the_storage
   *          The storage location of the item.
   * @param the_condition
   *          The condition of the item.
   * @param the_comments
   *          The comments of the item.
   */
  public Item(final int the_item_number, final String the_item_name,
      final int the_item_quantity, final double the_starting_bid,
      final String the_donor, final String the_size, final String the_storage,
      final String the_condition, final String the_comments) {
    my_item_number = the_item_number;
    my_item_name = the_item_name;
    my_quantity = the_item_quantity;
    my_starting_bid = the_starting_bid;
    my_donor = the_donor;
    my_size = the_size;
    my_storage = the_storage;
    my_condition = the_condition;
    my_comments = the_comments;

    my_bids = new PriorityQueue<Bid>();
    my_win_list = new ArrayList<Bid>();
  }

  /**
   * This constructor takes in no arguments and sets all the fields to default
   * values.
   */
  public Item() {
    this(0, "", 0, 0.00, "", "", "", "", "");
  }

  // //////////////////////////////////////////////////////////////////
  // SETTERS
  // //////////////////////////////////////////////////////////////////
  /**
   * Sets the item's number to theItemNumber.
   * 
   * @param the_item_number
   *          A String representing the new number.
   */
  public void setItemNumber(final int the_item_number) {
    my_item_number = the_item_number;
  }

  /**
   * Sets the item's name to theItemName.
   * 
   * @param the_item_name
   *          A String representing the new name.
   */
  public void setItemName(final String the_item_name) {
    my_item_name = the_item_name;
  }

  /**
   * Sets the item's quantity to theItemQuantity.
   * 
   * @param the_item_quantity
   *          An integer representing the new quantity.
   */
  public void setItemQuantity(final int the_item_quantity) {
    my_quantity = the_item_quantity;
  }

  /**
   * Sets the item's starting bid to theStartingBid.
   * 
   * @param the_starting_bid
   *          A double representing the new starting bid.
   */
  public void setStartingBid(final double the_starting_bid) {
    my_starting_bid = the_starting_bid;
  }

  /**
   * Sets the item's donor to theDonor.
   * 
   * @param the_donor
   *          A String representing the new donor.
   */
  public void setDonor(final String the_donor) {
    my_donor = the_donor;
  }

  /**
   * Sets the item's size to theSize.
   * 
   * @param the_size
   *          A String representing the new size.
   */
  public void setSize(final String the_size) {
    my_size = the_size;
  }

  /**
   * Sets the item's storage location to theStorage.
   * 
   * @param the_storage
   *          A String representing the new storage location.
   */
  public void setStorage(final String the_storage) {
    my_storage = the_storage;
  }

  /**
   * Sets the item's condition to theCondition.
   * 
   * @param the_condition
   *          A String representing the new condition.
   */
  public void setCondition(final String the_condition) {
    my_condition = the_condition;
  }

  /**
   * Sets the item's comments to theComments.
   * 
   * @param the_comments
   *          A String representing the new comments.
   */
  public void setComments(final String the_comments) {
    my_comments = the_comments;
  }


  /**
   * Sets the item's selling price to theSellingPrice.
   * 
   * @param the_selling_price
   *          A double representing the new selling price.
   */
  public void setSellingPrice(final double the_selling_price) {
    my_selling_price = the_selling_price;
  }

  // //////////////////////////////////////////////////////////////////
  // GETTERS
  // //////////////////////////////////////////////////////////////////
  /**
   * Returns the item number.
   * 
   * @return myItemNumber The item number.
   */
  public int getItemNumber() {
    return my_item_number;
  }

  /**
   * Returns the name of the item.
   * 
   * @return myItemName The item name.
   */
  public String getItemName() {
    return my_item_name;
  }

  /**
   * Returns the quantity of the item.
   * 
   * @return myQuantity The quantity.
   */
  public int getItemQuantity() {
    return my_quantity;
  }

  /**
   * Returns the starting bid of the item.
   * 
   * @return myStartingBid The starting bid.
   */
  public double getStartingBid() {
    return my_starting_bid;
  }

  /**
   * Returns the donor of the item.
   * 
   * @return myDonor The donor.
   */
  public String getDonor() {
    return my_donor;
  }

  /**
   * Returns the size of the item.
   * 
   * @return mySize The size.
   */
  public String getSize() {
    return my_size;
  }

  /**
   * Returns the storage location of the item.
   * 
   * @return myStorage The storage location.
   */
  public String getStorage() {
    return my_storage;
  }

  /**
   * Returns the condition of the item.
   * 
   * @return myCondition The condition.
   */
  public String getCondition() {
    return my_condition;
  }

  /**
   * Returns the comments of this item.
   * 
   * @return myComments The comments.
   */
  public String getComments() {
    return my_comments;
  }


  /**
   * Returns the selling price of this item.
   * 
   * @return mSellingPrice The selling price.
   */
  public double getSellingPrice() {
    return my_selling_price;
  }

  /**
   * Returns the list of Bid objects on this item.
   * 
   * @return myBids The list of Bids.
   */
  public Queue<Bid> getBids() {
    return my_bids;
  }

  // //////////////////////////////////////////////////////////////////
  // METHODS
  // //////////////////////////////////////////////////////////////////
  /**
   * This method takes in a Bid item and adds it to the current list of Bid
   * objects. Precondition: None Postcondition: The Bid item has been added to
   * the list of Bid objects.
   * 
   * @param the_bid
   *          The Bid object that is being added to the myBids list.
   * @return A boolean indicating whether the Bid object was successfully added
   *         to the list or not.
   */
  public boolean addBid(final Bid the_bid) {
    return my_bids.offer(the_bid);
  }

  /**
   * This method takes in a Bid item and adds it to the current list of Bid
   * objects. Precondition: The list myBids has at least one Bid. Postcondition:
   * The Bid item has been removed from the list of Bid objects.
   * 
   * @param the_bid
   *          The Bid object that is being removed from the myBids list.
   * @return A boolean indicating whether the Bid object was successfully
   *         deleted from the list or not.
   */
  public boolean removeBid(final Bid the_bid) {
    return my_bids.remove(the_bid);
  }

  /**
   * This method takes in two Strings, which represent the username and password
   * of one member of the AuctionCentral Staff, and verifies that they are
   * correct. It then returns the highest bid or returns null. Precondition: The
   * item must have at least one bid in myBids and the file admin.txt must be
   * existent and contain the proper username and password. Postcondition: If
   * the correct username and password were entered, the highest bid is
   * returned.
   * 
   * @param the_username
   *          The username that is being entered.
   * @param the_password
   *          The password that is being entered.
   * @return Returns the highest bid.
   */
  /*public Bid unsealBid(final String the_username, final String the_password) {
    Scanner input;
    Bid return_bid = null;

    input = getScanner("log//admin.txt");
    if (input != null && !my_bids.isEmpty()) {
      if (input.hasNext()) {
        final String username = input.next();
        if (input.hasNext()) {
          final String password = input.next();
          if (the_username.equals(username) && the_password.equals(password)) {
            // LOGIN SUCCESS
            return_bid = my_bids.get(0);
          }
        }
      }

      input.close();
    }

    return return_bid;
  }

  *//**
   * This is a private method that is used in the unsealBid() method. It takes
   * in a String containing the file location and attempts to assign a Scanner
   * object to it. The Scanner object is then returned. Precondition: The String
   * should contain a valid file path for the username and password.
   * Postcondtion: The Scanner object should be assigned to the File at the
   * String's location. If it doesn't then the Scanner is null.
   * 
   * @param the_file_name
   *          A String object containing the file path of the username and
   *          password.
   * @return A Scanner object if the file was found and null otherwise.
   *//*
  private Scanner getScanner(final String the_file_name) {
    Scanner temp = null;

    try {
      temp = new Scanner(new File(the_file_name)); 
    } catch (final FileNotFoundException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }

    return temp;
  }*/
  
  /**
   * another verision of unseal bid.
   * @author Kaiyuan Shi
   * @return the win bid
   */
  public void unsealBid() {
    
    int bid_num = Math.min( my_bids.size(), my_quantity);
    
    for (int i = 0; i < bid_num; i ++) {
      my_win_list.add(my_bids.poll());
    }
    
    my_bids.addAll(new ArrayList<Bid>(my_win_list));
    
    my_quantity -= my_win_list.size();
    showwinMessage();
    
  }
  
  private void showwinMessage() {
    
    /*if (win_bid == null) {
    //no win bid
    message = "No one bid for this item.";
  } else {
    
    //show win bid
    CreditCard win_card = win_bid.getPayment();
    Calendar card_exp = win_card.getExpDate();
    String card_exp_str = (card_exp.get(Calendar.MONTH) + 1) + "/";
    card_exp_str += card_exp.get(Calendar.YEAR);
    
    message = "Winner: " + win_bid.getBidderName() + NEW_LINE;
    message += "Price: " + win_bid.getPrice() + NEW_LINE;
    message += "Bid time: " + win_bid.getBidTime().get(Calendar.MONTH) + "/";
    message += win_bid.getBidTime().get(Calendar.DAY_OF_MONTH) + "/";
    message += win_bid.getBidTime().get(Calendar.YEAR) + "/ ";
    message += win_bid.getBidTime().get(Calendar.HOUR_OF_DAY) + ":";
    message += win_bid.getBidTime().get(Calendar.MINUTE) + NEW_LINE;
    message += "Credit Card #: " + win_card.getCardNum() + NEW_LINE;
    message += "Exp Date: " + card_exp_str + NEW_LINE;
    message += "Card CSC: " + win_card.getCSC();
    
  }*/
    
    if (my_win_list.size() == 0) {
      JOptionPane.showMessageDialog(null, "No one bid for this item.");
    } else {
      String message = "";
      for (int i = 0; i < my_win_list.size(); i++) {
        Bid win_bid = my_win_list.get(i);
        CreditCard win_card = win_bid.getPayment();
        Calendar card_exp = win_card.getExpDate();
        String card_exp_str = (card_exp.get(Calendar.MONTH) + 1) + "/";
        card_exp_str += card_exp.get(Calendar.YEAR);
        
        message = "Winner: " + win_bid.getBidderName() + NEW_LINE;
        message += "Price: " + win_bid.getPrice() + NEW_LINE;
        message += "Bid time: " + win_bid.getBidTime().get(Calendar.MONTH) + "/";
        message += win_bid.getBidTime().get(Calendar.DAY_OF_MONTH) + "/";
        message += win_bid.getBidTime().get(Calendar.YEAR) + "/ ";
        message += win_bid.getBidTime().get(Calendar.HOUR_OF_DAY) + ":";
        message += win_bid.getBidTime().get(Calendar.MINUTE) + NEW_LINE;
        message += "Credit Card #: " + win_card.getCardNum() + NEW_LINE;
        message += "Exp Date: " + card_exp_str + NEW_LINE;
        message += "Card CSC: " + win_card.getCSC();
        
        //show winner
        JOptionPane.showMessageDialog(null, message,
            "Winner #" + (i + 1), JOptionPane.PLAIN_MESSAGE);
      }
      
    }
    
    //show quantity
    if (my_quantity <= 0) {
      //nothing left
      JOptionPane.showMessageDialog(null, "no inventory left");
    } else {
      //something left
      JOptionPane.showMessageDialog(null, "there are " + my_quantity + " inventory left.");
    }
    my_quantity += my_win_list.size();
    my_win_list.clear();
  }

}