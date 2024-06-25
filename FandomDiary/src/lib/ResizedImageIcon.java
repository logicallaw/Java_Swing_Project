package lib;

import java.awt.Image;

import javax.swing.*;

public class ResizedImageIcon extends ImageIcon{
	public ResizedImageIcon(String filePath, int width, int height) {
		ImageIcon originImage = new ImageIcon(filePath);
		Image scaledImage = originImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		setImage(scaledImage);
	}
}
