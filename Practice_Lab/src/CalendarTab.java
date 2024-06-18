import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
/* Tab To Tab
 * 탭 안에 캘린더 구현 완료.
 * 하지만, 같은 calendar로 탭 간 전환이 안됨(중복으로 인식)
 * 다른 방법을 생각해야됨.
 */
public class CalendarTab extends JFrame{
	MyCalendarPane calendar = new MyCalendarPane();
	public CalendarTab() {
		setTitle("Calendar Tab to Tab");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JTabbedPane tabs = createTabbedPane();
		c.add(tabs);
		MyCalendarPane calendar = new MyCalendarPane();
		
		setSize(700,800);
		setVisible(true);
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.NORTH);
		
		pane.addTab("Summary", calendar);
		pane.addTab("Income/Expediture", calendar);
		pane.addTab("HI", calendar);
		return pane;
	}
	private class MyCalendarPane extends JPanel{
		private Container c = getContentPane();
		private JLabel monthLabel = new JLabel("", JLabel.CENTER);
		private JButton[] dayBtns = new JButton[42];
		private GregorianCalendar calendar = new GregorianCalendar();
		
		public MyCalendarPane() {
			MyCalendarPane me = MyCalendarPane.this;
			me.setLayout(new BorderLayout());
			
			// NORTH
			JPanel north = new JPanel();
			north.setLayout(new BorderLayout());
			JButton preBtn = new JButton("<");
			JButton nextBtn = new JButton(">");
			preBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					calendar.add(Calendar.MONTH, -1);
					updateCalendar();
				}
			});
			nextBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					calendar.add(Calendar.MONTH, 1);
					updateCalendar();
				}
			});
			north.add(preBtn, BorderLayout.WEST);
			north.add(monthLabel, BorderLayout.CENTER);
			north.add(nextBtn, BorderLayout.EAST);
			
			// CENTER
			JPanel center = new JPanel();
			center.setLayout(new GridLayout(7, 7));
			String[] dayNames = new String[] { "Sun", "Mon", "Tue", "Wed", "Tur", "Fri", "Sat" };
			for (String dayName : dayNames) {
				center.add(new JLabel(dayName, JLabel.CENTER));
			}
			for (int i = 0; i < dayBtns.length; i++) {
				dayBtns[i] = new JButton("");
				dayBtns[i].setEnabled(true);
				dayBtns[i].setVerticalAlignment(SwingConstants.TOP);
				dayBtns[i].setHorizontalAlignment(SwingConstants.LEFT);
				dayBtns[i].setMargin(new Insets(10, 5, 10, 10));
				center.add(dayBtns[i]);
			}
			updateCalendar();
			me.add(north, BorderLayout.NORTH);
			me.add(center, BorderLayout.CENTER);
			
		}
		private void updateCalendar() {
			monthLabel.setText(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월");
			// create preCalendar object
			Calendar preCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
			int preTotalDays = preCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			// create curCalendar object
			Calendar curCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
			int curTotalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int startIndex = curCalendar.get(Calendar.DAY_OF_WEEK) - 1;

			// add Previous Days
			int preDay = preTotalDays - startIndex + 1;
			int index = 0;
			for (int i = 0; i < startIndex; i++) {
				dayBtns[index].setText(Integer.toString(preDay));
				dayBtns[index].setForeground(Color.LIGHT_GRAY);
				index++;
				preDay++;
			}

			// add Current Days
			for (int i = 0; i < curTotalDays; i++) {
				dayBtns[index].setText(Integer.toString(i + 1));
				dayBtns[index].setForeground(Color.BLACK);
				index++;
			}

			// add Next Days
			int copyIndex = index;
			for (int i = 0; i < dayBtns.length - copyIndex; i++) {
				dayBtns[index].setText(Integer.toString(i + 1));
				dayBtns[index].setForeground(Color.LIGHT_GRAY);
				index++;
			}
			repaint();
			revalidate();

		}
	}
	public static void main(String[] args) {
		new CalendarTab();
	}
}

