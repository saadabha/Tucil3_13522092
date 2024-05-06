package src;

import java.util.*;

import src.Main.IntegerWrapper;

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

            if (currentWord.equals(endWord)) {
                countnode.value = visited.size()+1;
                return util.reconstructpath(current);
            }

            visited.add(currentWord);

            for (String neighbor : util.getneighbors(currentWord, dictionary)) {
                if (!visited.contains(neighbor)) {
                    node neighborNode = new node(neighbor, current, hammingDistance(neighbor, endWord));
                    pq.offer(neighborNode);
                }
            }
        }

        countnode.value = visited.size()+1;
        return Collections.singletonList("No ladder found");
    }
}
