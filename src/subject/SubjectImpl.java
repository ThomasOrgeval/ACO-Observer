package subject;

import java.util.concurrent.*;

public class SubjectImpl extends Subject {
    public SubjectImpl(ScheduledExecutorService scheduler) {
        super(scheduler);
    }

    public Future runTask(Runnable task) {
        return scheduler.submit(task);
    }

    public <T> Future<T> runTask(Runnable task, T result) {
        return scheduler.submit(task, result);
    }

    public <T> Future<T> runTask(Callable<T> task) {
        return scheduler.submit(task);
    }

    public ScheduledFuture scheduleTask(Runnable task, long delay, TimeUnit unit) {
        return scheduler.schedule(task, delay, unit);
    }

    public <V> ScheduledFuture<V> scheduleTask(Callable<V> task, long delay, TimeUnit unit) {
        return scheduler.schedule(task, delay, unit);
    }
}
