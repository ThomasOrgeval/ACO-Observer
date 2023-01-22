package proxy;

import subject.Capteur;

import java.util.concurrent.Callable;

public class GetValue implements Callable<Integer> {
    private final Capteur capteur;

    public GetValue(Capteur capteur) {
        this.capteur = capteur;
    }

    @Override
    public Integer call() {
        Integer integer = capteur.getValue();
        System.out.println("GetValue.call(): " + integer);
        return integer;
    }
}
