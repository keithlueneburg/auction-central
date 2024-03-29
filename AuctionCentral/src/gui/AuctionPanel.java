package gui;

import auction.Auction;

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
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import system.AuctionCentralSystem;
import user.NonProfitUser;
import user.User;



/**
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
@SuppressWarnings("serial")
public class AuctionPanel extends JPanel {
    
  // Global variables ----------------------------------
  
  /**
   * JOptionpane title text for popup message windows.
   */
  private static final String MESSAGE_WINDOW_TITLE = "message";
  
  /** The default height of the panel. */
  private static final int DEFAULT_HEIGHT = 680;
  
  /** Number 12. */
  private static final int TWELVE = 12;
  
  /** Number 3. */
  private static final int THREE = 3;
  
  /** Number 5. */
  private static final int FIVE = 5;
  
  /** Number 7. */
  private static final int SEVEN = 7;
  
  /** Number 8. */
  private static final int EIGHT = 8;
  
  /** Number 30. */
  private static final int THIRTY = 30;
  
  /** Number 400. */
  private static final int FOUR_HUNDRED = 400;
  
  /** The error message. */
  private static final String ERROR = "Error";
  
  /** Date error message. */
  private static final String DATE_ERROR = "Invalid date. " 
      + "Please enter in the style of MM/DD/YYYY.";
    
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
  private static final int BOX_HEIGHT = 47;
  
  /** The default box height for input spacing. */
  private static final int BOX_HEIGHT_TWO = 35;
  
  /** The default box width. */
  private static final int BOX_WIDTH = 5;
  
  /** The default border, a black line. */
  private static final Border BLACK_LINE = BorderFactory.createLineBorder(Color.BLACK, 3);
  
  /** The label that shows the auction's name. */
  private static final JLabel AUCTION_TITLE = new JLabel("Auction Information:");

  /**
   * System reserved character. Strings should not contain this.
   */
  private static final String RESERVED_CHARACTER = "`";
  
  // Non global variable -----------------------------
    
  /** The label that shows the auction's number. */
  private final JLabel my_auction_number = new JLabel("Auction # ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_auction_name = new JLabel("Auction Name: ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_contact_person = new JLabel("Contact Person: ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_contact_phone = new JLabel("Contact Phone: ###-###-####  ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_intake_person = new JLabel("Intake Person: ");
    
  /** The label that shows the auction's name. */
  private final JLabel my_auction_date = new JLabel("Auction Date: MM/DD/YYYY  ");
    
  /** The label that shows the auction's number. */
  private final JLabel my_start_time = new JLabel("Start Time:               (0 - 23)");
    
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
  
//  /** The button to click to edit an auction. */
//  private final JButton my_edit_info = new JButton("Edit");
  
  /** The button to click to view inventory of the auction. */
  private final JButton my_view_inventory = new JButton("View Inventory");
    
  /** The button used to save the auction information. */
  private final JButton my_save = new JButton("Save");
  
  /** The button used to go back. */
  private final JButton my_back = new JButton("Back");
  
  /** The button used to go delete. */
  private final JButton my_delete = new JButton("Delete");
  
  /** The text area used to go add comments. */
  private final JTextField my_comments_input = new JTextField(20);
 
  /** A reference to the main application frame. */
  private ApplicationFrame my_app_frame;
  
  /** The auction to display in the panel. */
  private final Auction my_auction;
  
  /** The label that shows the auction's number. */
  private final JLabel my_number;
  
  /** The name of the auction. */
  private String my_name;
  
  /** The name of the contact. */
  private String my_contact_p;
  
  /** The contact phone number of the auction. */
  private String my_contact_num;
  
  /** The intake person of the auction. */
  private String my_intake;
  
  /** The date of the auction. */
  private Calendar my_date;
  
  /** The duration of the auction. */
  private int my_duration;
  
  /** The comment of the auction. */
  private String my_comment;
  
  /** The items of the auction. */
  private int my_items;
  
  //Fields for data entry
  /** The name of the auction input. */
  private JFormattedTextField my_auction_name_input = new JFormattedTextField();
  
  /** The contact person input. */
  private JFormattedTextField my_contact_person_input = new JFormattedTextField();
  
  /** The contact phone input. */
  private JFormattedTextField my_contact_phone_input = new JFormattedTextField();
  
  /** The intake person input. */
  private JFormattedTextField my_intake_person_input = new JFormattedTextField();
  
  /** The date input. */
  private JFormattedTextField my_auction_date_input = new JFormattedTextField();
  
  /** The start time input. */
  private JFormattedTextField my_start_time_input = new JFormattedTextField();
  
  /** The duration input. */
  private JFormattedTextField my_duration_input = new JFormattedTextField();
  
  /** The current input. */
  private JFormattedTextField my_current_input = new JFormattedTextField();
  
  /** The anticipated input. */
  private JFormattedTextField my_anticipated_input = new JFormattedTextField();
  
  /** The auction central system. */
  private final AuctionCentralSystem my_system;
  
  /** The current user. */
  private User my_user;
  
  /** If the information is editable. */
  private boolean my_editable;
  
  /**
   * Sets up the Auction Panel.
   * @param the_frame - the frame this panel is attached to.
   * @param the_auction - the auction used for display.
   * @param the_system The system instance running behind the program.
   * @param an_editable Whether or not the fields will be editable initially.
   */
  public AuctionPanel(final Auction the_auction, final ApplicationFrame the_frame, 
      final AuctionCentralSystem the_system, final boolean an_editable) {
    super(new BorderLayout());
    setPreferredSize(DEFAULT_SIZE);
    setBorder(BLACK_LINE);
    
    my_system = the_system;
    my_app_frame = the_frame;
    my_user = the_system.getCurrentUser();
    my_editable = an_editable;
    
    my_auction = the_auction;
    my_number = new JLabel(the_auction.getAuctionNumber());
    
    start(an_editable);
  }
  
  /** 
   * Initiates through constructor.
   * 
   * @param an_editable - the editable boolean.
   */
  private void start(final boolean an_editable) {
    setFocusable(true);
    createAuction();
    //setupEditButton();
    setupSaveButton();
    setupViewInventoryButton();
    setupBackButton();
    setupDeleteButton();
    setTheBorder();
    setupInput();
    createAuctionLabels();
    allowEdits(an_editable);
  }
  
  /**
   * Allows the inputs to be editable fields. 
   * @param the_edit_flag - true if editable.
   */
  private void allowEdits(final boolean the_edit_flag) {
    if (my_system.getCurrentUser() instanceof NonProfitUser) {
      my_auction_name_input.setEditable(false);
      my_contact_person_input.setEditable(false);
    } else {
      my_auction_name_input.setEditable(the_edit_flag);
      my_contact_person_input.setEditable(the_edit_flag);
    }
    
    my_contact_phone_input.setEditable(the_edit_flag);
    my_intake_person_input.setEditable(the_edit_flag);
    my_auction_date_input.setEditable(the_edit_flag);
    my_start_time_input.setEditable(the_edit_flag);
    my_duration_input.setEditable(the_edit_flag);
    
    my_anticipated_input.setEditable(the_edit_flag);
    my_comments_input.setEditable(the_edit_flag);
    
    my_save.setVisible(the_edit_flag);
    
  }

  /**
   * Creates the auction used for editing.
   */
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
  
  /**
   * Initializes the input entries.
   */
  private void initializeInput() {
    my_auction_name_input.setText(my_name);
    my_contact_person_input.setText(my_contact_p);
    my_contact_phone_input.setText(my_contact_num);
    my_intake_person_input.setText(my_intake);
    final String formatted_date = "" + (my_date.get(Calendar.MONTH) + 1) 
        + '/' + my_date.get(Calendar.DAY_OF_MONTH) 
        + '/' + my_date.get(Calendar.YEAR);
    my_auction_date_input.setText(formatted_date);
    my_start_time_input.setText("" + my_date.get(Calendar.HOUR_OF_DAY));
    final Integer i = my_duration;
    my_duration_input.setText(i.toString());
    final Integer j = my_items;
    my_current_input.setText(j.toString());
    my_anticipated_input.setText(j.toString());
    my_comments_input.setText(my_comment);
    
    allowEdits(false);
  }
  
//  /**
//   * Sets up the edit button.
//   */
//  private void setupEditButton() {
//    my_edit_info.setMnemonic(KeyEvent.VK_E);
//    my_edit_info.setToolTipText("Edit the Auction Data");
//    my_edit_info.addActionListener(new ActionListener() {
//      /* (non-Javadoc)
//       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//       * 
//       * Clicking edit button sets all text fields to be editable.
//       */
//      public void actionPerformed(final ActionEvent the_event) {
//        //editInfo();
//        allowEdits(true);
//      }
//    });
//  }
  
  /**
   * Sets up the save button.
   */
  private void setupSaveButton() {
    my_save.setMnemonic(KeyEvent.VK_S);
    my_save.setToolTipText("Save the Auction Data");
    my_save.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        saveAuction();
      }
    });
  }
  
  /**
   * Sets up the view inventory button.
   */
  private void setupViewInventoryButton() {
    my_view_inventory.setMnemonic(KeyEvent.VK_V);
    my_view_inventory.setToolTipText("View the Inventory of the Selected Auction");
    // If you are creating a new auction then you can't view the inventory.
    if (my_editable) {
      my_view_inventory.setEnabled(false);
    }
    my_view_inventory.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        viewInventory();
      }
    });
  }
  
  /**
   * Sets up the view inventory button.
   */
  private void setupDeleteButton() {
    my_delete.setMnemonic(KeyEvent.VK_D);
    my_delete.setToolTipText("Delete this auction");
    my_delete.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        final int confirm = JOptionPane.showConfirmDialog(null,
            "Do you want to delete this auction?",
            MESSAGE_WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          if (my_system.deleteAuction(my_auction)) {
            JOptionPane.showMessageDialog(null, "Delete success!",
                MESSAGE_WINDOW_TITLE, JOptionPane.PLAIN_MESSAGE);
            my_app_frame.showAuctionList();
          } else {
            JOptionPane.showMessageDialog(null, 
                "Delete fail!", ERROR, JOptionPane.ERROR_MESSAGE);
          }
        }
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
  
  /**
   * Sets up the input entries.
   */
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
  }
  
  /**
   * Sets the fonts of the labels.
   */
  private void setFonts() {
    AUCTION_TITLE.setFont(new Font(ARIAL, Font.BOLD, MAIN_FONT_SIZE));
    my_auction_number.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_auction_name.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_contact_person.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_contact_phone.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_intake_person.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_auction_date.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_start_time.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_duration_time.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_number_of_items.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_current_items.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_anticipated_items.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_auction_comments.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
  }
  
  /**
   * Creates the labels that display the controls.
   */
  private void createAuctionLabels() {
    final JPanel center = new JPanel(new BorderLayout());
    final JPanel west = new JPanel();
    west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
    final JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
    final JPanel east = new JPanel(new GridLayout(0, 1));
    final JPanel south = new JPanel(new BorderLayout());
    final JPanel southcomment = new JPanel(new FlowLayout());
    final JPanel southset = new JPanel(new FlowLayout());
    
    setFonts();
    
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
    southcomment.add(my_comments_input);
    southcomment.add(my_save);
    south.add(southcomment, BorderLayout.CENTER);
    
    //southset.add(my_edit_info, BorderLayout.EAST);
    southset.add(my_view_inventory, BorderLayout.CENTER);
    
    String user_name = my_user.getFirstName() + " ";
    user_name += my_user.getLastName();
    if (user_name.equals(my_auction.getContactPerson()) && !my_editable) {
      southset.add(my_delete, BorderLayout.WEST);
    } 
    southset.add(my_back, BorderLayout.WEST);
    
    south.add(southset, BorderLayout.SOUTH);
    
    add(north, BorderLayout.NORTH);
    add(center, BorderLayout.WEST);
    add(east, BorderLayout.EAST);
    add(south, BorderLayout.SOUTH);
    
    createInput();

  }
  
  /**
   * Creates the input GUI elements.
   */
  private void createInput() {
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
    
    my_current_input.setEditable(false);
    
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_anticipated_input);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    
    add(textinput, BorderLayout.CENTER);
  }
  
  /**
   * Saves the new/edited auction.
   */
  private void saveAuction() {    // Return an auction    
    final String auction_name = my_auction_name_input.getText().trim();
    final String contact_person = my_contact_person_input.getText().trim();
    final String contact_phone = my_contact_phone_input.getText().trim();
    final String intake_person = my_intake_person_input.getText().trim();
    final String start_time_text = my_start_time_input.getText();
    final String duration_text = my_duration_input.getText();
    
    int start_time = -1;
    try {
      start_time = Integer.parseInt(start_time_text);
    } catch (final NumberFormatException nfe) {
      JOptionPane.showMessageDialog(null, 
          "Wrong type of number format or negative number", 
          ERROR, JOptionPane.ERROR_MESSAGE);
    }
    
    int duration = -1;
    try {
      duration = Integer.parseInt(duration_text);
    } catch (final NumberFormatException nfe) {
      JOptionPane.showMessageDialog(null, 
          "Cannot equal negative number or wrong input format", 
          ERROR, JOptionPane.ERROR_MESSAGE);
    }
    
    final Calendar auction_date = createDate(start_time);
    
    // first check for empty strings
    if (auction_name.length() <= 0 || contact_person.length() <= 0 
        || contact_phone.length() <= 0 
        || intake_person.length() <= 0) {
      JOptionPane.showMessageDialog(null, 
          "Fields cannot be blank", 
          ERROR, JOptionPane.ERROR_MESSAGE);
      
      // check to see if system reserved character is used
    } else if (intake_person.contains(RESERVED_CHARACTER)
        || my_comments_input.getText().contains(RESERVED_CHARACTER)) {
      JOptionPane.showMessageDialog(null, 
          "Invalid character: " + RESERVED_CHARACTER, 
          ERROR, JOptionPane.ERROR_MESSAGE);
      
    } else if (!testPhone(contact_phone)) {
    
      JOptionPane.showMessageDialog(null, 
          "invalid phone number", ERROR, JOptionPane.ERROR_MESSAGE);
    // then check for a bad date
    } else if (auction_date == null) {
      JOptionPane.showMessageDialog(null, 
          DATE_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
    
      // make sure start time is valid
    } else if (start_time_text.length() < 1 
        || my_start_time_input.getText().length() > 2 
        || !Character.isDigit(start_time_text.charAt(0))
        || !Character.isDigit(start_time_text.charAt(start_time_text.length() - 1))
        || start_time < 0 
        || start_time > new GregorianCalendar().getActualMaximum(Calendar.HOUR_OF_DAY)) {
      JOptionPane.showMessageDialog(null, 
          "Start time must be between 0 and 23 hours.", 
           ERROR, JOptionPane.ERROR_MESSAGE);      
    
    // check duration for valid number
    } else if (duration < 1 || duration > TWELVE) {
      JOptionPane.showMessageDialog(null, 
          "Duration must from 0 to 12 hours.", 
           ERROR, JOptionPane.ERROR_MESSAGE);
    
    // otherwise save the auction
    } else {
      my_auction.setAuctionName(auction_name);
      my_auction.setContactPerson(contact_person);    
      my_auction.setContactPhone(contact_phone);
      my_auction.setIntakePerson(intake_person);
      my_auction.setAuctionDate(auction_date);
      my_auction.setAuctionDuration(duration);    
      my_auction.setComments(my_comments_input.getText().trim());
      
      final String is_success = my_system.addAuction(my_auction);
      if (is_success == null) {
        my_app_frame.showAuctionList();
        allowEdits(false);
      } else {
        JOptionPane.showMessageDialog(null, 
            "error: " + is_success, 
            ERROR, JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  /**
   * test if a string is a valid phone number.
   * @param a_phone - the phone number.
   * @return is_valid - if it is a correct number.
   */
  private boolean testPhone(final String a_phone) {
    boolean is_valid = true;
    final String[] phone = a_phone.split("-");
    if (a_phone.length() != TWELVE || phone.length != THREE) {
      is_valid = false;
    } else {
      try {
        Integer.parseInt(phone[0]);
        Integer.parseInt(phone[1]);
        Integer.parseInt(phone[2]);
      } catch (final NumberFormatException ex) {
        is_valid = false;
      }
    }
    return is_valid;
  }
  
  /**
   * Creates a date object.
   * @param the_start_time TODO
   * @return - Calendar object.
   */
  private Calendar createDate(final int the_start_time) {
    //add last auction on 2014-4-30 used to test BR #3
    final Calendar cal = Calendar.getInstance();
    final String str = my_auction_date_input.getText().trim();
    
    try {
      final String[] tokens = str.split("/");
      
      if (tokens.length != 1 + 1 + 1) {
        JOptionPane.showMessageDialog(null, 
            DATE_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
        return Calendar.getInstance();
      }
      
      final int month = Integer.parseInt(tokens[0]);
      final int day = Integer.parseInt(tokens[1]);
      final int year = Integer.parseInt(tokens[2]);
      
      
      boolean is_valid_date = true;
      
      if (month <= 0 || month > TWELVE) {
        is_valid_date = false;
      } else if (month == 2) {
        if ((year % (FIVE - 1) == 0 && year % (TEN * TEN) != 0)
          || year % FOUR_HUNDRED == 0) { //leap year
          // 29 Days
          if (day <= 0 || day > (THIRTY - 1)) { 
            is_valid_date = false;
          }
        } else {
          // 28 Days
          if (day <= 0 || day > (THIRTY - 2)) {
            is_valid_date = false;
          }
        }
      } else if (month == 1 || month == THREE || month == FIVE || month == SEVEN
          || month == EIGHT || month == TEN || month == TWELVE) {
        // 31 Days
        if (day <= 0 || day > (THIRTY + 1)) {
          is_valid_date = false;
        }
      } else {
        // 30 Days
        if (day <= 0 || day > THIRTY) {
          is_valid_date = false;
        }
      }
      
      if (!is_valid_date) {
        JOptionPane.showMessageDialog(null, 
            DATE_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
        return Calendar.getInstance();
      }
      
      cal.set(year, month - 1, day, the_start_time , 0);
    } catch (final NumberFormatException e) {
      JOptionPane.showMessageDialog(null, 
          DATE_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
    } catch (final IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(null, 
          DATE_ERROR, ERROR, JOptionPane.ERROR_MESSAGE);
    }

    return cal;
  }
  
  /**
   * Shows the inventory view.
   */
  private void viewInventory() {  // Return a List<Items>
    my_app_frame.showInventory(my_auction);
    
  }  
}






