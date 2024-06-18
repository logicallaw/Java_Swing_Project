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
	String userInput = null;
	JTextArea mainWriteArea = new JTextArea(4, 30);
	
	// https://dev-coco.tistory.com/31
	LocalDateTime now = LocalDateTime.now();
	String formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm"));
	JLabel timeLabel = new JLabel(formattedNow);
	
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
		JMenuItem[] fandomDiaryItems = new JMenuItem[] { new JMenuItem("About FandomDiary"), new JMenuItem("Settings"),
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
		// JPanel(LayoutManager layout)
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		headerPanel.setBackground(Color.WHITE);

		JLabel titleLabel = new JLabel("Fandom Diary");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

		headerPanel.add(titleLabel);
		headerPanel.add(new JLabel("50도"));
		headerPanel.add(new JButton("검색")); // 이미지

		c.add(headerPanel, BorderLayout.NORTH);
	}

	private void createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBackground(Color.WHITE);

		JPanel mainWritePanel = new JPanel();
		mainWritePanel.setLayout(new BorderLayout(5, 5));
		mainWritePanel.setBackground(new Color(255, 239, 219));
//		JTextArea writeArea = new JTextArea(4, 30);
		mainWriteArea.setBackground(new Color(255, 239, 219));
		Color borderColor = new Color(250, 214, 189); // 경계 색상을 파란색으로 지정
		int borderWidth = 1; // 경계 두께를 얇게, 1픽셀로 지정
		LineBorder border = new LineBorder(borderColor, borderWidth, true);
		mainWriteArea.setBorder(border);
		mainWriteArea.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JPanel mainWriteFooterPanel = new JPanel(new BorderLayout(10,10));
		mainWriteFooterPanel.setBackground(new Color(255, 239, 219));

		JPanel mainWriteFooterLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mainWriteFooterLeftPanel.add(new JButton("사진"));
		mainWriteFooterLeftPanel.add(new JButton("위치"));
		JButton writeZoomIn = new JButton("확대");
		mainWriteFooterLeftPanel.add(writeZoomIn);
		mainWriteFooterLeftPanel.setBackground(new Color(255, 239, 219));
		
		JPanel mainWriteFooterRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainWriteFooterRightPanel.add(timeLabel);
		mainWriteFooterRightPanel.setBackground(new Color(255, 239, 219));
		
		mainWriteFooterPanel.add(mainWriteFooterLeftPanel, BorderLayout.WEST);
		mainWriteFooterPanel.add(mainWriteFooterRightPanel, BorderLayout.EAST);
		
		

		writeZoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDiaryFrame();
			}
		});

		mainWritePanel.add(new JScrollPane(mainWriteArea), BorderLayout.CENTER);
		mainWritePanel.add(new JButton("작성"), BorderLayout.EAST);
		mainWritePanel.add(mainWriteFooterPanel, BorderLayout.SOUTH);

		mainPanel.add(mainWritePanel, BorderLayout.NORTH);
		c.add(mainPanel, BorderLayout.CENTER);
		
		mainWriteArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				JTextArea ta = (JTextArea)e.getSource();
				String texts = ta.getText();
				userInput = texts;
			}
		});
		
	}

	private void createSideBarPanel() {
		/*
		 * 아이돌 프로필 작성 좋아하는 좌우명 생년월일 세로 정렬 사용.
		 */
		JPanel sidebarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sidebarPanel.setBackground(new Color(245, 222, 179));
		sidebarPanel.add(new JButton("1"));
		sidebarPanel.add(new JButton("2"));

		c.add(sidebarPanel, BorderLayout.WEST);
	}

	private void openDiaryFrame() {
		JFrame diaryFrame = new JFrame("일기 작성");
		diaryFrame.setLayout(new BorderLayout(10, 10));

		// diaryHeader
		JPanel diaryHeader = new JPanel(new BorderLayout(10, 10));

		JButton diaryHeaderExit = new JButton("X");
		JLabel diaryHeaderTitle = new JLabel("WRITE", JLabel.CENTER);
		diaryHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
		JButton diaryHeaderWrite = new JButton("V");
		
		diaryHeader.add(diaryHeaderExit, BorderLayout.WEST);
		diaryHeader.add(diaryHeaderTitle, BorderLayout.CENTER);
		diaryHeader.add(diaryHeaderWrite, BorderLayout.EAST);

		// diaryMain
		JPanel diaryMain = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JTextArea diaryMainWriteArea = new JTextArea(8,32);
		diaryMainWriteArea.setEditable(true);
		diaryMainWriteArea.setLineWrap(true);
		diaryMainWriteArea.setWrapStyleWord(true);
		diaryMainWriteArea.setFont(new Font("Arial", Font.PLAIN, 20));
		diaryMainWriteArea.setText(userInput);
		diaryMain.add(new JScrollPane(diaryMainWriteArea));
		
		// diaryFooter
		JPanel diaryFooter = new JPanel(new BorderLayout(10, 10));

		JPanel diaryFooterLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton[] btns = new JButton[] { new JButton("갤러리"), new JButton("위치") };

		for (int i = 0; i < btns.length; i++) {
			diaryFooterLeftPanel.add(btns[i]);
		}

		JPanel diaryFooterRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		
		// https://dev-coco.tistory.com/31
//		LocalDateTime now = LocalDateTime.now();
//		String formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm"));
//		JLabel timeLabel = new JLabel(formattedNow);
		
		JLabel numOfCharsLabel = new JLabel(Integer.toString(0));
		diaryFooterRightPanel.add(numOfCharsLabel);
		diaryFooterRightPanel.add(timeLabel);

		diaryFooter.add(diaryFooterLeftPanel, BorderLayout.WEST);
		diaryFooter.add(diaryFooterRightPanel, BorderLayout.EAST);

		// add:diaryHeader, diaryMain, diaryFooter
		diaryFrame.add(diaryHeader, BorderLayout.NORTH);
		diaryFrame.add(diaryMain, BorderLayout.CENTER);
		diaryFrame.add(diaryFooter, BorderLayout.SOUTH);
		
		diaryFrame.setSize(600, 800);
		diaryFrame.setVisible(true);
		
		diaryMainWriteArea.setFocusable(true);
		diaryMainWriteArea.requestFocus();

		diaryHeaderExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWriteArea.setText(userInput);
				diaryFrame.dispose();
			}
		});
		diaryMainWriteArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				JTextArea ta = (JTextArea)e.getSource();
				String texts = ta.getText();
				int numOfTexts = texts.length();
				if(numOfTexts <= 1000) {
					numOfCharsLabel.setText(Integer.toString(numOfTexts) + "/1000");
					userInput = texts;
				}
				else {
					JOptionPane.showMessageDialog(null, "글자수는 100자 내로 입력해주세요.","Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
	}
	
	
	public static void main(String[] args) {
		new FandomDiaryApp();
	}

}
