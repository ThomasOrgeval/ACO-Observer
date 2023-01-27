package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;


/**
 * Sequential broadcast
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class SequentialBroadcast implements Broadcast {
    /**
     * Sensor to broadcast
     */
    private SensorImpl sensor;

    /**
     * Channels to broadcast to
     */
    private List<ObserverAsync> channels;

    /**
     * Number of channels to broadcast to
     */
    private int value, count = 0;

    /**
     * Configure the broadcast
     *
     * @param sensor   sensor to broadcast
     * @param channels channels to broadcast to
     */
    @Override
    public void configure(SensorImpl sensor, List<ObserverAsync> channels) {
        this.sensor = sensor;
        this.channels = channels;
    }

    /**
     * Broadcast the value to all channels
     */
    @Override
    public void execute() {
        if (count == 0) {
            value = sensor.getBaseValue();
            count = channels.size();
            sensor.update();
        }
    }

    /**
     * Get the value to broadcast
     *
     * @return value to broadcast
     */
    @Override
    public int valueRead() {
        count--;
        return value;
    }
}
