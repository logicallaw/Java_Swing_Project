package method;

import java.util.Vector;
import java.util.Collections;
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
			if (subFile.getName().equals(".DS_Store") || diariesPath.contains(subFile.getName())) { // If your computer is MacOS, we must distinguish ".DS_Store"
				continue;
			}
			diariesPath.add(subFile.getName());

		}
		// https://reakwon.tistory.com/153
		Collections.sort(diariesPath);

		dir = new File("images");
		subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if (subFile.getName().equals(".DS_Store") || imagesPath.contains(subFile.getName())) {
				continue;
			}
			imagesPath.add(subFile.getName());
		}
		Collections.sort(imagesPath);
	}

	public static void addTexts(Vector<String> diariesPath, Vector<JLabel> diariesJLabel, int postIndex) {
//		for (String filePath : diariesPath) {
		for(; postIndex < diariesPath.size(); postIndex++) {
			StringBuffer sb = new StringBuffer();
			try {
				FileInputStream fis = new FileInputStream("diaries/" + diariesPath.get(postIndex));
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
			int sbIndex = (sb.length() > 20) ? 20 : sb.length();
			JLabel diary = new JLabel(sb.substring(0, sbIndex));
			diariesJLabel.add(diary);
		}
	}

	public static void addImages(Vector<String> imagesPath, Vector<ImageIcon> imagesIcons, int postIndex) {
//		for (String filePath : imagesPath) {
		for(; postIndex < imagesPath.size(); postIndex++) {
			File f = new File("images/" + imagesPath.get(postIndex));
			ImageIcon originImage = new ImageIcon("images/" + f.getName());
			// https://wildeveloperetrain.tistory.com/289
			Image scaledImage = originImage.getImage().getScaledInstance(400 / 4, 500 / 4, Image.SCALE_SMOOTH);
			imagesIcons.add(new ImageIcon(scaledImage));
		}
	}

	public static int updatePosts(Vector<JLabel> diariesJLabel, Vector<ImageIcon> imagesIcons, JPanel postPanel,
			int postIndex) {
		for (; postIndex < diariesJLabel.size(); postIndex++) {
			JPanel post = new JPanel(new BorderLayout(10, 10));
			post.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			JLabel postImage = new JLabel(imagesIcons.get(postIndex));
			JLabel postText = diariesJLabel.get(postIndex);
			postText.setFont(new Font("Roboto", Font.PLAIN, 15));

			post.add(postImage, BorderLayout.WEST);
			post.add(postText, BorderLayout.CENTER);

			postPanel.add(post, 0);
		}
		return postIndex;
	}

}
