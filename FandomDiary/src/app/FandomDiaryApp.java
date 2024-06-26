package app;

import lib.ResizedImageIcon;
import lib.ButtonFilledWithImage;
import lib.NonBorderButton;
import frame.EditLabelDialog;
import frame.InfoAppDialog;
import frame.SettingDialog;
import method.FileDiary;
import method.Diary;
import thread.MusicThread;
import frame.DiaryEditFrame;
import frame.DiaryFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.LinkedList;
import java.util.Vector;
import java.io.IOException;
import javax.sound.sampled.*;

public class FandomDiaryApp extends JFrame {
	// Frame field
	private Container c = getContentPane();
	private Vector<String> diariesPath = new Vector<String>();
	private Vector<String> imagesPath = new Vector<String>();
//	private Vector<String> postDayList = new Vector<String>();

	private JPanel postPanel = null;
	private LinkedList<JPanel> postIndexList = new LinkedList<JPanel>();

	private Vector<JTextArea> diariesJTextArea = new Vector<JTextArea>();
	private Vector<ButtonFilledWithImage> imagesBtns = new Vector<ButtonFilledWithImage>();

	private int postIndex = 0;

	// header field
	private Clip clip = null;
	private JSlider slider = null;
	private MusicThread th = null;
	private ButtonFilledWithImage[] headerBtns = null;
	private Boolean isPlay = false;
	private MusicButtonListener musicListener = null;

	// main field
	private JTextArea mainWriteArea = new JTextArea(4, 30);
	private String userInput = null;
	private boolean isText = false;
	private boolean hasImage = false;
	// https://dev-coco.tistory.com/31
	private LocalDateTime now = LocalDateTime.now();
	private String formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm"));
	private JLabel mainTimeLabel = new JLabel(formattedNow);
	private String srcPath = null;
	private String defaultImagePath = null;
//	private JButton mainWriteImageButton = new JButton("Image");
	private NonBorderButton mainWriteImageButton = new NonBorderButton("Image");
//	private FileDiary fileDiaryObj = null;

	// DiaryFrame Object
	private DiaryFrame currentDiaryFrame = null;
	private int currentPostIndex;

	public FandomDiaryApp() {
		setTitle("Fandom Diary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createMenuBar();
		createHeaderPanel();
		createMainPanel();
		createSideBarPanel();

		setSize(900, 900);

		// Automatic starting
		if (isPlay) {
			clip.start();
		}
		mainWriteArea.setFocusable(true);
		mainWriteArea.requestFocus();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("FandomDiary");
//		JMenu[] menus = new JMenu[] { new JMenu("FandomDiary"), new JMenu("Edit"), new JMenu("Help") };
		menu.setFont(new Font("Arial", Font.BOLD, 15));

		// FandomDiary menuItems
		JMenuItem[] fandomDiaryItems = new JMenuItem[] { new JMenuItem("About FandomDiary"), new JMenuItem("Settings"),
				new JMenuItem("Quite...") };
		for (int i = 0; i < fandomDiaryItems.length; i++) {
			menu.add(fandomDiaryItems[i]);
		}

		// Add the menus to the menu bar.
		menuBar.add(menu);
		
		SettingDialog settingDialog = new SettingDialog(FandomDiaryApp.this);
		InfoAppDialog infoAppDialog = new InfoAppDialog(FandomDiaryApp.this);
		// Listener
		fandomDiaryItems[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				infoAppDialog.setVisible(true);
			}
		});
		fandomDiaryItems[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settingDialog.setVisible(true);
			}
		});
		fandomDiaryItems[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isText || hasImage) {
					JOptionPane.showMessageDialog(FandomDiaryApp.this, "isTyping or hasImage!", "Quite",
							JOptionPane.ERROR_MESSAGE);
				} else {
					System.exit(0);
				}
			}
		});

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
				new ButtonFilledWithImage("public/btn_replay.png", 50, 50) };
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

		musicListener = new MusicButtonListener();
		for (ButtonFilledWithImage btn : headerBtns) {
			btn.addActionListener(musicListener);
			headerLeft.add(btn);
		}
		
		createClip("musics/IU_Holssi.wav");
		headerLeft.add(slider);

		th = new MusicThread(slider, clip);
		
		// headerRight
		JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
		headerRight.setBackground(new Color(128, 233, 238));
		
		JLabel heatLabel = new JLabel("97°", JLabel.CENTER);
		heatLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		heatLabel.setForeground(Color.RED);

		headerRight.add(heatLabel);
			
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
		main.setLayout(new BorderLayout());
		main.setBackground(new Color(255, 245, 238));

		// mainWrite
		JPanel mainWrite = new JPanel();
		mainWrite.setLayout(new BorderLayout(5, 5));
		mainWrite.setBackground(new Color(255, 239, 219));

		mainWriteArea.setBackground(new Color(255, 239, 219));
//		mainWriteArea.setMargin(new Insets(10,10,10,10));
		mainWriteArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		mainWriteArea.setLineWrap(true);
		mainWriteArea.setWrapStyleWord(true);

		JPanel mainWriteFooter = new JPanel(new BorderLayout(10, 10));
		mainWriteFooter.setBackground(new Color(255, 239, 219));

		JPanel mainWriteFooterLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		mainWriteFooterLeft.add(mainWriteImageButton);
		
		NonBorderButton writeZoomIn = new NonBorderButton("Zoom In");
		writeZoomIn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		
		mainWriteFooterLeft.add(writeZoomIn);
		mainWriteFooterLeft.setBackground(new Color(255, 239, 219));

		JPanel mainWriteFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainTimeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
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
		FileDiary.addTextsToVector(diariesPath, diariesJTextArea, postIndex);
		FileDiary.addImagesToVector(imagesPath, imagesBtns, postIndex);
		
//		FileDiary.addPostDayToVector(postDayList, diariesPath);

		postPanel = new JPanel();
		postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
		postPanel.setBackground(new Color(255, 245, 238));
		postIndex = updatePosts(postIndexList, diariesJTextArea, diariesPath, imagesBtns, postPanel, postIndex);
		
		JScrollPane scrollPost = new JScrollPane(postPanel);
		scrollPost.setBackground(new Color(255, 245, 238));
		// https://velog.io/@jmkim463/swing-JScrollPane
		scrollPost.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollBar jsbV = scrollPost.getVerticalScrollBar();
		JScrollBar jsbH = scrollPost.getHorizontalScrollBar();
		jsbV.setUnitIncrement(10);
		jsbH.setUnitIncrement(10);

		mainGallery.add(scrollPost, BorderLayout.CENTER);
		main.add(mainWrite, BorderLayout.NORTH);
		main.add(mainGallery, BorderLayout.CENTER);
		c.add(main, BorderLayout.CENTER);

		mainWriteArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				userInput = mainWriteArea.getText();
				isText = (userInput.length() != 0) ? true : false;
			}
		});

		writeZoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDiaryFrame = new DiaryFrame(FandomDiaryApp.this, "WRITE", mainWriteArea, diariesPath, imagesPath,
						diariesJTextArea, imagesBtns, now, postIndex, srcPath);
				currentDiaryFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent we) {
						if (currentDiaryFrame.isClickedWrite()) {
							// Initialize to default value
							userInput = "";
							srcPath = null;
							isText = false;
							hasImage = false;
							mainWriteArea.setText(userInput);
							// Update the current time.
							now = LocalDateTime.now();
							formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm"));
							mainTimeLabel.setText(formattedNow);
							// Update the current post.
							postIndex = updatePosts(postIndexList, diariesJTextArea, diariesPath, imagesBtns, postPanel,
									postIndex);
						} else {
							userInput = currentDiaryFrame.getUserInput();
							srcPath = currentDiaryFrame.getSrcPath();
							isText = currentDiaryFrame.isTyping();
							hasImage = currentDiaryFrame.hasImage();
						}
						repaint();
						revalidate();
					}
				});
			}
		});

		mainWriteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userInput = mainWriteArea.getText();
				writeDiary();
				mainWriteArea.setFocusable(true);
				mainWriteArea.requestFocus();
				FandomDiaryApp.this.repaint();
				FandomDiaryApp.this.revalidate();
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
				hasImage = true;
				mainWriteArea.setFocusable(true);
				mainWriteArea.requestFocus();
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
		JLabel[] sbLabels = new JLabel[] { new JLabel("IU"), new JLabel("93.05.16"), new JLabel("INFJ"),new JLabel("2008.09.18~")};

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
		String fileNameFormatted = now.format(DateTimeFormatter.ofPattern("MMdd_HHmm_ss_SS"));

		// Write Text and Image
		Diary.writeDiary(fileNameFormatted, userInput);
		Diary.writeImage(fileNameFormatted, srcPath);

		// Update Post
		FileDiary.getFilePath(diariesPath, imagesPath);
		FileDiary.addTextsToVector(diariesPath, diariesJTextArea, postIndex);
		FileDiary.addImagesToVector(imagesPath, imagesBtns, postIndex);

//		System.out.println("writeDiary before:" + postIndex);
		postIndex = updatePosts(postIndexList, diariesJTextArea, diariesPath, imagesBtns, postPanel, postIndex);
//		System.out.println("writeDiary after:" + postIndex);

		userInput = "";
		srcPath = null;
		isText = false;
		hasImage = false;
		mainWriteArea.setText(userInput);

		// Update the current times.
		now = LocalDateTime.now();
		formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm"));
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

	public int updatePosts(LinkedList<JPanel> postIndexList, Vector<JTextArea> diariesJTextArea,
			Vector<String> diariesPath, Vector<ButtonFilledWithImage> imagesBtns, JPanel postPanel,
			int LocalpostIndex) {
		for (; LocalpostIndex < diariesPath.size(); LocalpostIndex++) {
			JPanel postNewPanel = new JPanel(new BorderLayout(10, 10));
			postNewPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			postNewPanel.setBackground(new Color(255, 218, 185));

			ButtonFilledWithImage postImage = imagesBtns.get(LocalpostIndex);
			postImage.setFocusPainted(false);
			postImage.setBorderPainted(false);

			// postTextPanel
			JPanel postNewMain = new JPanel(new BorderLayout(10, 10));
			postNewMain.setBackground(new Color(255, 218, 185));

			JTextArea postNewMainText = diariesJTextArea.get(LocalpostIndex);
			JPanel postNewMainFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			postNewMainFooter.setBackground(new Color(255, 218, 185));
			postNewMainFooter.setPreferredSize(new Dimension(100, 30));

//			JLabel postCurrentDayLabel = new JLabel(FileDiary.getPostDayToString(diariesPath.get(LocalpostIndex)));
			NonBorderButton postCurrentDayLabel = new NonBorderButton(FileDiary.getPostDayToString(diariesPath.get(LocalpostIndex)));
			NonBorderButton infoBtn = new NonBorderButton("INFO");
			NonBorderButton deleteBtn = new NonBorderButton("DELETE");
			postCurrentDayLabel.setForeground(new Color(139, 69, 19));
			infoBtn.setForeground(new Color(139, 69, 19));
			deleteBtn.setForeground(new Color(139, 69, 19));
			
			postNewMainFooter.add(postCurrentDayLabel);
			postNewMainFooter.add(infoBtn);
			postNewMainFooter.add(deleteBtn);
			postNewMain.add(postNewMainText, BorderLayout.CENTER);
			postNewMain.add(postNewMainFooter, BorderLayout.SOUTH);

			// add:postImage, postTextPanel
			postNewPanel.add(postImage, BorderLayout.WEST);
			postNewPanel.add(postNewMain, BorderLayout.CENTER);

//			postPanel.add(postNow,0);
			
			postPanel.add(postNewPanel, 0);
			postIndexList.add(postNewPanel);

			infoBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JPanel parentPanel = (JPanel) deleteBtn.getParent().getParent().getParent();
					currentPostIndex = -1;
					for (int i = 0; i < postIndexList.size(); i++) {
						if (postIndexList.get(i) == parentPanel) {
							currentPostIndex = i;
							break;
						}
					}
					if (currentPostIndex == -1) {
						return;
					}
					JTextArea currentPostTextArea = diariesJTextArea.get(currentPostIndex);
					String currentText = Diary.getTextFromFile(diariesPath, currentPostIndex);
					String currentImagePath = imagesPath.get(currentPostIndex);
					DiaryEditFrame currentDiaryEditFrame = new DiaryEditFrame(FandomDiaryApp.this, "WRITE", currentText,
							currentImagePath, currentPostIndex, diariesPath, imagesPath);
					currentDiaryEditFrame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent we) {
							JTextArea tempTextArea = diariesJTextArea.get(currentPostIndex);
							tempTextArea.setText(currentDiaryEditFrame.getCurrentText());
							ButtonFilledWithImage tempImageButton = imagesBtns.get(currentPostIndex);
							tempImageButton.setIcon(new ResizedImageIcon(currentImagePath, 100, 100));
							repaint();
							revalidate();
						}
					});
				}
			});
			deleteBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete it?", "WARNING",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.NO_OPTION) {
						return;
					}
					JPanel parentPanel = (JPanel) deleteBtn.getParent().getParent().getParent();
					int currentPostIndex = -1;
					for (int i = 0; i < postIndexList.size(); i++) {
						if (postIndexList.get(i) == parentPanel) {
							currentPostIndex = i;
							break;
						}
					}
					if (currentPostIndex == -1) {
						return;
					}
//					System.out.println(currentPostIndex);
					postPanel.remove(postIndexList.get(currentPostIndex));
					postIndexList.remove(currentPostIndex);
					postIndex--;

					FileDiary.deleteTextAndImage(diariesPath, imagesPath, diariesJTextArea, imagesBtns,
							currentPostIndex);

					repaint();
					revalidate();
				}
			});
		}
		return LocalpostIndex;
//		return postIndexList.size();
	}

	

	public static void main(String[] args) {
		new FandomDiaryApp();
	}
}