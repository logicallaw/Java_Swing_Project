package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DiaryFrame extends JFrame {
	// FandomDiaryApp field
	private JTextArea mainWriteArea = null;
	private JLabel mainTimeLabel = null;

	// DiaryFrame field
	private JTextArea diaryMainWriteArea = null;
	private JLabel diaryTimeLabel = null;
	
	private String userInput = null;
	private String formattedNow = null;
	private LocalDateTime now = null;

	public DiaryFrame(JTextArea mwa, JLabel mtl, LocalDateTime n) {
		mainWriteArea = mwa;
		mainTimeLabel = mtl;
		now = n;

		userInput = mwa.getText();
		formattedNow = now.format(DateTimeFormatter.ofPattern("MM/dd a HH:mm ss"));

		setTitle("일기 작성");
		setLayout(new BorderLayout(10, 10));

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
		diaryMainWriteArea = new JTextArea(8, 32);
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

		JLabel numOfCharsLabel = new JLabel(Integer.toString(0));
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
	}
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
		diaryMainWriteArea.setText(userInput);
		
		repaint();
		revalidate();
	}

}
