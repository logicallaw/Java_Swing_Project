import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class TimeExample {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		LocalTime now1 = LocalTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
		String formatedNow = now1.format(formatter);
		
		System.out.println(formatedNow);
		//https://dev-coco.tistory.com/31
		
	}
}
