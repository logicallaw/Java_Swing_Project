package method;

import java.util.Vector;
import java.util.Collections;
import javax.swing.ImageIcon;
import java.awt.*;
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
			if (subFile.getName().equals(".DS_Store")) {
				continue;
			}
			diariesPath.add(subFile.getName());
		}
	    //https://reakwon.tistory.com/153
		Collections.sort(diariesPath);
		
		dir = new File("images");
		subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if (subFile.getName().equals(".DS_Store")) {
				continue;
			}
			imagesPath.add(subFile.getName());
		}
	    //https://reakwon.tistory.com/153
		Collections.sort(imagesPath);
	}
	
	public static void loadTexts(Vector<String> diariesPath, Vector<JLabel> diariesJLabel) {
		for (String filePath : diariesPath) {
			StringBuffer sb = new StringBuffer();
			try {
				FileInputStream fis = new FileInputStream("diaries/" + filePath);
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
			int sbIndex = (sb.length() > 20) ? 100 : sb.length();
			JLabel diary = new JLabel(sb.substring(0, sbIndex));
			diariesJLabel.add(diary);
		}
	}

	public static void loadImages(Vector<String> filePaths, Vector<ImageIcon> imagesIcons) {
		for (String filePath : filePaths) {
			File f = new File("images/" + filePath);
			ImageIcon originImage = new ImageIcon("images/" + f.getName());
			// https://wildeveloperetrain.tistory.com/289
			Image scaledImage = originImage.getImage().getScaledInstance(400 / 4, 500 / 4, Image.SCALE_SMOOTH);
			imagesIcons.add(new ImageIcon(scaledImage));
		}
	}
	
}
