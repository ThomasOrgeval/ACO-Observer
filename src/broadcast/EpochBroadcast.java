package broadcast;

import observer.ObserverAsync;
import subject.SensorImpl;

import java.util.List;

public class EpochBroadcast implements Broadcast {
    private SensorImpl sensor;
    private List<ObserverAsync> channels;

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
        channels.forEach(observer -> {
            try {
                observer.update(sensor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @return
     */
    @Override
    public Integer valueRead() {
        return sensor.getBaseValue();
    }
}
