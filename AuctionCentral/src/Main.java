import gui.ApplicationFrame;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import system.AuctionCentralSystem;
import user.AuctionCentralStaff;
import user.NonProfitUser;
import user.User;

/**
 * Class: Main
 * 
 * Responsible for initial instantiation of GUI and program.
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
 * @version 3/2/2014
 */
public final class Main {
  //TODO: Remove DEFAULT_USER from final build (should be decided by login window)
//  private static User DEFAULT_USER = new AuctionCentralStaff("staff_dude-0101",
//      "password", "John", "Smith");
  
  private static User DEFAULT_USER = new NonProfitUser("non-profit-guy",
      "password", "Bob", "Jones", "Charity group");
  
  /**
   * Private constructor, to prevent instantiation of this class.
   */
  private Main() {
    throw new IllegalStateException();
  }

  /**
   * The main method, invokes the AuctionCentral GUI. Command line arguments are
   * ignored.
   * 
   * @param the_args Command line arguments.
   */
  public static void main(final String[] the_args) {
    EventQueue.invokeLater(new Runnable()
    {  
      @Override
      public void run() {
        boolean valid_login = false;
        while (!valid_login) {
          // show a JOptionPane to get the username.
          final String input_username = JOptionPane.showInputDialog(null, "Enter username: ", 
              "AuctionCentral Login", JOptionPane.QUESTION_MESSAGE);
          // if JOptionPane clicks 'cancel', exit
          if (input_username == null) {
            System.exit(0);
          } else if (AuctionCentralSystem.isValidUser(input_username)) {
            valid_login = true;
          }
        }
        final ApplicationFrame gui = new ApplicationFrame(DEFAULT_USER);
        gui.start();
      }
    });
  }
}
