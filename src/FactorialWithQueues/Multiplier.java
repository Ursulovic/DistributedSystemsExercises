package FactorialWithQueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Multiplier extends Thread{
    private int myResult;

    private int id;

    private Variables variables;

    public Multiplier(int id, Variables variables) {
        this.id = id;
        this.variables = variables;
        myResult = 1;
    }

    @Override
    public void run() {
        Integer val;
        while (true) {
            try {

                val = variables.getFactors().poll(2, TimeUnit.SECONDS);

                if (val == null)
                    break;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.myResult *= val;
        }

        variables.getResults().add(myResult);
        variables.getMultiplierFinished().countDown();
        System.out.println("Thread " + id + " zavrsio!");
    }


    //--------------------------------------------------------------
    //--------------------------------------------------------------
    public static void main(String[] args) {

        //promenjive za Variables
        int value = 5;
        int THREAD_NUMBER = Runtime.getRuntime().availableProcessors();

        BlockingQueue<Integer> factors = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> resultsd = new LinkedBlockingQueue<>();
        CountDownLatch multiplierFinished = new CountDownLatch(THREAD_NUMBER);
        CountDownLatch resultFinished = new CountDownLatch(1);

        Variables variables = new Variables(value, factors, resultsd, THREAD_NUMBER, multiplierFinished, resultFinished);

        while (variables.getValue() != 0) {
            variables.getFactors().add(variables.getValue());
            variables.setValue(variables.getValue() - 1);
        }


        for (int i = 0; i < variables.getTHREAD_NUMBER(); i++) {
            Multiplier multiplier = new Multiplier(i, variables);
            multiplier.start();
        }

        ResultCalculator resultCalculator = new ResultCalculator(variables);
        resultCalculator.start();

        try {
            variables.getResultFinished().await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final result: " + variables.getResult());

    }

}
