
//Erin Walter- Assignment 7 due October 12 2016
//Running time analysis: θ(n)
//All tests passed
import java.util.*;

public class TestPrintLots {

	private static <AnyType> void printLots(List<AnyType> l, List<Integer> p) {
		if (l.isEmpty() || p.isEmpty()) {
			System.out.println("List is empty.");
			return;
		}
		Iterator<AnyType> itrl = l.iterator();
		AnyType elemL = itrl.next();
		int currentElem = 0;

		for (Integer pElem : p) {
			if (pElem <= l.size() && pElem >= 0) {
				while (currentElem < pElem) {
					if (itrl.hasNext()) {
						elemL = itrl.next();
						currentElem++;
						// System.out.println(elemL + "");
					}

				}
				System.out.print(elemL + " \n");
			} else if (pElem > l.size() && pElem < 0) {
				return;
			}
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		LinkedList<Integer> list1 = new LinkedList<>();
		for (int i = 0; i < 20; i++)
			list1.add(i * 10);
		System.out.println("List of values: " + list1);
		LinkedList<Integer> list2 = new LinkedList<>();
		for (int i = -6; i < list1.size() + 10; i = i + 3)
			list2.add(i);
		System.out.println("List of positions: " + list2);
		System.out.println("Print");
		printLots(list1, list2);

		LinkedList<String> list3 = new LinkedList<>();
		list3.add("a");
		list3.add("b");
		list3.add("c");
		System.out.println("List of values: " + list3);
		LinkedList<Integer> list4 = new LinkedList<>();
		for (int i = 0; i < list3.size(); i++)
			list4.add(i);
		System.out.println("List of positions: " + list4);
		System.out.println("Print");
		printLots(list3, list4);

		LinkedList<Integer> list5 = new LinkedList<>();
		LinkedList<Integer> list6 = new LinkedList<>();
		System.out.println("List of values in empty: " + list5);
		System.out.println("List of positions in list 6: " + list6);
		printLots(list5, list6);
	}
}

// OUTPUT:
// List of values: [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
// 140, 150, 160, 170, 180, 190]
// List of positions: [-6, -3, 0, 3, 6, 9, 12, 15, 18, 21, 24, 27]
// Print
// 0
// 30
// 60
// 90
// 120
// 150
// 180

// List of values: [a, b, c]
// List of positions: [0, 1, 2]
// Print
// a
// b
// c

// List of values in empty: []
// List of positions in list 6: []
// List is empty.
