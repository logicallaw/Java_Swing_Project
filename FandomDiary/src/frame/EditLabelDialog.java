package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditLabelDialog extends JDialog {
	JLabel myLabel = null;
	private JTextArea dialogMainTa = null;
	public EditLabelDialog(JFrame frame,JLabel myLabel) {
		super(frame, "Edit", true);
		this.myLabel = myLabel;
		setLayout(new BorderLayout(10,10));
		
		// Header
		JPanel dialogHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		dialogHeader.setBackground(new Color(255, 218, 167));
		JLabel dialogHeaderLabel = new JLabel("EDIT");
		dialogHeaderLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		dialogHeaderLabel.setBackground(new Color(255, 218, 167));
		dialogHeader.add(dialogHeaderLabel);
		
		// Main
		JPanel dialogMain = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		dialogMain.setBackground(new Color(255, 218, 167));
		dialogMainTa = new JTextArea(5,20);
		dialogMainTa.setBackground(new Color(255, 218, 167));
		dialogMainTa.setText(myLabel.getText());
		
		dialogMain.add(dialogMainTa);
		add(dialogHeader, BorderLayout.NORTH);
		add(dialogMain, BorderLayout.CENTER);
		
	
		setResizable(false);
		setSize(300, 200);
		setLocationRelativeTo(frame);
		getContentPane().setBackground(new Color(255, 218, 165));
		dialogMainTa.setFocusable(true);
		dialogMainTa.requestFocus();
		dialogMainTa.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		
		dialogMainTa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_ENTER) {
					myLabel.setText(dialogMainTa.getText());
					dispose();
				}
			}
		});
	}
}
