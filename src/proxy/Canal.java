package proxy;

import observer.Observer;
import observer.ObserverDeCapteur;
import observer.ObserverDeCapteurAsync;
import subject.Capteur;
import subject.CapteurAsync;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Canal implements ObserverDeCapteurAsync, CapteurAsync {
    private final ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    ObserverDeCapteur observerDeCapteur;
    Capteur capteur;

    public Canal(Capteur capteur) {
        this.capteur = capteur;
        capteur.attach(this);
    }

    @Override
    public Future<Void> update(Capteur capteur) {
        return scheduledThreadPoolExecutor.schedule(new Update(), new Random().nextInt(100, 500), TimeUnit.SECONDS);
    }

    @Override
    public void attach(Observer observer) {
        capteur.attach(observer);
    }

    @Override
    public void detach(Observer observer) {
        capteur.detach(observer);
    }

    @Override
    public Future<Integer> getValue() {
        return scheduledThreadPoolExecutor.schedule(new GetValue(capteur), new Random().nextInt(100, 500), TimeUnit.MILLISECONDS);
    }

    @Override
    public void tick() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
