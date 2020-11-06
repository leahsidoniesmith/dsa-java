package edu.emory.cs.set;

import java.util.Arrays;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class DisjointSet {
    private final int[] subsets;

    public DisjointSet(int size) {
        subsets = new int[size];
        Arrays.fill(subsets, -1); //-1 is the size of the subset
        //-1 implies 1 ID, -2 implies 2 IDS, etc.
        // a positive number here points to another set
    }

    //copy constructor
    public DisjointSet(DisjointSet set) {
        subsets = Arrays.copyOf(set.subsets, set.subsets.length);
    }

    /**
     * @param key the specific key to search.
     * @return the ID of the subset where the specific key belongs to.
     */
    public int find(int key) {
        return (subsets[key] < 0) ? key : (subsets[key] = find(subsets[key])); //efficient approach
    }



    /*//baseline approach
    public int find(int id) {
        return (subsets[id] < 0) ? id : find(subsets[id]);
    }

     */

    /**
     * @param key1 the first key to be compared.
     * @param key2 the second key to be compared.
     * @return true if the two specific keys are in the same set; otherwise, false.
     */
    public boolean inSameSet(int key1, int key2) {
        return find(key1) == find(key2);
    }

    public int union(int key1, int key2) {
        int r1 = find(key1);
        int r2 = find(key2);
        if (r1 == r2) return r1; //returns the subset id where both key1 and key2 belongs to

        if (subsets[r1] < subsets[r2]) { // there are more keys in subsets[r1]
            subsets[r1] += subsets[r2]; // Merge the set containing key2 to the set containing key1
            subsets[r2] = r1; // subsets[r2] now points to r1
            return r1; //return the index of r1
        }
        else {
            subsets[r2] += subsets[r1]; //merge the set containing key 1 to the set containing key 2
            subsets[r1] = r2; //subsets[r1] now points to r2
            return r2;
        }
    }

    public String toString() {
        return Arrays.toString(subsets);
    }
}

