package edu.emory.cs.trie.autocomplete;
import edu.emory.cs.trie.TrieNode;

import java.util.*;

public class AutocompleteHW extends Autocomplete<List<String>> {
    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
    }

    @Override
    public List<String> getCandidates(String prefix) {
        if (getMax() == 0 || prefix == null) {
            return new ArrayList<String>();
        }

        // get to the node representing the last character of the prefix
        prefix = prefix.trim();
        TrieNode<List<String>> start = this.find(prefix);
        if (start == null) {
            return new ArrayList<String>();
        }

        // ensure candidates list exists in the node
        List<String> candidates;
        if (start.hasValue()) {
            candidates = start.getValue();
        } else {
            candidates = new ArrayList<String>();
            start.setValue(candidates);
        }

        if (candidates.size() == getMax())
            return candidates;

        traverse(start);
        return candidates;
    }

    // breadth-first search the trie from start until we have found getMax() results
    private void traverse(TrieNode<List<String>> start) {
        List<String> candidates = start.getValue();

        Queue<TrieNode<List<String>>> bfsQueue = new LinkedList<>();
        bfsQueue.add(start);
        while (!bfsQueue.isEmpty()) {
            TrieNode<List<String>> next = bfsQueue.remove();
            if (next.isEndState()) {
                String word = getWord(next);
                if (!candidates.contains(word)) {
                    candidates.add(word);
                    if (candidates.size() == getMax())
                        return;
                }
            }

            // add the sorted child nodes to the bfs queue
            Map<Character, TrieNode<List<String>>> childMap = next.getChildrenMap();
            List<Character> keys = new ArrayList<>(childMap.keySet());
            Collections.sort(keys);
            for (Character key : keys) {
                TrieNode<List<String>> childNode = childMap.get(key);
                bfsQueue.add(childNode);
            }
        }
    }

    private String getWord(TrieNode<List<String>> node) {
        String result = "";
        while (node.getParent() != null) {
            result = node.getKey() + result;
            node = node.getParent();
        }
        return result;
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        if (prefix == null || candidate == null)
            return;

        prefix = prefix.trim();
        TrieNode<List<String>> node = this.find(prefix);

        // if prefix does not exist, add it to the trie (but not as a word)
        if (node == null) {
            this.put(prefix, null);
            TrieNode<List<String>> prefixNode = this.find(prefix);
            prefixNode.setEndState(false);
            node = prefixNode;
        }
        
        if(find(candidate) == null){
            this.put(candidate, null);
        }

        // ensure candidates list exists in the node
        List<String> candidates;
        if (node.hasValue()) {
            candidates = node.getValue();
        } else {
            candidates = new ArrayList<String>();
            node.setValue(candidates);
        }

        candidates.remove(candidate);
        candidates.add(0, candidate);

    }
}
