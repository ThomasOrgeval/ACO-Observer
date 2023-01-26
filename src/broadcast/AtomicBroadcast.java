package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;

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

        channels.forEach(observer -> {
            try {
                observer.update(sensor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @return null
     */
    @Override
    public Integer valueRead() {
        sensor.setLock(--count != 0);
        return sensor.getBaseValue();
    }
}
