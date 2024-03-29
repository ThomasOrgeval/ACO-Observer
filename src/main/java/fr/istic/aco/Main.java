package fr.istic.aco;

import fr.istic.aco.broadcast.AtomicBroadcast;
import fr.istic.aco.broadcast.Broadcast;
import fr.istic.aco.broadcast.EpochBroadcast;
import fr.istic.aco.broadcast.SequentialBroadcast;
import fr.istic.aco.observer.Display;
import fr.istic.aco.proxy.Channel;
import fr.istic.aco.subject.SensorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Main class
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class Main {
    /**
     * Number of observers to generate
     */
    private final static int NUMBER_OF_OBSERVERS = 3;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-- Atomic broadcast:");
        broadcast(new AtomicBroadcast());
        System.out.println("-- Sequential broadcast:");
        broadcast(new SequentialBroadcast());
        System.out.println("-- Epoch broadcast:");
        broadcast(new EpochBroadcast());
        System.out.println("-- Epoch broadcast 2:");
        broadcast(new EpochBroadcast());
        System.out.println("-- Epoch broadcast 3:");
        broadcast(new EpochBroadcast());
    }

    /**
     * Broadcast a sensor value to all observers
     *
     * @param broadcast broadcast to use (Atomic, Sequential or Epoch)
     * @throws InterruptedException
     */
    private static void broadcast(Broadcast broadcast) throws InterruptedException {
        SensorImpl sensor = new SensorImpl(broadcast);

        List<Display> displays = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_OBSERVERS; i++) {
            Display display = new Display(i);
            displays.add(display);
            Channel channel = new Channel(sensor, display);
            sensor.attach(channel);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(NUMBER_OF_OBSERVERS);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> new Thread(sensor::tick).start(), 1, 300, TimeUnit.MILLISECONDS);

        Thread.sleep(15000);
        future.cancel(false);
        Thread.sleep(500);

        displays.forEach(display -> System.out.println("Display " + display.getNumber() + ", values: " + display.getValues()));
    }
}
