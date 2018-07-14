import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Project2 {
  public static void main(String args[]) {
    System.out.println("Please select how to test the program:");
    System.out.println("(1) 20 sets of 100 randomly generated integers");
    System.out.println("(2) Fixed integer values 1-100");
    System.out.print("Enter choice: ");
    Scanner kb = new Scanner(System.in);
    int input = kb.nextInt();
    System.out.println();
    if (input == 1) {
      randomInt();
    } 
	else if (input == 2) {
      fixedInt();
    }
	else {
	  System.out.println("Improper choice");
	}
  }

  static void randomInt() {
    int[] insertionCount = new int[20];
    int[] optimalCount = new int[20];
	Random r = new Random();
    for (int i = 0; i < 20; i++) {
      int[] rands = new int[100];
      for (int j = 0; j < 100; j++) {
        rands[j] = r.nextInt(100)+1;
      }
      Heap insertions = new Heap();
      insertionCount[i] = insertions.insert(rands);
      Heap optimal = new Heap(rands);
      optimalCount[i] = optimal.heapify();
    }
    int iTotal = 0;
    int oTotal = 0;
    for (int n : insertionCount) iTotal += n;
    for (int n : optimalCount) oTotal += n;
    double iAvg = iTotal / insertionCount.length;
    double oAvg = oTotal / optimalCount.length;
    System.out.println("Average swaps for series of insertions: " + iAvg);
    System.out.println("Average swaps for optimal method: " + oAvg);
  }

  static void fixedInt() {
    int[] inc = new int[100];
    for (int i = 1; i <= 100; i++) {
      inc[i - 1] = i;
    }

    Heap insertions = new Heap();
    System.out.println("Number of Swaps: ");
	System.out.print(insertions.insert(inc));
	System.out.println();
    System.out.println("Heap result built using series of insertions: ");
	insertions.printTen();
    System.out.println();
    for (int i = 0; i < 10; i++) {
      insertions.removal();
    }

    System.out.println("Heap after 10 removals: ");
	insertions.printTen();
    System.out.println();
    System.out.println();
    Heap optimal = new Heap(inc);
    System.out.println("Number of Swaps: ");
	System.out.print(optimal.heapify());
	System.out.println();
    System.out.println("Heap result built using optimal method: ");
	optimal.printTen();
    System.out.println();
    for (int i = 0; i < 10; i++) {
      optimal.removal();
    }
    System.out.println("Heap after 10 removals: ");
	optimal.printTen();
  }
}