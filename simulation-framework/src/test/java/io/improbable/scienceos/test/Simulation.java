package io.improbable.scienceos.test;

import io.improbable.scienceos.EndSimulationException;
import io.improbable.scienceos.ISimulation;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by daniel on 08/02/17.
 */
public class Simulation implements ISimulation {

    private final static int NUMBER_TIME_STEPS = 10;

    final int nTiles=6;
    final int tileSize = 100;
    TileReference [] tilerefs = new TileReference[nTiles];
    private int remainingSteps;

    public Simulation() {
        this.remainingSteps = NUMBER_TIME_STEPS;
        int i;
        for(i=0; i<nTiles; ++i) {
            tilerefs[i] = new TileReference(tileSize);
        }
        Tile.join(tilerefs);
    }

    public void step() throws ExecutionException, InterruptedException, EndSimulationException {
        if (this.remainingSteps > 0) {
            this.performStep();
            this.remainingSteps -= 1;
        } else {
            throw new EndSimulationException();
        }
    }

    private void performStep() throws ExecutionException, InterruptedException {
        CompletableFuture<Void>[] steps = new CompletableFuture[nTiles];
        for(int i=0; i<nTiles; ++i) {
            steps[i] = tilerefs[i].step();
        }
        CompletableFuture.allOf(steps).get();
        CompletableFuture<Void>[] updates = new CompletableFuture[nTiles];
        for(int i=0; i<nTiles; ++i) {
            updates[i] = tilerefs[i].update();
        }
        CompletableFuture.allOf(updates).get();
        printState();
    }

    public void printState() throws ExecutionException, InterruptedException {
        for(int n=0; n<nTiles; ++n) {
            printState(n).get();
        }
        System.out.println("");
    }

    public CompletableFuture<Void> printState(int n) {
        return tilerefs[n].printState(n*tileSize, 0);
    }

    @Override
    public void stop() {

    }
}
