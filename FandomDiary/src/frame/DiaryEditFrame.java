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

public class DiaryEditFrame extends JFrame {
	// DiaryFrame field
	private JTextArea diaryMainWriteArea = null;
	private String currentText = null;
	private String currentImagePath = null;
	private int currentPostIndex;
	private Vector<String> diariesPath = null;
	private Vector<String> imagesPath = null;
	private boolean isSelectedImage = false;
	private ImageIcon nowImage = null;

	public DiaryEditFrame(JFrame frame, String title, String cT, String cIP, int cI, Vector<String> dP, Vector<String> iP) {
		setTitle(title);
		setLayout(new BorderLayout());
		setBackground(new Color(229, 207, 153));
		
		currentText = cT;
		currentImagePath = cIP;
		currentPostIndex = cI;
		diariesPath = dP;
		imagesPath = iP;
		
		// diaryHeader
		JPanel diaryHeader = new JPanel(new BorderLayout(10, 10));
		diaryHeader.setBackground(new Color(229, 207, 153));
		ButtonFilledWithImage[] diaryHeaderBtns = new ButtonFilledWithImage[] {
				new ButtonFilledWithImage("public/btn_exit.png", 50, 50),
				new ButtonFilledWithImage("public/btn_write.png", 50, 50) };
		JLabel diaryHeaderTitle = new JLabel("EDIT", JLabel.CENTER);
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
		diaryMainWriteArea.setText(currentText);
		diaryMainWriteArea.setBackground(new Color(255, 239, 219));
		diaryMain.add(new JScrollPane(diaryMainWriteArea));

		// diaryFooter
		JPanel diaryFooter = new JPanel(new BorderLayout(10, 10));
		diaryFooter.setBackground(new Color(229, 221, 175));
		JPanel diaryFooterLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		diaryFooterLeftPanel.setBackground(new Color(229, 221, 175));
		ButtonFilledWithImage diaryImageButton = new ButtonFilledWithImage("public/btn_diaryLoadImage.png", 50, 50);
//		ButtonFilledWithImage[] diaryFooterBtns = new ButtonFilledWithImage[] {
//				new ButtonFilledWithImage("public/btn_diaryLoadImage.png", 50, 50),
//				new ButtonFilledWithImage("public/btn_mainLoadImage.png", 100, 50) };
//
//		for (ButtonFilledWithImage diaryFooterBtn : diaryFooterBtns) {
//			diaryFooterLeftPanel.add(diaryFooterBtn);
//		}
		diaryFooterLeftPanel.add(diaryImageButton);

		JPanel diaryFooterRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		diaryFooterRightPanel.setBackground(new Color(229, 221, 175));

		JLabel numOfCharsLabel = new JLabel(Integer.toString(diaryMainWriteArea.getText().length()));
		numOfCharsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		diaryFooterRightPanel.add(numOfCharsLabel);
		
//		diaryTimeLabel = new JLabel(formattedNow);
//		diaryTimeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
//		diaryFooterRightPanel.add(diaryTimeLabel);

		diaryFooter.add(diaryFooterLeftPanel, BorderLayout.WEST);
		diaryFooter.add(diaryFooterRightPanel, BorderLayout.EAST);

		// add:diaryHeader, diaryMain, diaryFooter
		add(diaryHeader, BorderLayout.NORTH);
		add(diaryMain, BorderLayout.CENTER);
		add(diaryFooter, BorderLayout.SOUTH);
		
		diaryHeaderBtns[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		diaryMainWriteArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				numOfCharsLabel.setText(Integer.toString(diaryMainWriteArea.getText().length()));
				currentText = diaryMainWriteArea.getText();
			}
		});
		diaryHeaderBtns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editDiary();
				dispose();
			}
		});
		
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
				isSelectedImage = true;
				currentImagePath = chooser.getSelectedFile().getPath();
				diaryMainWriteArea.setFocusable(true);
				diaryMainWriteArea.requestFocus();
			}
		});
		
		setSize(600, 800);
		setLocationRelativeTo(frame);
		diaryMainWriteArea.setFocusable(true);
		diaryMainWriteArea.requestFocus();
		setVisible(true);
	}
	
	public void editDiary() {
		currentText = diaryMainWriteArea.getText();
		Diary.editTextFromFile(diariesPath, currentText, currentPostIndex);
		if(isSelectedImage) {
			Diary.editImageFromFile(imagesPath, currentImagePath, currentPostIndex);
		}
	}
	public String getCurrentText() {
		return currentText;
	}
	
}
