import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FixedImage extends JFrame{
	public FixedImage() {
		setTitle("Fixed Image");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		setSize(700,800);
		setVisible(true);
	}
	public static void main(String[] args) {
		new FixedImage();
	}

}
