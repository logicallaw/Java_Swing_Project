package frame;

import lib.NonBorderButton;
import method.Diary;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingDialog extends JDialog {
	private JFileChooser chooserImage = new JFileChooser();
	private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
	private String srcImagePath = null;

	private JFileChooser chooserMusic = new JFileChooser();
	private FileNameExtensionFilter musicFilter = new FileNameExtensionFilter("WAV", "wav");
	private String srcMusicPath = null;
	
	private boolean isSelectedImage = false;
	private boolean isSelectedMusic = false;
	private boolean isSucceed = false;

	public SettingDialog(JFrame frame) {
		super(frame, "Setting", true);
		setLayout(new BorderLayout(10, 10));

		// CENTER
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(new Color(255, 218, 165));

		JPanel centerP1 = new JPanel();
		centerP1.setLayout(new BoxLayout(centerP1, BoxLayout.X_AXIS));
		centerP1.setBackground(new Color(255, 218, 165));

		JLabel label1 = new JLabel("Default Image:");
		label1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		JTextField tf1 = new JTextField(20);
		tf1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		tf1.setText("Select a image file.");
		tf1.setBackground(new Color(255, 218, 167));
		tf1.setEditable(false);
		tf1.setBorder(null);

		NonBorderButton btn1 = new NonBorderButton("Image");
		btn1.setBackground(new Color(255, 218, 165));

		centerP1.add(label1);
		centerP1.add(Box.createRigidArea(new Dimension(25, 0)));
		centerP1.add(tf1);
		centerP1.add(Box.createRigidArea(new Dimension(10, 0)));
		centerP1.add(btn1);

		JPanel centerP2 = new JPanel();
		centerP2.setLayout(new BoxLayout(centerP2, BoxLayout.X_AXIS));
		centerP2.setBackground(new Color(255, 218, 165));

		JLabel label2 = new JLabel("Default Music:");
		label2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		JTextField tf2 = new JTextField(20);
		tf2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		tf2.setText("Select a music file.");
		tf2.setBackground(new Color(255, 218, 167));
		tf2.setEditable(false);
		tf2.setBorder(null);

		NonBorderButton btn2 = new NonBorderButton("Music");
		btn2.setBackground(new Color(255, 218, 165));

		centerP2.add(label2);
		centerP2.add(Box.createRigidArea(new Dimension(25, 0)));
		centerP2.add(tf2);
		centerP2.add(Box.createRigidArea(new Dimension(10, 0)));
		centerP2.add(btn2);

		centerPanel.add(centerP1);
		centerPanel.add(centerP2);

		// SOUTH
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		southPanel.setBackground(new Color(255, 218, 165));
		southPanel.setPreferredSize(new Dimension(420, 100));

		NonBorderButton saveButton = new NonBorderButton("Save");
		saveButton.setBackground(new Color(255, 218, 165));
		southPanel.add(saveButton);

		// Add to this : Center, South
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		setSize(420, 500);
		setLocationRelativeTo(frame);
		getContentPane().setBackground(new Color(255, 218, 165));

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooserImage.setFileFilter(imageFilter);
				int result = chooserImage.showOpenDialog(null);
				if (result != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "You didn't chooser image.", "Notice",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				srcImagePath = chooserImage.getSelectedFile().getPath();
				tf1.setText(srcImagePath);
				isSelectedImage = true;
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooserMusic.setFileFilter(musicFilter);
				int result = chooserMusic.showOpenDialog(null);
				if (result != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "You didn't chooser image.", "Notice",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				srcMusicPath = chooserMusic.getSelectedFile().getPath();
				tf2.setText(srcMusicPath);
				isSelectedMusic = true;
			}
		});
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isSelectedImage == true && isSelectedMusic == true) {
					int result = JOptionPane.showConfirmDialog(null, "Do you want to save your changes?", "Really save?",
							JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.NO_OPTION) {
						return;
					}
					JOptionPane.showMessageDialog(null, "The music update is applied at the next run.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				    Diary.copyFile(srcImagePath,"public/default_image.jpg");
				    Diary.copyFile(srcMusicPath, "public/default_music.wav");
				    isSucceed= true;
					
				} else {
					JOptionPane.showMessageDialog(null, "Please select an image and music file.", "WARNING", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public boolean isChangedImage() {
		return isSucceed;
	}

}