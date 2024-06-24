import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        // LinkedList 생성
        LinkedList<String> linkedList = new LinkedList<>();

        // 요소 추가
        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Cherry");

        // 맨 앞에 요소 추가
        linkedList.addFirst("Mango");
        
        // 맨 뒤에 요소 추가
        linkedList.addLast("Orange");
        for(String l : linkedList) {
        	System.out.println(l);
        }
        System.out.println();
        linkedList.remove("Cherry");
        for(String l : linkedList) {
        	System.out.println(l);
        }
        
        
//
//        // 요소 접근
//        System.out.println("First Element: " + linkedList.getFirst());
//        System.out.println("Last Element: " + linkedList.getLast());
//        
//        // 특정 인덱스 요소 접근
//        System.out.println("Element at index 2: " + linkedList.get(2));
//        
//        // 요소 삭제
//        linkedList.removeFirst();  // 맨 앞 요소 삭제
//        linkedList.removeLast();   // 맨 뒤 요소 삭제
//        linkedList.remove(1);      // 인덱스 1의 요소 삭제
//
//        // 리스트 크기
//        System.out.println("List Size: " + linkedList.size());
//        
//        // 리스트 출력
//        System.out.println("LinkedList: " + linkedList);
    }
}