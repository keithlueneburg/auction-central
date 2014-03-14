package gui;

import bidding.Address;
import bidding.CreditCard;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import system.AuctionCentralSystem;
import user.Bidder;
import user.Guest;
import user.User;

/**
 * Class: RegistrationPanel
 * 
 * Panel for registering a new user.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * 
 * University of Washington, Tacoma
 * 
 * Winter 2014
 * 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 * 
 * @version 3/13/2014
 */
@SuppressWarnings("serial")
public class RegistrationPanel extends JPanel {
  
  /**
   * Message to display when a user fails to fill out a text field.
   */
  private static final String BLANK_FIELDS_MESSAGE = "Fields cannot be blank";

  /**
   * Title text for error popup window. 
   */
  private static final String ERROR_MESSAGE_TITLE_TEXT = "Error";

  /**
   * Maximum numeric value for month field.
   */
  private static final int MAX_MONTH_VALUE = 12;

  /**
   * Height of east panel.
   */
  private static final int EAST_PANEL_HEIGHT = 680;

  /**
   * Width of east panel.
   */
  private static final int EAST_PANEL_WIDTH = 120;

  /** The default height of the panel. */
  private static final int DEFAULT_HEIGHT = 680;
    
  /** The default width of the panel. */
  private static final int DEFAULT_WIDTH = 824;
    
  /** The default size for this JPanel. */
  private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
  
  /** The default font for the labels. */
  private static final String ARIAL = "Arial";
  
  /** The default font size for the instruction labels. */
  private static final int INSTRUCTION_FONT_SIZE = 12;
  
  /** The default font size for the instruction labels. */
  private static final int MAIN_FONT_SIZE = 24;
  
  /** The default box height. */
  private static final int BOX_HEIGHT = 63;

  /** The default box height for input spacing. */
  private static final int BOX_HEIGHT_TWO = 58;
  
  /** The default box width. */
  private static final int BOX_WIDTH = 5;
  
  /** The default border, a black line. */
  private static final Border BLACK_LINE = 
      BorderFactory.createLineBorder(new JPanel().getBackground(), 15);
  
  /** The label that shows the auction's name. */
  private static final JLabel FORM_TITLE = new JLabel("Registration:");

  /**
   * System reserved character. Strings should not contain this. 
   */
  private static final String RESERVED_CHARACTER = "`";
  
  /////////LABELS////////
  
  /** Label for username field. */
  private final JLabel my_username_label = new JLabel("Username: ");

  /** Label for password field. */
  private final JLabel my_password_label = new JLabel("Password: ");
  
  /** Label for first name field. */
  private final JLabel my_first_name_label = new JLabel("First name: ");

  /** Label for last name field. */
  private final JLabel my_last_name_label = new JLabel("Last name: ");
  
  /** Label for credit card number field. */
  private final JLabel my_card_num_label = new JLabel("Card # ");
    
  /** Label for credit card CSC field. */
  private final JLabel my_csc_label = new JLabel("CSC: ");
    
  /** Label for credit card expiration date field. */
  private final JLabel my_exp_date_label = new JLabel("Expiration date:     ");

  /////////TEXT FIELDS////////
  
  /** Username field. */
  private final JFormattedTextField my_username_field = new JFormattedTextField();

  /** Password field. */
  private final JFormattedTextField my_password_field = new JFormattedTextField();
  
  /** First name field. */
  private final JFormattedTextField my_first_name_field = new JFormattedTextField();

  /** Last name field. */
  private final JFormattedTextField my_last_name_field = new JFormattedTextField();
  
  /** Card number input field. */
  private final JFormattedTextField my_card_num_field = new JFormattedTextField();
  
  /** Card CSC input field. */
  private final JFormattedTextField my_csc_field = new JFormattedTextField();
  
  /** Expiration date input field. */
  private final JFormattedTextField my_exp_date_field = new JFormattedTextField();
  
  /////////BUTTONS////////
  
  /** The button to click to go back to the main screen. */
  private final JButton my_back_button = new JButton("Back");
    
  /** The button used to save the card information. */
  private final JButton my_save_button = new JButton("Save");
  
  /** A reference to the main application frame. */
  private ApplicationFrame my_app_frame;

  /**
   * The system this panel belongs to.
   */
  private AuctionCentralSystem my_system;
  
  //modify by Kaiyuan
  /**
   * The user logged into the system.
   */
  private User my_user;
  
  /**
   * Sets up the RegistrationPanel.
   * 
   * @param the_frame - the frame this panel is attached to.
   * @param the_system The system instance running behind the program.
   * @param a_user The user logged into the system.
   * <dt><b>Preconditions:</b><dd>
   * The user can only be Bidder of Guest
   * If the user is a Bidder, he must not be registered.
   */
  public RegistrationPanel(final AuctionCentralSystem the_system, 
      final ApplicationFrame the_frame, final User a_user) {
    
    super(new BorderLayout());
    setPreferredSize(DEFAULT_SIZE);
    setBorder(BLACK_LINE);
    setFocusable(true);
    
    my_system = the_system;
    my_app_frame = the_frame;
    
    //modify by Kaiyuan
    my_user = a_user;
    
    setupBackButton();
    setupSaveButton();
        
    setupInput();
    createLabels();
    
    if (my_user instanceof Bidder) {
      bidderInput();
      my_back_button.setEnabled(false);
    }
    
  }

  /**
   * Tell accessibility tools about label/textfield pairs.
   */
  private void setupInput() {
    my_username_label.setLabelFor(my_username_field);
    my_password_label.setLabelFor(my_password_field);
    my_first_name_label.setLabelFor(my_first_name_field);
    my_last_name_label.setLabelFor(my_last_name_field);
    my_card_num_label.setLabelFor(my_card_num_field);
    my_csc_label.setLabelFor(my_csc_field);
    my_exp_date_label.setLabelFor(my_exp_date_field);
  }

  /**
   * Creates the labels that display the controls.
   */
  private void createLabels() {
    final JPanel center = new JPanel(new BorderLayout());
    final JPanel west = new JPanel();
    west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
    final JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
    final JPanel east = new JPanel(new GridLayout(1, 1));
    east.setPreferredSize(new Dimension(EAST_PANEL_WIDTH, EAST_PANEL_HEIGHT));
    final JPanel south = new JPanel(new BorderLayout());
    final JPanel southcomment = new JPanel(new FlowLayout());
    final JPanel southset = new JPanel(new FlowLayout());
    
    FORM_TITLE.setFont(new Font(ARIAL, Font.BOLD, MAIN_FONT_SIZE));
    my_username_label.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_password_label.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_first_name_label.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_last_name_label.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_card_num_label.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_csc_label.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
    my_exp_date_label.setFont(new Font(ARIAL, Font.BOLD, INSTRUCTION_FONT_SIZE));
        

    north.add(FORM_TITLE, BorderLayout.CENTER);

   
    west.add(my_username_label);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_password_label);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_first_name_label);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_last_name_label);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));

    west.add(my_card_num_label);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_csc_label);
    west.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT)));
    west.add(my_exp_date_label);
    
//    final JPanel auctionnum = new JPanel(new FlowLayout());
//    auctionnum.add(my_auction_number, BorderLayout.CENTER);
//    auctionnum.add(my_number);
//    east.add(auctionnum);
    

    
    
    center.add(west, BorderLayout.CENTER);

    southcomment.add(my_save_button);
    south.add(southcomment, BorderLayout.CENTER);
    
    //southset.add(my_edit_info, BorderLayout.EAST);
    southset.add(my_back_button, BorderLayout.WEST);
    south.add(southset, BorderLayout.SOUTH);
    
    add(north, BorderLayout.NORTH);
    add(center, BorderLayout.WEST);
    add(east, BorderLayout.EAST);
    add(south, BorderLayout.SOUTH);
    
    
    final JPanel textinput = new JPanel();
    textinput.setLayout(new BoxLayout(textinput, BoxLayout.Y_AXIS));
    
    textinput.add(my_username_field);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_password_field);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_first_name_field);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_last_name_field);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_card_num_field);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_csc_field);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    textinput.add(my_exp_date_field);
    textinput.add(Box.createRigidArea(new Dimension(BOX_WIDTH, BOX_HEIGHT_TWO)));
    
    add(textinput, BorderLayout.CENTER);
  }
  
  /**
   * Sets up the back button.
   */
  private void setupBackButton() {
    my_back_button.setMnemonic(KeyEvent.VK_B);
    my_back_button.setToolTipText("Go to the previous screen");
    my_back_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        // go back to previous page
        // needs to be passed a previous page
        my_app_frame.showAuctionList();
      }
    });
  }
  
  /**
   * Sets up the save button.
   */
  private void setupSaveButton() {
    my_save_button.setMnemonic(KeyEvent.VK_S);
    my_save_button.setToolTipText("Save the card info");
    my_save_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        saveUser();
      }
    });
  }

  /**
   * Save the user to the system.
   */
  private void saveUser() {

    final String username = my_username_field.getText().trim();
    final String password = my_password_field.getText().trim();
    final String first_name = my_first_name_field.getText().trim();
    final String last_name = my_last_name_field.getText().trim();
    
    final String card_num = my_card_num_field.getText();
    final String csc = my_csc_field.getText();
    final String exp_date_string = my_exp_date_field.getText();
    
    final Address address = new Address("street", 0, "city", "state", 98401);
    
    boolean username_is_taken = false;
    boolean name_is_taken = false;
    
    //modify by Kaiyuan
    if (my_user instanceof Guest) {
      //check usernames
      // If system returns a user, the username is taken.
      username_is_taken = my_system.isValidUser(username) != null;
      
      //check if first/last name combos are taken
      name_is_taken = my_system.duplicateFirstLastName(first_name, last_name);
    }
    
    ///check if date is valid and in future
    boolean is_valid_date = true;
    final Calendar today = Calendar.getInstance();
    int month = -1;
    int year = -1;
    
    // try to split the date string into tokens
    try {
      final String[] tokens = exp_date_string.split("/");
      
      // we should have two tokens (MM/YYYY)
      if (tokens.length != 2) {
        is_valid_date = false;
      } else {
        month = Integer.parseInt(tokens[0]);
        year = Integer.parseInt(tokens[1]);
      }
      
      // see if the date is in the future
      if (year < today.get(Calendar.YEAR)
          || (year == today.get(Calendar.YEAR)
          && month <= today.get(Calendar.MONTH) + 1)) {
        is_valid_date = false;
      // make sure month value is from 1 to 12
      } else if (month < 1 || month > MAX_MONTH_VALUE) {
        is_valid_date = false;
      }
    
    } catch (final Exception e) {
      is_valid_date = false;
    }
    
    // Create an expiration date if input was valid.
    final Calendar exp_date = Calendar.getInstance();
    if (is_valid_date) {
      exp_date.set(Calendar.DAY_OF_MONTH, 1);
      exp_date.set(Calendar.MONTH, month - 1);
      exp_date.set(Calendar.YEAR, year);
    }
      
    //modify by Kaiyuan
    if (exp_date.compareTo(Calendar.getInstance()) <= 0) {
      JOptionPane.showMessageDialog(null, "Expiration date must be in the future.",
          ERROR_MESSAGE_TITLE_TEXT, JOptionPane.ERROR_MESSAGE);
    } else {
    
      CreditCard card = null;
      String card_message = "errrror";
      try {
        card = new CreditCard(Long.parseLong(card_num), exp_date, Integer.parseInt(csc),
            username, address, "Bank");
      } catch (final NumberFormatException nfe) {
        card_message = BLANK_FIELDS_MESSAGE;
      } catch (final IllegalArgumentException iae) {
        card_message = iae.getMessage();
      }
      
      if (username.length() < 1 || password.length() < 1 || first_name.length() < 1 
          || last_name.length() < 1) {
  
        JOptionPane.showMessageDialog(null, 
            BLANK_FIELDS_MESSAGE, 
            ERROR_MESSAGE_TITLE_TEXT, JOptionPane.ERROR_MESSAGE);
      
      } else if (username.contains(RESERVED_CHARACTER)
          || password.contains(RESERVED_CHARACTER)
          || first_name.contains(RESERVED_CHARACTER)
          || last_name.contains(RESERVED_CHARACTER)) {
        
        JOptionPane.showMessageDialog(null, 
            "Invalid character: `", 
            ERROR_MESSAGE_TITLE_TEXT, JOptionPane.ERROR_MESSAGE);
      } else if (!is_valid_date) {
      
        JOptionPane.showMessageDialog(null, 
            "Invalid date. Must be in the style of MM/YYYY, and after the current month.", 
            ERROR_MESSAGE_TITLE_TEXT, JOptionPane.ERROR_MESSAGE);
      } else if (username_is_taken) {
      
        JOptionPane.showMessageDialog(null, 
            "Username is taken.", 
            ERROR_MESSAGE_TITLE_TEXT, JOptionPane.ERROR_MESSAGE);
      
      } else if (name_is_taken) {
        JOptionPane.showMessageDialog(null, 
            "This name is already in the database.", 
            ERROR_MESSAGE_TITLE_TEXT, JOptionPane.ERROR_MESSAGE);
      
      } else if (card == null) {
        JOptionPane.showMessageDialog(null, 
            card_message, 
            ERROR_MESSAGE_TITLE_TEXT, JOptionPane.ERROR_MESSAGE);
      } else {
        
        if (my_user instanceof Guest) {
          final Bidder new_bidder = new Bidder(username, password, first_name, last_name);
          new_bidder.register(card, address);
         
          my_system.addUser(new_bidder);
          
          my_app_frame.disableRegistration();
          
          my_back_button.doClick();
        } else { //modify by Kaiyuan
          ((Bidder) my_user).register(card, address);
          my_back_button.setEnabled(true);
          my_back_button.doClick();
        }
      }
    }
    
  }
  
  //modify by Kaiyuan
  /**
   * Set up the bidder input fields.
   */
  private void bidderInput() {
    my_username_field.setText(my_user.getUsername());
    my_username_field.setEditable(false);
    my_password_field.setText(my_user.getPassword());
    my_password_field.setEditable(false);
    my_first_name_field.setText(my_user.getFirstName());
    my_first_name_field.setEditable(false);
    my_last_name_field.setText(my_user.getLastName());
    my_last_name_field.setEditable(false);
  }
}
