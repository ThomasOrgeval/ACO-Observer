package fr.istic.aco.subject;

import fr.istic.aco.broadcast.Broadcast;
import fr.istic.aco.observer.Observer;
import fr.istic.aco.observer.ObserverAsync;

import java.util.ArrayList;
import java.util.List;


/**
 * Sensor implementation
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class SensorImpl implements Sensor {
    /**
     * List of observers
     */
    private final List<ObserverAsync> observers = new ArrayList<>();

    /**
     * Broadcast to use
     */
    private final Broadcast broadcast;

    /**
     * Value of the sensor
     */
    private int value = 0;

    /**
     * Lock to prevent concurrent access: used for atomic broadcast
     */
    private boolean lock = false;

    /**
     * Constructor
     *
     * @param broadcast broadcast to use
     */
    public SensorImpl(Broadcast broadcast) {
        this.broadcast = broadcast;
        broadcast.configure(this, observers);
    }

    /**
     * Attach an observer
     *
     * @param observer observer to attach
     */
    @Override
    public void attach(Observer observer) {
        assert observer instanceof ObserverAsync;
        observers.add((ObserverAsync) observer);
    }

    /**
     * Detach an observer
     *
     * @param observer observer to detach
     */
    @Override
    public void detach(Observer observer) {
        observers.remove((ObserverAsync) observer);
    }

    /**
     * Get the value of the sensor
     *
     * @return value of the sensor
     */
    @Override
    public int getValue() {
        return broadcast.valueRead();
    }

    /**
     * Tick the sensor
     */
    @Override
    public void tick() {
        if (!lock) {
            value++;
            broadcast.execute();
        }
    }

    /**
     * Update the value of the observers
     */
    public void update() {
        observers.forEach(observer -> {
            try {
                observer.update(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Get the base value of the sensor
     *
     * @return base value of the sensor
     */
    public int getBaseValue() {
        return value;
    }

    /**
     * Set the lock
     *
     * @param lock lock to set
     */
    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
