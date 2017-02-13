import java.util.concurrent.ExecutorService;

/**
 * Created by daniel on 08/02/17.
 */
public class Reference<T> {
    static WorkerPool pool = new WorkerPool(4);
    ExecutorService executor;
    T               referent;

    public Reference(T referent) {
        executor = pool.executorFor(referent);
        this.referent = referent;
    }

}