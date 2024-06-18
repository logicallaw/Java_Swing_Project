import javax.swing.*;
import java.awt.*;

public class IdolFandomDiary extends JFrame {

    public IdolFandomDiary() {
        setTitle("아이돌 덕질 다이어리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 헤더 패널
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(240, 228, 216));
        JLabel titleLabel = new JLabel("아이돌 덕질 다이어리", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        JLabel profileLabel = new JLabel(new ImageIcon("path/to/profile/icon.png"), JLabel.RIGHT);
        headerPanel.add(profileLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // 사이드바 패널
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(4, 1));
        sidebarPanel.setBackground(new Color(204, 229, 255));
        
        JButton journalButton = new JButton("일지 작성");
        JButton calendarButton = new JButton("이벤트 캘린더");
        JButton albumButton = new JButton("사진 앨범");
        JButton settingsButton = new JButton("설정");

        sidebarPanel.add(journalButton);
        sidebarPanel.add(calendarButton);
        sidebarPanel.add(albumButton);
        sidebarPanel.add(settingsButton);
        
        add(sidebarPanel, BorderLayout.WEST);

        // 메인 패널
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBackground(new Color(255, 248, 240));
        
        // 캘린더 뷰
        JPanel calendarViewPanel = new JPanel();
        calendarViewPanel.setBackground(new Color(230, 218, 210));
        calendarViewPanel.add(new JLabel("이벤트 캘린더", JLabel.CENTER));
        mainPanel.add(calendarViewPanel, "캘린더 뷰");
        
        // 일지 작성 섹션
        JPanel journalEntryPanel = new JPanel();
        journalEntryPanel.setBackground(new Color(245, 235, 224));
        journalEntryPanel.add(new JLabel("일지 작성", JLabel.CENTER));
        JTextArea journalTextArea = new JTextArea(20, 50);
        journalTextArea.setLineWrap(true);
        journalTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(journalTextArea);
        journalEntryPanel.add(scrollPane);
        mainPanel.add(journalEntryPanel, "일지 작성 섹션");

        // 사진 관리 섹션
        JPanel photoAlbumPanel = new JPanel();
        photoAlbumPanel.setBackground(new Color(255, 255, 255));
        photoAlbumPanel.add(new JLabel("사진 앨범", JLabel.CENTER));
        mainPanel.add(photoAlbumPanel, "사진 관리 섹션");

        add(mainPanel, BorderLayout.CENTER);

        // 디스플레이
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IdolFandomDiary());
    }
}