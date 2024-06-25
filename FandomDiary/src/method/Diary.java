package method;
import java.io.*;
import java.util.Vector;
public class Diary {
	public static void writeDiary(String fileNameFormatted, String userInput) {
		try {
			String filePath = "diaries/" + fileNameFormatted + ".txt";
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter writer = new BufferedWriter(fw);
			if(userInput == null) {
				writer.write("");
			} else {
				writer.write(userInput);
			}
			writer.flush(); writer.close(); fw.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	public static void writeImage(String fileNameFormatted, String srcPath) {
		if (srcPath != null) { //if image file is selected
			//get file extension
			int dotIndex = srcPath.lastIndexOf(".");
			String srcExtension = srcPath.substring(dotIndex);
			//set destPath
			String destPath = "images/" + fileNameFormatted + srcExtension;
			//copy Image
			Diary.copyImage(srcPath, destPath);
		} else { //if image file is not selected
			String destPath = "images/" + fileNameFormatted + ".jpg";
			Diary.copyImage("public/default_image.jpg", destPath);
		}
	}
	public static void copyImage(String srcPath, String destPath) {
		try {
			FileInputStream fis = new FileInputStream(srcPath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            FileOutputStream fos = new FileOutputStream(destPath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            
            byte[] buffer = new byte[1024 * 4]; //4KB buffer
            int c;
        
            while ((c = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, c);
            }
            
            bos.flush(); bos.close(); fos.close(); bis.close(); fis.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	public static void editText(Vector<String> diariesPath, String editedText, int currentPostIndex) {
		try {
			String filePath = diariesPath.get(currentPostIndex);
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(editedText);
			writer.flush(); writer.close(); fw.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	public static String getText(Vector<String> diariesPath, int currentPostIndex) {
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(diariesPath.get(currentPostIndex));
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
		return sb.toString();
	}
//	public static void editImage(Vector<String> imagesPath, String currentImagePath) {
//		if (srcPath != null) { //if image file is selected
//			//get file extension
//			int dotIndex = srcPath.lastIndexOf(".");
//			String srcExtension = srcPath.substring(dotIndex);
//			//set destPath
//			String destPath = "images/" + fileNameFormatted + srcExtension;
//			//copy Image
//			Diary.copyImage(srcPath, destPath);
//		} else { //if image file is not selected
//			String destPath = "images/" + fileNameFormatted + ".jpg";
//			Diary.copyImage("public/default_image.jpg", destPath);
//		}
//	}
}
