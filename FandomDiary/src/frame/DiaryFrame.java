package frame;
import app.ButtonFilledWithImage;
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
		setLayout(new BorderLayout());
		setBackground(new Color(229,207,153));
		
		// diaryHeader
		JPanel diaryHeader = new JPanel(new BorderLayout(10, 10));
		diaryHeader.setBackground(new Color(229,207,153));
		ButtonFilledWithImage[] diaryHeaderBtns = new ButtonFilledWithImage[] {
				new ButtonFilledWithImage("public/btn_exit.png", 50, 50),
				new ButtonFilledWithImage("public/btn_write.png", 50, 50)
		};
		JLabel diaryHeaderTitle = new JLabel("WRITE", JLabel.CENTER);
		diaryHeaderTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		diaryHeader.add(diaryHeaderBtns[0], BorderLayout.WEST);
		diaryHeader.add(diaryHeaderTitle, BorderLayout.CENTER);
		diaryHeader.add(diaryHeaderBtns[1], BorderLayout.EAST);

		// diaryMain
		JPanel diaryMain = new JPanel(new FlowLayout(FlowLayout.CENTER));
		diaryMain.setBackground(new Color(255, 245, 238));
		diaryMainWriteArea = new JTextArea(8, 20);
		diaryMainWriteArea.setEditable(true);
		diaryMainWriteArea.setLineWrap(true);
		diaryMainWriteArea.setWrapStyleWord(true);
		diaryMainWriteArea.setFont(new Font("Roboto", Font.PLAIN, 20));
		diaryMainWriteArea.setText(userInput);
		diaryMain.add(new JScrollPane(diaryMainWriteArea));

		// diaryFooter
		JPanel diaryFooter = new JPanel(new BorderLayout(10, 10));
		diaryFooter.setBackground(new Color(229,221,175));
		JPanel diaryFooterLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		diaryFooterLeftPanel.setBackground(new Color(229,221,175));
		ButtonFilledWithImage[] diaryFooterBtns = new ButtonFilledWithImage[] {
				new ButtonFilledWithImage("public/btn_diaryLoadImage.png", 50, 50),
				new ButtonFilledWithImage("public/btn_mainLoadImage.png", 100, 50)
		};
		
		for(ButtonFilledWithImage diaryFooterBtn : diaryFooterBtns) {
			diaryFooterLeftPanel.add(diaryFooterBtn);
		}

		JPanel diaryFooterRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		diaryFooterRightPanel.setBackground(new Color(229,221,175));

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
		diaryHeaderBtns[0].addActionListener(new ActionListener() {
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
		diaryHeaderBtns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeDiary();
				DiaryFrame.this.dispose();
			}
		});
		
		// diaryFooter Listener
		diaryFooterBtns[0].addActionListener(new ActionListener() {
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
		
		//Write Text and Image
		Diary.writeDiary(fileNameFormatted, userInput);
		Diary.writeImage(fileNameFormatted, srcPath);

		userInput = "";
		mainWriteArea.setText(userInput);
		diaryMainWriteArea.setText(userInput);
		
		repaint();
		revalidate();
	}

}
