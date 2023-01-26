package proxy;

import subject.SensorAsync;

import java.util.concurrent.Callable;

public class GetValue implements Callable<Integer> {
    private final SensorAsync sensorAsync;

    public GetValue(SensorAsync sensorAsync) {
        this.sensorAsync = sensorAsync;
    }

    @Override
    public Integer call() throws Exception {
        return sensorAsync.getValue();
    }
}
