package observer;

import subject.SensorAsync;

import java.util.ArrayList;
import java.util.List;

public class Display implements ObserverAsync {
    private final List<Integer> values = new ArrayList<>();

    @Override
    public void update(SensorAsync sensorAsync) throws Exception {
        try {
            values.add(sensorAsync.getValue());
            System.out.println("Display: " + sensorAsync.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getValues() {
        return values;
    }
}
