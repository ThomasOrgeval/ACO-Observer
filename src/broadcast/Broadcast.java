package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;

/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Broadcast {
    void configure(SensorImpl sensor, List<ObserverAsync> channels);

    void execute();

    int valueRead();
}
