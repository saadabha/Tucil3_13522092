package src;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Main {
    private JFrame frame;
    private JTextField startField;
    private JTextField endField;
    private JComboBox<String> algorithmComboBox;
    private JButton findButton;
    private JTextArea resultArea;
    private JLabel nodeCountLabel;
    private JLabel timeLabel;

    static class IntegerWrapper {
        public int value;

        public IntegerWrapper(int value) {
            this.value = value;
        }
    }

    public Main() {
        frame = new JFrame("Word Ladder Solver");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel startLabel = new JLabel("Start Word:");
        startLabel.setBounds(20, 20, 100, 20);
        frame.add(startLabel);

        startField = new JTextField();
        startField.setBounds(120, 20, 200, 20);
        frame.add(startField);

        JLabel endLabel = new JLabel("End Word:");
        endLabel.setBounds(20, 50, 100, 20);
        frame.add(endLabel);

        endField = new JTextField();
        endField.setBounds(120, 50, 200, 20);
        frame.add(endField);

        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmLabel.setBounds(20, 80, 100, 20);
        frame.add(algorithmLabel);

        algorithmComboBox = new JComboBox<>(new String[]{"UCS", "Greedy", "A*"});
        algorithmComboBox.setBounds(120, 80, 200, 20);
        frame.add(algorithmComboBox);

        findButton = new JButton("Find Shortest Ladder");
        findButton.setBounds(120, 110, 200, 30);
        frame.add(findButton);

        resultArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(20, 150, 360, 150);
        frame.add(scrollPane);

        nodeCountLabel = new JLabel("");
        nodeCountLabel.setBounds(20, 310, 200, 20);
        frame.add(nodeCountLabel);

        timeLabel = new JLabel("");
        timeLabel.setBounds(220, 310, 200, 20);
        frame.add(timeLabel);

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startWord = startField.getText().toLowerCase();
                if (!util.isWordInDictionary(startWord)) {
                    resultArea.setText("Kata tidak ditemukan dalam kamus. Silakan masukkan kata yang valid.");
                    return;
                }
        

                String endWord = endField.getText().toLowerCase();
                if (!util.isWordInDictionary(endWord) || startWord.length() != endWord.length()){
                    if (!util.isWordInDictionary(endWord)) {
                        resultArea.setText("Kata tidak ditemukan dalam kamus. Silakan masukkan kata yang valid.");
                    } else {
                        resultArea.setText("Panjang kata tidak sama dengan panjang kata awal. Silakan masukkan kata yang valid.");
                    }
                    return;
                }

                String algorithm = (String) algorithmComboBox.getSelectedItem();

                IntegerWrapper countnode = new IntegerWrapper(0);
                List<String> ladder = new ArrayList<>();
                long startTime = System.nanoTime();
                long endTime = 0;
                Set<String> words = util.getWordsWithLength(startWord.length());

                switch (algorithm) {
                    case "UCS":
                        ladder = ucs.findShortestLadder(startWord, endWord, words, countnode);
                        break;
                    case "Greedy":
                        ladder = greedy.findShortestLadder(startWord, endWord, words, countnode);
                        break;
                    case "A*":
                        ladder = Astar.findShortestLadder(startWord, endWord, words, countnode);
                        break;
                }
                endTime = System.nanoTime();
                long duration = endTime - startTime;
                double seconds = duration / 1_000_000_000.0;

                resultArea.setText("Shortest Ladder:\n" + String.join("\n", ladder));
                nodeCountLabel.setText("Node Count: " + countnode.value);
                timeLabel.setText("Execution Time: " + seconds + " s");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
