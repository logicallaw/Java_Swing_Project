//import javax.swing.*;
//import javax.swing.text.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//public class DigitalScrapbook extends JFrame {
//    private JTextPane textPane;
//    private JPanel imagePanel;
//    private JTextField searchField;
//    private JTextArea resultArea;
//
//    public DigitalScrapbook() {
//        setTitle("Digital Scrapbook");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // 상단 패널 (이미지 추가 버튼)
//        JPanel topPanel = new JPanel(new FlowLayout());
//        JButton addImageButton = new JButton("Add Image");
//        addImageButton.addActionListener(new AddImageListener());
//        topPanel.add(addImageButton);
//        add(topPanel, BorderLayout.NORTH);
//
//        // 중앙 패널 (이미지 패널과 텍스트 패널)
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//        imagePanel = new JPanel(new FlowLayout());
//        JScrollPane imageScrollPane = new JScrollPane(imagePanel);
//        splitPane.setLeftComponent(imageScrollPane);
//
//        textPane = new JTextPane();
//        JScrollPane textScrollPane = new JScrollPane(textPane);
//        splitPane.setRightComponent(textScrollPane);
//
//        add(splitPane, BorderLayout.CENTER);
//
//        // 하단 패널 (검색 기능과 PDF 내보내기)
//        JPanel bottomPanel = new JPanel(new BorderLayout());
//        JPanel searchPanel = new JPanel(new FlowLayout());
//        searchField = new JTextField(20);
//        JButton searchButton = new JButton("Search");
//        searchButton.addActionListener(new SearchListener());
//        searchPanel.add(searchField);
//        searchPanel.add(searchButton);
//        bottomPanel.add(searchPanel, BorderLayout.NORTH);
//
//        resultArea = new JTextArea(5, 40);
//        resultArea.setEditable(false);
//        JScrollPane resultScrollPane = new JScrollPane(resultArea);
//        bottomPanel.add(resultScrollPane, BorderLayout.CENTER);
//
//        JButton exportPdfButton = new JButton("Export to PDF");
//        exportPdfButton.addActionListener(new ExportPdfListener());
//        bottomPanel.add(exportPdfButton, BorderLayout.SOUTH);
//
//        add(bottomPanel, BorderLayout.SOUTH);
//    }
//
//    private class AddImageListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JFileChooser fileChooser = new JFileChooser();
//            int returnValue = fileChooser.showOpenDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                File selectedFile = fileChooser.getSelectedFile();
//                try {
//                    BufferedImage image = ImageIO.read(selectedFile);
//                    JLabel imageLabel = new JLabel(new ImageIcon(image));
//                    imagePanel.add(imageLabel);
//                    imagePanel.revalidate();
//                    imagePanel.repaint();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private class SearchListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String keyword = searchField.getText();
//            String textContent = textPane.getText();
//            if (textContent.contains(keyword)) {
//                resultArea.setText("Keyword found: " + keyword);
//            } else {
//                resultArea.setText("Keyword not found: " + keyword);
//            }
//        }
//    }
//
//    private class ExportPdfListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//            int returnValue = fileChooser.showSaveDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                String filePath = file.getAbsolutePath();
//                if (!filePath.endsWith(".pdf")) {
//                    filePath += ".pdf";
//                }
//                try {
//                    Document document = new Document();
//                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
//                    document.open();
//                    document.add(new Paragraph(textPane.getText()));
//                    document.close();
//                    JOptionPane.showMessageDialog(null, "PDF exported successfully to " + filePath);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            DigitalScrapbook scrapbook = new DigitalScrapbook();
//            scrapbook.setVisible(true);
//        });
//    }
//}