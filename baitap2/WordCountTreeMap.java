package baitap2;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordCountTreeMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== DEM SO LAN XUAT HIEN CUA MOI TU (TreeMap) ===");
        System.out.print("Nhap chuoi: ");
        String input = sc.nextLine();


        TreeMap<String, Integer> wordCount = countWords(input);

        System.out.println("\nKet qua (sap xep theo bang chu cai):");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        sc.close();
    }


    static TreeMap<String, Integer> countWords(String s) {
        TreeMap<String, Integer> map = new TreeMap<>();
        if (s == null) return map;


        s = s.trim().toLowerCase();
        if (s.isEmpty()) return map;


        String[] words = s.split("[^\\p{L}\\p{N}]+");


        for (String w : words) {
            if (w.isEmpty()) continue;

            if (map.containsKey(w)) {
                map.put(w, map.get(w) + 1);
            } else {
                map.put(w, 1);
            }
        }
        return map;
    }
}
