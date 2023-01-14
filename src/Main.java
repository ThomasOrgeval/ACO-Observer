import observer.Observer;
import observer.ObserverImpl;
import subject.SubjectImpl;

public class Main {
    public static void main(String[] args) {
        SubjectImpl subject = new SubjectImpl();
        Observer observer = new ObserverImpl(subject);
        subject.runTask(() -> subject.setState(1));
        subject.runTask(() -> subject.setState(2));
        subject.runTask(() -> subject.setState(3));
    }
}
