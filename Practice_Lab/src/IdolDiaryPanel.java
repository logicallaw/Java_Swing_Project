import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class IdolDiaryPanel extends JFrame{
	Container c = getContentPane();
	public IdolDiaryPanel() {
		setTitle("Diary Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout(10,10));
		c.setBackground(new Color(255, 248, 240));
		
		//NORTH
		JPanel north = new JPanel();
		north.setPreferredSize(new Dimension(700,100));
		
		north.setLayout(new BorderLayout());
		north.setBackground(new Color(240, 228, 216));
		
		ImageIcon img = new ImageIcon("images/dlwlrma.jpg");
		JLabel imgLabel = new JLabel(img);
		imgLabel.setPreferredSize(new Dimension(100,100));
		
		ImageIcon img1 = new ImageIcon("images/dlwlrma.jpg");
		JLabel imgLabel1 = new JLabel(img);
		imgLabel1.setPreferredSize(new Dimension(100,100));
				
		
		JPanel northC = new JPanel();
		northC.setLayout(new GridLayout(0,1,0,1));
		JLabel l1 = new JLabel("IU");
		l1.setFont(new Font("Helvetica", Font.BOLD, 14));
		northC.add(l1);
		northC.add(new JLabel("2001 02 03"));
		northC.add(new JLabel("데뷔일:"));
		
		north.add(imgLabel, BorderLayout.WEST);
		north.add(northC, BorderLayout.CENTER);
		north.add(imgLabel1, BorderLayout.EAST);
		//CENTER
		MyCalendarPane center = new MyCalendarPane();
		//SOUTH
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(100,100));
		south.setBackground(new Color(240, 248, 240));
		south.setLayout(new GridLayout(2,3));
		
		ImageIcon[] imgs = new ImageIcon[] {
				new ImageIcon("images/apple.jpg"),
				new ImageIcon("images/cherry.jpg"),
				new ImageIcon("images/banana.jpg"),
				new ImageIcon("images/pear.jpg"),
				new ImageIcon("images/mango.jpg"),
				new ImageIcon("images/beauty.jpg"),
		};
		JLabel[] las = new JLabel[6];
		for(int i = 0; i < las.length; i++) {
			las[i] = new JLabel(imgs[i]);
			south.add(las[i]);
		}
		
		//add:NORTH, CENTER, SOUTH
		c.add(north, BorderLayout.NORTH);
		c.add(center, BorderLayout.CENTER);
		c.add(south, BorderLayout.SOUTH);
		
		createMenuBar();
		
		setSize(400,700);
		setVisible(true);
	}
	private void createMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu source = new JMenu("Source");
		
		mb.add(file);
		mb.add(edit);
		mb.add(source);
		
		setJMenuBar(mb);
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
			north.setBackground(Color.YELLOW);
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
			center.setBackground(Color.cyan);
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
				dayBtns[i].setBackground(Color.LIGHT_GRAY);
				dayBtns[i].setOpaque(true);
				dayBtns[i].setBorderPainted(true);
				
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
		new IdolDiaryPanel();
	}
}
