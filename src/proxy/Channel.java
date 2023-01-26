package proxy;

import observer.Display;
import observer.ObserverAsync;
import subject.Sensor;
import subject.SensorAsync;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class Channel implements ObserverAsync, SensorAsync {
    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
    private final Display observer;
    private final Sensor sensor;

    public Channel(Sensor sensor, Display observer) {
        this.sensor = sensor;
        this.observer = observer;
    }

    @Override
    public void update(SensorAsync sensor) throws Exception {
        Update update = new Update(observer, this);
        long delay = (long) (Math.random() * 300);
        scheduledExecutorService.schedule(update, delay, TimeUnit.MILLISECONDS).get();
    }

    @Override
    public int getValue() throws Exception {
        GetValue value = new GetValue(sensor);
        long delay = (long) (Math.random() * 300);
        return scheduledExecutorService.schedule(value, delay, TimeUnit.MILLISECONDS).get();
    }
}
