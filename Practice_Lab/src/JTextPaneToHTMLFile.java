import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;

public class JTextPaneToHTMLFile extends JFrame {
    private JTextPane textPane;

    public JTextPaneToHTMLFile() {
        setTitle("JTextPane to HTML File Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextPane textPane = new JTextPane();
        textPane.setText("This is an example text.\n"
                        + "It supports multiple lines.\n"
                        + "And you can control the line spacing.\n");
        textPane.setEditable(false);

        // StyledDocument 가져오기
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();

        // 줄 간격 설정 (1.5배)
        StyleConstants.setLineSpacing(attrs, 5f); // 기본 간격에 추가 간격을 설정합니다. 0.5는 50% 추가 간격을 의미합니다.
        
        // 문서 전체에 속성 적용
        doc.setParagraphAttributes(0, doc.getLength(), attrs, false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        setSize(300,400);
    }

    public static void main(String[] args) {
        new JTextPaneToHTMLFile();
    }
}