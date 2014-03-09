import gui.ApplicationFrame;

import java.awt.EventQueue;
import java.util.Observable;

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
  private static User DEFAULT_STAFF_USER = new AuctionCentralStaff("staff_dude-0101",
      "password", "John", "Smith");
  
  private static User DEFAULT_NPO_USER = new NonProfitUser("non-profit-guy",
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
        
        AuctionCentralSystem system = new AuctionCentralSystem();
        
        User user = DEFAULT_STAFF_USER;
        User login_user = null;
        
        boolean valid_login = false;

        
        while (!valid_login) {
          // show a JOptionPane to get the username.
          final String input_username = JOptionPane.showInputDialog(null, "Enter username: ", 
              "AuctionCentral Login", JOptionPane.QUESTION_MESSAGE);
          // if JOptionPane clicks 'cancel', exit
          if (input_username == null) {
            System.exit(0);
          } else {
            login_user = system.isValidUser(input_username);
          
            if (login_user != null) {
              valid_login = true;
            }
          }
            
        }
        
        RoleNotifier user_type_notifier = new RoleNotifier(system);
        user_type_notifier.changeRole(login_user);
        
        final ApplicationFrame gui = new ApplicationFrame(login_user, system);
        gui.start();
        
        
        
        
        
//      while (!valid_login) {
//      // show a JOptionPane to get the username.
//      final String input_username = JOptionPane.showInputDialog(null, "Enter username: ", 
//          "AuctionCentral Login", JOptionPane.QUESTION_MESSAGE);
//      // if JOptionPane clicks 'cancel', exit
//      if (input_username == null) {
//        System.exit(0);
//      } else if (input_username.equals("staff")) {
//        user = DEFAULT_STAFF_USER;
//      } else if (input_username.equals("nonprofit")) {
//        user = DEFAULT_NPO_USER;
//      } else if (AuctionCentralSystem.isValidUser_static(input_username)) {
//      }
//        valid_login = true;
//      }
//    final ApplicationFrame gui = new ApplicationFrame(user, system);
//    gui.start();
//    }
      }
        
      });
  }
  
  
}

/**
 * Changes the user role currently logged in to the system.
 * 
 * @author Keith Lueneburg
 * @version 3/9/2014
 *
 */
final class RoleNotifier extends Observable {

  public RoleNotifier(final AuctionCentralSystem the_system) {
    // TODO Auto-generated constructor stub
    addObserver(the_system);
  }
  
  void changeRole(User the_user) {
    setChanged();
    notifyObservers(the_user);
  }
  
}

