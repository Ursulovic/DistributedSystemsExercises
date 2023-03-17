package FactorialWithQueues;

public class Multiplier extends Thread{
    private int myResult;

    private int id;

    public Multiplier(int id) {
        this.id = id;
        myResult = 1;
    }

    @Override
    public void run() {
        super.run();
        int val;
        while (true) {
            try {
                synchronized (Variables.factors) {
                    if (Variables.factors.isEmpty())
                        break;
                    val = Variables.factors.take();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myResult *= val;
        }

        Variables.results.add(myResult);
        Variables.countDownLatch.countDown();
        System.out.println("Thread " + id + " zavrsio!");
    }


    //--------------------------------------------------------------
    //--------------------------------------------------------------
    public static void main(String[] args) {
        while (Variables.value != 0) {
            Variables.factors.add(Variables.value--);
        }


        for (int i = 0; i < Variables.THREAD_NUMBER; i++) {
            Multiplier multiplier = new Multiplier(i);
            multiplier.start();
        }

        ResultCalculator resultCalculator = new ResultCalculator();
        resultCalculator.start();

        try {
            Variables.countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        synchronized (Variables.resultCalculatorLock) {
            try {
                Variables.resultCalculatorLock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Final result: " + Variables.result);

    }

}
