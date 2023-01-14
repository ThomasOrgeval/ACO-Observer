package subject;

public class SubjectImpl extends Subject {
    private int currentEpoch;

    public void runTask(Runnable task) {
        lock.lock();
        try {
            currentEpoch++;
            task.run();
        } finally {
            lock.unlock();
        }
    }

    public int getCurrentEpoch() {
        return currentEpoch;
    }
}
