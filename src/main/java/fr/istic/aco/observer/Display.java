package fr.istic.aco.observer;

import fr.istic.aco.subject.SensorAsync;

import java.util.ArrayList;
import java.util.List;


/**
 * Display
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class Display implements ObserverAsync {
    /**
     * Values received by the display
     */
    private final List<Integer> values = new ArrayList<>();

    /**
     * Identification of the display
     */
    private final int number;

    /**
     * Constructor
     *
     * @param number identification of the display
     */
    public Display(int number) {
        this.number = number;
    }

    /**
     * Update the display
     *
     * @param sensorAsync sensor to update from
     * @throws Exception
     */
    @Override
    public void update(SensorAsync sensorAsync) throws Exception {
        try {
            values.add(sensorAsync.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the values received by the display
     *
     * @return values received by the display
     */
    public List<Integer> getValues() {
        return values;
    }

    /**
     * Get the identification of the display
     *
     * @return identification of the display
     */
    public int getNumber() {
        return number;
    }
}
