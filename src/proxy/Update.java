package proxy;

import observer.ObserverAsync;
import subject.SensorAsync;

import java.util.concurrent.Callable;


/**
 * Update active object
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class Update implements Callable<Void> {
    /**
     * Observer to update
     */
    private final ObserverAsync observer;

    /**
     * Sensor to get the value from
     */
    private final SensorAsync sensor;

    /**
     * Constructor
     *
     * @param observer observer to update
     * @param sensor   sensor to get the value from
     */
    public Update(ObserverAsync observer, SensorAsync sensor) {
        this.observer = observer;
        this.sensor = sensor;
    }

    /**
     * Update the observer
     *
     * @return null
     * @throws Exception
     */
    @Override
    public Void call() throws Exception {
        observer.update(sensor);
        return null;
    }
}
