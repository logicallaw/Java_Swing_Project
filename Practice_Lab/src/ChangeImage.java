import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ChangeImage extends JFrame{
	public ChangeImage() {
		setTitle("Change Image");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MyPanel());
		
		setSize(700,800);
		setVisible(true);
		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}
	class MyPanel extends JPanel{
		private ImageIcon img = new ImageIcon("images/dlwlrma.jpg");
		private int x = 10, y = 10, width = img.getIconWidth(), height = img.getIconHeight();
		public MyPanel() {
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyChar() == '+') {
						width = (int)(width * 1.1);
						height = (int)(height * 1.1);
						repaint();
						revalidate();
					}
				}
			});
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img.getImage(), x, y, width, height, this);
		}
	}
	public static void main(String[] args) {
		new ChangeImage();
	}

}
