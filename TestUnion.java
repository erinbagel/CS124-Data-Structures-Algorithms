// Erin Walter
// Running Time: A list m, and a list n, and union of lists which is ϴ(n).   
import java.util.*;

public class TestUnion {

	public static <AnyType extends Comparable<AnyType>> List<AnyType> union(List<AnyType> list1, List<AnyType> list2) {

		List<AnyType> unionList = new LinkedList<AnyType>();
		boolean unionDone = false;
		if (list1.isEmpty()) {
			return list2;
		}
		if (list2.isEmpty()) {
			return list1;
		} else if (list1.isEmpty() && list2.isEmpty() || list1.equals(list2)) {
			return list1;
		}

		Iterator<AnyType> itr1 = list1.iterator();
		Iterator<AnyType> itr2 = list2.iterator();
		AnyType elem1 = itr1.next();
		AnyType elem2 = itr2.next();

		while (!unionDone) {
			if (elem1.equals(elem2)) {
				unionList.add(elem1);
				if (!itr1.hasNext() || !itr2.hasNext()) {
					unionDone = true;
				} else {
					elem1 = itr1.next();
					elem2 = itr2.next();
				}
			} else if (elem1.compareTo(elem2) < 0) {
				unionList.add(elem1);
				if (itr1.hasNext()) {
					elem1 = itr1.next();
				} else {
					unionDone = true;
					unionList.add(elem2);
				}
			} else {
				unionList.add(elem2);
				if (itr2.hasNext()) {
					elem2 = itr2.next();
				} else {
					unionDone = true;
					unionList.add(elem1);
				}
			}
		}
		while(itr1.hasNext()){
			unionList.add(itr1.next());
		}
		while(itr2.hasNext()){
			unionList.add(itr2.next());
		}
		return unionList;
	}

	public static void main(String[] args) {
		LinkedList<Integer> list1 = new LinkedList<>();
		for (int i = 0; i < 20; i++)
			list1.add(i);
		System.out.println("List 1: " + list1);
		LinkedList<Integer> list2 = new LinkedList<>();
		for (int i = -6; i < list1.size() + 10; i = i + 3)
			list2.add(i);
		System.out.println("List 2: " + list2);
		List<Integer> resultList = union(list1, list2);
		System.out.println("Union is: " + resultList);
		
		
		LinkedList<Integer> emptylist = new LinkedList<>();
		List<Integer> resultList3 = union(list2, emptylist);
		System.out.println("Union of List 2 and Empty List is: " + resultList3);
		
		LinkedList<Integer> emptylist1 = new LinkedList<>();
		System.out.println("Empty list 1: " + emptylist);
		System.out.println("Empty list 2: " + emptylist1);
		List<Integer> resultsListemptys = union(emptylist, emptylist1);
		System.out.println("Union emptys is: " + resultsListemptys);
		
		LinkedList<String> list3 = new LinkedList<>();
		list3.add("a");
		list3.add("b");
		System.out.println("String List 1: " + list3);
		LinkedList<String> list4 = new LinkedList<>();
		list4.add("a");
		list4.add("b");
		list4.add("c");
		System.out.println("String List 2: " + list4);
		List<String>resultList2 = union(list3, list4);
		System.out.println("Union is: " + resultList2);
	}
}

//OUTPUT:
//List 1: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
//List 2: [-6, -3, 0, 3, 6, 9, 12, 15, 18, 21, 24, 27]
//Union is: [-6, -3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 24, 27]
//Union of List 2 and Empty List is: [-6, -3, 0, 3, 6, 9, 12, 15, 18, 21, 24, 27]
//Empty list 1: []
//Empty list 2: []
//Union emptys is: []
//String List 1: [a, b]
//String List 2: [a, b, c]
//Union is: [a, b, c]