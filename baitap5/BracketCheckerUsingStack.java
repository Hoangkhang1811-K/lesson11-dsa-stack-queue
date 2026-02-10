package baitap5;

import java.util.Scanner;
import java.util.Stack;

public class BracketCheckerUsingStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap bieu thuc: ");
        String expr = sc.nextLine();

        System.out.println(isWell(expr) ? "Well" : "Not Well");

        sc.close();
    }

    static boolean isWell(String s) {
        if (s == null) return false;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (st.isEmpty()) return false;
                char left = st.pop();
                if (!match(left, c)) return false;
            }
        }

        return st.isEmpty();
    }

    static boolean match(char left, char right) {
        return (left == '(' && right == ')')
                || (left == '[' && right == ']')
                || (left == '{' && right == '}');
    }
}
