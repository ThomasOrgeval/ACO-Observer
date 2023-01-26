package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;


/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class SequentialBroadcast implements Broadcast {
    private SensorImpl sensor;
    private List<ObserverAsync> channels;
    private int value, count = 0;

    /**
     * @param sensor
     * @param channels
     */
    @Override
    public void configure(SensorImpl sensor, List<ObserverAsync> channels) {
        this.sensor = sensor;
        this.channels = channels;
    }

    /**
     *
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
     * @return null
     */
    @Override
    public int valueRead() {
        count--;
        return value;
    }
}
