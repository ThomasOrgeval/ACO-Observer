package proxy;

import subject.SensorAsync;

import java.util.concurrent.Callable;


/**
 * GetValue active object
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class GetValue implements Callable<Integer> {
    /**
     * Sensor to get the value from
     */
    private final SensorAsync sensorAsync;

    /**
     * Constructor
     *
     * @param sensorAsync sensor to get the value from
     */
    public GetValue(SensorAsync sensorAsync) {
        this.sensorAsync = sensorAsync;
    }

    /**
     * Get the value from the sensor
     *
     * @return value from the sensor
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        return sensorAsync.getValue();
    }
}
