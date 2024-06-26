import java.util.Vector;

public class IndexOfFromIndexExample {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("Apple");
        vector.add("Banana");
        vector.add("Cherry");
        vector.add("Banana");
        vector.add("Date");
        for(String v : vector) {
        	System.out.println(v);
        }

        // 3번 인덱스 이후의 "Banana" 찾기
        int index = vector.indexOf("Banana", 4);
        System.out.println("The first occurrence of 'Banana' from index 3 is at index: " + index);

        // 5번 인덱스 이후의 "Banana" 찾기 - 요소가 없음
        int indexNotFound = vector.indexOf("Banana", 5);
        System.out.println("The index of 'Banana' from index 5 is: " + indexNotFound);
    }
}