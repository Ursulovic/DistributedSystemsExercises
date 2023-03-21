package FactorialWithQueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Variables {


    // Finalni rezultat
    private  int result = 1;

    // Ovo je broj za koj trazimo faktorial
    private int value = 5;

    // Red za vrednosti koje treba da se mnoze
    private final BlockingQueue<Integer> factors;


    // Red u koj threadovi koj racunaju upisuju njihove lokalne rezultate
    private final BlockingQueue<Integer> results;

    //broj jezgara
    private final int THREAD_NUMBER;

    // Latch koj blokira main thread dok se ne zavrse threadovi koj se bave mnozenjem
    private final CountDownLatch multiplierFinished;

    private final CountDownLatch resultFinished;


    public synchronized void multiplyWithResult(int val) {
        this.result *= val;
    }


    public Variables(int value, BlockingQueue<Integer> factors, BlockingQueue<Integer> results, int THREAD_NUMBER, CountDownLatch multiplierFinished, CountDownLatch resultFinished) {
        this.value = value;
        this.factors = factors;
        this.results = results;
        this.THREAD_NUMBER = THREAD_NUMBER;
        this.multiplierFinished = multiplierFinished;
        this.resultFinished = resultFinished;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BlockingQueue<Integer> getFactors() {
        return factors;
    }

    public BlockingQueue<Integer> getResults() {
        return results;
    }

    public int getTHREAD_NUMBER() {
        return THREAD_NUMBER;
    }

    public CountDownLatch getMultiplierFinished() {
        return multiplierFinished;
    }

    public int getResult() {
        return result;
    }

    public CountDownLatch getResultFinished() {
        return resultFinished;
    }
}
