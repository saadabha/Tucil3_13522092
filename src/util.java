package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class util {
    public static List<String> reconstructpath(node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(node.word);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static Set<String> getneighbors(String word, Set<String> dictionary) {
        Set<String> neigbhors = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String newWord = new String(chars);
                if (!newWord.equals(word) && dictionary.contains(newWord)) {
                    neigbhors.add(newWord);
                }
            }
        }

        return neigbhors;
    }

    public static boolean isWordInDictionary(String word) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../test/dictionary.txt"));
            String line = reader.readLine();

            while (line != null) {
                if (line.equals(word)) {
                    reader.close();
                    return true;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Set<String> getWordsWithLength(int length) {
        Set<String> words = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../test/dictionary.txt"));
            String line = reader.readLine();

            while (line != null) {
                if (line.length() == length) {
                    words.add(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
