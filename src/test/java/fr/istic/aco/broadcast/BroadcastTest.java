package fr.istic.aco.broadcast;

import fr.istic.aco.observer.Display;
import fr.istic.aco.proxy.Channel;
import fr.istic.aco.subject.SensorImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * AtomicBroadcastTest
 *
 * @author Orgeval Thomas & Bourgeois Bastien
 */
public class BroadcastTest {
    /**
     * Number of observers to generate
     */
    private final static int NUMBER_OF_OBSERVERS = 3;

    @Test
    public void atomicBroadcastTest() throws InterruptedException {
        List<Display> displays = getDisplays(new AtomicBroadcast());

        // Check all values are incremented by 1 and present in all displays
        boolean isWorking = displays.stream().allMatch(display -> {
            AtomicInteger count = new AtomicInteger(1);
            return display.getValues().stream().allMatch(i -> i == count.getAndIncrement());
        });

        assertTrue(isWorking);
    }

    @Test
    public void sequentialBroadcastTest() throws InterruptedException {
        List<Display> displays = getDisplays(new SequentialBroadcast());

        boolean isWorking = displays.stream().allMatch(display -> {
            AtomicInteger count = new AtomicInteger();
            return display.getValues().stream().allMatch(i -> {
                int i1 = count.addAndGet(i - count.get());
                return i1 == i;
            });
        });

        assertTrue(isWorking);
    }

    private List<Display> getDisplays(Broadcast broadcast) throws InterruptedException {
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
        return displays;
    }
}
