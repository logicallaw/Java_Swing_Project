import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveLabelExample extends JFrame {
    private JPanel panel;
    private JLabel labelToRemove;

    public RemoveLabelExample() {
        setTitle("Remove JLabel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());

        // BoxLayout으로 설정된 JPanel 생성
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // JLabel 추가
        JLabel label1 = new JLabel("Label 1");
        labelToRemove = new JLabel("Label to remove");
        JLabel label3 = new JLabel("Label 3");

        panel.add(label1);
        panel.add(labelToRemove);
        panel.add(label3);

        add(panel, BorderLayout.CENTER);

        // 삭제 버튼 생성 및 이벤트 리스너 추가
        JButton removeButton = new JButton("Remove Label");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLabel(labelToRemove);
            }
        });
        add(removeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // JLabel 삭제 메서드
    private void removeLabel(JLabel label) {
        if (panel != null && label != null) {
            panel.remove(label);  // JLabel을 JPanel에서 제거
            panel.revalidate();  // 레이아웃을 재계산
            panel.repaint();     // 화면을 다시 그리기
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RemoveLabelExample());
    }
}