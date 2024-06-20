import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.Vector;

public class BoxImageAndPost extends JFrame {
	private Vector<String> diaries = new Vector<>();
	private Vector<String> images = new Vector<>();
	public BoxImageAndPost() {
		setTitle("Post Panel Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		JPanel postPanel = new JPanel();
		postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(postPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		diaries.add("iu1.txt");
		diaries.add("iu2.txt");
		
		images.add("iu1.jpg");
		images.add("iu2.jpg");
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel(new ImageIcon("images/iu1.jpg")));
		p1.add(new JLabel("HI"));
		
		postPanel.add(p1);
		c.add(scrollPane, BorderLayout.CENTER);
		
		
		setSize(400, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BoxImageAndPost();
	}

}
