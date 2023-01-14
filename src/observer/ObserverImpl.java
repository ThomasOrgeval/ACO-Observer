package observer;

import subject.SubjectImpl;

public class ObserverImpl implements Observer {
    private final SubjectImpl subject;

    public ObserverImpl(SubjectImpl subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public void update() {
        int state = subject.getState();
        int epoch = subject.getCurrentEpoch();
        // Traitement de l'update
        System.out.println("Observer: Nouvel état: " + state + ", époque: " + epoch);
    }
}
