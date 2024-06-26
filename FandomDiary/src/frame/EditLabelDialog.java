package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditLabelDialog extends JDialog {
	JLabel myLabel = null;
	public EditLabelDialog(JFrame frame,JLabel myLabel) {
		super(frame, "Edit Label", true);
		this.myLabel = myLabel;
		setLayout(new BorderLayout(10,10));
		
		JPanel dialogHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		dialogHeader.add(new JLabel("Edit your label"));
		JButton dialogHeaderWrite = new JButton("V");
		dialogHeader.add(dialogHeaderWrite);
		
		JPanel dialogMain = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JTextArea dialogMainTa = new JTextArea(5,20);
		dialogMainTa.setText(myLabel.getText());
		
		dialogMain.add(dialogMainTa);
		add(dialogHeader, BorderLayout.NORTH);
		add(dialogMain, BorderLayout.CENTER);
		
		dialogHeaderWrite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myLabel.setText(dialogMainTa.getText());
				dialogMainTa.setText("");
				setVisible(false);
			}
		});
		
		setSize(300,400);
		setLocationRelativeTo(frame);
		setResizable(false);
		dialogMainTa.setFocusable(true);
		dialogMainTa.requestFocus();
	}
}
