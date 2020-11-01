package edu.emory.cs.trie.autocomplete;

import org.junit.Test;

import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AutocompleteTest {
    static class Eval {
        int correct = 0;
        int total = 0;
    }

    @Test
    public void test() {
        final String dict_file = "src/main/resources/dict.txt";
        final int max = 5;

        Autocomplete<?> ac = new AutocompleteHW(dict_file, max);
        Eval eval = new Eval();
        testAutocomplete(ac, eval);
    }

    private void testAutocomplete(Autocomplete<?> ac, Eval eval) {
        String prefix;
        List<String> expected;

        // prefix is empty
        prefix = "";
        expected = List.of("a", "b", "c", "d", "e");
        testGetCandidates(ac, eval, prefix, expected);

        // prefix is spaces
        prefix = "   ";
        expected = List.of("a", "b", "c", "d", "e");
        testGetCandidates(ac, eval, prefix, expected);

        // prefix is null
        prefix = null;
        expected = List.of();
        testGetCandidates(ac, eval, prefix, expected);

        // prefix not found
        prefix = "zzzzzzzzz";
        expected = List.of();
        testGetCandidates(ac, eval, prefix, expected);

        // prefix length 1
        prefix = "a";
        expected = List.of("a", "aa", "ab", "ac", "ad");
        testGetCandidates(ac, eval, prefix, expected);

        // prefix that is a word
        prefix = "abas";
        expected = List.of("abas", "abase", "abash", "abask", "abased");
        testGetCandidates(ac, eval, prefix, expected);

        // results < max
        prefix = "zun";
        expected = List.of("zuni", "zunis", "zunian", "zunyite");
        testGetCandidates(ac, eval, prefix, expected);

        // pick a word that is not in the dictionary
        ac.pickCandidate("zun", "zuniper");
        prefix = "zun";
        expected = List.of("zuniper", "zuni", "zunis", "zunian", "zunyite");
        testGetCandidates(ac, eval, prefix, expected);

        // prefix or candidate is null in pick candidate
        ac.pickCandidate(null, "boo");
        ac.pickCandidate("boo", null);

        // prefix and candidate are not in the dictionary
        ac.pickCandidate("leahrocks", "leahrocksyoursocks");
        prefix = "leahrocks";
        expected = List.of("leahrocksyoursocks");
        testGetCandidates(ac, eval, prefix, expected);

        // prefix is not in the dictionary
        ac.pickCandidate("leahisawesome", "love");
        prefix = "leahisawesome";
        expected = List.of("love");
        testGetCandidates(ac, eval, prefix, expected);

        // candidate is not in the dictionary
        ac.pickCandidate("love", "NinaDobrev");
        prefix = "love";
        expected = List.of("NinaDobrev", "love", "loved", "lovee", "lover");
        testGetCandidates(ac, eval, prefix, expected);

        // pickCandidate multiple times on the same prefix
        ac.pickCandidate("ba", "baboom");
        ac.pickCandidate("ba", "balloon");
        ac.pickCandidate("ba", "baby");
        prefix = "ba";
        expected = List.of("baby", "balloon", "baboom", "ba", "baa");
        testGetCandidates(ac, eval, prefix, expected);


        System.out.printf("Score: %d/%d\n", eval.correct, eval.total);
    }

    private void testGetCandidates(Autocomplete<?> ac, Eval eval, String prefix, List<String> expected) {
        String log = String.format("%2d: ", eval.total);
        eval.total++;

        try {
            List<String> candidates = ac.getCandidates(prefix);

            if (expected.equals(candidates)) {
                eval.correct++;
                log += "PASS";
            }
            else {
                log += String.format("FAIL -> expected = %s, returned = %s", expected, candidates);
            }
        }
        catch (Exception e) {
          // System.out.println(e);
           log += "ERROR";
        }

        System.out.println(log);
    }
}