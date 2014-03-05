package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import auction.Auction;


/**
 * Class: AuctionPanel
 * 
 * Displays the panel that shows the auction information.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * University of Washington, Tacoma
 * Winter 2014
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Casey Morrison
 * @version 1.0 Winter 2014
 */
public class AuctionPanel extends JPanel implements ActionListener {
  
  /**
   * Required for serializable class (extends JPanel).
   */
  private static final long serialVersionUID = 6765841635915082438L;
  
  // Global variables ----------------------------------
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
  
  /** The label that shows the auction's name. */
  private static final JLabel AUCTION_TITLE = new JLabel("Auction Information:");
  
  // Non global variable -----------------------------
    
  /** The label that shows the auction's number. */
  private final JLabel my_auction_number = new JLabel("Auction # ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_auction_name = new JLabel("Auction Name: ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_contact_person = new JLabel("Contact Person: ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_contact_phone = new JLabel("Contact Phone: ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_intake_person = new JLabel("Intake Person: ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_auction_date = new JLabel("Auction Date: ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_start_time = new JLabel("Start Time: ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_duration_time = new JLabel("Duration (Hours): ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_number_of_items = new JLabel("# of Items: ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_current_items = new JLabel("Current: ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_anticipated_items = new JLabel("Anticipated: ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_auction_comments = new JLabel("Comments: ");
  
  /** The button to click to edit an auction. */
  private final JButton my_edit_info = new JButton("Edit");
  
  /** The button to click to view inventory of the auction. */
  private final JButton my_view_inventory = new JButton("View Inventory");
    
  /** The button used to save the auction information. */
  private final JButton my_save = new JButton("Save");
  
  /** The button used to go back. */
  private final JButton my_back = new JButton("Back");
  
  /** The text area used to go add comments. */
  private final JTextField my_text = new JTextField(20);
  
//  /** The text area used to go add comments. */
//  private final JTextArea my_text_area = new JTextArea();
  
  /**
   * A reference to the main application frame.
   */
  private ApplicationFrame my_app_frame;
  
  /** The auction to display in the panel. */
  private final Auction my_auction;
  
  /** The label that shows the auction's number. */
  private final JLabel my_number;
  
  private String my_name;
  private String my_contact_p;
  private String my_contact_num;
  private String my_intake;
  private Calendar my_date;
  private int my_duration;
  private String my_comment;
  private int my_items;
  
  //Fields for data entry
  private JFormattedTextField my_auction_name_input = new JFormattedTextField();
  private JFormattedTextField my_contact_person_input = new JFormattedTextField();
  private JFormattedTextField my_contact_phone_input = new JFormattedTextField();
  private JFormattedTextField my_intake_person_input = new JFormattedTextField();
  private JFormattedTextField my_auction_date_input = new JFormattedTextField();
  private JFormattedTextField my_start_time_input = new JFormattedTextField();
  private JFormattedTextField my_duration_input = new JFormattedTextField();
  private JFormattedTextField my_current_input = new JFormattedTextField();
  private JFormattedTextField my_anticipated_input = new JFormattedTextField();
  
  /**
   * Sets up the Auction Panel.
   * @param the_frame - the frame this panel is attached to.
   */
  public AuctionPanel(final Auction the_auction, final ApplicationFrame the_frame) {
    super(new BorderLayout());
    setPreferredSize(DEFAULT_SIZE);
    setBorder(BLACK_LINE);
    setFocusable(true);
    
    my_app_frame = the_frame;
    
    my_auction = the_auction;
    final String temp = the_auction.getAuctionNumber();
    my_number = new JLabel(temp);
    
    
    createAuction();
    setupEditButton();
    setupSaveButton();
    setupViewInventoryButton();
    setupBackButton();
    setTheBorder();
    setupInput();
    createAuctionLabels();
    
    allowEdits(false);
  }
  
  private void allowEdits(final boolean my_edit_flag) {
    my_auction_name_input.setEditable(my_edit_flag);
    my_contact_person_input.setEditable(my_edit_flag);
    my_contact_phone_input.setEditable(my_edit_flag);
    my_intake_person_input.setEditable(my_edit_flag);
    my_auction_date_input.setEditable(my_edit_flag);
    my_start_time_input.setEditable(my_edit_flag);
    my_duration_input.setEditable(my_edit_flag);
    my_current_input.setEditable(my_edit_flag);
    my_anticipated_input.setEditable(my_edit_flag);
    
  }

  private void createAuction() {
    my_name = my_auction.getAuctionName();
    my_contact_p = my_auction.getContactPerson();
    my_contact_num = my_auction.getContactPhone();
    my_intake = my_auction.getIntakePerson();
    my_date = my_auction.getAuctionDate();
    my_duration = my_auction.getAuctionDuration();
    my_comment = my_auction.getComments();
    my_items = my_auction.getItemCount();
    initializeInput();
  }
  
  private void initializeInput() {
    my_auction_name_input.setText(my_name);
    my_contact_person_input.setText(my_contact_p);
    my_contact_phone_input.setText(my_contact_num);
    my_intake_person_input.setText(my_intake);
    my_auction_date_input.setText(my_date.getTime().toString());
//    my_start_time_input.setText(my_duration);
    final Integer i = my_duration;
    my_duration_input.setText(i.toString());
    final Integer j = my_items;
    my_current_input.setText(j.toString());
    my_anticipated_input.setText(j.toString());
    my_text.setText(my_comment);
    
    allowEdits(false);
  }
  
  /**
   * Sets up the edit button.
   */
  private void setupEditButton() {
    my_edit_info.setMnemonic(KeyEvent.VK_E);
    my_edit_info.setToolTipText("Edit the Auction Data");
    my_edit_info.addActionListener(new ActionListener() {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       * 
       * Clicking edit button sets all text fields to be editable.
       */
      public void actionPerformed(final ActionEvent the_event) {
        //editInfo();
        allowEdits(true);
      }
    });
  }
  
  /**
   * Sets up the save button.
   */
  private void setupSaveButton() {
    my_save.setMnemonic(KeyEvent.VK_S);
    my_save.setToolTipText("Save the Auction Data");
    my_save.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        saveAuction();
        allowEdits(false);
      }
    });
  }
  
  /**
   * Sets up the view inventory button.
   */
  private void setupViewInventoryButton() {
    my_view_inventory.setMnemonic(KeyEvent.VK_V);
    my_view_inventory.setToolTipText("View the Inventory of the Selected Auction");
    my_view_inventory.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        viewInventory();
      }
    });
  }
  
  /**
   * Sets up the back button.
   */
  private void setupBackButton() {
    my_back.setMnemonic(KeyEvent.VK_B);
    my_back.setToolTipText("Go to the previous screen");
    my_back.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        // go back to previous page
        // needs to be passed a previous page
        my_app_frame.showAuctionList();
      }
    });
  }
  
  /**
   * Sets the border of the panel.
   */
  private void setTheBorder() {
    TitledBorder titleborder;
    titleborder = BorderFactory.createTitledBorder(BLACK_LINE, "Auction: ");
    titleborder.setTitlePosition(TitledBorder.TOP);
    titleborder.setTitleJustification(TitledBorder.CENTER);
    setBorder(titleborder);
  }
  
  private void setupInput() {
    //Tell accessibility tools about label/textfield pairs.
    my_auction_name.setLabelFor(my_auction_name_input);
    my_contact_person.setLabelFor(my_contact_person_input);
    my_contact_phone.setLabelFor(my_contact_phone_input);
    my_intake_person.setLabelFor(my_intake_person_input);
    my_auction_date.setLabelFor(my_auction_date_input);
    my_start_time.setLabelFor(my_start_time_input);
    my_duration_time.setLabelFor(my_duration_input);
    my_current_items.setLabelFor(my_current_input);
    my_anticipated_items.setLabelFor(my_anticipated_input);
    
//    my_auction_name_input.setActionCommand(textFieldString);
//    my_auction_name_input.addActionListener(this);
//    
//    my_contact_person_input.setActionCommand(textFieldString);
//    my_contact_person_input.addActionListener(this);
//    
//    my_contact_phone_input.setActionCommand(textFieldString);
//    my_contact_phone_input.addActionListener(this);
//    
//    my_intake_person_input.setActionCommand(textFieldString);
//    my_intake_person_input.addActionListener(this);
//  
//    my_auction_date_input.setActionCommand(textFieldString);
//    my_auction_date_input.addActionListener(this);
//  
//    my_duration_input.setActionCommand(textFieldString);
//    my_duration_input.addActionListener(this);
//    
//    my_auction_date_input.setActionCommand(textFieldString);
//    my_auction_date_input.addActionListener(this);
//  
//    my_current_input.setActionCommand(textFieldString);
//    my_current_input.addActionListener(this);
//  
//    my_anticipated_input.setActionCommand(textFieldString);
//    my_anticipated_input.addActionListener(this);
  }
  
  /**
   * Creates the labels that display the controls.
   */
  private void createAuctionLabels() {
    final JPanel center = new JPanel(new BorderLayout());
//    center.setSize(800, 400);
    final JPanel west = new JPanel();
    west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
    final JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
    final JPanel east = new JPanel(new GridLayout(0, 1));
    final JPanel south = new JPanel(new BorderLayout());
    final JPanel southcomment = new JPanel(new FlowLayout());
    final JPanel southset = new JPanel(new FlowLayout());
//    south.setSize(800, 200);
    
    AUCTION_TITLE.setFont(new Font(ARIAL, Font.BOLD, MAIN_FONT_SIZE));
    my_auction_number.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_auction_name.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_contact_person.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_contact_phone.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_intake_person.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_auction_date.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_start_time.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_duration_time.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_number_of_items.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_current_items.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_anticipated_items.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    my_auction_comments.setFont(new Font(ARIAL, Font.PLAIN, INSTRUCTION_FONT_SIZE));
    
    north.add(AUCTION_TITLE, BorderLayout.CENTER);
    
    final JPanel auctionnum = new JPanel(new FlowLayout());
    auctionnum.add(my_auction_number, BorderLayout.CENTER);
    auctionnum.add(my_number);
    east.add(auctionnum);
    
    west.add(my_auction_name);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_contact_person);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_contact_phone);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_intake_person);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_auction_date);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_start_time);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_duration_time);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT - TEN)));
    west.add(my_number_of_items);
    west.add(my_current_items);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_anticipated_items);
    
    center.add(west, BorderLayout.CENTER);

    southcomment.add(my_auction_comments);
    southcomment.add(my_text);
    southcomment.add(my_save);
    south.add(southcomment, BorderLayout.CENTER);
    
    southset.add(my_edit_info, BorderLayout.EAST);
    southset.add(my_view_inventory, BorderLayout.CENTER);
    southset.add(my_back, BorderLayout.WEST);
    south.add(southset, BorderLayout.SOUTH);
    
    add(north, BorderLayout.NORTH);
    add(center, BorderLayout.WEST);
    add(east, BorderLayout.EAST);
    add(south, BorderLayout.SOUTH);
    
    
    final JPanel textinput = new JPanel();
    textinput.setLayout(new BoxLayout(textinput, BoxLayout.Y_AXIS));
    
    textinput.add(my_auction_name_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_contact_person_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_contact_phone_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_intake_person_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_auction_date_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_start_time_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_duration_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_current_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_anticipated_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    
    add(textinput, BorderLayout.CENTER);

  }
  
  
  private Auction saveAuction() {    // Return an auction    
    my_auction.setAuctionName(my_auction_name_input.getText().trim());
    my_auction.setContactPerson(my_contact_person_input.getText().trim());
    my_auction.setContactPhone(my_contact_phone_input.getText().trim());
    my_auction.setIntakePerson(my_intake_person_input.getText().trim());
//    my_auction.setAuctionDate(my_auction_date_input.getText().trim());    // not sure how to convert string into calendar...
//    my_auction.setAuctionDuration(my_duration_input.getText().trim());    // not sure how to convert an input string into an int...
    my_auction.setComments(my_auction_comments.getText().trim());
    
    return my_auction;
  }
  
  private Boolean editInfo() {       // Return a Boolean
    final Boolean test = false;
      // finish
    return test;
  }
  
  private void viewInventory() {  // Return a List<Items>
    
  }
  
  private void showAuctions() {
    
  }
  
  private void showItemPanel() {
    
  }
  
  private void showUserPanel() {
    
  }
  
  private void getAuctionInfoPanel() {  // Takes an Auction as a parameter
    
  }

  @Override
  public void actionPerformed(final ActionEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  
}






