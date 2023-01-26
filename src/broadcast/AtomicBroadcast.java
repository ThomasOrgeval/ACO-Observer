package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;

/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class AtomicBroadcast implements Broadcast {
    private SensorImpl sensor;
    private List<ObserverAsync> channels;
    private int count = 0;

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
        sensor.setLock(true);
        count = channels.size();
        sensor.update();
    }

    /**
     * @return null
     */
    @Override
    public int valueRead() {
        if (--count == 0) {
            sensor.setLock(false);
        }
        return sensor.getBaseValue();
    }
}
