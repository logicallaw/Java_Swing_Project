//package lib;
//
//import javax.swing.border.AbstractBorder;
//import java.awt.*;
//
//public class RoundedBorder extends AbstractBorder {
//    private int radius;
//
//    public RoundedBorder(int radius) {
//        this.radius = radius;
//    }
//
//    @Override
//    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setColor(Color.WHITE); // 경계선 색상 설정
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 안티앨리어싱
//        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // 둥근 사각형 경계선 그리기
//        g2d.dispose();
//    }
//
//    @Override
//    public Insets getBorderInsets(Component c) {
//        return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
//    }
//
//    @Override
//    public Insets getBorderInsets(Component c, Insets insets) {
//        insets.left = insets.right = insets.top = insets.bottom = radius + 1;
//        return insets;
//    }
//}