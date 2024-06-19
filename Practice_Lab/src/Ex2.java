import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Ex2 extends JFrame{
	public Ex2() {
		setTitle("CustomizedMyButton");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.add(new ButtonFilledWithImage("public/btn_play.png"));
		
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Ex2();
	}

}
