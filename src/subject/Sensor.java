package subject;


/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Sensor extends SensorAsync, Subject {
    int getValue();

    void tick();
}
