package frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SettingFrame extends JFrame{
	public SettingFrame() {
		setTitle("Settings");
		
		setSize(400,500);
		setVisible(true); 
	}
	public static void main(String[] args) {
		new SettingFrame();
	}
}
