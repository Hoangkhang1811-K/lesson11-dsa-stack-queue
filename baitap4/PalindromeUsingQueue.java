package baitap4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

    public class PalindromeUsingQueue {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Nhap chuoi: ");
            String input = sc.nextLine();

            System.out.println(isPalindrome(input) ? "Palindrome" : "Not Palindrome");

            sc.close();
        }

        static boolean isPalindrome(String s) {
            if (s == null) return false;

            String cleaned = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

            Queue<Character> queue = new LinkedList<>();
            Stack<Character> stack = new Stack<>();

            for (char c : cleaned.toCharArray()) {
                queue.add(c);
                stack.push(c);
            }

            while (!queue.isEmpty()) {
                if (!queue.poll().equals(stack.pop())) return false;
            }
            return true;
        }
    }


