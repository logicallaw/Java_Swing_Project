import java.awt.GraphicsEnvironment;

public class AvailableFonts {
    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = ge.getAvailableFontFamilyNames();

        for (String font : fontFamilies) {
            System.out.println(font);
        }
    }
}