package method;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DiaryWrite {
	public static void writeDiary(String fileNameFormatted, String userInput) {
		try {
			String fileName = "diaries/" + fileNameFormatted + ".txt";
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(userInput);
			writer.flush();
			writer.close();
			fw.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
}
