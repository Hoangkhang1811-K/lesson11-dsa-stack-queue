package baitap3;

import java.util.Scanner;
import java.util.Stack;

public class BaseConverterUsingStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== CHUYEN DOI HE CO SO (STACK) ===");
            System.out.println("1. Decimal -> Binary");
            System.out.println("2. Binary  -> Decimal");
            System.out.println("3. Decimal -> Hex");
            System.out.println("4. Hex     -> Decimal");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": {
                    int n = readInt(sc, "Nhap so thap phan (>=0): ", 0, Integer.MAX_VALUE);
                    System.out.println("Ket qua: " + decToBaseUsingStack(n, 2));
                    break;
                }
                case "2": {
                    String bin = readNonEmpty(sc, "Nhap so nhi phan: ");
                    if (!isValidBinary(bin)) {
                        System.out.println("Nhi phan khong hop le!");
                        break;
                    }
                    System.out.println("Ket qua: " + baseToDecUsingStack(bin, 2));
                    break;
                }
                case "3": {
                    int n = readInt(sc, "Nhap so thap phan (>=0): ", 0, Integer.MAX_VALUE);
                    System.out.println("Ket qua: " + decToBaseUsingStack(n, 16));
                    break;
                }
                case "4": {
                    String hex = readNonEmpty(sc, "Nhap so he 16: ");
                    if (!isValidHex(hex)) {
                        System.out.println("He 16 khong hop le!");
                        break;
                    }
                    System.out.println("Ket qua: " + baseToDecUsingStack(hex, 16));
                    break;
                }
                case "0":
                    sc.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    static String decToBaseUsingStack(int n, int base) {
        if (n == 0) return "0";

        Stack<Integer> stack = new Stack<>();
        int x = n;
        while (x > 0) {
            stack.push(x % base);
            x /= base;
        }

        StringBuilder out = new StringBuilder();
        while (!stack.isEmpty()) {
            int digit = stack.pop();
            out.append(toDigitChar(digit));
        }
        return out.toString();
    }

    static int baseToDecUsingStack(String s, int base) {
        String str = s.trim().toUpperCase();

        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) stack.push(c);

        int power = 0;
        long result = 0;

        while (!stack.isEmpty()) {
            char c = stack.pop();
            int digit = fromDigitChar(c);
            result += (long) digit * powInt(base, power);
            power++;
        }

        if (result > Integer.MAX_VALUE) {
            throw new ArithmeticException("Gia tri vuot qua Integer.MAX_VALUE");
        }
        return (int) result;
    }

    static char toDigitChar(int digit) {
        if (digit >= 0 && digit <= 9) return (char) ('0' + digit);
        return (char) ('A' + (digit - 10));
    }

    static int fromDigitChar(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'A' && c <= 'F') return 10 + (c - 'A');
        return -1;
    }

    static int powInt(int base, int exp) {
        int r = 1;
        for (int i = 0; i < exp; i++) r *= base;
        return r;
    }

    static boolean isValidBinary(String s) {
        if (s == null) return false;
        String str = s.trim();
        if (str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (c != '0' && c != '1') return false;
        }
        return true;
    }

    static boolean isValidHex(String s) {
        if (s == null) return false;
        String str = s.trim().toUpperCase();
        if (str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            boolean ok = (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F');
            if (!ok) return false;
        }
        return true;
    }

    static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int x = Integer.parseInt(line);
                if (x < min || x > max) {
                    System.out.println("Gia tri phai nam trong [" + min + ", " + max + "].");
                    continue;
                }
                return x;
            } catch (NumberFormatException e) {
                System.out.println("Nhap so nguyen hop le!");
            }
        }
    }

    static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("Khong duoc de trong!");
        }
    }
}
