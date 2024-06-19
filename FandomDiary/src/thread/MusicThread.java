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
		// clip != null && clip.isOpen()
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
