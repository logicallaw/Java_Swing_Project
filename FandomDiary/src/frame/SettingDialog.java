package frame;

import javax.swing.*;
import java.awt.*;

import lib.NonBorderButton;

public class SettingDialog extends JDialog {
    public SettingDialog(JFrame frame) {
        super(frame, "Setting", true);
        setLayout(new BorderLayout(10, 10));

        // CENTER
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 218, 165));

        JPanel centerP1 = new JPanel();
        centerP1.setLayout(new BoxLayout(centerP1, BoxLayout.X_AXIS));
        centerP1.setBackground(new Color(255, 218, 165));

        JLabel label1 = new JLabel("Default Image:");
        label1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        JTextField tf1 = new JTextField(20);
        tf1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        tf1.setText("images/0626_1750_30_89.jpg");
        tf1.setBackground(new Color(255, 218, 167));
        tf1.setEditable(false);

        NonBorderButton btn1 = new NonBorderButton("Image");
        btn1.setBackground(new Color(255, 218, 165));

        centerP1.add(label1);
        centerP1.add(Box.createRigidArea(new Dimension(25, 0)));
        centerP1.add(tf1);
        centerP1.add(Box.createRigidArea(new Dimension(10, 0)));
        centerP1.add(btn1);

        JPanel centerP2 = new JPanel();
        centerP2.setLayout(new BoxLayout(centerP2, BoxLayout.X_AXIS));
        centerP2.setBackground(new Color(255, 218, 165));

        JLabel label2 = new JLabel("Default Music:");
        label2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        JTextField tf2 = new JTextField(20);
        tf2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        tf2.setText("music/IU_Lovewinsall.wav");
        tf2.setBackground(new Color(255, 218, 167));
        tf2.setEditable(false);

        NonBorderButton btn2 = new NonBorderButton("Music");
        btn2.setBackground(new Color(255, 218, 165));

        centerP2.add(label2);
        centerP2.add(Box.createRigidArea(new Dimension(25, 0)));
        centerP2.add(tf2);
        centerP2.add(Box.createRigidArea(new Dimension(10, 0)));
        centerP2.add(btn2);

        centerPanel.add(centerP1);
        centerPanel.add(centerP2);

        // SOUTH
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        southPanel.setBackground(new Color(255, 218, 165));
        southPanel.setPreferredSize(new Dimension(420, 100));

        NonBorderButton saveButton = new NonBorderButton("Save");
        saveButton.setBackground(new Color(255, 218, 165));
        southPanel.add(saveButton);

        // add
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setSize(420, 500);
        setLocationRelativeTo(frame);
        getContentPane().setBackground(new Color(255, 218, 165));
    }

}