package io.improbable.science.test.gameoflife;

import io.improbable.science.PoolPool;
import io.improbable.science.Reference;

import java.util.concurrent.CompletableFuture;

/**
 * Created by daniel on 07/02/17.
 */
public class TileReference extends Reference<Tile> {

    public TileReference(Tile referent) {
        super(referent);
    }

    CompletableFuture<Void> step() {
        return CompletableFuture.runAsync(() -> referent.step(), executor).thenRunAsync(() ->{}, PoolPool.currentExecutor());
    }

    CompletableFuture<Void> update() {
        return CompletableFuture.runAsync(() -> referent.update(), executor).thenRunAsync(() ->{}, PoolPool.currentExecutor());
    }

    CompletableFuture<boolean []> getLeftBoundary() {
        return CompletableFuture.supplyAsync(() -> referent.getLeftBoundary(), executor).thenApplyAsync((i) ->{return(i);}, PoolPool.currentExecutor());
    }

    CompletableFuture<boolean []> getRightBoundary() {
        return CompletableFuture.supplyAsync(() -> referent.getRightBoundary(), executor).thenApplyAsync((i) ->{return(i);}, PoolPool.currentExecutor());
    }

}
