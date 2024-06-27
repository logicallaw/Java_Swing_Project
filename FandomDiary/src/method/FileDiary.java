package method;

import lib.ButtonFilledWithImage;
import java.util.Vector;
import java.util.Collections;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileDiary {	
	// Browse the diaries and images folders to save and manage each file name as a vector.
	public static void getFilePath(Vector<String> diariesPath, Vector<String> imagesPath) {
		File dir = new File("diaries");
		File[] subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if (subFile.getName().equals(".DS_Store") || diariesPath.contains("diaries/" + subFile.getName())) { // If your computer is MacOS, we must distinguish ".DS_Store"
				continue;
			}
			diariesPath.add("diaries/" + subFile.getName());

		}
		// https://reakwon.tistory.com/153
		Collections.sort(diariesPath);

		dir = new File("images");
		subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if (subFile.getName().equals(".DS_Store") || imagesPath.contains("images/" + subFile.getName())) {
				continue;
			}
			imagesPath.add("images/" + subFile.getName());
		}
		Collections.sort(imagesPath);
	}
	// Make the text of the post JTextArea and save it as a vector.
	public static void addTextsToVector(Vector<String> diariesPath, Vector<JTextArea> diariesJTextArea, int postIndex) {
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
			diary.setBackground(new Color(255, 218, 185));
			diary.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			diary.setRows(6);
			diary.setColumns(10);
			diary.setLineWrap(true);
			diary.setEditable(false);
			diary.setPreferredSize(new Dimension(100, 50));
			
			diariesJTextArea.add(diary);
		}
	}
	// Make the image of the post a thumbnail and save it as a vector.
	public static void addImagesToVector(Vector<String> imagesPath, Vector<ButtonFilledWithImage> imagesBtns, int postIndex) {
		for(; postIndex < imagesPath.size(); postIndex++) {
			ButtonFilledWithImage temp = new ButtonFilledWithImage(imagesPath.get(postIndex), 100, 100);
			imagesBtns.add(temp);
		}
	}
	// When you press the Delete button in mainGallery, you perform an operation to delete the text and image of the post.
	public static void deleteTextAndImage(Vector<String> diariesPath, Vector<String> imagesPath, Vector<JTextArea> diariesJTextArea, Vector<ButtonFilledWithImage> imagesBtns, int deleteIndex) {
		File deleteText = new File(diariesPath.get(deleteIndex));
		File deleteImage = new File(imagesPath.get(deleteIndex));
		diariesPath.remove(deleteIndex);
		imagesPath.remove(deleteIndex);
		diariesJTextArea.remove(deleteIndex);
		imagesBtns.remove(deleteIndex);
		
		if(deleteText.delete() && deleteImage.delete()) {
			System.out.println("Delete is success!");
		}
		else {
			System.out.println("Failed for delete.");
		}
	}
	public static void getFilePathSidebar(Vector<String> sbLabelsPath) {
		File dir = new File("public/sidebar");
		File[] subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if (subFile.getName().equals(".DS_Store") || sbLabelsPath.contains("public/sidebar/" + subFile.getName())) { // If your computer is MacOS, we must distinguish ".DS_Store"
				continue;
			}
			sbLabelsPath.add("public/sidebar/" + subFile.getName());
		}
		// https://reakwon.tistory.com/153
		Collections.sort(sbLabelsPath);
	}
	
	public static void addSideBarToVector(Vector<String> sbLabelsPath, Vector<JLabel> sbLabels) {
		for(int i = 0; i < sbLabelsPath.size(); i++) {
			StringBuffer sb = new StringBuffer();
			try {
				FileInputStream fis = new FileInputStream(sbLabelsPath.get(i));
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);

				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				isr.close();
				fis.close();
			} catch (IOException err) {
				err.printStackTrace();
			}
			JLabel sbLabel = new JLabel(sb.toString());
			sbLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			sbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			sbLabels.add(sbLabel);
		}
	}
	
	public static void editSideBar(String beforeText, String afterText, Vector<String> sbLabelsPath) {
		for(String sbLabelPath : sbLabelsPath) {
			StringBuffer sb = new StringBuffer();
			try {
				FileInputStream fis = new FileInputStream(sbLabelPath);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);

				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				isr.close();
				fis.close();
			} catch (IOException err) {
				err.printStackTrace();
			}
			
			// If you have found the index for the current label...
			if(sb.toString().equals(beforeText)) {
				try {
					FileWriter fw = new FileWriter(sbLabelPath);
					BufferedWriter writer = new BufferedWriter(fw);
					writer.write(afterText);
					writer.flush(); writer.close(); fw.close();
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
	}
	
	// Returns the date of the post to String.
	public static String getPostDayToString(String filePath) {
		int month = Integer.valueOf(filePath.substring(8,10));
		int day = Integer.valueOf(filePath.substring(10, 12));
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		if (month >= 1 && month <= 12) {
			return months[month - 1] + " " + day;
		} else {
			return "";
		}
	}
}