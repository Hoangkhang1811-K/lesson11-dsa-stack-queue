package baitap6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.Queue;
import java.util.LinkedList;

public class DemergingUsingQueue {
    static class Person {
        String fullName;
        String gender;   // "NU" or "NAM"
        String dob;      // keep as string (already sorted in input)
        String rawLine;

        Person(String fullName, String gender, String dob, String rawLine) {
            this.fullName = fullName;
            this.gender = gender;
            this.dob = dob;
            this.rawLine = rawLine;
        }
    }

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        Queue<Person> nuQueue = new LinkedList<>();
        Queue<Person> namQueue = new LinkedList<>();

        try {
            ensureFileExists(inputFile);

            try (BufferedReader br = Files.newBufferedReader(Paths.get(inputFile), StandardCharsets.UTF_8)) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    Person p = parseLine(line);
                    if (p == null) continue;

                    if ("NU".equalsIgnoreCase(p.gender)) {
                        nuQueue.add(p);
                    } else if ("NAM".equalsIgnoreCase(p.gender)) {
                        namQueue.add(p);
                    }
                }
            }

            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outputFile), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

                while (!nuQueue.isEmpty()) {
                    bw.write(nuQueue.poll().rawLine);
                    bw.newLine();
                }
                while (!namQueue.isEmpty()) {
                    bw.write(namQueue.poll().rawLine);
                    bw.newLine();
                }
            }

            System.out.println("Da ghi ket qua ra file: " + outputFile);

        } catch (IOException e) {
            System.out.println("Loi IO: " + e.getMessage());
        }
    }

    // Format input (1 dong):
    // HoTen, GioiTinh, NgaySinh
    // VD:
    // Nguyen Van A,NAM,1999-02-10
    // Tran Thi B,NU,2000-12-01
    static Person parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) return null;

        String name = parts[0].trim();
        String gender = parts[1].trim().toUpperCase();
        String dob = parts[2].trim();

        return new Person(name, gender, dob, name + "," + gender + "," + dob);
    }

    static void ensureFileExists(String fileName) throws IOException {
        Path p = Paths.get(fileName);
        if (!Files.exists(p)) {
            Files.createFile(p);
            System.out.println("Da tao file " + fileName + ". Hay them du lieu theo format: HoTen,GioiTinh,NgaySinh");
        }
    }

}
