import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ImgPanel extends JPanel{
	private Image img;
	public ImgPanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0,0, null);
	}
}
public class Lesson08 {

	public static void main(String[] args) {
		JFrame jf = new JFrame("Image_Test");
		jf.setSize(640,480);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		
		ImgPanel panel = new ImgPanel(new ImageIcon("./img/image_Test.jpg").getImage());
		jf.add(panel);
		jf.pack();
		
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
