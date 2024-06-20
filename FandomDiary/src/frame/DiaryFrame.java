package frame;

import method.Diary;
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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DiaryFrame extends JFrame{
	// FandomDiaryApp field
	private JTextArea mainWriteArea = null;
	private JLabel mainTimeLabel = null;

	// DiaryFrame field
	private JTextArea diaryMainWriteArea = null;
	private JLabel diaryTimeLabel = null;
	
	private String userInput = null;
	private String formattedNow = null;
	private LocalDateTime now = null;
	private String srcPath = null;
	
	// Listener

	public DiaryFrame(JTextArea mwa, JLabel mtl, LocalDateTime n) {
		mainWriteArea = mwa;
		mainTimeLabel = mtl;
		now = n;

		userInput = mwa.getText();
		formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm ss"));
		
		setTitle("Write");
		setLayout(new BorderLayout(10, 10));

		// diaryHeader
		JPanel diaryHeader = new JPanel(new BorderLayout(10, 10));
		diaryHeader.setBackground(new Color(239, 231, 221));
		JButton diaryHeaderExit = new JButton("X");
		JLabel diaryHeaderTitle = new JLabel("WRITE", JLabel.CENTER);
		diaryHeaderTitle.setFont(new Font("Arial", Font.BOLD, 20));
		JButton diaryHeaderWrite = new JButton("V");
		
		diaryHeader.add(diaryHeaderExit, BorderLayout.WEST);
		diaryHeader.add(diaryHeaderTitle, BorderLayout.CENTER);
		diaryHeader.add(diaryHeaderWrite, BorderLayout.EAST);

		// diaryMain
		JPanel diaryMain = new JPanel(new FlowLayout(FlowLayout.CENTER));
		diaryMain.setBackground(new Color(255, 245, 238));
		diaryMainWriteArea = new JTextArea(8, 32);
		diaryMainWriteArea.setEditable(true);
		diaryMainWriteArea.setLineWrap(true);
		diaryMainWriteArea.setWrapStyleWord(true);
		diaryMainWriteArea.setFont(new Font("Arial", Font.PLAIN, 20));
		diaryMainWriteArea.setText(userInput);
		diaryMain.add(new JScrollPane(diaryMainWriteArea));

		// diaryFooter
		JPanel diaryFooter = new JPanel(new BorderLayout(10, 10));
		diaryFooter.setBackground(new Color(255, 245, 238));
		JPanel diaryFooterLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton[] diaryBtns = new JButton[] { new JButton("사진"), new JButton("위치") };
		
		for (int i = 0; i < diaryBtns.length; i++) {
			diaryFooterLeftPanel.add(diaryBtns[i]);
		}
		
		JPanel diaryFooterRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

		JLabel numOfCharsLabel = new JLabel(Integer.toString(mainWriteArea.getText().length()));
		diaryFooterRightPanel.add(numOfCharsLabel);
		diaryTimeLabel = new JLabel(formattedNow);
		diaryFooterRightPanel.add(diaryTimeLabel);

		diaryFooter.add(diaryFooterLeftPanel, BorderLayout.WEST);
		diaryFooter.add(diaryFooterRightPanel, BorderLayout.EAST);

		// add:diaryHeader, diaryMain, diaryFooter
		add(diaryHeader, BorderLayout.NORTH);
		add(diaryMain, BorderLayout.CENTER);
		add(diaryFooter, BorderLayout.SOUTH);

		setSize(600, 800);
		setVisible(true);

		diaryMainWriteArea.setFocusable(true);
		diaryMainWriteArea.requestFocus();
		
		// diaryHeader Listener
		diaryHeaderExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWriteArea.setText(userInput);
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
		diaryHeaderWrite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeDiary();
				DiaryFrame.this.dispose();
			}
		});
		
		// diaryFooter Listener
		diaryBtns[0].addActionListener(new ActionListener() {
			private JFileChooser chooser = new JFileChooser();
			private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setFileFilter(imageFilter);
				int result = chooser.showOpenDialog(null);
				if(result != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "You didn't chooser image.", "Notice", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				srcPath = chooser.getSelectedFile().getPath();
				DiaryFrame.this.diaryMainWriteArea.setFocusable(true);
				DiaryFrame.this.diaryMainWriteArea.requestFocus();
			}
		});
	}
	private void writeDiary() {
		String fileNameFormatted = now.format(DateTimeFormatter.ofPattern("MMdd_HHmm_ss"));
		
		// Write Text
		Diary.writeDiary(fileNameFormatted, userInput);
		
		// Write Image
		if(srcPath != null) {
			int dotIndex = srcPath.lastIndexOf(".");
			String srcExtension = srcPath.substring(dotIndex);
			String destPath = "images/" + fileNameFormatted + srcExtension;
			Diary.copyImage(srcPath, destPath);
		} else {
			String destPath = "images/" + fileNameFormatted + ".jpg";
			Diary.copyImage("config/image/default_image.jpg", destPath);
		}
		
		userInput = "";
		mainWriteArea.setText(userInput);
		diaryMainWriteArea.setText(userInput);
		
		repaint();
		revalidate();
	}

}
