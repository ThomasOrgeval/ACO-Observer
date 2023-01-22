package observer;

public interface Observer<T, U> {
    U update(T object);
}