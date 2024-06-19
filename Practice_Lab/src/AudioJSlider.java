import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.event.*;

public class AudioJSlider extends JFrame {
	private JButton[] btns = { new JButton(new ImageIcon("public/btn_play.png")),
			new JButton(new ImageIcon("public/btn_stop.png")), new JButton("Play again") };
	private Clip clip = null;

	private JSlider slider = null;
	private TimerThread th = null;

	public AudioJSlider() {
		setTitle("AudioMyJSlider Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		MyActionListener al = new MyActionListener();
		for (JButton btn : btns) {
			c.add(btn);
			btn.addActionListener(al);
		}

		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		createAudio("audio/IU_Holssi.wav");
		th = new TimerThread(slider, clip);

		c.add(slider);
		setSize(400, 200);
		setVisible(true);

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

	private void createAudio(String pathName) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton temp = (JButton) e.getSource();
			if (temp == btns[0]) {
				clip.start();
			} else if (temp == btns[1]) {
				clip.stop();
			} else if (temp == btns[2]) {
				clip.setFramePosition(0);
				clip.start();
			}
		}
	}

	class TimerThread extends Thread {
		private JSlider slider = null;
		private Clip clip = null;

		public TimerThread(JSlider sl, Clip c) {
			slider = sl;
			clip = c;
		}

		@Override
		public void run() {
			//clip != null && clip.isOpen()
			while (clip.isOpen()) {
				int currentFrame = clip.getFramePosition();
				int frameLength = clip.getFrameLength();
				int progress = (int) (currentFrame / (double) frameLength * 100);
				slider.setValue(progress);
				try {
					sleep(100); // 100ms 주기로 업데이트
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new AudioJSlider();
	}

}
