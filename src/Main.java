import observer.Observer;
import observer.ObserverImpl;
import subject.SubjectImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        SubjectImpl subject = new SubjectImpl(scheduler);
        Observer observer = new ObserverImpl(subject);
        subject.runTask(() -> subject.setState(1));
        subject.runTask(() -> subject.setState(2));
        subject.runTask(() -> subject.setState(3));
    }
}
