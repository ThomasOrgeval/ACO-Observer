package subject;

import broadcast.Broadcast;
import observer.Observer;
import observer.ObserverAsync;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class SensorImpl implements Sensor {
    private final List<ObserverAsync> observers = new ArrayList<>();
    private final Broadcast broadcast;
    private int value = 0;
    private boolean lock = false;

    public SensorImpl(Broadcast broadcast) {
        this.broadcast = broadcast;
        broadcast.configure(this, observers);
    }

    @Override
    public void attach(Observer observer) {
        assert observer instanceof ObserverAsync;
        observers.add((ObserverAsync) observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove((ObserverAsync) observer);
    }

    @Override
    public int getValue() {
        return broadcast.valueRead();
    }

    @Override
    public void tick() {
        if (!lock) {
            value++;
            broadcast.execute();
        }
    }

    public void update() {
        observers.forEach(observer -> {
            try {
                observer.update(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public int getBaseValue() {
        return value;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
