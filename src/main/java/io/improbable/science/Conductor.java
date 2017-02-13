package io.improbable.science;

import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

public class Conductor implements Runnable {

    private final ISteppable sim;

    public Conductor(ISteppable sim) {
        this.sim = sim;
    }

    public void run() {
        try {
            int i,p;
            for(i=0; i<100; ++i) {
                Reference.pool.mainExecutor().submit(() -> {
                    sim.step();
                }).get();
            }
            sleep(200);
            Reference.pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
