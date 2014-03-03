import gui.ApplicationFrame;

import java.awt.EventQueue;

import user.AuctionCentralStaff;
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
  private static User DEFAULT_USER = new AuctionCentralStaff("xx_username_xx",
      "password", "John", "Smith");
  
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
        final ApplicationFrame gui = new ApplicationFrame(DEFAULT_USER);
        gui.start();
      }
    });
  }
}
