import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BlockGame {

	static class MyFrame extends JFrame {
		//상수
		static int BALL_WIDTH = 20;
		static int BALL_HEIGHT = 20;
		static int BLOCK_ROWS = 5;
		static int BLOCK_COLUMNS = 10;
		static int BLOCK_WIDTH = 40;
		static int BLOCK_HEIGHT = 20;
		static int BLOCK_GAP = 3;
		static int BAR_WIDTH = 80;
		static int BAR_HEIGHT = 20;
		static int CANVAS_WIDTH = 400 + (BLOCK_GAP*BLOCK_COLUMNS) - BLOCK_GAP + 15;
		static int CANVAS_HEIGHT = 600;
		
		//변수
		static MyPanel myPanel = null;
		static int score = 0;
		static Timer timer = null;
		static Block[][] blocks = new Block[BLOCK_ROWS][BLOCK_COLUMNS];
		static Bar bar = new Bar();
		static Ball ball = new Ball();
		static int barXTarget = bar.x;
		static int dir = 0; 
		static int ballSpeed = 5;
		static boolean isGameFinished;
		
		
		static class Ball {
			int x = CANVAS_WIDTH / 2 - BALL_WIDTH/2;
			int y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
			int width = BALL_WIDTH;
			int height = BALL_HEIGHT;
			
			Point getCenter() {
				return new Point(x + (BALL_WIDTH/2), y + (BALL_HEIGHT/2));
			}
			Point getBottomeCenter() {
				return new Point(x + (BALL_WIDTH/2), y + (BALL_HEIGHT));
			}
			Point getTopCenter() {
				return new Point(x +(BALL_WIDTH/2), y +(BALL_HEIGHT));
			}
			Point getLeftCenter() {
				return new Point(x,y+(BALL_HEIGHT/2));
			}
			Point getRightCenter() {
				return new Point(x + (BALL_WIDTH), y+(BALL_HEIGHT/2));
			}
		}
		static class Bar{
			int x = CANVAS_WIDTH/2 - BAR_WIDTH/2;
			int y = CANVAS_HEIGHT/2 + 150;
			int width = BAR_WIDTH;
			int height = BAR_HEIGHT;
		}
		static class Block {
			int x = 0;
			int y = 0;
			int width = BLOCK_WIDTH;
			int height = BLOCK_HEIGHT;
			int color = 0; //0 : 하양 / 1: 노랑 / 2 : 파랑 / 3 : 마젠타 / 4 : 빨강
			boolean isHidden = false; // 블록 충돌 후 사라지게 만들어줌 
		}
		
		static class MyPanel extends JPanel {
			public MyPanel() {
				this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
				this.setBackground(Color.BLACK);
			}
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2d = (Graphics2D)g;
				
				drawUI(g2d);
			}
			
			private void drawUI(Graphics2D g2d) {
				for(int i = 0; i < BLOCK_ROWS; i++) {
					for(int j = 0 ; j < BLOCK_COLUMNS; j++) {
						if(blocks[i][j].isHidden) {
							continue;
						}
						if(blocks[i][j].color == 0) {
							g2d.setColor(Color.WHITE);
						}else if(blocks[i][j].color == 1) {
							g2d.setColor(Color.YELLOW);
						}else if(blocks[i][j].color == 2) {
							g2d.setColor(Color.BLUE);
						}else if(blocks[i][j].color == 3) {
							g2d.setColor(Color.MAGENTA);
						}else if(blocks[i][j].color == 4) {
							g2d.setColor(Color.RED);
						}
						g2d.fillRect(blocks[i][j].x, blocks[i][j].y, blocks[i][j].width, blocks[i][j].height);
					}
					//draw score
					g2d.setColor(Color.WHITE);
					g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
					if(isGameFinished) {
						g2d.setColor(Color.RED);
						g2d.drawString("Game Finish!", CANVAS_WIDTH/2-55, 50);
					}
					g2d.drawString("score : " + score, CANVAS_WIDTH/2-30, 20);
					
					//draw Ball
					g2d.setColor(Color.WHITE);
					g2d.fillOval(ball.x, ball.y, BALL_WIDTH, BALL_HEIGHT);
					
					//draw Bar
					g2d.setColor(Color.WHITE);
					g2d.fillRect(bar.x, bar.y, bar.width, bar.height);
				}
			}
			
		}
		
		public MyFrame(String title) {
			super(title);
			this.setVisible(true);
			this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
			this.setLocationRelativeTo(null);
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			initData();
			
			myPanel = new MyPanel();
			this.add("Center", myPanel);
			
			setKeyListener();
			startTimer();
			
		}
		public void initData() {
			for(int i = 0; i < BLOCK_ROWS; i++) {
				for(int j = 0 ; j < BLOCK_COLUMNS; j++) {
					blocks[i][j] = new Block();
					blocks[i][j].x = BLOCK_WIDTH * j + BLOCK_GAP*j;
					blocks[i][j].y = 100 + BLOCK_HEIGHT*i + BLOCK_GAP*i;
					blocks[i][j].width = BLOCK_WIDTH;
					blocks[i][j].height = BLOCK_HEIGHT;
					blocks[i][j].color = 4 - i;
					blocks[i][j].isHidden = false;
				}
			}
		}
		public void setKeyListener() {
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						System.out.println("Pressed Left Key");
						barXTarget -= 20;
						if(bar.x < barXTarget) {
							barXTarget = bar.x;
						}
					}
					else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						System.out.println("Pressed Right Key");
						barXTarget += 20;
						if(bar.x > barXTarget) {
							barXTarget = bar.x;
						}
					}
				}
			});;
		}
		public void startTimer() {
			timer = new Timer(20, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					movement();
					checkCollision(); //벽, 바랑 충돌
					checkCollisionBlock(); //블록50개 충돌
					myPanel.repaint(); //redraw
					
					isGameFinish();
				}
			});
			timer.start();
			
		}
		public void isGameFinish() {
			int count = 0;
			for(int i = 0; i < BLOCK_ROWS; i++) {
				for(int j  = 0; j < BLOCK_COLUMNS; j++) {
					Block block = blocks[i][j];
					if( block.isHidden )
						count++;
				}
			}
			if (count == BLOCK_ROWS * BLOCK_COLUMNS) {
				isGameFinished = true;
			}
		}
		public void movement() {
			if(bar.x < barXTarget) {
				bar.x += 5;
			}else if(bar.x > barXTarget) {
				bar.x -= 5;
			}
			
			if(dir == 0) { //Up_right
				ball.x += ballSpeed;
				ball.y -= ballSpeed;
			}else if(dir == 1) { //Down_right
				ball.x += ballSpeed;
				ball.y += ballSpeed;
			}else if(dir == 2) { //Up_left
				ball.x -= ballSpeed;
				ball.y -= ballSpeed;
			}else if(dir == 3) { //Down_left
				ball.x -= ballSpeed;
				ball.y += ballSpeed;
			}
			
		}
		public boolean duplRect(Rectangle rect1, Rectangle rect2) {
			return rect1.intersects(rect2);
			
		}
		public void checkCollision() {
			if(dir == 0) { //Up_right
				//wall
				if(ball.y < 0) {
					dir = 1;
				}
				if(ball.x > CANVAS_WIDTH-BALL_WIDTH) {
					dir = 2;
				}
				//bar
				//None
			}else if(dir == 1) { //Down_right
				//wall
				if(ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) {
					dir = 0;
					
					//game reset
					dir = 0;
					ball.x = CANVAS_WIDTH/2 - BALL_WIDTH/2;
					ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
					score = 0;
				}
				if(ball.x > CANVAS_WIDTH-BALL_WIDTH) {
					dir = 3;
				}
				//bar
				if(ball.getBottomeCenter().y >= bar.y) {
					if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) {
						dir = 0;
					}
				}
				
			}else if(dir == 2) { //Up_left
				//wall
				if(ball.y < 0) {
					dir = 3;
				}
				if(ball.x < 0) {
					dir = 0;
				}
				//bar
				//None
			}else if(dir == 3) { //Down_left
				//wall
				if(ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) {
					dir = 2;
					
					//game reset
					dir = 0;
					ball.x = CANVAS_WIDTH/2 - BALL_WIDTH/2;
					ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
					score = 0;
				}
				if(ball.x < 0) {
					dir = 1;
				}
				//bar
				if(ball.getBottomeCenter().y >= bar.y) {
					if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) {
						dir = 2;
					}
				}
			}
		}
		public void checkCollisionBlock() {
			//0 - upRight 1 - DownRight 2- Up-left 3 - Down-Left
			for(int i = 0; i < BLOCK_ROWS; i++) {
				for(int j = 0; j < BLOCK_COLUMNS; j++) {
					Block block = blocks[i][j];
					if(block.isHidden == false) {
						if(dir == 0) {
							if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
									 new Rectangle(block.x, block.y, block.width, block.height)) ) {
								if(ball.x > block.x+2 &&
										ball.getRightCenter().x <= block.x + block.width-2) {
									dir = 1;
								}else {
									dir = 2;
								}
								block.isHidden = true;
								if(block.color == 0) {
									score += 10;
								}
								else if(block.color == 1) {
									score += 20;
								}
								else if(block.color == 2) {
									score += 30;
								}
								else if(block.color == 3) {
									score += 40;
								}
								else if(block.color == 4) {
									score += 50;
								}
							}
						}else if(dir == 1) {
							if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
									 new Rectangle(block.x, block.y, block.width, block.height)) ) {
								if(ball.x > block.x+2 &&
										ball.getRightCenter().x <= block.x + block.width-2) {
									dir = 0;
								}else {
									dir = 3;
								}
								block.isHidden = true;
								if(block.color == 0) {
									score += 10;
								}
								else if(block.color == 1) {
									score += 20;
								}
								else if(block.color == 2) {
									score += 30;
								}
								else if(block.color == 3) {
									score += 40;
								}
								else if(block.color == 4) {
									score += 50;
								}
							}
						}else if(dir == 2) {
							if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
									 new Rectangle(block.x, block.y, block.width, block.height)) ) {
								if(ball.x > block.x+2 &&
										ball.getRightCenter().x <= block.x + block.width-2) {
									dir = 3;
								}else {
									dir = 0;
								}
								block.isHidden = true;
								if(block.color == 0) {
									score += 10;
								}
								else if(block.color == 1) {
									score += 20;
								}
								else if(block.color == 2) {
									score += 30;
								}
								else if(block.color == 3) {
									score += 40;
								}
								else if(block.color == 4) {
									score += 50;
								}
							}
						}else if(dir == 3) {
							if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
									 new Rectangle(block.x, block.y, block.width, block.height)) ) {
								if(ball.x > block.x+2 &&
										ball.getRightCenter().x <= block.x + block.width-2) {
									dir = 2;
								}else {
									dir = 1;
								}
								block.isHidden = true;
								if(block.color == 0) {
									score += 10;
								}
								else if(block.color == 1) {
									score += 20;
								}
								else if(block.color == 2) {
									score += 30;
								}
								else if(block.color == 3) {
									score += 40;
								}
								else if(block.color == 4) {
									score += 50;
								}
							}
						}
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		
		new MyFrame("Block Game");
	}
}
