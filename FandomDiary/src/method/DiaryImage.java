package method;
import java.io.*;

public class DiaryImage {
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
