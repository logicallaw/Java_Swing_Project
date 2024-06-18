import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IdolFandomDiaryUI extends JFrame {

    private List<DiaryEntry> diaryEntries = new ArrayList<>();
    private JPanel titlePanel;

    public IdolFandomDiaryUI() {
        setTitle("Fandom Diary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // 레이아웃 설정
        setLayout(new BorderLayout(10, 10));

        // 헤더 패널
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Fandom Diary");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        headerPanel.add(new JButton("설정"));
        headerPanel.add(new JButton("검색"));
        headerPanel.add(new JButton("달력"));

        // 글쓰기 버튼
        JButton writeButton = new JButton("글쓰기");
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDiaryEntryWindow();
            }
        });
        headerPanel.add(writeButton);

        add(headerPanel, BorderLayout.NORTH);

        // 사이드바 패널
        JPanel sidebarPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        sidebarPanel.setBackground(new Color(240, 240, 240));
        for (int i = 1; i <= 5; i++) {
            JPanel idolPanel = new JPanel();
            idolPanel.setLayout(new BorderLayout());
            JLabel idolLabel = new JLabel(new ImageIcon("path/to/idol" + i + ".jpg"));
            idolLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JButton likeButton = new JButton("❤️");
            idolPanel.add(idolLabel, BorderLayout.CENTER);
            idolPanel.add(likeButton, BorderLayout.SOUTH);
            sidebarPanel.add(idolPanel);
        }
        add(sidebarPanel, BorderLayout.WEST);

        // 메인 패널 설정
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);

        // 글쓰기 섹션
        JPanel writeSection = new JPanel();
        writeSection.setLayout(new BorderLayout(5, 5));
        writeSection.setBorder(BorderFactory.createTitledBorder("일기 작성"));

        JTextField titleField = new JTextField("제목을 입력하세요...", 30);
        JTextArea diaryTextArea = new JTextArea(5, 40);
        diaryTextArea.setLineWrap(true);
        diaryTextArea.setWrapStyleWord(true);

        JPanel writeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String diaryText = diaryTextArea.getText();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                diaryEntries.add(new DiaryEntry(title, diaryText));
                JOptionPane.showMessageDialog(mainPanel, "일기가 저장되었습니다:\n" + title + "\n" + currentDate);
                refreshTitleList();
                titleField.setText("");
                diaryTextArea.setText("");
            }
        });
        writeButtonPanel.add(saveButton);

        writeSection.add(titleField, BorderLayout.NORTH);
        writeSection.add(new JScrollPane(diaryTextArea), BorderLayout.CENTER);
        writeSection.add(writeButtonPanel, BorderLayout.SOUTH);

        mainPanel.add(writeSection, BorderLayout.NORTH);

        // 제목 리스트 패널
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.WHITE);

        // 제목 리스트에 스크롤 기능 추가
        JScrollPane titleScrollPane = new JScrollPane(titlePanel);
        titleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(titleScrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // 디스플레이
        setVisible(true);
    }

    // 글쓰기 창을 여는 메서드
    private void openDiaryEntryWindow() {
        JFrame diaryFrame = new JFrame("일기 작성");
        diaryFrame.setSize(600, 400);
        diaryFrame.setLayout(new BorderLayout(10, 10));
        diaryFrame.setLocationRelativeTo(this);

        // 날짜와 시간을 출력하는 라벨
        JLabel dateLabel = new JLabel("날짜: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()), JLabel.LEFT);
        JLabel timeLabel = new JLabel("시간: " + new SimpleDateFormat("HH:mm:ss").format(new Date()), JLabel.LEFT);

        // 일기 제목 필드
        JTextField titleField = new JTextField(30);

        // 일기 텍스트 필드
        JTextArea diaryTextArea = new JTextArea(10, 40);
        diaryTextArea.setLineWrap(true);
        diaryTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(diaryTextArea);

        // 사진 첨부 버튼
        JButton attachPhotoButton = new JButton("사진 첨부");
        attachPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(diaryFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // 선택한 파일을 처리하는 코드 작성
                    JOptionPane.showMessageDialog(diaryFrame, "사진이 첨부되었습니다: " + selectedFile.getAbsolutePath());
                }
            }
        });

        // 저장 버튼
        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 일기 저장 로직 구현
                String title = titleField.getText();
                String diaryText = diaryTextArea.getText();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                diaryEntries.add(new DiaryEntry(title, diaryText));
                JOptionPane.showMessageDialog(diaryFrame, "일기가 저장되었습니다:\n" + title + "\n" + diaryText + "\n" + currentDate);
                diaryFrame.dispose(); // 창 닫기
                refreshTitleList();
            }
        });

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dateLabel);
        datePanel.add(timeLabel);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(new JLabel("제목: "));
        titlePanel.add(titleField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(attachPhotoButton);
        buttonPanel.add(saveButton);

        diaryFrame.add(datePanel, BorderLayout.NORTH);
        diaryFrame.add(titlePanel, BorderLayout.CENTER);
        diaryFrame.add(scrollPane, BorderLayout.CENTER);
        diaryFrame.add(buttonPanel, BorderLayout.SOUTH);

        diaryFrame.setVisible(true);
    }

    // 일기 보기 창을 여는 메서드
    private void openDiaryViewWindow(DiaryEntry entry) {
        JFrame diaryViewFrame = new JFrame("일기 보기");
        diaryViewFrame.setSize(600, 400);
        diaryViewFrame.setLayout(new BorderLayout(10, 10));
        diaryViewFrame.setLocationRelativeTo(this);

        JLabel titleLabel = new JLabel("제목: " + entry.getTitle(), JLabel.CENTER);
        JTextArea diaryTextArea = new JTextArea(entry.getContent());
        diaryTextArea.setLineWrap(true);
        diaryTextArea.setWrapStyleWord(true);
        diaryTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(diaryTextArea);

        diaryViewFrame.add(titleLabel, BorderLayout.NORTH);
        diaryViewFrame.add(scrollPane, BorderLayout.CENTER);

        diaryViewFrame.setVisible(true);
    }

    // 제목 리스트 새로 고침 메서드
    private void refreshTitleList() {
        titlePanel.removeAll();
        for (DiaryEntry entry : diaryEntries) {
            JButton titleButton = new JButton(entry.getTitle());
            titleButton.setHorizontalAlignment(SwingConstants.LEFT);
            titleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openDiaryViewWindow(entry);
                }
            });

            // 제목 버튼을 꽉 차게 만들기 위해 JPanel 사용
            JPanel titleButtonPanel = new JPanel(new BorderLayout());
            titleButtonPanel.add(titleButton, BorderLayout.CENTER);        titlePanel.add(titleButtonPanel);
        }
        titlePanel.revalidate();
        titlePanel.repaint();
    }

    // 일기 항목 클래스
    class DiaryEntry {
        private String title;
        private String content;

        public DiaryEntry(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IdolFandomDiaryUI());
    }
}