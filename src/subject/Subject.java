package subject;

import observer.Observer;

import java.util.ArrayList;

public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    void attach(Observer observer);

    void detach(Observer observer);
}
