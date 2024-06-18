import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class Lab13_4 extends JFrame {
	private MyPanel p = new MyPanel();
	private JLabel imgLabel = new JLabel();
	public Lab13_4() {
		setTitle("Change to gray");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.add(imgLabel, BorderLayout.CENTER);
		setContentPane(p);
		createMenu();
		setSize(250,200);
		setVisible(true);
	}
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = {"Load", "Gray", "Save", "Exit"};
		MenuActionListener listener = new MenuActionListener();
		
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			menu.add(menuItem[i]);
		}
		mb.add(menu);
		setJMenuBar(mb);
	}
	class MenuActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "Load":
				p.load();
				break;
			case "Gray":
				p.gray();
				break;
			case "Save":
				p.save();
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
	}
	
	class MyPanel extends JPanel{
		private BufferedImage img;
		private JFileChooser fc = new JFileChooser();
		
		public void load() {
			int result = fc.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				String path = fc.getSelectedFile().getPath();
				try {
					img = ImageIO.read(new File(path));
				} catch(IOException e) {
					e.printStackTrace();
				}
				imgLabel.setIcon(new ImageIcon(img));
				pack();
			}
		}
		public void gray() {
			for(int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					Color c = new Color(img.getRGB(x, y));
					int Y = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
					img.setRGB(x, y, new Color(Y,Y,Y).getRGB());
				}
			}
			imgLabel.setIcon(new ImageIcon(img));
		}
		public void save() {
			int result = fc.showSaveDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				String path = fc.getSelectedFile().getPath();
				try {
					ImageIO.write(img, "jpg", new File(path));
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new Lab13_4();
	}

}
