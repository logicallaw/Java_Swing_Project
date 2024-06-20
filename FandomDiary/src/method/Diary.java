package method;
import java.io.*;
public class Diary {
	public static void writeDiary(String fileNameFormatted, String userInput) {
		try {
			String filePath = "diaries/" + fileNameFormatted + ".txt";
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(userInput);
			writer.flush();
			writer.close();
			fw.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	public static void copyImage(String srcPath, String destPath) {
		try {
			FileInputStream fis = new FileInputStream(srcPath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            FileOutputStream fos = new FileOutputStream(destPath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            
            byte[] buffer = new byte[1024];
            int c;
        
            while ((c = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, c);
            }
            
            bos.flush();
            bos.close(); fos.close(); bis.close(); fis.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
}
