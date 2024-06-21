package app;

import frame.EditLabelDialog;
import method.FileDiary;
import method.Diary;
import thread.MusicThread;
import frame.DiaryFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.Vector;
import java.io.IOException;
import javax.sound.sampled.*;

public class FandomDiaryApp extends JFrame {
	// Frame field
	private Container c = getContentPane();
	private Vector<JLabel> diariesJLabel = new Vector<JLabel>();
	private Vector<String> diariesPath = new Vector<String>();
	private Vector<String> imagesPath = new Vector<String>();
	private Vector<ImageIcon> imagesIcons = new Vector<ImageIcon>();

	// header field
	private Clip clip = null;
	private JSlider slider = null;
	private MusicThread th = null;
	private ButtonFilledWithImage[] headerBtns = null;
	private JButton settingBtn = null;
	private Boolean isPlay = false;
	private MusicButtonListener musicListener = null;

	// main field
	private JTextArea mainWriteArea = new JTextArea(4, 30);
	private String userInput = null;
	// https://dev-coco.tistory.com/31
	private LocalDateTime now = LocalDateTime.now();
	private String formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm ss"));
	private JLabel mainTimeLabel = new JLabel(formattedNow);
	private String srcPath = null;
	private JButton mainWriteImageButton = new JButton("Image");

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

		// Automatic starting
		if (isPlay) {
			clip.start();
		}
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
		headerPanel.setBackground(new Color(128, 233, 238));

		// headerLeft
		JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		headerLeft.setBackground(new Color(128, 233, 238));
		headerBtns = new ButtonFilledWithImage[] { new ButtonFilledWithImage("public/btn_play.png", 50, 50),
				new ButtonFilledWithImage("public/btn_stop.png", 50, 50),
				new ButtonFilledWithImage("public/btn_replay.png", 50, 50)};
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

		musicListener = new MusicButtonListener();
		for (ButtonFilledWithImage btn : headerBtns) {
			btn.addActionListener(musicListener);
			headerLeft.add(btn);
		}
//		JLabel headerGIFLabel = new JLabel(new ImageIcon("public/music.gif"));
//		headerGIFLabel.setPreferredSize(new Dimension(50,50));
		createClip("musics/IU_Holssi.wav");
		headerLeft.add(slider);
		
//		headerLeft.add(headerGIFLabel);
		
		th = new MusicThread(slider, clip);

//		// headerRight
//		JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		headerRight.setBackground(new Color(244,245,228));
//		headerRight.setSize(300,400);
//		ButtonFilledWithImage headerRightTitle = new ButtonFilledWithImage("public/label_title.png", 30, 30);
//		headerRight.add(headerRightTitle);
////		settingBtn = new JButton("설정");

		headerPanel.add(headerLeft, BorderLayout.WEST);
//		headerPanel.add(headerRight, BorderLayout.EAST);
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
		main.setLayout(new BorderLayout());
		main.setBackground(new Color(255, 245, 238));

		// mainWrite
		JPanel mainWrite = new JPanel();
		mainWrite.setLayout(new BorderLayout(5, 5));
		mainWrite.setBackground(new Color(255, 239, 219));

		mainWriteArea.setBackground(new Color(255, 239, 219));
		Color borderColor = new Color(250, 214, 189);
		int borderWidth = 1;
		LineBorder border = new LineBorder(borderColor, borderWidth, true);
		mainWriteArea.setBorder(border);
		mainWriteArea.setFont(new Font("Roboto", Font.PLAIN, 15));

		JPanel mainWriteFooter = new JPanel(new BorderLayout(10, 10));
		mainWriteFooter.setBackground(new Color(255, 239, 219));

		JPanel mainWriteFooterLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		mainWriteImageButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		mainWriteFooterLeft.add(mainWriteImageButton);
		JButton mainWriteLocateButton = new JButton("Locate");
		mainWriteLocateButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		mainWriteFooterLeft.add(mainWriteLocateButton);

		JButton writeZoomIn = new JButton("Zoom In");
		writeZoomIn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		mainWriteFooterLeft.add(writeZoomIn);
		mainWriteFooterLeft.setBackground(new Color(255, 239, 219));

		JPanel mainWriteFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainWriteFooterRight.add(mainTimeLabel);
		mainWriteFooterRight.setBackground(new Color(255, 239, 219));

		mainWriteFooter.add(mainWriteFooterLeft, BorderLayout.WEST);
		mainWriteFooter.add(mainWriteFooterRight, BorderLayout.EAST);

		mainWrite.add(new JScrollPane(mainWriteArea), BorderLayout.CENTER);
		JButton mainWriteButton = new JButton("Write");
		mainWriteButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		mainWrite.add(mainWriteButton, BorderLayout.EAST);
		mainWrite.add(mainWriteFooter, BorderLayout.SOUTH);

		// mainGallery
		JPanel mainGallery = new JPanel(new BorderLayout(10, 10));
		mainGallery.setBackground(new Color(255, 245, 238));

		FileDiary.getFilePath(diariesPath, imagesPath);
		FileDiary.loadTexts(diariesPath, diariesJLabel);
		FileDiary.loadImages(imagesPath, imagesIcons);

		JPanel postPanel = new JPanel();
		postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));

		for (int i = 0; i < imagesIcons.size(); i++) {
			JPanel post = new JPanel(new BorderLayout(10, 10));
			post.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel postImage = new JLabel(imagesIcons.get(i));
			JLabel postText = diariesJLabel.get(i);
			postText.setFont(new Font("Roboto", Font.PLAIN, 15));
			post.add(postImage, BorderLayout.WEST);
			post.add(postText, BorderLayout.CENTER);
			postPanel.add(post, 0);
		}

		JScrollPane scrollPost = new JScrollPane(postPanel);
		// https://velog.io/@jmkim463/swing-JScrollPane
		scrollPost.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollBar jsb = scrollPost.getVerticalScrollBar();
		jsb.setUnitIncrement(8);

		mainGallery.add(scrollPost, BorderLayout.CENTER);
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
				currentDiaryFrame = new DiaryFrame(mainWriteArea, mainTimeLabel, now);
				currentDiaryFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent we) {
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
				FandomDiaryApp.this.mainWriteArea.setFocusable(true);
				FandomDiaryApp.this.mainWriteArea.requestFocus();
				repaint();
				revalidate();
			}
		});

		mainWriteImageButton.addActionListener(new ActionListener() {
			private JFileChooser chooser = new JFileChooser();
			private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setFileFilter(imageFilter);
				int result = chooser.showOpenDialog(null);
				if (result != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "You didn't chooser image.", "Notice",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				srcPath = chooser.getSelectedFile().getPath();
				FandomDiaryApp.this.mainWriteArea.setFocusable(true);
				FandomDiaryApp.this.mainWriteArea.requestFocus();
			}
		});
	}

	private void createSideBarPanel() {
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(new Color(236, 161, 163));
		sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
		sidebarPanel.setPreferredSize(new Dimension(200, 300));

		ButtonFilledWithImage sbProfile = new ButtonFilledWithImage("public/default_image.jpg", 100, 100);
		// https://ldne.tistory.com/56
		sbProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel[] sbLabels = new JLabel[] { new JLabel("IU"), new JLabel("93.05.16"), new JLabel("Be happy!"),
				new JLabel("I like Kimchi stew and Shabu-Shabu") };
		
		for (JLabel la : sbLabels) {
			la.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			la.setAlignmentX(Component.CENTER_ALIGNMENT);
			la.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					EditLabelDialog editLabelDialog = new EditLabelDialog(FandomDiaryApp.this, la);
					editLabelDialog.setVisible(true);
					repaint();
					revalidate();
				}
			});
		}

		// https://www.cs.rutgers.edu/courses/111/classes/fall_2011_tjang/texts/notes-java/GUI/layouts/42boxlayout-spacing.html
		sidebarPanel.add(sbProfile);
		sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		for (JLabel la : sbLabels) {
			sidebarPanel.add(la);
			sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		c.add(sidebarPanel, BorderLayout.WEST);
	}

	// Method
	private void writeDiary() {
		String fileNameFormatted = now.format(DateTimeFormatter.ofPattern("MMdd_HHmm_ss"));

		// Write Text
		Diary.writeDiary(fileNameFormatted, userInput);

		// Write Image
		Diary.writeImage(fileNameFormatted, srcPath);

		userInput = "";
		mainWriteArea.setText(userInput);

		// Update the current times.
		now = LocalDateTime.now();
		formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm ss"));
		mainTimeLabel.setText(formattedNow);
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