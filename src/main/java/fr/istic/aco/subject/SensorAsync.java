package fr.istic.aco.subject;


/**
 * Sensor interface
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface SensorAsync {
    /**
     * Get the value from the sensor (asynchronous)
     *
     * @return value from the sensor
     * @throws Exception
     */
    int getValue() throws Exception;
}
