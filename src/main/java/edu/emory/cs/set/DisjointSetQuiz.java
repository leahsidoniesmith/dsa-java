package edu.emory.cs.set;

/** @author Jinho D. Choi */
public class DisjointSetQuiz {
    static public void main(String[] args) {
        DisjointSet ds = new DisjointSet(5);
        System.out.println(ds.toString());
        ds.union(2, 3);
        System.out.println(ds.toString());
        ds.union(4, 3);
        System.out.println(ds.toString());
        ds.union(0, 1);
        System.out.println(ds.toString());
        ds.union(1, 3);
        System.out.println(ds.toString());
    }
}