import broadcast.AtomicBroadcast;
import broadcast.Broadcast;
import broadcast.EpochBroadcast;
import broadcast.SequentialBroadcast;
import observer.Display;
import proxy.Channel;
import subject.SensorImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-- Atomic broadcast:");
        broadcast(new AtomicBroadcast());
        System.out.println("-- Sequential broadcast:");
        broadcast(new SequentialBroadcast());
        System.out.println("-- Epoch broadcast:");
        broadcast(new EpochBroadcast());
    }

    private static void broadcast(Broadcast broadcast) throws InterruptedException {
        SensorImpl sensor = new SensorImpl(broadcast);

        Display display1 = new Display();
        Display display2 = new Display();
        Display display3 = new Display();

        Channel channel1 = new Channel(sensor, display1);
        Channel channel2 = new Channel(sensor, display2);
        Channel channel3 = new Channel(sensor, display3);

        sensor.attach(channel1);
        sensor.attach(channel2);
        sensor.attach(channel3);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> new Thread(sensor::tick), 1, 500, TimeUnit.MILLISECONDS);

        Thread.sleep(25000);
        future.cancel(false);
        Thread.sleep(2500);

        System.out.println("Display 1: " + display1.getValues());
        System.out.println("Display 2: " + display2.getValues());
        System.out.println("Display 3: " + display3.getValues());
    }
}
