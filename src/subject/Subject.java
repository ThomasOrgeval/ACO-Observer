package subject;

import observer.Observer;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Subject {
    protected final ArrayList<Observer> observers = new ArrayList<>();
    protected final ScheduledExecutorService scheduler;
    protected int state;

    public Subject(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            scheduler.schedule(observer::update, 0, TimeUnit.MILLISECONDS);
        }
    }
}
