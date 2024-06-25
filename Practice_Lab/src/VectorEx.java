import java.util.Vector;
public class VectorEx {
	public static void main(String[] args) {
		Vector<String> v1 = new Vector<>();
		v1.add("HI");
		v1.add("Bye");
		v1.add("JUNHO");
		v1.remove(1);
		for(String e : v1) {
			System.out.println(e);
		}
	}
}
