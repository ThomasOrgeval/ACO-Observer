package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;

/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class EpochBroadcast implements Broadcast {
    private SensorImpl sensor;

    /**
     * @param sensor
     * @param channels
     */
    @Override
    public void configure(SensorImpl sensor, List<ObserverAsync> channels) {
        this.sensor = sensor;
    }

    /**
     *
     */
    @Override
    public void execute() {
        sensor.update();
    }

    /**
     * @return
     */
    @Override
    public int valueRead() {
        return sensor.getBaseValue();
    }
}
