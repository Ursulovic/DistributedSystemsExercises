package FactorialWithQueues;

public class ResultCalculator extends Thread{

    private Variables variables;

    public ResultCalculator(Variables variables) {
        this.variables = variables;
    }

    @Override
    public void run() {


        try {
            variables.getMultiplierFinished().await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Integer val;

        while (true) {
            val = variables.getResults().poll();

            if (val == null) {
                variables.getResultFinished().countDown();
                break;
            }
            variables.multiplyWithResult(val);

        }


        System.out.println("Result calculator zavrsio!!!");
    }

}
