import java.util.Collections;
import java.util.Vector;

public class VectorSorting {
    public static void main(String[] args) {
        // Vector 생성 및 데이터 추가
        Vector<String> vector = new Vector<>();
        vector.add("Banana");
        vector.add("Apple");
        vector.add("Grape");
        vector.add("Cherry");
        vector.add("Orange");

        System.out.println("정렬 전: " + vector);

        // Vector를 사전순으로 정렬
        Collections.sort(vector);

        System.out.println("정렬 후: " + vector);
    }
}