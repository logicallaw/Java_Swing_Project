package frame;

import lib.ButtonFilledWithImage;
import method.Diary;
import method.FileDiary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DiaryFrame extends JFrame {
	// FandomDiaryApp field
	private JTextArea mainWriteArea = null;
	private Vector<String> diariesPath = null;
	private Vector<String> imagesPath = null;
	private Vector<JTextArea> diariesJLabel = null;
	private Vector<ButtonFilledWithImage> imagesBtns = null;
	private int postIndex;

	// DiaryFrame field
	private JTextArea diaryMainWriteArea = null;
	private JLabel diaryTimeLabel = null;
	private String formattedNow = null;
	private LocalDateTime now = null;

	private String userInput = "";
	private String srcPath = null;
	private boolean isClickedWrite = false;

	public DiaryFrame(JFrame frame, String title, JTextArea mwa, Vector<String> dp, Vector<String> ip, Vector<JTextArea> dj,
			Vector<ButtonFilledWithImage> iI, LocalDateTime n, int pIx, String srPh) {
		mainWriteArea = mwa;
		
		diariesPath = dp;
		imagesPath = ip;
		diariesJLabel = dj;
		imagesBtns = iI;

		now = n;
		postIndex = pIx;
		srcPath = srPh;

		userInput = mwa.getText();
		formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm"));

		setTitle(title);
		setLayout(new BorderLayout());
		setBackground(new Color(229, 207, 153));

		// diaryHeader
		JPanel diaryHeader = new JPanel(new BorderLayout(10, 10));
		diaryHeader.setBackground(new Color(229, 207, 153));
		ButtonFilledWithImage[] diaryHeaderBtns = new ButtonFilledWithImage[] {
				new ButtonFilledWithImage("public/btn_exit.png", 50, 50),
				new ButtonFilledWithImage("public/btn_write.png", 50, 50) };
		JLabel diaryHeaderTitle = new JLabel("WRITE", JLabel.CENTER);
		diaryHeaderTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

		diaryHeader.add(diaryHeaderBtns[0], BorderLayout.WEST);
		diaryHeader.add(diaryHeaderTitle, BorderLayout.CENTER);
		diaryHeader.add(diaryHeaderBtns[1], BorderLayout.EAST);

		// diaryMain
		JPanel diaryMain = new JPanel(new FlowLayout(FlowLayout.CENTER));
		diaryMain.setBackground(new Color(255, 245, 238));
		diaryMainWriteArea = new JTextArea(15, 36);
		diaryMainWriteArea.setLineWrap(true);
		diaryMainWriteArea.setWrapStyleWord(true);
		diaryMainWriteArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		diaryMainWriteArea.setText(userInput);
		diaryMainWriteArea.setBackground(new Color(255, 239, 219));
		diaryMain.add(new JScrollPane(diaryMainWriteArea));

		// diaryFooter
		JPanel diaryFooter = new JPanel(new BorderLayout(10, 10));
		diaryFooter.setBackground(new Color(229, 221, 175));
		JPanel diaryFooterLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		diaryFooterLeftPanel.setBackground(new Color(229, 221, 175));
		ButtonFilledWithImage diaryImageButton = new ButtonFilledWithImage("public/btn_diaryLoadImage.png", 50, 50);
		diaryFooterLeftPanel.add(diaryImageButton);
		
		JPanel diaryFooterRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		diaryFooterRightPanel.setBackground(new Color(229, 221, 175));

		JLabel numOfCharsLabel = new JLabel(Integer.toString(mainWriteArea.getText().length()));
		numOfCharsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		diaryFooterRightPanel.add(numOfCharsLabel);
		diaryTimeLabel = new JLabel(formattedNow);
		diaryTimeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		diaryFooterRightPanel.add(diaryTimeLabel);

		diaryFooter.add(diaryFooterLeftPanel, BorderLayout.WEST);
		diaryFooter.add(diaryFooterRightPanel, BorderLayout.EAST);

		// add:diaryHeader, diaryMain, diaryFooter
		add(diaryHeader, BorderLayout.NORTH);
		add(diaryMain, BorderLayout.CENTER);
		add(diaryFooter, BorderLayout.SOUTH);

		setSize(600, 800);
		setLocationRelativeTo(frame);
		diaryMainWriteArea.setFocusable(true);
		diaryMainWriteArea.requestFocus();
		setVisible(true);

		// diaryHeader Listener
		diaryHeaderBtns[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWriteArea.setText(userInput);
				DiaryFrame.this.dispose();
			}
		});
		diaryHeaderBtns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isClickedWrite = true;
				writeDiary();
				DiaryFrame.this.dispose();
			}
		});
		diaryMainWriteArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				numOfCharsLabel.setText(Integer.toString(diaryMainWriteArea.getText().length()));
				userInput = diaryMainWriteArea.getText();
			}
		});

		// diaryFooter Listener
		diaryImageButton.addActionListener(new ActionListener() {
			private JFileChooser chooser = new JFileChooser();
			private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setFileFilter(imageFilter);
				int result = chooser.showOpenDialog(null);
				if (result != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "You didn't choose a image.", "Notice",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				srcPath = chooser.getSelectedFile().getPath();
				diaryMainWriteArea.setFocusable(true);
				diaryMainWriteArea.requestFocus();
			}
		});
	}

	public String getSrcPath() {
		return srcPath;
	}

	public String getUserInput() {
		return userInput;
	}

	public boolean isTyping() {
		return userInput.length() != 0;
	}

	public boolean hasImage() {
		return srcPath != null;
	}

	public boolean isClickedWrite() {
		return isClickedWrite;
	}

	private void writeDiary() {
		String fileNameFormatted = now.format(DateTimeFormatter.ofPattern("MMdd_HHmm_ss_SS"));
		
		// Write Text and Image
		Diary.writeDiary(fileNameFormatted, userInput);
		Diary.writeImage(fileNameFormatted, srcPath);

		FileDiary.getFilePath(diariesPath, imagesPath);
		FileDiary.addTextsToVector(diariesPath, diariesJLabel, postIndex);
		FileDiary.addImagesToVector(imagesPath, imagesBtns, postIndex);

		userInput = "";
		srcPath = null;
	}

}
