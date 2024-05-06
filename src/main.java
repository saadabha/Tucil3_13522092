package src;

import java.util.*;

public class main {
    static class IntegerWrapper {
        public int value;

        public IntegerWrapper(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan string mulai: ");
        String startword = scanner.nextLine();
        startword = startword.toLowerCase();
        while (!util.isWordInDictionary(startword)) {
            System.out.println("Kata tidak ditemukan dalam kamus. Silakan masukkan kata yang valid.");
            System.out.print("Masukkan string mulai: ");
            startword = scanner.nextLine();
            startword = startword.toLowerCase();
        }

        System.out.print("Masukkan string tujuan: ");
        String endword = scanner.nextLine();
        endword = endword.toLowerCase();
        while (!util.isWordInDictionary(endword) || startword.length() != endword.length()){
            if (!util.isWordInDictionary(endword)) {
                System.out.println("Kata tidak ditemukan dalam kamus. Silakan masukkan kata yang valid.");
            } else {
                System.out.println("Panjang kata tidak sama dengan panjang kata awal. Silakan masukkan kata yang valid.");
            }
            System.out.print("Masukkan string tujuan: ");
            endword = scanner.nextLine();
            endword = endword.toLowerCase();
        }

        System.out.println("Berikut adalah pilihan algoritma yang dapat digunakan:");
        System.out.println("1. UCS");
        System.out.println("2. Greedy");
        System.out.println("3. A*");

        boolean isValidInput = false;
        int choice = 0;

        while (!isValidInput) {
            try {
                System.out.print("Masukkan pilihan algoritma: ");
                choice = scanner.nextInt();
                while (choice < 1 || choice > 3) {
                    System.out.println("Pilihan algoritma tidak valid. Silakan masukkan pilihan algoritma yang valid.");
                    System.out.print("Masukkan pilihan algoritma: ");
                    choice = scanner.nextInt();
                }
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Silakan masukkan input yang valid.");
                scanner.nextLine();
            }
        }
        scanner.close();

        IntegerWrapper countnode = new IntegerWrapper(0);
        List<String> result = new ArrayList<>();
        long startTime = System.nanoTime();
        long endTime = 0;
        Set<String> words = util.getWordsWithLength(startword.length());

        switch (choice) {
            case 1:
                result = ucs.findShortestLadder(startword, endword, words, countnode);
                for (String str : result) {
                    System.out.println(str);
                }
                endTime = System.nanoTime();
                break;
            case 2:
                result = ucs.findShortestLadder(startword, endword, words, countnode);
                for (String str : result) {
                    System.out.println(str);
                }
                endTime = System.nanoTime();
                break;
            case 3:
                result = ucs.findShortestLadder(startword, endword, words, countnode);
                for (String str : result) {
                    System.out.println(str);
                }
                endTime = System.nanoTime();
                break;
        }
        long duration = endTime - startTime;

        double seconds = duration / 1_000_000_000.0;
        System.out.println("Jumlah node yang dikunjungi: " + countnode.value);
        System.out.println("Waktu eksekusi: " + seconds + " s");
    }
}