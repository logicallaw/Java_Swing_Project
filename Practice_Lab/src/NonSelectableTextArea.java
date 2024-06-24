import javax.swing.*;
import java.awt.*;

public class NonSelectableTextArea extends JTextArea {
    
    public NonSelectableTextArea(String text, int rows, int columns) {
        super(text, rows, columns);
        setEditable(false);  // JTextArea를 편집 불가능하도록 설정
    }

    // 커서 위치 설정을 무효화
    @Override
    public void setCaretPosition(int position) {
        // 텍스트 선택 방지를 위해 아무 작업도 하지 않음
    }

    // 텍스트 선택을 무효화
    @Override
    public void select(int selectionStart, int selectionEnd) {
        // 텍스트 선택 방지를 위해 아무 작업도 하지 않음
    }

    // 선택된 텍스트를 항상 null로 반환
    @Override
    public String getSelectedText() {
        return null;  // 선택된 텍스트가 없음
    }

    // 전체 선택을 무효화
    @Override
    public void selectAll() {
        // 전체 선택 방지를 위해 아무 작업도 하지 않음
    }

    // 선택 시작 위치 설정을 무효화
    @Override
    public void setSelectionStart(int selectionStart) {
        // 선택 시작 위치 설정 방지를 위해 아무 작업도 하지 않음
    }

    // 선택 종료 위치 설정을 무효화
    @Override
    public void setSelectionEnd(int selectionEnd) {
        // 선택 종료 위치 설정 방지를 위해 아무 작업도 하지 않음
    }

    // 캐럿 리스너 추가를 무효화
    @Override
    public void addCaretListener(javax.swing.event.CaretListener listener) {
        // 캐럿 리스너 추가 방지를 위해 아무 작업도 하지 않음
    }

    // 캐럿 리스너 제거를 무효화
    @Override
    public void removeCaretListener(javax.swing.event.CaretListener listener) {
        // 캐럿 리스너 제거 방지를 위해 아무 작업도 하지 않음
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Non-Selectable JTextArea Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 예제 텍스트 설정
        String exampleText = "이것은 JTextArea에 대한 예제입니다.\n"
                            + "텍스트 선택과 드래그가 비활성화되어 있습니다.\n"
                            + "이 영역에서는 텍스트를 선택하거나 복사할 수 없습니다.";

        NonSelectableTextArea textArea = new NonSelectableTextArea(exampleText, 10, 30);
        textArea.setLineWrap(true);  // 텍스트 자동 줄바꿈
        textArea.setWrapStyleWord(true);  // 단어 단위로 줄바꿈

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}