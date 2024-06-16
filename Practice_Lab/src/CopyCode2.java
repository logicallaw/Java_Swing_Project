//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.event.*;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//public class CalendarCopy extends JFrame{
//	private JLabel monthLabel = null;
//	private JButton[] dayButtons = new JButton[42]; // 6 rows X 7 columns
//	private GregorianCalendar calendar = null;
//	
//	public CalendarCopy() {
//		setTitle("Simple Calendar");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Container c = getContentPane();
//        c.setLayout(new BorderLayout());
//        
//        calendar = new GregorianCalendar();
//        
//        //header
//        JPanel header = new JPanel();
//        header.setLayout(new BorderLayout());
//        
//        JButton preBtn = new JButton("<");
//        JButton nextBtn = new JButton(">");
//        preBtn.addActionListener(new ActionListener() {
//        	@Override
//        	public void actionPerformed(ActionEvent e) {
//        		calendar.add(Calendar.MONTH, -1);
//        		updateCalendar();
//        	}
//        });
//        nextBtn.addActionListener(new ActionListener() {
//        	@Override
//        	public void actionPerformed(ActionEvent e) {
//        		calendar.add(Calendar.MONTH, 1);
//        		updateCalendar();
//        	}
//        });
//        
//        monthLabel = new JLabel("", JLabel.CENTER);
//       
//        header.add(preBtn, BorderLayout.WEST);
//        header.add(nextBtn, BorderLayout.EAST);
//        header.add(monthLabel, BorderLayout.CENTER);
//        
//        //day
//        JPanel day = new JPanel();
//        day.setLayout(new GridLayout(7,7));
//        String[] dayNames = new String[] {"Sun", "Mon", "Tue", "Wed", "Tur", "Fri", "Sat"};
//        for (String dayName : dayNames) {
//        	day.add(new JLabel(dayName, JLabel.CENTER));
//        }
//        for (int i = 0; i < dayButtons.length; i++) {
//        	dayButtons[i] = new JButton();
//        	dayButtons[i].setEnabled(false);
//        	day.add(dayButtons[i]);
//        }
//        
//        //add:header, day
//        c.add(header, BorderLayout.NORTH);
//        c.add(day, BorderLayout.CENTER);
//        
//        updateCalendar();
//        
//        setSize(700,800);
//        setVisible(true);
//        
//	}
//	private void updateCalendar() {
//		for(JButton dayButton : dayButtons) {
//			dayButton.setText("");
//			dayButton.setEnabled(true);
//			dayButton.setVerticalAlignment(SwingConstants.TOP);
//			dayButton.setHorizontalAlignment(SwingConstants.LEFT);
//			dayButton.setMargin(new Insets(10,5,10,10));
//		}
//
//		monthLabel.setText(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월");
//	
//		//create preCalendar
//		Calendar preCalendar = Calendar.getInstance();
//		preCalendar.add(Calendar.MONTH, -1);
//		int preTotalDays = preCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		
//		//create curCalendar
//		Calendar curCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
//		int curTotalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		int startDayIndex = curCalendar.get(Calendar.DAY_OF_WEEK) - 1;
//		
//		//add Previous Days
//		int preDay = preTotalDays - startDayIndex;
//		int index = 0;
//		for(int i = 0; i < startDayIndex; i++) {
//			dayButtons[index].setText(Integer.toString(preDay));
//			dayButtons[index].setForeground(Color.LIGHT_GRAY);
//			index++;
//			preDay++;
//		}
//		
//		//add Current Days
//		for(int i = 0; i < curTotalDays; i++) {
//			dayButtons[index].setText(Integer.toString(i + 1));
//			dayButtons[index].setForeground(Color.BLACK);
//			index++;
//		}
//		
//		//add Next Days
//		int copyIndex = index;
//		for (int i = 0; i < dayButtons.length - copyIndex ; i++) {
//			dayButtons[index].setText(Integer.toString(i + 1));
//			dayButtons[index].setForeground(Color.LIGHT_GRAY);
//			index++;
//		}
//		repaint();
//		revalidate();
//	}
//	public static void main(String[] args) {
//		new CalendarCopy();
//	}
//}
