import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class DiaryImageFileChooser extends JFrame{
	JButton button = new JButton("Image");
	public DiaryImageFileChooser() {
		setTitle("DiaryImageFileChooser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(button);
		button.addActionListener(new OpenImageActionListener());
		
		setSize(350,400);
		setVisible(true);
	}
	class OpenImageActionListener implements ActionListener{
		private JFileChooser chooser = null;
		public OpenImageActionListener() {
			chooser = new JFileChooser();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
			chooser.setFileFilter(filter);
			int result = chooser.showOpenDialog(null);
			if(result != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String filePath = chooser.getSelectedFile().getPath();
			button.setIcon(new ImageIcon(filePath));
			pack();
		}
	}
	public static void main(String[] args) {
		new DiaryImageFileChooser();
	}

}
