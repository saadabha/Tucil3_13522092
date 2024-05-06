package src;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import src.Main.IntegerWrapper;

public class Astar {
    public static List<String> findShortestLadder(String startWord, String endWord, Set<String> dictionary, IntegerWrapper countnode) {
        PriorityQueue<node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        Set<String> visited = new HashSet<>();
        node startNode = new node(startWord, greedy.hammingDistance(startWord, endWord));
        
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
                    node neighborNode = new node(neighbor, current,  greedy.hammingDistance(neighbor, endWord));
                    neighborNode.cost += neighborNode.distancefromroot();
                    pq.offer(neighborNode);
                }
            }
        }

        countnode.value = visited.size()+1;
        return Collections.singletonList("No ladder found");
    }
}
