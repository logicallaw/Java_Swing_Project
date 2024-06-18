import javax.swing.*;
import java.awt.*;

public class IdolDiaryApp extends JFrame {

    public IdolDiaryApp() {
        setTitle("아이돌 덕질 일지");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        JLabel appNameLabel = new JLabel("아이돌 덕질 일지", JLabel.CENTER);
        JLabel userProfileLabel = new JLabel("사용자 프로필", JLabel.RIGHT);
        headerPanel.add(appNameLabel, BorderLayout.CENTER);
        headerPanel.add(userProfileLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        // Calendar Panel
        JPanel calendarPanel = new JPanel();
        calendarPanel.setBorder(BorderFactory.createTitledBorder("이벤트 캘린더"));
        mainPanel.add(calendarPanel);

        // Journal Entry Panel
        JPanel journalEntryPanel = new JPanel();
        journalEntryPanel.setBorder(BorderFactory.createTitledBorder("일지 작성"));
        journalEntryPanel.setLayout(new BorderLayout());
        JTextArea journalTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(journalTextArea);
        journalEntryPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel attachPanel = new JPanel();
        JButton attachPhotoButton = new JButton("사진 첨부");
        JButton attachVideoButton = new JButton("비디오 첨부");
        attachPanel.add(attachPhotoButton);
        attachPanel.add(attachVideoButton);
        journalEntryPanel.add(attachPanel, BorderLayout.SOUTH);

        mainPanel.add(journalEntryPanel);
        add(mainPanel, BorderLayout.CENTER);

        // Sidebar Panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(3, 1));
        JButton photoAlbumButton = new JButton("사진 앨범");
        JButton searchButton = new JButton("검색 기능");
        JButton premiumSettingsButton = new JButton("프리미엄 설정");
        sidebarPanel.add(photoAlbumButton);
        sidebarPanel.add(searchButton);
        sidebarPanel.add(premiumSettingsButton);
        add(sidebarPanel, BorderLayout.WEST);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IdolDiaryApp());
    }
}