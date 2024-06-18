import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class KeyEnter extends JFrame {
	public KeyEnter() {
		setTitle("KeyEnter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		JTextArea ta = new JTextArea(4,50);
		
		ta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String texts = ta.getText();
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						FileWriter fw = new FileWriter("test.txt");
						BufferedWriter writer = new BufferedWriter(fw);
						writer.write(texts);
						writer.close();
						fw.close();
					} catch(IOException ee) {
						System.out.println("IOException Error!");
					}
				}
			}
		});
		while(true) {
			LocalDateTime now = LocalDateTime.now();
			String fileName = now.format(DateTimeFormatter.ofPattern("ss_HHmmddMM")) + ".txt";
			System.out.println(fileName);
		}
	}
	public static void main(String[] args) {
		new KeyEnter();
	}

}
