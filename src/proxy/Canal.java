package proxy;

import observer.Observer;
import observer.ObserverDeCapteurAsync;
import subject.Capteur;
import subject.CapteurAsync;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class Canal implements ObserverDeCapteurAsync, CapteurAsync, Subject {
    private final ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    List<Observer> observers = new ArrayList<>();
    Capteur capteur;
    private final AtomicInteger epoch = new AtomicInteger(0);

    public Canal(Capteur capteur) {
        this.capteur = capteur;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        capteur.detach(observer);
    }

    @Override
    public Future<Void> update(Capteur capteur) {
        return scheduledThreadPoolExecutor.submit(() -> {
            int currentEpoch = epoch.getAndIncrement();
            System.out.println("Epoch: " + currentEpoch);
            capteur.tick();
            return null;
        });
    }

    @Override
    public Future<Integer> getValue() {
        return scheduledThreadPoolExecutor.submit(() -> capteur.getValue());
    }
}
