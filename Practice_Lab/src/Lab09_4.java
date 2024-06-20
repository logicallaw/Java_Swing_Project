import java.io.*;

public class Lab09_4 {
    public static void main(String[] args) {
        // 원본 파일 경로
        String sourcePath = "images/iu2.jpg";
        // 대상 파일 경로
        String destinationPath = "images/iu2_copy.jpg";

        try {
            // 입력 스트림 생성
            FileInputStream fis = new FileInputStream(sourcePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            // 출력 스트림 생성
            FileOutputStream fos = new FileOutputStream(destinationPath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            // 버퍼를 사용하여 파일을 복사
            byte[] buffer = new byte[1024]; // 1KB 버퍼
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            // 스트림 닫기
            bis.close();
            bos.close();

            System.out.println("이미지 복사가 완료되었습니다: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}