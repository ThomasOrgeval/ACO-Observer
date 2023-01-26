package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;

public interface Broadcast {
    void configure(SensorImpl sensor, List<ObserverAsync> channels);

    void execute();

    Integer valueRead();
}
