import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class RSPFrame {
	
	static int rsp = 0;
	static WithPlayer vsPlayerGame = new WithPlayer();
	static WithComputer vsComputerGame = new WithComputer(); 
	static int betCoin;
	static boolean errorCount;

	
	static class Frame extends JFrame{
		/**
		 * 
		 */
		private static final long serialVersionUID = -728667491342521363L;
		//전체 창 크기
		static final int CANVAS_WIDTH = 700; 
		static final int CANVAS_HEIGHT = 500;
		/////////////////////////////////////
		static StartPanel startPanel = null;
		static ChooseType choosePlayType = null;
		static VsPlayer vsPlayPanel = null;
		static VsCom vsComputerPanel = null;
		static JTextArea player1Result;
		static int playerTurn;
		static int startCoin = 25;
		
		ImageIcon scissor = new ImageIcon(getClass().getClassLoader().getResource("chan_s.png"));
		ImageIcon paper = new ImageIcon(getClass().getClassLoader().getResource("pon_s.png"));
		ImageIcon rock = new ImageIcon(getClass().getClassLoader().getResource("ken_s.png"));
		
		static class VsCom extends JPanel {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2400218363758017341L;
			private Image img;
			
			public VsCom(Image img) {
				this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
				this.setBackground(Color.MAGENTA);
				this.setLayout(null);
				this.img = img;
				this.setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
			}
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
			}
		}
		static class VsPlayer extends JPanel {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3545964741464478688L;
			private Image img;
			
			public VsPlayer(Image img) {
				this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
				this.setBackground(Color.GREEN);
				this.setLayout(null);
				this.img = img;
				this.setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
			}
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
			}
		}
	
		static class StartPanel extends JPanel {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2960074072999720079L;
			private Image img;
			
			public StartPanel(Image img) {
				this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
				this.setBackground(Color.BLACK);
				this.setLayout(null);
				this.img = img;
				this.setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));	
			}
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
			}
		}
		static class ChooseType extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 4740677934846799727L;
			private Image img;
			public ChooseType(Image img) {
				this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
				this.setBackground(Color.DARK_GRAY);
				this.setLayout(null);
				this.img = img;
				this.setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
			}
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
			}
		}
		
		
		public Frame() {
			/* 프레임 설정 */
			this.setTitle("가위바위보 게임!");
			this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			Container contentPane = this.getContentPane();
			contentPane.setLayout(null);
			this.setVisible(true);
			
			/* 패널 및 버튼 설정들 */
			startPanel = new StartPanel(new ImageIcon(getClass().getClassLoader().getResource("re_700.png")).getImage()); //시작 페이지
			this.add(startPanel);
			choosePlayType = new ChooseType(new ImageIcon(getClass().getClassLoader().getResource("playTypeChoice.jpg")).getImage()); //플레이타입선택페이지
			this.add(choosePlayType);
			vsPlayPanel = new VsPlayer(new ImageIcon(getClass().getClassLoader().getResource("playPanel.jpg")).getImage()); //vsPlayer선택 플레이페이지
			this.add(vsPlayPanel);
			vsComputerPanel = new VsCom(new ImageIcon(getClass().getClassLoader().getResource("playPanel.jpg")).getImage()); //vsComputer선택 플레이페이지
			this.add(vsComputerPanel);
			
			JButton startButton = new JButton("INSERT COIN!");
			startButton.setBounds(CANVAS_WIDTH/2-95 , CANVAS_HEIGHT/2+100, 200, 50);
			startButton.setLayout(null);
			startPanel.add(startButton);
			startPanel.setVisible(true);
			choosePlayType.setVisible(false);
			vsPlayPanel.setVisible(false);
			vsComputerPanel.setVisible(false);
			JButton vsPlayerBtn = new JButton("2인 플레이");
			JButton vsComputerBtn = new JButton("1인 플레이");
			vsPlayerBtn.setLayout(null);
			vsComputerBtn.setLayout(null);
			vsPlayerBtn.setBounds(150, 290, 100, 60);
			vsComputerBtn.setBounds(470, 290, 100, 60);
			choosePlayType.add(vsPlayerBtn);
			choosePlayType.add(vsComputerBtn);
			
			/* 버튼 추가 */
			JButton scissorBtn = new JButton(scissor);
			JButton rockBtn = new JButton(rock);
			JButton paperBtn = new JButton(paper);
			JButton scissorBtn2 = new JButton(scissor);
			JButton rockBtn2 = new JButton(rock);
			JButton paperBtn2 = new JButton(paper);
			JButton goLastBtn = new JButton("돌아가기");
			JButton goLastBtn2 = new JButton("돌아가기");
			
			/* vsPlayer 히스토리 로그 */
			player1Result = new JTextArea("START COIN : " + startCoin + "\n");
			JScrollPane scrollResult = new JScrollPane(player1Result);
			scrollResult.setBounds(374 , 75, 300, 350);
			vsPlayPanel.add(scrollResult);
			player1Result.setEditable(false);
			
			/* vsComputer 히스토리 로그 */
			JTextArea playResult = new JTextArea("Computer와 대결합니다. \n 게임 룰 \n 시작 코인은 50개입니다.\n 100개 달성시 승리하게 됩니다. \n "
					+ "3연승시 추가로 10코인을 받을 수 있습니다.\n");
			JScrollPane scrollResult2 = new JScrollPane(playResult);
			scrollResult2.setBounds(374 , 75, 300, 350);
			vsComputerPanel.add(scrollResult2);
			playResult.setEditable(false);
			
			/* 패널에 버튼들 추가 */
			vsPlayPanel.add(goLastBtn);
			vsPlayPanel.add(scissorBtn);
			vsPlayPanel.add(rockBtn);
			vsPlayPanel.add(paperBtn);
			vsComputerPanel.add(scissorBtn2);
			vsComputerPanel.add(rockBtn2);
			vsComputerPanel.add(paperBtn2);
			vsComputerPanel.add(goLastBtn2);
			
			/* 가위바위보 버튼 이미지 설정 */
			scissorBtn.setBorderPainted(false);
			rockBtn.setBorderPainted(false);
			paperBtn.setBorderPainted(false);
			scissorBtn.setContentAreaFilled(false);
			rockBtn.setContentAreaFilled(false);
			paperBtn.setContentAreaFilled(false);
			scissorBtn.setFocusPainted(false);
			rockBtn.setFocusPainted(false);
			paperBtn.setFocusPainted(false);
			
			scissorBtn2.setBorderPainted(false);
			rockBtn2.setBorderPainted(false);
			paperBtn2.setBorderPainted(false);
			scissorBtn2.setContentAreaFilled(false);
			rockBtn2.setContentAreaFilled(false);
			paperBtn2.setContentAreaFilled(false);
			scissorBtn2.setFocusPainted(false);
			rockBtn2.setFocusPainted(false);
			paperBtn2.setFocusPainted(false);
			
			/* 가위바위보버튼 위치설정 */
			scissorBtn.setBounds(125, 120, 150, 150);
			rockBtn.setBounds(45, 270, 150, 150);
			paperBtn.setBounds(205, 270, 150, 150);
			scissorBtn2.setBounds(125, 120, 150, 150);
			rockBtn2.setBounds(45, 270, 150, 150);
			paperBtn2.setBounds(205, 270, 150, 150);
			
			/* 이전버튼 위치 설정 */
			goLastBtn.setBounds(20,20,100,50);
			goLastBtn2.setBounds(20,20,100,50);
			
			/* 버튼클릭시 화면전환  */
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startPanel.setVisible(false);
					choosePlayType.setVisible(true);	
				}
			});
			vsPlayerBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					choosePlayType.setVisible(false);
					vsPlayPanel.setVisible(true);	
					
				}
			});
			/* 가위바위보 버튼 동작  */
			rockBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					betDialog();
					rsp = 2;
					vsPlayerGame.setPlayerChoose(rsp, betCoin);
					int errorCheck = vsPlayerGame.getError();
					int turnCheck = vsPlayerGame.getPlayerTurn();
					if(errorCheck == 0) {
						player1Result.append(vsPlayerGame.getBetLog());
						scrollResult.getViewport().setViewPosition(new Point(0,player1Result.getDocument().getLength()));
						if(turnCheck%2 > 0) {
							player1Result.append(vsPlayerGame.getWinLog());
							scrollResult.getViewport().setViewPosition(new Point(0,player1Result.getDocument().getLength()));
						}
					}
				}
				
			});
			scissorBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					betDialog();
					rsp = 1;
					vsPlayerGame.setPlayerChoose(rsp,betCoin);
					int errorCheck = vsPlayerGame.getError();
					int turnCheck = vsPlayerGame.getPlayerTurn();
					if(errorCheck == 0) {
						player1Result.append(vsPlayerGame.getBetLog());
						scrollResult.getViewport().setViewPosition(new Point(0,player1Result.getDocument().getLength()));
						if(turnCheck%2 > 0) {
							player1Result.append(vsPlayerGame.getWinLog());
							scrollResult.getViewport().setViewPosition(new Point(0,player1Result.getDocument().getLength()));
						}
					}
				}
			});
			paperBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					betDialog();
					rsp = 3;
					vsPlayerGame.setPlayerChoose(rsp,betCoin);
					int errorCheck = vsPlayerGame.getError();
					int turnCheck = vsPlayerGame.getPlayerTurn();
					if(errorCheck == 0) {
						player1Result.append(vsPlayerGame.getBetLog());
						scrollResult.getViewport().setViewPosition(new Point(0,player1Result.getDocument().getLength()));
						if(turnCheck%2 > 0) {
							player1Result.append(vsPlayerGame.getWinLog());
							scrollResult.getViewport().setViewPosition(new Point(0,player1Result.getDocument().getLength()));
						}
					}
				}
			});
			
			/*이전버튼 동작*/
			goLastBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					choosePlayType.setVisible(true);
					vsPlayPanel.setVisible(false);
					vsPlayerGame.pageCount(1);
					player1Result.setText("START COIN : " + startCoin + "\n");
				}
			});
				
			
			vsComputerBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					choosePlayType.setVisible(false);
					vsComputerPanel.setVisible(true);
				}
			});
					
			rockBtn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {	
					betDialog();
					rsp = 2;
					vsComputerGame.settingPlayer(rsp, betCoin);
					int errorCheck = vsComputerGame.getError();
					int bonusCheck = vsComputerGame.getBonusCheck();
					if(errorCheck == 0) {
						playResult.append(vsComputerGame.getPlayerRSPLog());
						playResult.append(vsComputerGame.getComputerRSPLog());
						playResult.append(vsComputerGame.getBetLog());
						playResult.append(vsComputerGame.getWinLog());
						if(bonusCheck == 1) {
							playResult.append(vsComputerGame.getBonusLog());
						}
						scrollResult2.getViewport().setViewPosition(new Point(0, playResult.getDocument().getLength()));
						
					}
				}		
			});
			scissorBtn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					betDialog();
					rsp = 1;
					vsComputerGame.settingPlayer(rsp, betCoin);
					int errorCheck = vsComputerGame.getError();
					int bonusCheck = vsComputerGame.getBonusCheck();
					if(errorCheck == 0) {
						playResult.append(vsComputerGame.getPlayerRSPLog());
						playResult.append(vsComputerGame.getComputerRSPLog());
						playResult.append(vsComputerGame.getBetLog());
						playResult.append(vsComputerGame.getWinLog());
						if(bonusCheck == 1) {
							playResult.append(vsComputerGame.getBonusLog());
						}
						scrollResult2.getViewport().setViewPosition(new Point(0, playResult.getDocument().getLength()));
					}	
				}	
			});
			paperBtn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					betDialog();
					rsp = 3;
					vsComputerGame.settingPlayer(rsp, betCoin);
					int errorCheck = vsComputerGame.getError();
					int bonusCheck = vsComputerGame.getBonusCheck();
					if(errorCheck == 0) {
						playResult.append(vsComputerGame.getPlayerRSPLog());
						playResult.append(vsComputerGame.getComputerRSPLog());
						playResult.append(vsComputerGame.getBetLog());
						playResult.append(vsComputerGame.getWinLog());
						if(bonusCheck == 1) {
							playResult.append(vsComputerGame.getBonusLog());
						}
						scrollResult2.getViewport().setViewPosition(new Point(0, playResult.getDocument().getLength()));
					}
				}		
			});
			goLastBtn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					choosePlayType.setVisible(true);
					vsComputerPanel.setVisible(false);
					vsComputerGame.pageCount(1);
					playResult.setText("Computer와 대결합니다. \n 게임 룰 \n 시작 코인은 50개입니다.\n 100개 달성시 승리하게 됩니다. \n "
							+ "3연승시 추가로 10코인을 받을 수 있습니다.\n");
					
				}
			});
		}
	}
	public static void betDialog() {
		String inputBetCoin = JOptionPane.showInputDialog("배팅할 코인의 수를 입력하세요!");
		betCoin = Integer.parseInt(inputBetCoin);
	}
	public static void player1bankruptDialog() {
		JOptionPane.showMessageDialog(null, "Player2 Win!!\nPlayer1이 모든 코인을 소진했습니다!!");
	}
	public static void player2bankruptDialog() {
		JOptionPane.showMessageDialog(null, "Player1 Win!!\nPlayer2가 모든 코인을 소진했습니다!!");
	}
	public static void errorDialog() {
		JOptionPane.showMessageDialog(null, "소지한 코인만큼만 배팅해주세요");
	}
	public static void playerLoseDialog() {
		JOptionPane.showMessageDialog(null, "LOSER");
	}
	public static void playerWinDialog() {
		JOptionPane.showMessageDialog(null, "100COIN 달성! \nGAME CLEAR!!");
	}
	public static void main(String[] args) {
		new Frame();
	}
}

