package subject;

import observer.Observer;

/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);
}
