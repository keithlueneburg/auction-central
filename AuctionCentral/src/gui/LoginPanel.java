package.gui

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LoginPanel extends JFrame{


	/** The default height of the frame. */
	private static final int DEFAULT_HEIGHT = 680;

	/** The default width of the frame. */
	private static final int DEFAULT_WIDTH = 824;

	/** The default size for this JPanel. */
	private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

	/**
	 * Required for serializable class (extends JFrame).
	 */
	private static final long serialVersionUID = 8363319605363170281L;

	/**
	 * creates a LoginPanel.
	 */
	public LoginPanel(){
		super("Login");
		setSize(DEFAULT_SIZE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JButton button1 = new JButton("Login");
		button1.addActionListener(new ExitListener());
		add(button1, BorderLayout.CENTER);
		setVisible(true);
	}

	/** An ActionListener for the exit item. */
	public class ExitListener implements ActionListener 
	{

		/**
		 * Closes the JFrame.
		 * @param the_event the event 
		 */
		public void actionPerformed(final ActionEvent the_event)
		{
			dispose();
		}
	}
}
