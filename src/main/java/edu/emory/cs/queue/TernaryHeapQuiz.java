package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TernaryHeapQuiz<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private final List<T> keys;

    public TernaryHeapQuiz() {
        this(Comparator.naturalOrder());
    }

    public TernaryHeapQuiz(Comparator<T> priority) {
        super(priority);
        keys = new ArrayList<>();
        keys.add(null); //add null for easier calculations
    }

    @Override
    public int size() {
        return keys.size() - 1;
    }

    /**
     * @param i1 the index of the first key in {@link #keys}.
     * @param i2 the index of the second key in {@link #keys}.
     * @return a negative integer, zero, or a positive integer as the first key is
     * less than, equal to, or greater than the second key.
     */
    private int compare(int i1, int i2) {
        return priority.compare(keys.get(i1), keys.get(i2));
    }


    @Override
    public void add(T key) {
        keys.add(key);
        swimT(size());
    }

    /**
     * swims new element up to correct position based on its priority
     * @param k the size of the heap and the index of the last key
     */
    private void swimT(int k){
      while(k > 1){
          int temp = k;
          if(k % 3 == 2){ //case for left-most element
              temp = k + 1;
          }
          if(compare(temp/3, k) < 0) {
              Collections.swap(keys, temp / 3, k);
              k = temp / 3;
          } else k = -1;
      }
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        Collections.swap(keys, 1, size());
        T max = keys.remove(size());
        sinkT();
        return max;
    }

    /**
     * sinks the root key until the list becomes a heap
     */
    private void sinkT() {
        for (int k = 1, i = 2; i <= size(); k = i, i = (i*3) - 1) {
            int maxchild = i;
            if (i < size()) {  //check middle child for higher priority
                if (compare(i, i + 1) < 0) maxchild = i + 1;
            }
            if(i < size() - 1){ //check rightmost child for higher priority
                if (compare(maxchild, i + 2) < 0) maxchild = i + 2;
            }
            if (compare(k, maxchild) >= 0) break;
            i = maxchild;
            Collections.swap(keys, k, i);
        }
    }

}
