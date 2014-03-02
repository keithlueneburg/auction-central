import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1024, 680);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 680));
		panel.setBackground(Color.BLUE);
		
		f.add(panel, BorderLayout.WEST);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
	}
}