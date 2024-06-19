import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AudioEx extends JFrame {
    private JButton[] btns = {new JButton(new ImageIcon("public/btn_play.png")), new JButton(new ImageIcon("public/btn_stop.png")), new JButton("Play again")};
    private Clip clip = null;
    private JSlider slider;
    private Thread updateThread;
    private boolean running = true;

    public AudioEx() {
        setTitle("AudioEx");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        MyActionListener al = new MyActionListener();
        for (JButton btn : btns) {
            c.add(btn);
            btn.addActionListener(al);
        }

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0); // 최소값, 최대값, 초기값 설정
        slider.setMajorTickSpacing(10); // 주요 틱 마커 간격
        slider.setMinorTickSpacing(1); // 부 틱 마커 간격
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(350, 20));
        c.add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (slider.getValueIsAdjusting() && clip != null && clip.isOpen()) {
                    int newPosition = (int) (slider.getValue() * clip.getFrameLength() / 100.0);
                    clip.setFramePosition(newPosition);
                }
            }
        });

        setVisible(true);
        loadAudio("audio/IU_Holssi.wav");

        // Thread to update the slider based on clip position
        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    if (clip != null && clip.isOpen()) {
                        int currentFrame = clip.getFramePosition();
                        int frameLength = clip.getFrameLength();
                        int progress = (int) (currentFrame / (double) frameLength * 100);
                        slider.setValue(progress);
                    }
                    try {
                        Thread.sleep(100); // 100ms 주기로 업데이트
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        updateThread.start();
    }

    private void loadAudio(String pathName) {
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
            JButton temp = (JButton)e.getSource();
            if(temp == btns[0]) {
                clip.start();
            } else if (temp == btns[1]) {
                clip.stop();
            }
            else if (temp == btns[2]) {
                clip.setFramePosition(0);
                clip.start();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        running = false; // Thread 종료
    }

    public static void main(String[] args) {
        new AudioEx();
    }
}