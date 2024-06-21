package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditLabelFrame extends JFrame{
	JLabel myLabel = null;
	public EditLabelFrame(JLabel myLabel) {
		this.myLabel = myLabel;
		setTitle("EDIT");
		setLayout(new BorderLayout(10, 10));
		
		//header
		JPanel editLabelHeader = new JPanel(new BorderLayout(10, 10));
		editLabelHeader.setBackground(new Color(128, 233, 238));
		JButton editLabelHeaderExit = new JButton("X");
		JLabel editLabelHeaderTitle = new JLabel("EDIT", JLabel.CENTER);
		
		
		//main
		
	}
}
