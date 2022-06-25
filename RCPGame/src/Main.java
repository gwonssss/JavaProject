import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	static class MyFrame extends JFrame {
		//창 크기 조절
		static final int CANVAS_WIDTH = 800;
		static final int CANVAS_HEIGHT = 600;
		
		static firstPanel firstPanel = null;
		static player1 player1 = null;
		
		public MyFrame(String title) {
			super(title);
			this.setVisible(true);
			this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
			this.setLocationRelativeTo(null);
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			firstPanel = new firstPanel();
			this.add("Center", firstPanel);
		}
		public class player1 extends JPanel {
			public player1() {
				setSize(CANVAS_WIDTH,CANVAS_HEIGHT);
				setBackground(Color.GREEN);
			}
		}
		
		public class firstPanel extends JPanel {
			
			public firstPanel() {
				setSize(CANVAS_WIDTH,CANVAS_HEIGHT);
				setBackground(Color.BLACK);
				JButton startBtn = new JButton("Start");
				add(startBtn);
				startBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						add("Center",player1);
						
					}
					
				});
			}
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame("RSP Game");
	}

}
