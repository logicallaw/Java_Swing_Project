package thread;

import javax.sound.sampled.Clip;
import javax.swing.JSlider;

public class MusicThread extends Thread {
	private JSlider slider = null;
	private Clip clip = null;

	public MusicThread(JSlider sl, Clip c) {
		slider = sl;
		clip = c;
	}

	@Override
	public void run() {
		while (clip.isOpen()) {
			int currentFrame = clip.getFramePosition();
			int frameLength = clip.getFrameLength();
			int progress = (int) (currentFrame / (double) frameLength * 100);
			slider.setValue(progress);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
