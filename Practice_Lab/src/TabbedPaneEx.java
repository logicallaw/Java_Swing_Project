import javax.swing.*;
import java.awt.*;
public class TabbedPaneEx extends JFrame{
	public TabbedPaneEx() {
		setTitle("탭팬 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		//Tab
		JTabbedPane pane = createTabbedPane();
		c.add(pane, BorderLayout.CENTER);
		
		//MenuBar
		createMenuBar();
		
		setSize(700,800);
		setVisible(true);
	}
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.NORTH);
		pane.addTab("Summary", new JLabel(new ImageIcon("images/apple.jpg")));
		pane.addTab("Income/Expenditure", new JLabel(new ImageIcon("images/cherry.jpg")));
		pane.addTab("To-Do List", new MyPanel());
		pane.addTab("Lecture", new JLabel(new ImageIcon("images/img.jpg")));
		return pane;	
	}
	private class MyPanel extends JPanel{
		public MyPanel() {
			this.setBackground(Color.yellow);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect(10,10,50,50);
			g.setColor(Color.blue);
			g.fillOval(10,70,50,50);
			g.setColor(Color.BLACK);
			g.drawString("tab 3에 들어가는 JPanel 입니다.", 30, 50);
		}
	}
	
	private void createMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("screen");
		
		mb.add(screenMenu);
		mb.add(new JMenu("Exit"));
		mb.add(new JMenu("Source"));
		mb.add(new JMenu("Project"));
		mb.add(new JMenu("Run"));
		
		setJMenuBar(mb);
	}
	public static void main(String[] args) {
		new TabbedPaneEx();
	}

}
