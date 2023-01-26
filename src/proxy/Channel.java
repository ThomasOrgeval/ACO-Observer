package proxy;

import observer.Display;
import observer.ObserverAsync;
import subject.Sensor;
import subject.SensorAsync;
import subject.SensorImpl;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Channel implements ObserverAsync, SensorAsync {
    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
    private final Display observer;
    private final Sensor sensor;
    private final Random random = new Random();

    public Channel(Sensor sensor, Display observer) {
        this.sensor = sensor;
        this.observer = observer;
    }

    @Override
    public void update(SensorAsync sensor) throws Exception {
        Update update = new Update(observer, this);
        long delay = (long) (Math.random() * 500);
        scheduledExecutorService.schedule(update, random.nextInt(500), TimeUnit.MILLISECONDS).get();
    }

    @Override
    public Integer getValue() throws Exception {
        GetValue value = new GetValue(sensor);
        long delay = (long) (Math.random() * 500);
        return scheduledExecutorService.schedule(value, random.nextInt(500), TimeUnit.MILLISECONDS).get();
    }
}
