package src;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import src.main.IntegerWrapper;

public class Astar {
    public static List<String> findShortestLadder(String startWord, String endWord, Set<String> dictionary, IntegerWrapper countnode) {
        PriorityQueue<node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        Set<String> visited = new HashSet<>();
        node startNode = new node(startWord, greedy.hammingDistance(startWord, endWord));
        
        countnode.value++;
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
                node neighborNode = new node(neighbor, current, current.cost + 1 + greedy.hammingDistance(neighbor, endWord));
                pq.offer(neighborNode);
            }
        }

        return Collections.singletonList("No ladder found");
    }
}
