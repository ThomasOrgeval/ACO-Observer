package subject;

import observer.Observer;

/**
 * Subject interface
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Subject {
    /**
     * Attach an observer to the subject
     *
     * @param observer observer to attach
     */
    void attach(Observer observer);

    /**
     * Detach an observer from the subject
     *
     * @param observer observer to detach
     */
    void detach(Observer observer);
}
