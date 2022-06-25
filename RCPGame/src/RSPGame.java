import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RSPGame {

	private JFrame frmRspgame;
	int player;
	int player2;
	int computer;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RSPGame window = new RSPGame();
					window.frmRspgame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RSPGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRspgame = new JFrame();
		frmRspgame.getContentPane().setBackground(Color.WHITE);
		frmRspgame.setFont(new Font("Dialog", Font.BOLD, 12));
		frmRspgame.setTitle("RSPGame");
		frmRspgame.setBounds(100, 100, 797, 701);
		frmRspgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRspgame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 783, 664);
		frmRspgame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("startBtn");
		btnNewButton_3.setBounds(324, 117, 95, 23);
		panel_1.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 783, 664);
		frmRspgame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(299, 120, 95, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(103, 373, 95, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(505, 370, 95, 23);
		panel.add(btnNewButton_2);
	}
}
