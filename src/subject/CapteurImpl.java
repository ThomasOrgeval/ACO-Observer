package subject;

import observer.Observer;

import java.util.concurrent.Future;

public class CapteurImpl implements Capteur {
    protected Future<Integer> value;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public Future<Integer> getValue() {
        return value;
    }

    @Override
    public void tick() {
    }
}
