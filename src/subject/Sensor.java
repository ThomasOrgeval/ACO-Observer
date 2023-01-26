package subject;

public interface Sensor extends SensorAsync, Subject {
    Integer getValue();

    void tick();
}
