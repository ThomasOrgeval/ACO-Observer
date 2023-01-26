package observer;

import subject.SensorAsync;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class Display implements ObserverAsync {
    private final List<Integer> values = new ArrayList<>();
    private final int number;

    public Display(int number) {
        this.number = number;
    }

    @Override
    public void update(SensorAsync sensorAsync) throws Exception {
        try {
            values.add(sensorAsync.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getValues() {
        return values;
    }

    public int getNumber() {
        return number;
    }
}
