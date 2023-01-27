package proxy;

import observer.Display;
import observer.ObserverAsync;
import subject.Sensor;
import subject.SensorAsync;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Channel
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class Channel implements ObserverAsync, SensorAsync {
    /**
     * Scheduled executor service to simulate the delay
     */
    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);

    /**
     * Observer to update
     */
    private final Display observer;

    /**
     * Sensor to get the value from
     */
    private final Sensor sensor;

    /**
     * Constructor
     *
     * @param sensor   sensor to get the value from
     * @param observer observer to update
     */
    public Channel(Sensor sensor, Display observer) {
        this.sensor = sensor;
        this.observer = observer;
    }

    /**
     * Update the observer
     *
     * @param sensor object to update
     * @throws Exception
     */
    @Override
    public void update(SensorAsync sensor) throws Exception {
        Update update = new Update(observer, this);
        long delay = (long) (Math.random() * 300);
        scheduledExecutorService.schedule(update, delay, TimeUnit.MILLISECONDS).get();
    }

    /**
     * Get the value from the sensor
     *
     * @return value from the sensor
     * @throws Exception
     */
    @Override
    public int getValue() throws Exception {
        GetValue value = new GetValue(sensor);
        long delay = (long) (Math.random() * 300);
        return scheduledExecutorService.schedule(value, delay, TimeUnit.MILLISECONDS).get();
    }
}
