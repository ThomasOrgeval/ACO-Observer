package fr.istic.aco.broadcast;

import fr.istic.aco.observer.ObserverAsync;
import fr.istic.aco.subject.SensorImpl;

import java.util.List;

/**
 * Epoch broadcast
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class EpochBroadcast implements Broadcast {
    /**
     * Sensor to broadcast
     */
    private SensorImpl sensor;

    /**
     * Channels to broadcast to
     *
     * @param sensor   sensor to broadcast
     * @param channels channels to broadcast to
     */
    @Override
    public void configure(SensorImpl sensor, List<ObserverAsync> channels) {
        this.sensor = sensor;
    }

    /**
     * Broadcast the value to all channels
     */
    @Override
    public void execute() {
        sensor.update();
    }

    /**
     * Get the value to broadcast
     *
     * @return value to broadcast
     */
    @Override
    public int valueRead() {
        return sensor.getBaseValue();
    }
}
