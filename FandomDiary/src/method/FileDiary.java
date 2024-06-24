package method;

import lib.ButtonFilledWithImage;

import java.util.Vector;
import java.util.Collections;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import frame.DiaryFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileDiary {
	public static void getFilePath(Vector<String> diariesPath, Vector<String> imagesPath) {
		File dir = new File("diaries");
		File[] subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if (subFile.getName().equals(".DS_Store") || diariesPath.contains(subFile.getName())) { // If your computer is MacOS, we must distinguish ".DS_Store"
				continue;
			}
			diariesPath.add("diaries/" + subFile.getName());

		}
		// https://reakwon.tistory.com/153
		Collections.sort(diariesPath);

		dir = new File("images");
		subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if (subFile.getName().equals(".DS_Store") || imagesPath.contains(subFile.getName())) {
				continue;
			}
			imagesPath.add("images/" + subFile.getName());
		}
		Collections.sort(imagesPath);
	}

	public static void addTexts(Vector<String> diariesPath, Vector<JTextArea> diariesJTextArea, int postIndex) {
//		for (String filePath : diariesPath) {
		for(; postIndex < diariesPath.size(); postIndex++) {
			StringBuffer sb = new StringBuffer();
			try {
				FileInputStream fis = new FileInputStream(diariesPath.get(postIndex));
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);

				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				isr.close();
				fis.close();
			} catch (IOException err) {
				err.printStackTrace();
			}
			int sbIndex = (sb.length() > 430) ? 430 : sb.length();
			JTextArea diary = new JTextArea(6, 20);
			diary.setText(sb.substring(0, sbIndex));
			diary.setEditable(false);
			diariesJTextArea.add(diary);
		}
	}

	public static void addImages(Vector<String> imagesPath, Vector<ButtonFilledWithImage> imagesBtns, int postIndex) {
		for(; postIndex < imagesPath.size(); postIndex++) {
			ButtonFilledWithImage temp = new ButtonFilledWithImage(imagesPath.get(postIndex), 100, 100);
			imagesBtns.add(temp);
		}
	}
	
	public static int updatePosts(LinkedList<JPanel> postList, Vector<JTextArea> diariesJTextArea, Vector<String> diariesPath, Vector<ButtonFilledWithImage> imagesBtns, JPanel postPanel,
			int postIndex) {
		/* 해당 게시글이 첫 번째일때만 출력
		 * 다른 날짜는 update필요
		 * 
		 */
//		String formattedNow = diariesPath.get(postIndex).replace(".txt", "");
//		String[] listNow = formattedNow.split("_");
//		int month = Integer.parseInt(listNow[0].substring(0,2));
//		int day = Integer.parseInt(listNow[0].substring(2,4));
//		String postNowString = getPostNowString(month, day);
//		
//		JLabel postNow = new JLabel(postNowString, JLabel.LEFT);
//		postNow.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		
		for (; postIndex < diariesJTextArea.size(); postIndex++) {

			JPanel postNewPanel= new JPanel(new BorderLayout(10, 10));
			postNewPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			postNewPanel.setBackground(new Color(255,218,185));
			
			ButtonFilledWithImage postImage = imagesBtns.get(postIndex);
			
			// postTextPanel
			JPanel postNewMain = new JPanel(new BorderLayout(10,10));
			postNewMain.setBackground(new Color(255,218,185));
			
			JTextArea postNewMainText = diariesJTextArea.get(postIndex);
			postNewMainText.setRows(6);
			postNewMainText.setColumns(10);
			postNewMainText.setLineWrap(true);
//			postNewMainText.setWrapStyleWord(true);
			postNewMainText.setPreferredSize(new Dimension(100,50));
			
			postNewMainText.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			postNewMainText.setBackground(new Color(255,218,185));
			
			JPanel postNewMainFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
			postNewMainFooter.setBackground(new Color(255,218,155));
			postNewMainFooter.setPreferredSize(new Dimension(100, 30));
			JLabel la1 = new JLabel("INFO");
			la1.setForeground(Color.LIGHT_GRAY);
			la1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			JLabel la2 = new JLabel("DELETE");
			la2.setForeground(Color.LIGHT_GRAY);
			la2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			postNewMainFooter.add(la1);
			postNewMainFooter.add(la2);
			postNewMain.add(postNewMainText, BorderLayout.CENTER);
			postNewMain.add(postNewMainFooter, BorderLayout.SOUTH);
			
			// add:postImage, postTextPanel
			postNewPanel.add(postImage, BorderLayout.WEST);
			postNewPanel.add(postNewMain, BorderLayout.CENTER);
			
			
//			postPanel.add(postNow,0);
			postList.add(postNewPanel);
			postPanel.add(postNewPanel, 0);
		}
		return postIndex;
	}
	public static String getPostNowString(int month, int day) {
		String[] months = {
	            "January", "February", "March", "April", "May", "June",
	            "July", "August", "September", "October", "November", "December"
	        };
		if(month >= 1 && month <= 12) {
			return months[month - 1] + " " + day;
		}
		else {
			return "";
		}
	}
}
