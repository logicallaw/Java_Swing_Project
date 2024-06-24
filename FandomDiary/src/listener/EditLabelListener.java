package listener;

import java.awt.event.*;
import javax.swing.*;

public class EditLabelListener extends MouseAdapter {
	@Override
	public void mouseReleased(MouseEvent e) {
		JLabel myLabel = (JLabel)e.getSource();
		String userInput = JOptionPane.showInputDialog("Enter");
		if(userInput == null) {
			return;
		} else {
			myLabel.setText(userInput);
		}
	}
}