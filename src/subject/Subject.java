package subject;

import observer.Observer;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Subject {
    protected final ArrayList<Observer> observers = new ArrayList<>();
    protected final ReentrantLock lock = new ReentrantLock();
    protected int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        lock.lock();
        try {
            this.state = state;
            notifyAllObservers();
        } finally {
            lock.unlock();
        }
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
