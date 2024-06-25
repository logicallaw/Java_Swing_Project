package method;

import lib.ButtonFilledWithImage;

import java.util.Vector;
import java.util.Collections;
import javax.swing.*;
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
			diary.setEditable(false);
			diariesJTextArea.add(diary);
		}
	}

	public static void addImagesToVector(Vector<String> imagesPath, Vector<ButtonFilledWithImage> imagesBtns, int postIndex) {

		for(; postIndex < imagesPath.size(); postIndex++) {
			ButtonFilledWithImage temp = new ButtonFilledWithImage(imagesPath.get(postIndex), 100, 100);
			imagesBtns.add(temp);
		}
	}
	
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
}
