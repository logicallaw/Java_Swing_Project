package app;

import java.awt.*;
import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FandomDiaryApp extends JFrame {
	private Container c = getContentPane();
	public FandomDiaryApp() {
		setTitle("Fandom Diary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenuBar();
		createHeaderPanel();
		createMainPanel();
		createSideBarPanel();
		
		setSize(900, 600);
		setVisible(true);
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu[] menus = new JMenu[] { new JMenu("FandomDiary"), new JMenu("Edit"), new JMenu("Help") };
		menus[0].setFont(new Font("Arial", Font.BOLD, 15));
		
		// FandomDiary menuItems
		JMenuItem[] fandomDiaryItems = new JMenuItem[] {
				new JMenuItem("About FandomDiary"),
				new JMenuItem("Settings"),
				new JMenuItem("Quite...") };
		for (int i = 0; i < fandomDiaryItems.length; i++) {
			menus[0].add(fandomDiaryItems[i]);
		}
		
		// Add the menus to the menu bar.
		for (int i = 0; i < menus.length; i++) {
			menuBar.add(menus[i]);
		}
		
		// setJMenuBar
		setJMenuBar(menuBar);
	}
	private void createHeaderPanel() {
		//JPanel(LayoutManager layout)
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		headerPanel.setBackground(Color.WHITE);
		
		JLabel titleLabel = new JLabel("Fandom Diary");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		
		headerPanel.add(titleLabel);
		headerPanel.add(new JLabel("50도"));
		headerPanel.add(new JButton("검색")); //이미지
		
		c.add(headerPanel, BorderLayout.NORTH);
	}
	private void createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBackground(Color.WHITE);
		
		JPanel writePanel = new JPanel();
		writePanel.setLayout(new BorderLayout(5, 5));
		writePanel.setBackground(new Color(255, 239, 219));
		JTextArea writeArea = new JTextArea(4, 30);
		writeArea.setBackground(new Color(255, 239, 219));
		Color borderColor = new Color(250, 214, 189); // 경계 색상을 파란색으로 지정
        int borderWidth = 1;  // 경계 두께를 얇게, 1픽셀로 지정
        LineBorder border = new LineBorder(borderColor, borderWidth, true);
        writeArea.setBorder(border);
	
		JPanel writeFooterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		writeFooterPanel.add(new JButton("사진"));
		writeFooterPanel.add(new JButton("위치"));
		writeFooterPanel.add(new JButton("시간"));
		JButton writeZoomIn = new JButton("확대");
		writeFooterPanel.add(writeZoomIn);
		writeFooterPanel.setBackground(new Color(255, 239, 219));
		
		writeZoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDiaryWindow();
			}
		});
		
		writePanel.add(new JScrollPane(writeArea), BorderLayout.CENTER);
		writePanel.add(new JButton("작성"), BorderLayout.EAST);
		writePanel.add(writeFooterPanel, BorderLayout.SOUTH);
		
		mainPanel.add(writePanel, BorderLayout.NORTH);
		c.add(mainPanel, BorderLayout.CENTER);	
	}
	private void createSideBarPanel() {
		/* 아이돌 프로필 작성
		 * 좋아하는 좌우명
		 * 생년월일
		 * 세로 정렬 사용.
		 */
		JPanel sidebarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sidebarPanel.setBackground(new Color(245, 222, 179));
		sidebarPanel.add(new JButton("1"));
		sidebarPanel.add(new JButton("2"));
		
		c.add(sidebarPanel, BorderLayout.WEST);
	}
	
	private void openDiaryWindow() {
		JFrame diaryFrame = new JFrame("일기 작성");
		diaryFrame.setLayout(new BorderLayout(10, 10));
		
		//https://dev-coco.tistory.com/31
		LocalDateTime now = LocalDateTime.now();
		String formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm"));
		JLabel timeLabel = new JLabel(formattedNow);
		
		//diaryHeader
		JPanel diaryHeader = new JPanel(new BorderLayout(10,10));
		
		//diaryMain
		
		//diaryFooter
		
		// add:timeLabel
		
		
		diaryFrame.setSize(600, 400);
		diaryFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new FandomDiaryApp();
	}

}
