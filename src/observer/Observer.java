package observer;


/**
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public interface Observer<T> {
    void update(T object) throws Exception;
}
