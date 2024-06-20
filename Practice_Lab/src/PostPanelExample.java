import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostPanelExample extends JFrame {
    private JPanel postPanel;
    private JScrollPane scrollPane;
    private int postCount = 0;

    public PostPanelExample() {
        setTitle("Post Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        // 게시물을 추가할 패널 생성
        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));

        // 스크롤 가능한 패널로 설정
        scrollPane = new JScrollPane(postPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // 게시물 추가 버튼
        JButton addButton = new JButton("Add Post");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPost();
            }
        });
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 새로운 게시물을 추가하는 메서드
    private void addNewPost() {
        postCount++;
        JPanel newPost = new JPanel();
        newPost.setLayout(new BorderLayout());
        newPost.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newPost.setPreferredSize(new Dimension(350, 100));
        newPost.add(new JLabel("Post #" + postCount), BorderLayout.CENTER);

        postPanel.add(newPost);
        postPanel.revalidate();
        postPanel.repaint();

        // 자동으로 아래로 스크롤
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            }
        });
    }

    public static void main(String[] args) {
        new PostPanelExample();
    }
}