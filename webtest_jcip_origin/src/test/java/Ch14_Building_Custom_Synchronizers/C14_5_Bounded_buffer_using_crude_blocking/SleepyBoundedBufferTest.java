package Ch14_Building_Custom_Synchronizers.C14_5_Bounded_buffer_using_crude_blocking;

import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Jin Lei Stormborn, the Unburnt, King of of Meereen, King of the
 * Andals and the Rhoynar and the First Men, Lord of the Seven Kingdoms,
 * Protector of the Realm, Caho of the Great Grass Sea, Breaker of
 * Shackles, Father of Dragons.
 */
public class SleepyBoundedBufferTest {

    private final static ExecutorService pool = Executors.newCachedThreadPool();
    private SleepyBoundedBuffer<String> buffer = new SleepyBoundedBuffer<String>();

    @Test
    public void test() throws InterruptedException, ExecutionException {
        Future<String> takeResult = pool.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return buffer.take();
            }

        });

        pool.execute(() -> {
            try {
                buffer.put("a msg");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        assertEquals("a msg", takeResult.get());
    }
}
