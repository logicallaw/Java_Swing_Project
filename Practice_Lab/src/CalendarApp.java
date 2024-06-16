import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarApp extends JFrame {
    private JLabel monthLabel;
    private JButton[] dayButtons = new JButton[42]; // 6 rows x 7 columns
    private Calendar calendar;

    public CalendarApp() {
        setTitle("Simple Calendar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        calendar = new GregorianCalendar();

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, -1);
                updateCalendar();
                repaint();
                revalidate();
            }
        });
        JButton nextButton = new JButton(">");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, 1);
                updateCalendar();
                repaint();
                revalidate();
            }
        });

        monthLabel = new JLabel("", JLabel.CENTER);
        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new GridLayout(7, 7));
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String dayName : dayNames) {
            dayPanel.add(new JLabel(dayName, JLabel.CENTER));
        }
        for (int i = 0; i < dayButtons.length; i++) {
            dayButtons[i] = new JButton();
            dayButtons[i].setEnabled(false);
            dayPanel.add(dayButtons[i]);
        }

        add(headerPanel, BorderLayout.NORTH);
        add(dayPanel, BorderLayout.CENTER);

        updateCalendar();
        setVisible(true);
    }

    private void updateCalendar() {
        for (JButton dayButton : dayButtons) {
            dayButton.setText("");
            dayButton.setEnabled(false);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        monthLabel.setText(sdf.format(calendar.getTime()));

        Calendar tempCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int startDay = tempCalendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= daysInMonth; i++) {
            int index = startDay - 1 + i - 1;
            dayButtons[index].setText(String.valueOf(i));
            dayButtons[index].setEnabled(true);
        }
    }

    public static void main(String[] args) {
    	new CalendarApp();
    }
}