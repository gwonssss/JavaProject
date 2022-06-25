import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class test01 {
	private JFrame frmShutTheFuck;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test01 window = new test01();
					window.frmShutTheFuck.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public test01() {
		initialize();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShutTheFuck = new JFrame();
		frmShutTheFuck.setTitle("shut the fuck up");
		frmShutTheFuck.setBounds(100, 100, 800,600);
		frmShutTheFuck.setLocationRelativeTo(null);
		frmShutTheFuck.setResizable(false);
		frmShutTheFuck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShutTheFuck.getContentPane().setLayout(null);
		
		JPanel secondPage = new JPanel();
		secondPage.setBounds(0, 0, 786, 563);
		frmShutTheFuck.getContentPane().add(secondPage);
		secondPage.setLayout(null);
		
		JButton lastBtn = new JButton("go to last");
		lastBtn.setBounds(478, 144, 95, 288);
		secondPage.add(lastBtn);
		
		JPanel firstPage = new JPanel();
		firstPage.setBounds(0, 0, 786, 563);
		frmShutTheFuck.getContentPane().add(firstPage);
		firstPage.setLayout(null);
		
		JButton nextBtn = new JButton("go to next");
		nextBtn.setBounds(109, 120, 95, 332);
		firstPage.add(nextBtn);
		
		secondPage.setVisible(false);
	
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				secondPage.setVisible(true);
				firstPage.setVisible(false);
			}
		});
		
		lastBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				firstPage.setVisible(true);
				secondPage.setVisible(false);
			}
		});
	}
}
