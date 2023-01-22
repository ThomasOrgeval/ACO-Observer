package observer;

import subject.Capteur;

public interface ObserverDeCapteur extends Observer<Capteur, Void> {
    Void update(Capteur capteur);
}
