package lib;

import java.awt.*;
import javax.swing.*;

public class NonBorderButton extends JButton {
	public NonBorderButton(String text){
		setText(text);
		setMargin(new Insets(0, 0, 0, 0));
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(true);
		setBorderPainted(false);
		setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
	}

}
