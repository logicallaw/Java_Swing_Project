package frame;

import lib.ResizedImageIcon;
import java.awt.*;
import javax.swing.*;

public class InfoAppDialog extends JDialog {
	public InfoAppDialog(JFrame frame) {
		super(frame, "About", false);
		setLayout(null);
		ResizedImageIcon appImageIcon = new ResizedImageIcon("public/default_image.jpg", 100, 100);
		JLabel appImageLabel = new JLabel(appImageIcon);
		appImageLabel.setLocation(100,20);
		appImageLabel.setSize(new Dimension(100,100));
		
		JLabel appNameLabel = new JLabel("FandomDiary");
		appNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		appNameLabel.setSize(new Dimension(200, 30));
		appNameLabel.setLocation(87,130);
		
		JLabel appVersionLabel = new JLabel("Version 1.0.0");
		appVersionLabel.setFont(new Font("Arial", Font.BOLD, 15));
		appVersionLabel.setSize(new Dimension(200, 30));
		appVersionLabel.setLocation(105,160);
		
		JLabel appInfoLabel = new JLabel("Copyright @ Kim Jun Ho. All rights reserved.");
		appInfoLabel.setFont(new Font("Arial", Font.BOLD, 10));
		appInfoLabel.setForeground(Color.BLACK);
		appInfoLabel.setSize(new Dimension(300, 30));
		appInfoLabel.setLocation(45,230);
		
		add(appImageLabel);
		add(appNameLabel);
		add(appVersionLabel);
		add(appInfoLabel);
		
		setSize(300,300);
		setResizable(false);
		setLocationRelativeTo(frame);
		getContentPane().setBackground(new Color(255, 218, 165));
	}
}
