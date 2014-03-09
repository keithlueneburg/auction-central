package gui;

/**
 * @author Josh Hammer
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;




import javax.swing.border.Border;

import auction.Auction;
import system.AuctionCentralSystem;
import user.Bidder;
import user.User;
import auction.Item;

public class ItemPanel extends JPanel {

  /** The default height of the panel. */
  private static final int DEFAULT_HEIGHT = 680;

  /** The default width of the panel. */
  private static final int DEFAULT_WIDTH = 824;

  /** The default size for this JPanel. */
  private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

  /** The default font for the labels. */
  private static final String ARIAL = "Arial";

  /** The number 10. */
  private static final int TEN = 10;

  /** The default font size for the instruction labels. */
  private static final int INSTRUCTION_FONT_SIZE = 12;

  /** The default font size for the instruction labels. */
  private static final int MAIN_FONT_SIZE = 24;

  /** The default box height. */
  private static final int BOX_HEIGHT = 45;

  /** The default box height for input spacing. */
  private static final int BOX_HEIGHT_TWO = 35;

  /** The default box width. */
  private static final int BOX_WIDTH = 5;

  /** The default border, a black line. */
  private static final Border BLACK_LINE = BorderFactory.createLineBorder(Color.BLACK, 3);

  /** The label that shows the item's name. */
  private static final JLabel ITEM_TITLE = new JLabel("Item Information:");

  /**
   * Required for serialization.
   */
  private static final long serialVersionUID = -2013499557306400729L;

  /**
   * A reference to the main application frame.
   */
  private ApplicationFrame my_app_frame;

  /** The auction to display in the panel. */
  private final Auction my_auction;

  /** The item to display in the panel. */
  private final Item my_item;

  /** The label that shows the item's number. */
  private final JLabel my_number;
  
  /** The selling price of the item.*/
  private  double my_price;
  
  /**The donor of the item.*/
  private  String my_donor;
  
  /** The quantity of the item.*/
  private  int my_quantity;
  
  /** The minimum bid.*/
  private double my_minimum_bid;
  
  /** The size of the item.*/
  private String my_size;
  
  /** The storage of the item.*/
  private String my_storage;
  
  /** The condition of the item.*/
  private  String my_condition;
  
  /** The comments on the item.*/
  private  String my_comment;

  /** The name of the item. */
  private String my_name;

  /** The button used to save the item information. */
  private final JButton my_save = new JButton("Save");
  
  /** The button used to go back. */
  private final JButton my_back = new JButton("Back");
  
  /** The name of the item input. */
  private JFormattedTextField my_item_name_input = new JFormattedTextField();

  /**The price of the item.*/
  private JFormattedTextField my_price_input = new JFormattedTextField();

  /**The quantity of the item.*/
  private JFormattedTextField my_quantity_input = new JFormattedTextField();

  /**The minimum bid price of the item.*/
  private JFormattedTextField my_minimum_bid_input = new JFormattedTextField();

  /**The name of the donor of the item.*/
  private JFormattedTextField my_donor_input = new JFormattedTextField();

  /**The size of the item.*/
  private JFormattedTextField my_size_input = new JFormattedTextField();

  /**The storage of the item.*/
  private JFormattedTextField my_storage_input = new JFormattedTextField();

  /**The condition of the item.*/
  private JFormattedTextField my_condition_input = new JFormattedTextField();

  /**The comments on the item.*/
  private JFormattedTextField my_comment_input = new JFormattedTextField();

  /** The auction central system. */
  private final AuctionCentralSystem my_system;

  /**
   * 
   * @param the_item
   * @param the_frame
   * @param the_auction
   * @param the_system
   * @param an_editable
   */
  public ItemPanel(final Item the_item, final ApplicationFrame the_frame,
      final Auction the_auction, final AuctionCentralSystem the_system,
      final boolean an_editable) {	
    super(new BorderLayout());

    my_system = the_system;
    my_app_frame = the_frame;
    my_auction = the_auction;
    my_item = the_item;
    final String temp = "" + my_item.getItemNumber();
    my_number = new JLabel(temp);

    allowEdits(an_editable);
    configurePanel();
  }	

  private void configurePanel(){	
    setButtonVisibility(a_user);
    setPreferredSize(DEFAULT_SIZE);
    setBorder(BLACK_LINE);
    setFocusable(true);
    createItem();

  }	

  /**
   * Allows the inputs to be editable fields. 
   * @param the_edit_flag - true if editable.
   */
  private void allowEdits(final boolean the_edit_flag) {
    my_item_name_input.setEditable(the_edit_flag);
    my_donor_input.setEditable(the_edit_flag);
    my_size_input.setEditable(the_edit_flag);
    my_price_input.setEditable(the_edit_flag);
    my_quantity_input.setEditable(the_edit_flag);
    my_minimum_bid_input.setEditable(the_edit_flag);
    my_storage_input.setEditable(the_edit_flag);
    my_condition_input.setEditable(the_edit_flag);
    my_comment_input.setEditable(the_edit_flag);
  }
  
  /**
   * Creates the auction used for editing.
   */
  private void createItem() {
    my_name = my_item.getItemName();
    my_donor = my_item.getDonor();
    my_size = my_item.getSize();
    my_price = my_item.getSellingPrice();
    my_quantity = my_item.getItemQuantity();
    my_minimum_bid = my_item.getStartingBid();
    my_storage = my_item.getStorage();
    my_condition = my_item.getCondition();
    my_comment = my_item.getComments();
    initializeInput();
  }

  /**
   * Initializes the input entries.
   */
  private void initializeInput() {
    my_item_name_input.setText(my_name);
    my_donor_input.setText(my_donor);
    my_size_input.setText(my_size);
    my_price_input.setText("" + my_price);
    my_quantity_input.setText("" + my_quantity);
    my_minimum_bid_input.setText("" + my_minimum_bid);
    my_storage_input.setText(my_storage);
    my_condition_input.setText(my_condition);
    my_comment_input.setText(my_comment);
    
    allowEdits(false);
  }
  
  /**
   * Sets up the save button.
   */
  private void setupSaveButton() {
    my_save.setMnemonic(KeyEvent.VK_S);
    my_save.setToolTipText("Save the Item Data");
    my_save.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        saveItem();
      }
    });
  }
  
  /**	
   * Sets button visibility to false if they should not be available to the user	
   * type that is logged in.	
   * 	
   * @param a_user	
   *          The user that determines what buttons are available.	
   */	
  private void setButtonVisibility(final User a_user) {	
    if (!(a_user instanceof Bidder)) {	
      my_bid_button.setVisible(false);	
    }	
  }	
}	