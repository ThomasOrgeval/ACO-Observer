package proxy;

import observer.ObserverAsync;
import subject.SensorAsync;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {
    private final ObserverAsync observer;
    private final SensorAsync sensor;

    public Update(ObserverAsync observer, SensorAsync sensor) {
        this.observer = observer;
        this.sensor = sensor;
    }

    @Override
    public Void call() throws Exception {
        observer.update(sensor);
        return null;
    }
}
