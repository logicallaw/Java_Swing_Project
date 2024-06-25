import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanelExample extends JFrame {
    public ButtonPanelExample() {
        setTitle("Button Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());

        // JPanel 생성
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // JButton 생성
        JButton button = new JButton("Get Panel Info");

        // JButton에 ActionListener 추가
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JButton의 부모 JPanel 정보 얻기
                JPanel parentPanel = (JPanel) button.getParent();
                System.out.println("Button's parent panel: " + parentPanel);
                // 필요한 경우 JPanel의 추가 정보나 상태를 출력 또는 처리
                // 예: 부모 패널의 배경색 변경
                parentPanel.setBackground(Color.YELLOW);
            }
        });

        // JButton을 JPanel에 추가
        panel.add(button);

        // JPanel을 JFrame에 추가
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ButtonPanelExample());
    }
}