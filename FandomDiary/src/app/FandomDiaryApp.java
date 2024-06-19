package app;

import thread.MusicThread;
import frame.DiaryFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.Vector;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.sound.sampled.*;

public class FandomDiaryApp extends JFrame {
	// Frame field
	private Container c = getContentPane();
	private Vector<String> images_dir_fileNames = new Vector<String>();
	private Vector<ImageIcon> images_dir_Icons = new Vector<ImageIcon>();

	// header field
	private Clip clip = null;
	private JSlider slider = null;
	private MusicThread th = null;
	private ButtonFilledWithImage[] headerBtns = null;
	private JButton settingBtn = null;
	
	// main field
	private JTextArea mainWriteArea = new JTextArea(4, 30);
	private String userInput = null;
	// https://dev-coco.tistory.com/31
	private LocalDateTime now = LocalDateTime.now();
	private String formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm ss"));
	private JLabel mainTimeLabel = new JLabel(formattedNow);

	// DiaryFrame Object
	private DiaryFrame currentDiaryFrame = null;

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
		// headerPanel
		JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
		headerPanel.setBackground(new Color(242, 230, 216));

		// headerLeft
		JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		headerLeft.setBackground(new Color(242, 230, 216));
		headerBtns = new ButtonFilledWithImage[] { new ButtonFilledWithImage("public/btn_play.png"),
				new ButtonFilledWithImage("public/btn_stop.png"), new ButtonFilledWithImage("public/btn_again.png"), };
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

		MusicButtonListener musicListener = new MusicButtonListener();
		for (ButtonFilledWithImage btn : headerBtns) {
			btn.addActionListener(musicListener);
			headerLeft.add(btn);
		}
		headerLeft.add(slider);

		createClip("musics/IU_Holssi.wav");
		th = new MusicThread(slider, clip);

		// headerRight
		JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		headerRight.setBackground(new Color(239, 231, 221));
		JLabel titleLabel = new JLabel("Fandom Diary");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		headerRight.add(titleLabel);
		headerRight.add(new JLabel("50도"));
		settingBtn = new JButton("설정");
		headerRight.add(settingBtn); // 이미지

		headerPanel.add(headerLeft, BorderLayout.WEST);
		headerPanel.add(headerRight, BorderLayout.EAST);
		c.add(headerPanel, BorderLayout.NORTH);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (slider.getValueIsAdjusting() && clip != null && clip.isOpen() && clip.isRunning()) {
					int p = (int) (slider.getValue() * clip.getFrameLength() / 100.0);
					clip.setFramePosition(p);
				}
			}
		});

		th.start();
	}

	private void createMainPanel() {
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout(10, 10));
		main.setBackground(new Color(255, 245, 238));

		// mainWrite
		JPanel mainWrite = new JPanel();
		mainWrite.setLayout(new BorderLayout(5, 5));
		mainWrite.setBackground(new Color(255, 239, 219));

		mainWriteArea.setBackground(new Color(255, 239, 219));
		Color borderColor = new Color(250, 214, 189); // 경계 색상을 파란색으로 지정
		int borderWidth = 1; // 경계 두께를 얇게, 1픽셀로 지정
		LineBorder border = new LineBorder(borderColor, borderWidth, true);
		mainWriteArea.setBorder(border);
		mainWriteArea.setFont(new Font("Arial", Font.PLAIN, 15));

		JPanel mainWriteFooter = new JPanel(new BorderLayout(10, 10));
		mainWriteFooter.setBackground(new Color(255, 239, 219));

		JPanel mainWriteFooterLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mainWriteFooterLeft.add(new JButton("사진"));
		mainWriteFooterLeft.add(new JButton("위치"));
		JButton writeZoomIn = new JButton("확대");
		mainWriteFooterLeft.add(writeZoomIn);
		mainWriteFooterLeft.setBackground(new Color(255, 239, 219));

		JPanel mainWriteFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainWriteFooterRight.add(mainTimeLabel);
		mainWriteFooterRight.setBackground(new Color(255, 239, 219));

		mainWriteFooter.add(mainWriteFooterLeft, BorderLayout.WEST);
		mainWriteFooter.add(mainWriteFooterRight, BorderLayout.EAST);

		mainWrite.add(new JScrollPane(mainWriteArea), BorderLayout.CENTER);
		JButton mainWriteButton = new JButton("작성");
		mainWrite.add(mainWriteButton, BorderLayout.EAST);
		mainWrite.add(mainWriteFooter, BorderLayout.SOUTH);

		// mainGallery
		JPanel mainGallery = new JPanel(new GridLayout(4, 4, 10, 10));
		mainGallery.setBackground(new Color(255, 245, 238));
		File dir = new File("images");
		File[] subFiles = dir.listFiles();

		for (int i = 0; i < subFiles.length; i++) {
			File f = subFiles[i];
			images_dir_fileNames.add(f.getName());
			ImageIcon originImage = new ImageIcon("images/" + f.getName());
			// https://wildeveloperetrain.tistory.com/289
			Image scaledImage = originImage.getImage().getScaledInstance(400 / 4, 500 / 4, Image.SCALE_SMOOTH);
			images_dir_Icons.add(new ImageIcon(scaledImage));
		}

		for (int i = 0; i < images_dir_Icons.size(); i++) {
			mainGallery.add(new JLabel(images_dir_Icons.get(i)));
		}

		main.add(mainWrite, BorderLayout.NORTH);
		main.add(mainGallery, BorderLayout.CENTER);
		c.add(main, BorderLayout.CENTER);

		mainWriteArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				userInput = mainWriteArea.getText();
			}
		});

		writeZoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				 openDiaryFrame();
				currentDiaryFrame = new DiaryFrame(mainWriteArea, mainTimeLabel, now);

				// Update the current times.
				currentDiaryFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent we) {
						// currentDiaryFrame이 dispose되면 시간 업데이트.
						now = LocalDateTime.now();
						formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm ss"));
						mainTimeLabel.setText(formattedNow);
					}
				});
			}
		});

		mainWriteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeDiary();
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
	
	// Method
	private void writeDiary() {
		try {
			String fileName = "diaries/" + now.format(DateTimeFormatter.ofPattern("MMdd_HHmm_ss")) + ".txt";
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(userInput);
			writer.flush();
			writer.close();
			fw.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
		userInput = "";
		mainWriteArea.setText(userInput);

		// Update the current times.
		now = LocalDateTime.now();
		formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm ss"));
		mainTimeLabel.setText(formattedNow);
		repaint();
		revalidate();
	}
	
	private void createClip(String pathName) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener
	private class MusicButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton temp = (JButton) e.getSource();
			if (temp == headerBtns[0]) {
				clip.start();
			} else if (temp == headerBtns[1]) {
				clip.stop();
			} else if (temp == headerBtns[2]) {
				clip.setFramePosition(0);
				clip.start();
			}
		}
	}

	public static void main(String[] args) {
		new FandomDiaryApp();
	}
}