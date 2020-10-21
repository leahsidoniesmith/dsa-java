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

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


/** @author Jinho D. Choi */
public class TrieQuizTest {
    @Test
    public void testGetEntities() {
        final List<String> L = List.of("United States", "South Korea", "Japan", "Canada");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);
        String input = "flCanadafl South Korea abcUnited States. Canada. fJapanese.";
        List<Entity> entities = List.of(
                new Entity(26, 39, 0),
                new Entity(11, 22, 1),
                new Entity(50, 55, 2),
                new Entity(2, 8, 3),
                new Entity(41, 47, 3));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }
}
