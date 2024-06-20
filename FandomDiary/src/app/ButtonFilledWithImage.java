package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ButtonFilledWithImage extends JButton {
	public ButtonFilledWithImage(String pathName) {
		ImageIcon originImage = new ImageIcon(pathName);
		Image scaledImage = originImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(scaledImage);

		setPreferredSize(new Dimension(30, 30));
		setText(null);
		setMargin(new Insets(0, 0, 0, 0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		this.setIcon(resizedIcon);
	}

}

