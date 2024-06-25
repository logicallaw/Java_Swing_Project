package lib;

import java.awt.*;
import javax.swing.*;

public class ButtonFilledWithImage extends JButton {
	public ButtonFilledWithImage(String filePath, int width, int height) {
//		ImageIcon originImage = new ImageIcon(pathName);
//		Image scaledImage = originImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
//		ImageIcon resizedIcon = new ImageIcon(scaledImage);
		ImageIcon resizedIcon = new ResizedImageIcon(filePath, width, height);
		setPreferredSize(new Dimension(width, height));
		setText(null);
		setMargin(new Insets(0, 0, 0, 0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		this.setIcon(resizedIcon);
	}

}
