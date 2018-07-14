import java.util.Arrays;

class Heap {
  private final int CAPACITY = 2;

  protected int size;
  protected int[] heap;

  public Heap() {
    size = 0;
    heap = new int[CAPACITY];
  }

  public Heap(int[] array) {
    size = array.length;
    heap = new int[array.length + 1];
    System.arraycopy(array, 0, heap, 1, array.length);
  }

  public int size() {
    return size;
  }

  public int[] resize() {
    return Arrays.copyOf(heap, heap.length + 1);
  }

  public boolean hasParent(int i) {
    return i > 1;
  }

  public int getLeft(int i) {
    return i * 2;
  }

  public int getRight(int i) {
    return i * 2 + 1;
  }

  public boolean hasLeftChild(int i) {
    return getLeft(i) <= size;
  }

  public boolean hasRightChild(int i) {
    return getRight(i) <= size;
  }

  public int getParentIndex(int i) {
    return i / 2;
  }

  public int getParent(int i) {
    return heap[getParentIndex(i)];
  }

  public int insert(int[] data) {
    int count = 0;
    for(int i = 0; i < data.length; i++) {
      if (size >= heap.length - 1) {
        heap = this.resize();
      }

      size++;
      heap[size] = data[i];    
	  int temp = size;
	  while (temp > 1 && getParent(temp) < heap[temp]) { //keep swapping up until parent is no longer less the inserted
        swap(temp, getParentIndex(temp));
	    count++;
        temp = getParentIndex(temp);
      }
    }

    return count;
  }

  public int heapify() {
    int swapCount = 0;
	for (int temp = size() / 2; temp > 0; temp--) {
	  int i = temp;
	  while (i < size) {
	    if (hasLeftChild(i) == true && hasRightChild(i) == true) { //if both children exist
		  if (heap[getLeft(i)] > heap[getRight(i)]) { //if left is greater than right
	  	    if (heap[i] < heap[getLeft(i)]) { //swap if parent is less than the left children
			  swap(i, getLeft(i));
			  swapCount++;
			  i = getLeft(i);
		     }
			 else break;
		  } 
	      else { //right is greater than left, and parent is less than right
		    if (heap[i] < heap[getRight(i)]) {
			  swap(i, getRight(i));
			  swapCount++;
			  i = getRight(i);
		    }
			else break;
		  }
	    } 
	    else if (hasLeftChild(i) == true) { //same pattern, except for only left child
		  if (heap[i] < heap[getLeft(i)]) {
		    swap(i, getLeft(i));
		    swapCount++;
		    i = getLeft(i);
	      }
		  else break;
	    } 
	    else { //no children exist
		  break;
	    }
      }
	}
    return swapCount;
  }

  public void removal() {
    if (size == 0) return;
    if (size >= 1) {
      int p = heap.length - 1;
      heap[1] = heap[p];
      int[] newHeap = new int[heap.length - 1]; //create a new array and copy it over with minus 1 to array size
      System.arraycopy(heap, 1, newHeap, 1, newHeap.length - 1);
      heap = newHeap;
      size--;
      heapify();
    }
  }
  public void swap(int i1, int i2) { //utility to swap parent and child
    int temp = heap[i1];
    heap[i1] = heap[i2];
    heap[i2] = temp;
  }
  
  public void printTen() { //utility to print first 10 of the heap
	for (int i = 1; i <= 10; i++) {
	  if (i == 10) {
		System.out.print(heap[i]);
	  }
	  else
	    System.out.print(heap[i] + ", ");
	}
  }

}