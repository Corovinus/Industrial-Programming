package org.example;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final ReentrantLock lock = new ReentrantLock();
    private static int[] array;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите имя файла с массивом: ");
            String fileName = scanner.nextLine();
            array = readArrayFromFile(fileName);

            System.out.println("Исходный массив:");
            printArray(array);

            System.out.print("Введите порядок сортировки (1 для возрастания, 2 для убывания): ");
            int sortOrder = scanner.nextInt();

            if (sortOrder != 1 && sortOrder != 2) {
                System.out.println("Неверный ввод! Допустимы только 1 или 2.");
                return;
            }
            SortThread sortThread = new SortThread(array, sortOrder);

            Thread thread = new Thread(sortThread);
            thread.start();

            thread.join();

            System.out.println("Отсортированный массив:");
            printArray(array);

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static int[] readArrayFromFile(String fileName) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                for (String part : parts) {
                    list.add(Integer.parseInt(part));
                }
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }


    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
