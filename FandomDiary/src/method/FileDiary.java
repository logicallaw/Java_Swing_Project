package method;

import java.util.Vector;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileDiary {
	public static void loadImages(Vector<ImageIcon> images_dir_Icons) {
		File dir = new File("images");
		File[] subFiles = dir.listFiles();
		if (subFiles.length != 0) {
			int i = (subFiles[0].getName().equals(".DS_Store") == true) ? 1 : 0;
			for (; i < subFiles.length; i++) {
				File f = subFiles[i];
				ImageIcon originImage = new ImageIcon("images/" + f.getName());
				// https://wildeveloperetrain.tistory.com/289
				Image scaledImage = originImage.getImage().getScaledInstance(400 / 4, 500 / 4, Image.SCALE_SMOOTH);
				images_dir_Icons.add(new ImageIcon(scaledImage));
			}
		}
	}

	public static void loadTexts(Vector<JLabel> diaries_dir_JLabel) {
		File dir = new File("diaries");
		File[] subFiles = dir.listFiles();
		for (int i = 0; i < subFiles.length; i++) {
			if (subFiles[i].getName().equals(".DS_Store")) {
				continue;
			}
			File f = subFiles[i];
			String filePath = f.getPath();
			StringBuffer sb = new StringBuffer();
			try {
				FileInputStream fis = new FileInputStream(filePath);
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
			JLabel diary = new JLabel(sb.substring(0));
			diaries_dir_JLabel.add(diary);
		}

	}
}
