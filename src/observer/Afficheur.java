package observer;

import subject.Capteur;

public class Afficheur implements ObserverDeCapteur {

    @Override
    public Void update(Capteur capteur) {
        try {
            System.out.println("Afficheur: " + capteur.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
