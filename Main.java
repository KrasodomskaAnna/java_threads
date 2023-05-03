package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Resource resource = new Resource();
        ResultsCollection resultsCollection = new ResultsCollection();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            Thread calculator = new Thread(new CalcThread(resource, resultsCollection));
            calculator.start();
            threads.add(calculator);
        }

        // https://javastart.pl/baza-wiedzy/java-podstawy-jezyka/podstawowe-wejscie-wyjscie
        Scanner scanner = new Scanner(System.in);
        boolean isWorking = true;
        while (isWorking) {
            String command = scanner.next();

            switch (command) {
                case "exit" -> isWorking = false;
                case "new" -> resource.put(scanner.nextInt());
                default -> {}
            }
        }

        for (Thread t : threads) {
            t.interrupt();
        }
    }
}