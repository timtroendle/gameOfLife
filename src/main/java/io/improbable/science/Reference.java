package io.improbable.science;

import java.util.concurrent.ExecutorService;

/**
 * Created by daniel on 08/02/17.
 */
public class Reference<T> {
    public static PoolPool pool = new PoolPool(4);
    public ExecutorService executor;
    public T               referent;

    public Reference(T referent) {
        executor = pool.executorFor(referent);
        this.referent = referent;
//        System.out.println("Created reference for "+referent+" on "+executor);
    }

}
