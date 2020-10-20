/*
 * Copyright 2020 Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.emory.cs.trie;

import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class TrieQuiz extends Trie<Integer> {
    /**
     * PRE: this trie contains all country names as keys and their unique IDs as values
     * (e.g., this.get("United States") -> 0, this.get("South Korea") -> 1).
     * @param input the input string in plain text
     *              (e.g., "I was born in South Korea and raised in the United States").
     * @return the list of entities (e.g., [Entity(14, 25, 1), Entity(44, 57, 0)]).
     */
    List<Entity> getEntities(String input) {
        List<Entity> list = new ArrayList<>();
        List<Integer> wordStart = new ArrayList<>();
        wordStart.add(0); //assume input string starts with a word
        for(int i = 0; i < input.length(); i++){
            if(Character.isWhitespace(input.charAt(i))){
                wordStart.add(i+1); //words start 1 character after a space
            }
        }

        for(int i = 0; i < wordStart.size(); i++){ //check every word in the string
            TrieNode node = this.getRoot(); //start at the root of the trie for each word
            int index = wordStart.get(i); //starting index in the input string for this particular word
            while ( !node.isEndState() && node.getChild(input.charAt(index)) != null){ //while we are not at a word and the child exists
                node = node.getChild(input.charAt(index)); //get the child
                index++; //increment to next character in input string
            }

            if(node.getValue() != null){ //if there are no more children or the end state is T, check if we are at a word
                list.add(new Entity(wordStart.get(i), index, (int) node.getValue()));
            }
        }

        return list;
    }
}