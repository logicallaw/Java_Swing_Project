import javax.swing.*;
import java.awt.*;
public class IdolDiaryBorderLayout extends JFrame {
	public IdolDiaryBorderLayout(){
		setTitle("Fandom Diary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		//headerPanel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		headerPanel.setBackground(Color.WHITE);
		JLabel titleLable = new JLabel("Fandom Diary");
		titleLable.setFont(new Font("Arial", Font.BOLD, 24));
		headerPanel.add(titleLable);
		headerPanel.add(new JButton("36.5도"));
		headerPanel.add(new JButton("검색"));

		//mainPanel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBackground(Color.WHITE);
		
		JPanel writePanel = new JPanel();
		writePanel.setLayout(new BorderLayout(5,5));
		writePanel.setBorder(BorderFactory.createTitledBorder("일기 작성"));
		JTextArea writeArea = new JTextArea(5, 40);
		
		writePanel.add(new JScrollPane(writeArea), BorderLayout.NORTH);
		
		JPanel writeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton saveButton = new JButton("저장");
		writeButtonPanel.add(saveButton);
		
		writePanel.add(writeButtonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(writePanel, BorderLayout.NORTH);
		
		//sidebarPanel
		JPanel sidebarPanel = new JPanel(new GridLayout(6, 1, 5, 5));
		sidebarPanel.setBackground(new Color(245, 222, 179));
		for (int i = 1; i <= 5; i++) {
            JPanel idolPanel = new JPanel();
            idolPanel.setLayout(new BorderLayout());
            JLabel idolLabel = new JLabel(new ImageIcon("path/to/idol" + i + ".jpg"));
            idolLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JButton likeButton = new JButton("❤️");
            idolPanel.add(idolLabel, BorderLayout.CENTER);
            idolPanel.add(likeButton, BorderLayout.SOUTH);
            sidebarPanel.add(idolPanel);
        }
		
		//add:header, main, side
		c.add(headerPanel, BorderLayout.NORTH);
		c.add(mainPanel, BorderLayout.CENTER);
		c.add(sidebarPanel, BorderLayout.WEST);
		
		createMenuBar();
		
        setSize(900,600);
        setVisible(true);
        
	}
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu[] menus = new JMenu[] {
				new JMenu("FandomDiary"),
				new JMenu("Edit"),
				new JMenu("Help")
		};
		menus[0].setFont(new Font("Arial", Font.BOLD, 20));
		
		//FandomDiary menuItems
		JMenuItem[] fandomDiaryItems = new JMenuItem[] {
				new JMenuItem("About FandomDiary"),
				new JMenuItem("Settings..."),
				new JMenuItem("Quite FandomDiary")
		};
		for(int i = 0; i < fandomDiaryItems.length; i++) {
			menus[0].add(fandomDiaryItems[i]);
		}
		
		for(int i = 0; i < menus.length; i++) {
			menuBar.add(menus[i]);
		}
		
		setJMenuBar(menuBar);
		
	}
	public static void main(String[] args) {
		new IdolDiaryBorderLayout();
	}
}
