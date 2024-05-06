package src;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import src.main.IntegerWrapper;

public class greedy {
    public static int hammingDistance(String str1, String str2) {
        if (str1.length() != str2.length()) {
            throw new IllegalArgumentException("Strings must have equal length");
        }
        int distance = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public static List<String> findShortestLadder(String startWord, String endWord, Set<String> dictionary, IntegerWrapper countnode) {
        PriorityQueue<node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        Set<String> visited = new HashSet<>();
        node startNode = new node(startWord, hammingDistance(startWord, endWord));
        
        pq.offer(startNode);

        while (!pq.isEmpty()) {
            node current = pq.poll();
            String currentWord = current.word;

            countnode.value += 1;

            if (currentWord.equals(endWord)) {
                return util.reconstructpath(current);
            }

            visited.add(currentWord);

            for (String neighbor : util.getneighbors(currentWord, dictionary, visited)) {
                node neighborNode = new node(neighbor, current, hammingDistance(neighbor, endWord));
                pq.offer(neighborNode);
            }
        }

        return Collections.singletonList("No ladder found");
    }
}
