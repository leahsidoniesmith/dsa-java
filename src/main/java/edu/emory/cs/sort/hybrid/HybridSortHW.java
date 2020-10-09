package edu.emory.cs.sort.hybrid;
import edu.emory.cs.utils.Utils;
import edu.emory.cs.sort.AbstractSort;
import edu.emory.cs.sort.comparison.InsertionSort;
import edu.emory.cs.sort.comparison.ShellSortKnuth;
import edu.emory.cs.sort.divide_conquer.IntroSort;
import java.lang.reflect.Array;

public class HybridSortHW<T extends Comparable<T>> implements HybridSort<T> {
    private final AbstractSort<T> IntroSort;
    private final AbstractSort<T> InsertionSort;
    private final AbstractSort<T> ShellSortKnuth;
    private T[][] tempMatrix;

    public HybridSortHW() {
        IntroSort = new IntroSort<T>(new ShellSortKnuth<>());
        InsertionSort = new InsertionSort<T>();
        ShellSortKnuth = new ShellSortKnuth<T>();
    }

    @Override
    public T[] sort(T[][] input) {
        int inc = 0;
        int dec = 0;
        int step = (int) Utils.log2(input[0].length);

        //find property of input array
        for (int i = 0; i < input.length; i++) { //for each row
            for (int j = 0; j < input[i].length - 1; j += step) { //checks column entries (i.e. elements in the array of that row)
                if (input[i][j].compareTo(input[i][j + 1]) < 0) {
                    inc++;
                } else dec++;
            }

            //assign sorting methods based on inc and dec
            if (inc / 4 > dec && dec != 0) { //mostly sorted ascending case (no need to re-sort the already sorted array)
                InsertionSort.sort(input[i], 0, input[i].length);
            } else if (dec / 4 > inc || inc == 0) { //totally sorted or mostly sorted descending case
                ShellSortKnuth.sort(input[i], 0, input[i].length);
            } else { //randomly sorted case
                IntroSort.sort(input[i], 0, input[i].length);
            }

            //reset counters for next subarray/row
            inc = 0;
            dec = 0;

        }

        //merging the sorted arrays
        int size = input.length * input[0].length;
        T[] result = (T[]) Array.newInstance(input[0][0].getClass(), size);
        int[] size2 = {input.length, input[0].length};
        T[][] output = (T[][]) Array.newInstance(input[0][0].getClass(),size2);
        output = merge(input);

        //copy to result array
        for (int i = 0; i < output[0].length; i++) {
            result[i] = output[0][i];
        }

        return result;
    }

    public T[][] merge(T[][] input) {
        if (input.length == 1) { //if we have merged all rows to just one row, then we are done
            return tempMatrix;
        }

        if(input.length % 2 != 0){ //odd case
            T[][] tempMatrix = (T[][]) new Comparable[(input.length / 2) + 1][]; //updates to hold merged rows
            int row = 0;

            for (int i = 0; i < input.length - 1; i += 2) { //merge every 2 rows except for the last row
                T[] temp1 = (T[]) new Comparable[input[i].length];
                T[] temp2 = (T[]) new Comparable[input[i + 1].length]; //instantiate 2 temporary arrays that hold each array
                System.arraycopy(input[i], 0, temp1, 0, temp1.length);
                System.arraycopy(input[i + 1], 0, temp2, 0, temp2.length); //copy rows to temp arrays

                T[] mergeTemp = (T[]) new Comparable[temp1.length + temp2.length];
                tempMatrix[row] = (T[]) new Comparable[temp1.length + temp2.length]; //assign a length to the current row

                int t1 = 0;
                int t2 = 0; //counters for merge

                //perform merging (classic mergesort implementation, inspired by Professor's original implementation)
                for (int j = 0; j < temp1.length + temp2.length; j++) {
                    if (t1 >= temp1.length) { //no key in temp1
                        mergeTemp[j] = temp2[t2++];
                    } else if (t2 >= temp2.length) { //no key in temp2
                        mergeTemp[j] = temp1[t1++];
                    } else if (temp1[t1].compareTo(temp2[t2]) < 0) { //temp1 < temp2
                        mergeTemp[j] = temp1[t1++];
                    } else {
                        mergeTemp[j] = temp2[t2++]; //temp2 < temp1
                    }
                    tempMatrix[row][j] = mergeTemp[j];
                }
                row++; //increment row so next two arrays will merge onto the next row
            }

            //add array that did not have a match (to merge with ) to the end
            T[] odd = (T[]) new Comparable[input[input.length - 1].length];
            tempMatrix[row] = (T[]) new Comparable[odd.length];
            for(int k = 0; k < odd.length; k++){
                tempMatrix[row][k] = input[input.length - 1][k];
            }

            if(tempMatrix.length == 1){
                return tempMatrix;
            } else {
                tempMatrix = merge(tempMatrix);
                return tempMatrix;
            }

        } else { //even case
            T[][] tempMatrix = (T[][]) new Comparable[input.length / 2][]; //updates to hold merged rows
            int row = 0;
            for (int i = 0; i < input.length; i += 2) { //merge every 2 rows
                T[] temp1 = (T[]) new Comparable[input[i].length];
                T[] temp2 = (T[]) new Comparable[input[i + 1].length]; //instantiate 2 temporary arrays that hold each array
                System.arraycopy(input[i], 0, temp1, 0, temp1.length);
                System.arraycopy(input[i + 1], 0, temp2, 0, temp2.length); //copy rows to temp arrays

                T[] mergeTemp = (T[]) new Comparable[temp1.length + temp2.length];
                tempMatrix[row] = (T[]) new Comparable[temp1.length + temp2.length]; //assign a length to the current row

                int t1 = 0;
                int t2 = 0; //counters for merge

                //perform merging
                for (int j = 0; j < temp1.length + temp2.length; j++) {
                    if (t1 >= temp1.length) { //no key in temp1
                        mergeTemp[j] = temp2[t2++];
                    } else if (t2 >= temp2.length) { //no key in temp2
                        mergeTemp[j] = temp1[t1++];
                    } else if (temp1[t1].compareTo(temp2[t2]) < 0) { //temp1 < temp2
                        mergeTemp[j] = temp1[t1++];
                    } else {
                        mergeTemp[j] = temp2[t2++]; //temp2 < temp1
                    }
                    tempMatrix[row][j] = mergeTemp[j];
                }
                row++; //increment row so next two arrays will merge onto the next row
            }

            if(tempMatrix.length == 1){
                return tempMatrix;
            } else {
                tempMatrix = merge(tempMatrix);
                return tempMatrix;
            }
        }
    }
}


