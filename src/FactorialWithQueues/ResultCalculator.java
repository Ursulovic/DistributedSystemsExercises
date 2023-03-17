package FactorialWithQueues;

public class ResultCalculator extends Thread{

    @Override
    public void run() {
        super.run();

        while (!Variables.results.isEmpty() || Variables.countDownLatch.getCount() != 0) {
            try {
                int val = Variables.results.take();
                Variables.result *= val;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        synchronized (Variables.resultCalculatorLock) {
            Variables.resultCalculatorLock.notifyAll();
        }

        System.out.println("Result calculator zavrsio!!!");
    }

}
