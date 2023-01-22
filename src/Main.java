import observer.Afficheur;
import observer.ObserverDeCapteur;
import subject.Capteur;
import subject.CapteurImpl;
import proxy.Canal;

public class Main {
    public static void main(String[] args) {
        Capteur subject = new CapteurImpl();
        Canal canal = new Canal(subject);

        for (int i = 0; i < 3; i++) {
            ObserverDeCapteur capteur = new Afficheur();
            canal.attach(capteur);
        }

        for (int i = 0; i < 10; i++) {
            canal.getValue();
        }
    }
}
