import java.io.File;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class IUIO extends JFrame{
	public IUIO() {
		setTitle("IU LOVE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(4,4,10,10));
		
		Vector<String> fileNames = new Vector<String>();
		Vector<ImageIcon> imgIcons = new Vector<ImageIcon>();
		
		File dir = new File("images");
		File[] subFiles = dir.listFiles();
		for (int i = 0; i < subFiles.length; i++) {
			File f = subFiles[i];
			String fileName = f.getName();
			if (fileName.equals(".DS_Store")) {
				continue;
			}
			else {
				fileNames.add(f.getName());
				ImageIcon originImage = new ImageIcon("images/" + f.getName());
				Image scaledImage = originImage.getImage().getScaledInstance(600 / 4, 700 / 4, Image.SCALE_SMOOTH);
				imgIcons.add(new ImageIcon(scaledImage));
			}
		}
	
		for (int i = 0; i < imgIcons.size(); i++) {
			c.add(new JLabel(imgIcons.get(i)));
		}
		
		setSize(600,700);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new IUIO();	
	}

}
