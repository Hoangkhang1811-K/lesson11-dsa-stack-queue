package baitap1;

import java.util.Scanner;
import java.util.Stack;

public class StackReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ======= 1) Đảo ngược mảng số nguyên dùng Stack =======
        System.out.println("=== DAO NGUOC MANG SO NGUYEN (STACK) ===");
        int n = readInt(sc, "Nhap so luong phan tu n = ", 0, Integer.MAX_VALUE);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = readInt(sc, "Nhap a[" + i + "] = ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        System.out.print("Mang ban dau: ");
        printArray(arr);

        reverseArrayUsingStack(arr);

        System.out.print("Mang sau khi dao: ");
        printArray(arr);

        // ======= 2) Đảo ngược chuỗi (đảo thứ tự TỪ) dùng Stack =======
        System.out.println("\n=== DAO NGUOC CHUOI (DAO THU TU TU - STACK) ===");
        System.out.print("Nhap chuoi: ");
        String input = sc.nextLine(); // an newline con sot lai
        if (input.isEmpty()) {
            input = sc.nextLine();     // neu bi rong do newline truoc do
        }

        String reversedSentence = reverseWordsUsingStack(input);

        System.out.println("Chuoi ban dau: " + input);
        System.out.println("Chuoi sau khi dao tu: " + reversedSentence);

        sc.close();
    }


    static void reverseArrayUsingStack(int[] a) {
        Stack<Integer> stack = new Stack<>();


        for (int value : a) {
            stack.push(value);
        }


        for (int i = 0; i < a.length; i++) {
            a[i] = stack.pop();
        }
    }


    static String reverseWordsUsingStack(String s) {
        s = s == null ? "" : s.trim();
        if (s.isEmpty()) return "";

        String[] words = s.split("\\s+"); // tách theo 1+ khoảng trắng
        Stack<String> wStack = new Stack<>();

        for (String w : words) {
            wStack.push(w);
        }

        StringBuilder out = new StringBuilder();
        while (!wStack.isEmpty()) {
            out.append(wStack.pop());
            if (!wStack.isEmpty()) out.append(" ");
        }
        return out.toString();
    }


    static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }


    static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int x = Integer.parseInt(line);
                if (x < min || x > max) {
                    System.out.println("Gia tri phai nam trong [" + min + ", " + max + "]. Nhap lai!");
                    continue;
                }
                return x;
            } catch (NumberFormatException e) {
                System.out.println("Nhap so nguyen hop le! Nhap lai!");
            }
        }
    }

}
