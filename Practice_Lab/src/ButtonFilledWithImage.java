import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ButtonFilledWithImage extends JButton {
	public ButtonFilledWithImage(String pathName) {
		ImageIcon originImage = new ImageIcon(pathName);
		Image scaledImage = originImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(scaledImage);
		// 버튼 크기 설정
		setPreferredSize(new Dimension(30, 30));
		// 텍스트 제거
		setText(null);
		// 여백 제거
		setMargin(new Insets(0, 0, 0, 0));
		// 테두리 및 내용 영역 제거
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		this.setIcon(resizedIcon);
	}

}
