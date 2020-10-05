package edu.emory.cs.sort.distribution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class RadixSortQuiz extends RadixSort {
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        msd(array, beginIndex, endIndex , maxBit);
    }

    //using key indexed sorting (also known as counting sort) to implement msd
    protected void msd(Integer[] a, int lo, int hi, int bit){
        if(hi <= lo + 1) return;
        if(bit < 0) return;

        int[] count = new int[12];

        for(int i = lo; i < hi; i++){
            count[digitatBit(bit, a[i]) + 2]++; //count frequencies of whatever bit we are on to get size of bins
        }

        for(int i = 1; i < count.length; i++){  //compute cumulates of count[]
            count[i] += count[i-1];
        }

        int temp[] = new int[hi - lo]; //create temp array

        for(int i = lo; i < hi; i++){ //move elements into order by their MSD in the temp array
            temp[count[digitatBit(bit, a[i]) + 1]] = a[i];
            count[digitatBit(bit, a[i]) + 1]++;
        }

        for(int i = 0; i < temp.length; i++){ //copy temp array to actual array
            a[lo + i] = temp[i];
        }

        for(int i = 0; i < 10; i++) {
                msd(a, lo + count[i], lo + count[i + 1], bit - 1); //recursion on each piece of array
        }

    }

    //helper function to return digit at bit
    protected int digitatBit(int bit, int n){ //this is from the left - so for the number 356, digitatBit(3, 356) returns 3;  digitatBit(2, 356) returns 5
        return (int) (n / Math.pow(10, bit - 1)) % 10;
    }
}

