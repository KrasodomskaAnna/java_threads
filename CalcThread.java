package org.example;

public class CalcThread implements Runnable {
    private final Resource resource;
    private final ResultsCollection resultsCollection;

    public CalcThread(Resource resource, ResultsCollection resultsCollection) {
        this.resource = resource;
        this.resultsCollection = resultsCollection;
    }


    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " has started");
        while (true) {
            Integer param;
            try {
                param = resource.take();
                System.out.println("Thread " + Thread.currentThread().getId() + " has taken " + param);
            } catch (InterruptedException e) {
                System.out.println("Thread " + Thread.currentThread().getId() + " has terminated");
                return;
            }

            Boolean result;
            try {
                result = isPrime(param);
            } catch (InterruptedException e) {
                System.out.println("Thread " + Thread.currentThread().getId() + " has terminated");
                return;
            }
            if (result) {
                resultsCollection.put(param);
            }
            System.out.println("Thread " + Thread.currentThread().getId() + " for " + param + " has put " + result);
        }
    }

    private Boolean isPrime(Integer n) throws InterruptedException {
        // quick checks
        if (n == 2) {
            try {
                Thread.sleep(10*1000);                          // 10s
            } catch (InterruptedException ex) {
            }
            return true;
        }
        if (n <= 1 || n%2 == 0) {
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException ex) {
            }
            return false;
        }
        for (int i = 3; i*i <= n; i+=2) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}