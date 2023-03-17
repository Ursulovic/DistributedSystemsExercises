package FactorialWithQueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class Variables {


    // Finalni rezultat
    public static int result = 1;

    // Ovo je broj za koj trazimo faktorial
    public static int value = 5;

    // Red za vrednosti koje treba da se mnoze
    public static final BlockingQueue<Integer> factors = new LinkedBlockingQueue<>();


    // Red u koj threadovi koj racunaju upisuju njihove lokalne rezultate
    public static final BlockingQueue<Integer> results = new LinkedBlockingQueue<>();

    //broj jezgara
    public static final int THREAD_NUMBER = Runtime.getRuntime().availableProcessors();

    // Latch koj blokira main thread dok se ne zavrse threadovi koj se bave mnozenjem
    public static final CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);

    // Lock koj sluzi da blokira main thread dok ResultCalculator ne zavrsi sa radom
    public static final Object resultCalculatorLock = "resultCalculatorLock";
}
