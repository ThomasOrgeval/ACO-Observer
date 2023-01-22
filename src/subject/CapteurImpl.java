package subject;

import observer.Observer;

import java.util.concurrent.Future;

public class CapteurImpl implements Capteur {
    protected int value;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void tick() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
