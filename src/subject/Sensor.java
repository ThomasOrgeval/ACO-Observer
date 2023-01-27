package subject;


/**
 * Sensor interface
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Sensor extends SensorAsync, Subject {
    /**
     * Get the value from the sensor (synchronous)
     *
     * @return value from the sensor
     */
    int getValue();

    /**
     * Get the base value from the sensor (synchronous)
     *
     * @return base value from the sensor
     */
    void tick();
}
