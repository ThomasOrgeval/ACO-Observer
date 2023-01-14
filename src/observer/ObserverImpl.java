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
        StackTraceElement currentMethod = Thread.currentThread().getStackTrace()[1];
        // Traitement de l'update
        System.out.println("Observer: Nouvel état: " + state + ", appel de la méthode: " + currentMethod.getMethodName());
    }
}
