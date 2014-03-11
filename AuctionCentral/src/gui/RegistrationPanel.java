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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import system.AuctionCentralSystem;
import auction.Auction;


/**
 * @author luenekw
 *
 */
@SuppressWarnings("serial")

public class RegistrationPanel extends JPanel {
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

  /////////LABELS////////
  
  /** Label for username field. */
  private final JLabel my_username_label = new JLabel("Username: ");

  /** Label for password field. */
  private final JLabel my_password_label = new JLabel("Password: ");
  
  /** Label for first name field. */
  private final JLabel my_first_name_label = new JLabel("First name: ");

  /** Label for last namefield. */
  private final JLabel my_last_name_label = new JLabel("Last name: ");
  
  /** Label for creditcard number field. */
  private final JLabel my_card_num_label = new JLabel("Card # ");
    
  /** Label for creditcard CSC field. */
  private final JLabel my_csc_label = new JLabel("CSC: ");
    
  /** Label for creditcard expiration date field. */
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
  
  /**
   * Sets up the RegistrationPanel.
   * 
   * @param the_frame - the frame this panel is attached to.
   * @param the_system The system instance running behind the program.
   */
  public RegistrationPanel(final AuctionCentralSystem the_system, 
      final ApplicationFrame the_frame) {
    
    super(new BorderLayout());
    setPreferredSize(DEFAULT_SIZE);
    setBorder(BLACK_LINE);
    setFocusable(true);
    
    my_system = the_system;
    my_app_frame = the_frame;
    
    setupBackButton();
    setupSaveButton();
        
    setupInput();
    createLabels();
    
    
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
    east.setPreferredSize(new Dimension(120, 680));
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

  private void saveUser() {

    final String username = my_username_field.getText();
    final String password = my_password_field.getText();
    final String first_name = my_first_name_field.getText();
    final String last_name = my_last_name_field.getText();
    
    final String card_num = my_card_num_field.getText();
    final String csc = my_csc_field.getText();
    final String exp_date = my_exp_date_field.getText();
    
    /*
    if (fields are bad) {
      show an error....
    } else if () {
      
    } else {
      my_system.addUser();
    }
    */
  }
}
