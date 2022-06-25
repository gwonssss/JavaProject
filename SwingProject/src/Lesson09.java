import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Lesson09 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lesson09 window = new Lesson09();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lesson09() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 930, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 916, 651);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\ducco\\Desktop\\ws\\SwingProject\\img\\image_Test.jpg"));
		btnNewButton.setSelectedIcon(new ImageIcon("C:\\Users\\ducco\\Desktop\\ws\\SwingProject\\img\\image_Test.jpg"));
		btnNewButton.setBounds(160, 207, 168, 129);
		btnNewButton.setPressedIcon(new ImageIcon("C:\\Users\\ducco\\Desktop\\ws\\SwingProject\\img\\image_Test2.jpg"));
		panel.add(btnNewButton);
	}
}
