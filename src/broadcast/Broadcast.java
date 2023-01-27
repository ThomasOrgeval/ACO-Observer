package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;

/**
 * Broadcast interface
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Broadcast {
    /**
     * Configure the broadcast
     *
     * @param sensor   sensor to broadcast
     * @param channels channels to broadcast to
     */
    void configure(SensorImpl sensor, List<ObserverAsync> channels);

    /**
     * Broadcast the value to all channels
     */
    void execute();

    /**
     * Get the value to broadcast
     *
     * @return value to broadcast
     */
    int valueRead();
}
