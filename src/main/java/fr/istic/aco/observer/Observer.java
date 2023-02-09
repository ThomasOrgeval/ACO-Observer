package fr.istic.aco.observer;


/**
 * Observer interface
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Observer<T> {
    /**
     * Update the observer
     *
     * @param object object to update
     * @throws Exception
     */
    void update(T object) throws Exception;
}
