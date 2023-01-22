package observer;

import subject.Capteur;

import java.util.concurrent.Future;

public interface ObserverDeCapteurAsync extends Observer<Capteur, Future<Void>> {
    Future<Void> update(Capteur capteur);
}
